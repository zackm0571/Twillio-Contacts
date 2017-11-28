package com.zackmatthews.twiliocontacts.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zackmatthews.twiliocontacts.R;
import com.zackmatthews.twiliocontacts.manager.ContactSdk;
import com.zackmatthews.twiliocontacts.models.Contact;

/**
 * Created by zackmatthews on 11/22/17.
 */

public class ContactListAdapater extends BaseAdapter implements ContactSdk.ContactListener{
    private Context ctx;
    public ContactListAdapater(Context context){
        this.ctx = context;
        ContactSdk.getInstance().setListener(ContactListAdapater.this);
    }
    @Override
    public int getCount() {
        return ContactSdk.getInstance().getContacts().size();
    }

    @Override
    public Object getItem(int i) {
        if(i >= ContactSdk.getInstance().getContacts().size()) return null;
        return ContactSdk.getInstance().getContacts().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public void onContactAdded(Contact contact) {
        ((Activity)ctx).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onContactUpdated(Contact oldContact, Contact newContact) {
        ((Activity)ctx).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
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

        Contact contact = ContactSdk.getInstance().getContacts().get(i);
        holder.firstName.setText(contact.firstName);
        holder.lastName.setText(contact.lastName);
        holder.phoneNumber.setText(contact.phoneNumber);
        return view;
    }
}
