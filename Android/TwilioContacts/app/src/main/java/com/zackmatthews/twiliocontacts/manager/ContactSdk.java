package com.zackmatthews.twiliocontacts.manager;

import com.zackmatthews.twiliocontacts.models.Contact;
import java.util.ArrayList;

public class ContactSdk{

	static {
		System.loadLibrary("contact-lib");
	}

	public static ContactSdk getInstance() {
		if(instance == null){
			instance = new ContactSdk();
		}
		return instance;
	}

	public ContactListener getListener() {
		return listener;
	}

	public void setListener(ContactListener listener) {
		if(listener == null) return;
		this.listener = listener;
	}

	public interface ContactListener{
		void onContactAdded(Contact contact);
		void onContactUpdated(Contact oldContact, Contact newContact);
		void onContactListRefreshed();
	}

	private static ContactSdk instance;
	private ContactListener listener;
	public native boolean addContact(Contact contact, ContactListener contactListener);
	public native ArrayList<Contact> getContacts();
}
