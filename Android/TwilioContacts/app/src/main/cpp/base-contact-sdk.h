#include <string>
#include <vector>
#include <set>
using namespace std;
extern "C"{
	class Contact{
		public:
		string firstName;
		string lastName;
		string phoneNumber; //E.164	
		bool operator< (const Contact &right) const;
	};
	class BaseContactSdk{
		public:
		set<Contact> getContacts();
		bool addContact(Contact contact);
		bool updateContact(Contact oldContact, Contact newContact);
	};
};

