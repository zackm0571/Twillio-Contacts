package com.zackmatthews.twiliocontacts;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.zackmatthews.twiliocontacts.adapters.ContactListAdapater;
import com.zackmatthews.twiliocontacts.manager.ContactSdk;
import com.zackmatthews.twiliocontacts.models.Contact;

public class MainActivity extends Activity {
    private ListView listView;
    private ContactListAdapater listViewAdapter;
    private Handler handler = new Handler();
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
        addContactTest();
    }


    private void addContactTest(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Contact contact = new Contact();
                contact.firstName = "Ferris";
                contact.lastName= "Bueller";
                contact.phoneNumber = "+13774146999";
                ContactSdk.getInstance().addContact(contact, (ContactListAdapater)listView.getAdapter());
            }
        }, 3000);

    }
}
