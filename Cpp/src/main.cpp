#include <base-contact-sdk.h>
#include "rapidjson/document.h"
#include "rapidjson/writer.h"
#include "rapidjson/stringbuffer.h"
#include <iostream>
#include <set>
using namespace rapidjson;
using namespace std;
int main(){
    // 1. Parse a JSON string into DOM.
    const char* json = "{\"project\":\"rapidjson\",\"stars\":10}";
    Document d;
    d.Parse(json);

    // 2. Modify it by DOM.
    Value& s = d["stars"];
    s.SetInt(s.GetInt() + 1);

    // 3. Stringify the DOM
    StringBuffer buffer;
    Writer<StringBuffer> writer(buffer);
    d.Accept(writer);

    // Output {"project":"rapidjson","stars":11}
    std::cout << buffer.GetString() << std::endl;
	BaseContactSdk *sdk = new BaseContactSdk();
	set<Contact> contacts = sdk->getContacts();

	for (std::set<Contact>::iterator it=contacts.begin(); it!=contacts.end(); ++it){
	    	std::cout << it->firstName;
  		std::cout << '\n';

	}
  	std::cout << '\n';
	return 0;
}
