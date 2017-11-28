package com.zackmatthews.twiliocontacts;

import android.os.Build;
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
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

    @Test
    public void updateContact(){
        int index = 0;
        Contact contact = ContactSdk.getInstance().getContacts().get(index);
        Contact newContact = new Contact("Ferris", "Bueller", "+17047766551");
        boolean isSuccessful = ContactSdk.getInstance().updateContact(contact, newContact, (ContactListAdapater)mActivityRule.getActivity().getListView().getAdapter());


        final String outputText = (isSuccessful) ?
                String.format(Locale.getDefault(),"Contact '%s %s' updated",
                        newContact.firstName, newContact.lastName)
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


@Test
    public void randomIntervalUpdates(){
        final long delayMills = 3500;

        for(int i = 0; i < ContactSdk.getInstance().getContacts().size(); i++){
            long sleepTime;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                sleepTime = ThreadLocalRandom.current().nextLong(1000, 6000);
            } else {
                sleepTime = Math.max(new Random(System.currentTimeMillis()).nextLong(), 5000) + 1000;
            }

            Contact contact = ContactSdk.getInstance().getContacts().get(i);
            Contact newContact = new Contact(contact.firstName, contact.lastName + "-von-winger-flabber", contact.phoneNumber);
            boolean isSuccessful = ContactSdk.getInstance().updateContact(contact, newContact, (ContactListAdapater)mActivityRule.getActivity().getListView().getAdapter());

            final String outputText = (isSuccessful) ?
                    String.format(Locale.getDefault(),"Contact '%s %s' updated",
                            newContact.firstName, newContact.lastName)
                    : "Cannot add contact, contact already exists";
            mActivityRule.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mActivityRule.getActivity(), outputText, Toast.LENGTH_SHORT).show();
                }
            });

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
