#include <base-contact-sdk.h>
#include "rapidjson/document.h"
#include "rapidjson/writer.h"
#include "rapidjson/stringbuffer.h"
#include <iostream>
using namespace rapidjson;

extern "C" {
	vector<Contact> contactsData;
	std::string contacts_json = "[{\"first\" : \"Alexander\",\"last\" : \"Bell\",\"phone\" : \"+16170000001\"},"
                                                "{\"first\" : \"Thomas\",\"last\" : \"Watson\",\"phone\" : \"+16170000002\"},"
                                                "{\"first\" : \"Elisha\",\"last\" : \"Gray\",\"phone\" : \"+18476003599\"},"
                                                "{\"first\" : \"Antonio\",\"last\" : \"Meucci\",\"phone\" : \"+17188763245\"},"
                                                "{\"first\" : \"Guglielmo\",\"last\" : \"Marconi\",\"phone\" : \"+39051203222\"},"
                                                "{\"first\" : \"Samuel\",\"last\" : \"Morse\",\"phone\" : \"+16172419876\"},"
                                                "{\"first\" : \"Tim\",\"last\" : \"Berners-Lee\",\"phone\" : \"+44204549898\"},"
                                                "{\"first\" : \"John\",\"last\" : \"Baird\",\"phone\" : \"+4408458591006\"},"
                                                "{\"first\" : \"Thomas\",\"last\" : \"Edison\",\"phone\" : \"+19086575678\"}]";

	vector<Contact> parseContacts(){
		vector<Contact> contacts;  
		Document d;
    		d.Parse(contacts_json.c_str());

		for (Value::ConstValueIterator itr = d.Begin(); itr != d.End(); ++itr){
			Contact contact;
		 	if(itr->GetObject().HasMember("first")){
				contact.firstName = itr->GetObject().FindMember("first")->value.GetString();
			}
			

		 	if(itr->GetObject().HasMember("last")){
				contact.lastName = itr->GetObject().FindMember("last")->value.GetString();
			}
		

		 	if(itr->GetObject().HasMember("phone")){
				contact.phoneNumber = itr->GetObject().FindMember("phone")->value.GetString();
			}
	
			contacts.push_back(contact);
		}
		return contacts;
	}

	vector<Contact> BaseContactSdk::getContacts(){
		if(contactsData.size() == 0){
			contactsData = parseContacts();
		}

		return contactsData;
	}

	bool BaseContactSdk::updateContact(Contact oldContact, Contact newContact){
		Contact index;
		for(int i = 0; i < contactsData.size(); i++){
			index = contactsData.at(i);
			bool isContact = (oldContact.firstName == index.firstName) 
					&& (oldContact.lastName == index.lastName)
					&& (oldContact.phoneNumber == index.phoneNumber);
			if(isContact){
				contactsData.at(i) = newContact;
				return true;
			}
		}	
		return false;
	}
	bool BaseContactSdk::addContact(Contact contact){
		contactsData.push_back(contact);
		return true;
	}
}
