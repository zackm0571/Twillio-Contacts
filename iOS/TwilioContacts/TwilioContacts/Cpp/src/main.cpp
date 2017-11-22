#include <base-contact-sdk.h>
#include "rapidjson/document.h"
#include "rapidjson/writer.h"
#include "rapidjson/stringbuffer.h"
#include <iostream>
#include <vector>
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
	vector<Contact> contacts = sdk->getContacts();

	for(int i = 0; i < contacts.size(); i++){
		std::cout << contacts.at(i).firstName << std::endl;
		std::cout << contacts.at(i).lastName << std::endl;
		std::cout << contacts.at(i).phoneNumber << std::endl;

		
	}
	return 0;
}
