#include <string>
#include <vector>
using namespace std;
extern "C"{
	class Contact{
		public:
		string firstName;
		string lastName;
		string phoneNumber; //E.164	
	};
	class BaseContactSdk{
		public:
		vector<Contact> getContacts();
		bool addContact(Contact contact);
		bool updateContact(Contact oldContact, Contact newContact);
	};
};

