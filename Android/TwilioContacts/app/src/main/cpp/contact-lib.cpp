#include <jni.h>
#include "base-contact-sdk.h"
#include <string>

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_zackmatthews_twiliocontacts_manager_ContactSdk_addContact(JNIEnv *env, jobject instance,
                                                                   jobject contact,
                                                                   jobject contactListener) {

    Contact nativeContact;

    jclass cls = (env)->FindClass("com.zackmatthews.twiliocontacts.manager/Contact");
    jfieldID firstname = (env)->GetFieldID(cls, "firstName", "Ljava/lang/String;");
    jfieldID lastname = (env)->GetFieldID(cls, "lastName", "Ljava/lang/String;");
    jfieldID phoneNumber = (env)->GetFieldID(cls, "phoneNumber", "Ljava/lang/String;");


    jstring jstr = (jstring)(env)->GetObjectField(contact, firstname);
    const jsize len = env->GetStringUTFLength();
    const char* strChars = env->GetStringUTFChars(str, (jboolean *)0);

    std::string Result(strChars, len);

    env->ReleaseStringUTFChars(str, strChars);
    // TODO
    return true;
}
