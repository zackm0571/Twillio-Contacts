package com.zackmatthews.twiliocontacts.manager;
public class ContactSdk{
	static {
		System.loadLibrary("contact-lib");
	}

	public class Contact{
		String firstName;
		String lastName;
		String phoneNumber; //E.164
	}

	interface ContactListener{
		void onContactAdded(Contact contact);
		void onContactUpdated(Contact newContact, Contact oldContact);
	}

	public native boolean addContact(Contact contact, ContactListener contactListener);
}
