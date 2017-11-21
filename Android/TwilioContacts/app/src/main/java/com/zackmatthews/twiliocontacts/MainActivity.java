package com.zackmatthews.twiliocontacts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.zackmatthews.twiliocontacts.manager.ContactSdk;
import com.zackmatthews.twiliocontacts.models.Contact;

import java.util.ArrayList;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        ContactSdk sdk = new ContactSdk();
        ArrayList<Contact> contacts = sdk.getContacts();
        for(Contact contact : contacts){
            System.out.println(contact.toString());
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
