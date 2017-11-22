package com.zackmatthews.twiliocontacts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.zackmatthews.twiliocontacts.adapters.ContactListAdapater;
import com.zackmatthews.twiliocontacts.manager.ContactSdk;
import com.zackmatthews.twiliocontacts.models.Contact;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView listView;
    private ContactListAdapater listViewAdapter;
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.contactListView);
        listViewAdapter = new ContactListAdapater(MainActivity.this);
        listView.setAdapter(listViewAdapter);

        ContactSdk sdk = new ContactSdk();
        ArrayList<Contact> contacts = sdk.getContacts();
        for(Contact contact : contacts){
            System.out.println(contact.toString());
        }
    }
}
