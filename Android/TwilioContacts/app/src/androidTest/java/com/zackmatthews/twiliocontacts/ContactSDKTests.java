package com.zackmatthews.twiliocontacts;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Toast;

import com.zackmatthews.twiliocontacts.adapters.ContactListAdapater;
import com.zackmatthews.twiliocontacts.manager.ContactSdk;
import com.zackmatthews.twiliocontacts.models.Contact;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

/**
 * Created by zackmatthews on 11/28/17.
 */

@RunWith(AndroidJUnit4.class)
public class ContactSDKTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void addContact(){
        Contact contact = new Contact("Magic", "Mike", "+17278889898");
        boolean isSuccessful = ContactSdk.getInstance().addContact(contact, (ContactListAdapater)mActivityRule.getActivity().getListView().getAdapter());


        final String outputText = (isSuccessful) ?
                String.format(Locale.getDefault(),"Contact '%s %s' added",
                        contact.firstName, contact.lastName)
                : "Cannot add contact, contact already exists";
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mActivityRule.getActivity(), outputText, Toast.LENGTH_SHORT).show();
            }
        });


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
