package com.zackmatthews.twiliocontacts.manager;

import com.zackmatthews.twiliocontacts.models.Contact;

import java.util.ArrayList;

public class ContactSdk{
	static {
		System.loadLibrary("contact-lib");
	}
	interface ContactListener{
		void onContactAdded(Contact contact);
		void onContactUpdated(Contact newContact, Contact oldContact);
	}

	public native boolean addContact(Contact contact, ContactListener contactListener);
	public native ArrayList<Contact> getContacts();
}
