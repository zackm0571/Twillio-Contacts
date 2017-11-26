public class Contact{
        String firstName;
        String lastName;
        String phoneNumber; //E.164
}

public class ContactSdk{
	ContactListener{
		onContactAdded(Contact contact);
		onContactUpdated(Contact newContact, Contact oldContact);
	}

	public void addContact(Contact contact, ContactListener contactListener);
}
