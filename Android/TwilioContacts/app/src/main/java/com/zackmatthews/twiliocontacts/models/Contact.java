package com.zackmatthews.twiliocontacts.models;

/**
 * Created by zackmatthews on 11/21/17.
 */

public class Contact {
    public String firstName;
    public String lastName;
    public String phoneNumber; //E.164

    public Contact(){

    }
    public Contact(String firstName, String lastName, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
