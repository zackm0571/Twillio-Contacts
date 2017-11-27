package com.zackmatthews.twiliocontacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.zackmatthews.twiliocontacts.adapters.ContactListAdapater;
import com.zackmatthews.twiliocontacts.manager.ContactSdk;
import com.zackmatthews.twiliocontacts.models.Contact;
/* Sample app, executes tests by default *
 * set ENABLE_TESTS to false to disable tests */
public class MainActivity extends Activity{
    /** Constants **/
    private static final boolean ENABLE_TESTS = true;

    /** UI **/
    private ListView listView;
    private FloatingActionButton fab;
    private AlertDialog addContactDialog;
    private View addContactView;

    /** Misc **/
    private Handler handler = new Handler();
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        if(ENABLE_TESTS) {
            addContactTest();
            updateContactTest();
        }
    }

    private void initUI(){
        ContactListAdapater listViewAdapter = new ContactListAdapater(MainActivity.this);
        listView = findViewById(R.id.contactListView);
        listView.setAdapter(listViewAdapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddContactMenu();
            }
        });
    }
    private void showAddContactMenu(){
        if(addContactDialog != null){
            addContactDialog.dismiss();
        }
        if(addContactView == null){
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            if(inflater != null) {
                addContactView = inflater.inflate(R.layout.add_contact_menu, (ViewGroup)findViewById(R.id.mainViewGroup), false);
            }
        }
        addContactDialog = new AlertDialog.Builder(MainActivity.this).setMessage("Add contact")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(addContactView == null) return;
                        EditText et_firstName = addContactView.findViewById(R.id.add_Contact_tv_firstName);
                        EditText et_lastName = addContactView.findViewById(R.id.add_Contact_tv_lastName);
                        EditText et_phoneNumber = addContactView.findViewById(R.id.add_Contact_tv_phoneNumber);

                        String firstName = et_firstName.getText().toString();
                        String lastName = et_lastName.getText().toString();
                        String phoneNumber = et_phoneNumber.getText().toString();

                        boolean isValid =
                                (firstName.replace(" ", "").length() > 0 )
                                        && (lastName.replace(" ", "").length() > 0 )
                                        && (phoneNumber.replace(" ", "").length() > 0 );

                        if(isValid) {
                            Contact contact = new Contact(firstName, lastName, phoneNumber);
                            ContactSdk.getInstance().addContact(contact, (ContactListAdapater)listView.getAdapter());
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        EditText et_firstName = addContactView.findViewById(R.id.add_Contact_tv_firstName);
                        EditText et_lastName = addContactView.findViewById(R.id.add_Contact_tv_lastName);
                        EditText et_phoneNumber = addContactView.findViewById(R.id.add_Contact_tv_phoneNumber);

                        et_firstName.setText("");
                        et_lastName.setText("");
                        et_phoneNumber.setText("");
                    }
                })
                .setView(addContactView).create();

        if(addContactView.getParent()!=null)
            ((ViewGroup)addContactView.getParent()).removeView(addContactView);

        addContactDialog.show();
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
                boolean isSuccessful = ContactSdk.getInstance().addContact(contact, (ContactListAdapater)listView.getAdapter());
                String outputText = (isSuccessful) ? "Contact 'Ferris Bueller' added" : "Cannot add contact, contact already exists";
                Toast.makeText(MainActivity.this, outputText, Toast.LENGTH_SHORT).show();
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
