#include <jni.h>
#include "base-contact-sdk.h"
#include <string>


static BaseContactSdk sdk;
extern "C"
std::string jstring2string(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char *)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

extern "C"
jobject nativeContact2JavaContact(JNIEnv *env, Contact nativeContact){
    jobject javaContact;

    jclass cls = (env)->FindClass("com/zackmatthews/twiliocontacts/models/Contact");
    jmethodID constructor = (env)->GetMethodID(cls, "<init>", "()V");
    javaContact = (env)->NewObject(cls, constructor);

    jfieldID firstname = (env)->GetFieldID(cls, "firstName", "Ljava/lang/String;");
    jfieldID lastname = (env)->GetFieldID(cls, "lastName", "Ljava/lang/String;");
    jfieldID phoneNumber = (env)->GetFieldID(cls, "phoneNumber", "Ljava/lang/String;");

    (env)->SetObjectField(javaContact, firstname, env->NewStringUTF(nativeContact.firstName.c_str()));
    (env)->SetObjectField(javaContact, lastname, env->NewStringUTF(nativeContact.lastName.c_str()));
    (env)->SetObjectField(javaContact, phoneNumber, env->NewStringUTF(nativeContact.phoneNumber.c_str()));

    return javaContact;
}
extern "C"
Contact javaContact2NativeContact(JNIEnv *env, jobject javaContact){
    Contact nativeContact;

    jclass contactClass = (env)->GetObjectClass(javaContact);

    jfieldID firstname = (env)->GetFieldID(contactClass, "firstName", "Ljava/lang/String;");
    jfieldID lastname = (env)->GetFieldID(contactClass, "lastName", "Ljava/lang/String;");
    jfieldID phoneNumber = (env)->GetFieldID(contactClass, "phoneNumber", "Ljava/lang/String;");

    jstring firstString= (jstring)(env)->GetObjectField(javaContact, firstname);
    jstring lastString= (jstring)(env)->GetObjectField(javaContact, lastname);
    jstring phoneString= (jstring)(env)->GetObjectField(javaContact, phoneNumber);

    nativeContact.firstName = std::string(jstring2string(env, firstString));
    nativeContact.lastName = std::string(jstring2string(env, lastString));
    nativeContact.phoneNumber = std::string(jstring2string(env, phoneString));

    return nativeContact;
}
extern "C"
jobject vector2list(JNIEnv *env, vector<Contact> contacts) {
    jclass java_util_ArrayList = static_cast<jclass>(env->NewGlobalRef(env->FindClass("java/util/ArrayList")));
    jmethodID java_util_ArrayList_= env->GetMethodID(java_util_ArrayList, "<init>", "(I)V");
    jmethodID java_util_ArrayList_add  = env->GetMethodID(java_util_ArrayList, "add", "(Ljava/lang/Object;)Z");

    jobject obj = env->NewObject(java_util_ArrayList, java_util_ArrayList_, contacts.size());

    for (int n = 0; n < contacts.size(); n++) {
        Contact contact = static_cast<Contact>(contacts[n]);
        jobject javaContact = nativeContact2JavaContact(env, contact);
        env->CallBooleanMethod(obj, java_util_ArrayList_add, javaContact);
    }

    return obj;
}
extern "C"
JNIEXPORT jboolean JNICALL
Java_com_zackmatthews_twiliocontacts_manager_ContactSdk_addContact(JNIEnv *env, jobject instance,
                                                                   jobject contact,
                                                                   jobject contactListener) {
    Contact nativeContact = javaContact2NativeContact(env, contact);
    bool isSuccessful = sdk.addContact(nativeContact);

    jclass listenerClass = env->GetObjectClass(contactListener);
    jmethodID onAddedMethodId = env->GetMethodID(listenerClass, "onContactAdded", "(Lcom/zackmatthews/twiliocontacts/models/Contact;)V");
    env->CallVoidMethod(contactListener, onAddedMethodId, contact);

    return (jboolean)isSuccessful;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_zackmatthews_twiliocontacts_manager_ContactSdk_getContacts(JNIEnv *env, jobject instance) {
    return vector2list(env, sdk.getContacts());
}extern "C"
JNIEXPORT jboolean JNICALL
Java_com_zackmatthews_twiliocontacts_manager_ContactSdk_updateContact(JNIEnv *env, jobject instance,
                                                                      jobject oldContact,
                                                                      jobject newContact,
                                                                      jobject contactListener) {

    Contact nOldContact = javaContact2NativeContact(env, oldContact);
    Contact nNewContact = javaContact2NativeContact(env, newContact);
    bool isSuccessful = sdk.updateContact(nOldContact, nNewContact);

    jclass listenerClass = env->GetObjectClass(contactListener);
    jmethodID onUpdatedMethodId = env->GetMethodID(listenerClass, "onContactUpdated", "(Lcom/zackmatthews/twiliocontacts/models/Contact;Lcom/zackmatthews/twiliocontacts/models/Contact;)V");
    env->CallVoidMethod(contactListener, onUpdatedMethodId, oldContact, newContact, contactListener);

    return (jboolean)isSuccessful;
}