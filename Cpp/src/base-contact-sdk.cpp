#include <base-contact-sdk.h>
#include "rapidjson/document.h"
#include "rapidjson/writer.h"
#include "rapidjson/stringbuffer.h"
#include <iostream>
using namespace rapidjson;

extern "C" {
	set<Contact> contactsData;
	std::string contacts_json = "[{\"first\" : \"Alexander\",\"last\" : \"Bell\",\"phone\" : \"+16170000001\"},"
                                                "{\"first\" : \"Thomas\",\"last\" : \"Watson\",\"phone\" : \"+16170000002\"},"
                                                "{\"first\" : \"Elisha\",\"last\" : \"Gray\",\"phone\" : \"+18476003599\"},"
                                                "{\"first\" : \"Antonio\",\"last\" : \"Meucci\",\"phone\" : \"+17188763245\"},"
                                                "{\"first\" : \"Guglielmo\",\"last\" : \"Marconi\",\"phone\" : \"+39051203222\"},"
                                                "{\"first\" : \"Samuel\",\"last\" : \"Morse\",\"phone\" : \"+16172419876\"},"
                                                "{\"first\" : \"Tim\",\"last\" : \"Berners-Lee\",\"phone\" : \"+44204549898\"},"
                                                "{\"first\" : \"John\",\"last\" : \"Baird\",\"phone\" : \"+4408458591006\"},"
                                                "{\"first\" : \"Thomas\",\"last\" : \"Edison\",\"phone\" : \"+19086575678\"}]";
	

	bool Contact::operator< (const Contact &right) const
	{
		std::string comp = right.firstName + right.lastName + right.phoneNumber;
		std::string selfComp = this->firstName + this->lastName + this->phoneNumber;
		int val = selfComp.compare(comp);
    		return (val < 0) ? true : false;
	}
	set<Contact> parseContacts(){
		set<Contact> contacts;  
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
	
			contacts.insert(contact);
		}
		return contacts;
	}

	set<Contact> BaseContactSdk::getContacts(){
		if(contactsData.size() == 0){
			contactsData = parseContacts();
		}

		return contactsData;
	}

	 bool containsContact(Contact contact){
		if(contactsData.find(contact) != contactsData.end()){
			return true;
		}
                return false;
        }

	bool BaseContactSdk::updateContact(Contact oldContact, Contact newContact){
		if(containsContact(oldContact) && !containsContact(newContact)){
			std::set<Contact>::iterator itr = contactsData.find(oldContact);
			contactsData.erase(itr);
			contactsData.insert(newContact);
			return true;
		}
		return false;
	}
	bool BaseContactSdk::addContact(Contact contact){
		if(!containsContact(contact)){
			contactsData.insert(contact);
			return true;
		}
		return false;
	}
}
