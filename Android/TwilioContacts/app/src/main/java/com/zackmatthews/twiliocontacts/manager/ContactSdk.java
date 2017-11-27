package com.zackmatthews.twiliocontacts.manager;

import com.zackmatthews.twiliocontacts.models.Contact;
import java.util.ArrayList;

public class ContactSdk{
	//Load contact lib
	static {
		System.loadLibrary("contact-lib");
	}
	//Singleton
	public static ContactSdk getInstance() {
		if(instance == null){
			instance = new ContactSdk();
		}
		return instance;
	}
	//Callback getter / setter
	public ContactListener getListener() {
		return listener;
	}

	public void setListener(ContactListener listener) {
		if(listener == null) return;
		this.listener = listener;
	}
	//Callback interface
	public interface ContactListener{
		void onContactAdded(Contact contact);
		void onContactUpdated(Contact oldContact, Contact newContact);
	}
	//Properties
	private static ContactSdk instance;
	private ContactListener listener;
	//NDK wrapper

	/**
	 * @param contact Contact to add
	 * @param contactListener Listener to receive callbacks
	 * @return boolean whether operation was successful
	 */
	public native boolean addContact(Contact contact, ContactListener contactListener);

	/**
	 * @param oldContact Contact to update
	 * @param newContact Updated contact
	 * @param contactListener Listener to receive callbacks
	 * @return boolean whether operation was successful
	 */
	public native boolean updateContact(Contact oldContact, Contact newContact, ContactListener contactListener);

	/**
	 * @return List of native contacts
	 */
	public native ArrayList<Contact> getContacts();
}
