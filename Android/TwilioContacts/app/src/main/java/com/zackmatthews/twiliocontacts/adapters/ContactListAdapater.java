package com.zackmatthews.twiliocontacts.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zackmatthews.twiliocontacts.R;
import com.zackmatthews.twiliocontacts.manager.ContactSdk;
import com.zackmatthews.twiliocontacts.models.Contact;

import java.util.List;

/**
 * Created by zackmatthews on 11/22/17.
 */

public class ContactListAdapater extends BaseAdapter implements ContactSdk.ContactListener{
    private List<Contact> contacts;
    private Context ctx;
    public ContactListAdapater(Context context){
        this.ctx = context;
        contacts = ContactSdk.getInstance().getContacts();
        ContactSdk.getInstance().setListener(ContactListAdapater.this);
    }
    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        if(i >= contacts.size()) return null;
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public void onContactAdded(Contact contact) {

    }

    @Override
    public void onContactUpdated(Contact newContact, Contact oldContact) {

    }

    @Override
    public void onContactListRefreshed() {

    }

    static class ViewHolder{
        TextView lastName, firstName, phoneNumber;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null && ctx != null){
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(inflater != null) {
                view = inflater.inflate(R.layout.contact_row, null);
                holder = new ViewHolder();
                holder.firstName = view.findViewById(R.id.tv_firstName);
                holder.lastName = view.findViewById(R.id.tv_lastName);
                holder.phoneNumber = view.findViewById(R.id.tv_phoneNumber);
                view.setTag(holder);
            }

        }
        else{
            if(view != null && view.getTag() != null) {
                holder = (ViewHolder) view.getTag();
            }
        }
        if(holder == null) return view;

        Contact contact = contacts.get(i);
        holder.firstName.setText(contact.firstName);
        holder.lastName.setText(contact.lastName);
        holder.phoneNumber.setText(contact.phoneNumber);
        return view;
    }
}
