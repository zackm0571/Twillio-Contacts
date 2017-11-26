package com.zackmatthews.twiliocontacts;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.zackmatthews.twiliocontacts.adapters.ContactListAdapater;
import com.zackmatthews.twiliocontacts.manager.ContactSdk;
import com.zackmatthews.twiliocontacts.models.Contact;
/* Sample app, executes tests by default *
 * set ENABLE_TESTS to false to disable tests */
public class MainActivity extends Activity {
    private static final boolean ENABLE_TESTS = true;
    private ListView listView;
    private Handler handler = new Handler();
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactListAdapater listViewAdapter = new ContactListAdapater(MainActivity.this);
        listView = findViewById(R.id.contactListView);
        listView.setAdapter(listViewAdapter);
        if(ENABLE_TESTS) {
            addContactTest();
            updateContactTest();
        }
    }

    /*** TESTS ***/
    private void addContactTest(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Contact contact = new Contact();
                contact.firstName = "Ferris";
                contact.lastName= "Bueller";
                contact.phoneNumber = "+13774146999";
                ContactSdk.getInstance().addContact(contact, (ContactListAdapater)listView.getAdapter());
                Toast.makeText(MainActivity.this, "Contact 'Ferris Bueller' added", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    private void updateContactTest(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Contact contact = ContactSdk.getInstance().getContacts().get(0);
                Contact newContact = new Contact();
                newContact.firstName = "Mad";
                newContact.lastName= "Max";
                newContact.phoneNumber = "+13441888999";
                ContactSdk.getInstance().updateContact(contact, newContact, (ContactListAdapater)listView.getAdapter());
                Toast.makeText(MainActivity.this, "Contact at index 0 updated to 'Mad max'", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }
}
