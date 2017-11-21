#include <base-contact-sdk.h>
#include "rapidjson/document.h"
#include "rapidjson/writer.h"
#include "rapidjson/stringbuffer.h"
#include <iostream>
using namespace rapidjson;

extern "C" {
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
		//const Value& a = d.GetObject();

		for (Value::ConstValueIterator itr = d.Begin(); itr != d.End(); ++itr){

			Contact contact;
		 	if(itr->GetObject().HasMember("first")){
				contact.firstName = itr->GetObject().FindMember("first")->value.GetString();
			}
			
			contacts.push_back(contact);
		}


		return contacts;
	}

	vector<Contact> BaseContactSdk::getContacts(){
		return parseContacts();
	}

}
