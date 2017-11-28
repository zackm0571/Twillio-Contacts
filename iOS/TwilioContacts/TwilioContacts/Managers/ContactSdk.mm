//
//  ContactSdk.m
//  TwilioContacts
//
//  Created by Zack Matthews on 11/22/17.
//  Copyright © 2017 zackmatthews. All rights reserved.
//

#import "ContactSdk.h"
@interface ContactSdk()
@property NSArray *contacts;
@end
@implementation ContactSdk
static BaseContactSdk sdk;
static ContactSdk *instance;

-(Contact)getNativeContact:(TWContact*)contact{
    Contact nativeContact;
    nativeContact.firstName = contact.firstName.UTF8String;
    nativeContact.lastName = contact.lastName.UTF8String;
    nativeContact.phoneNumber = contact.phoneNumber.UTF8String;
    return nativeContact;
}
-(TWContact*)getCocoaContact:(Contact) contact{
    TWContact *cocoaContact = [[TWContact alloc] init];
    [cocoaContact setFirstName:[NSString stringWithUTF8String:contact.firstName.c_str()]];
    [cocoaContact setLastName:[NSString stringWithUTF8String:contact.lastName.c_str()]];
    [cocoaContact setPhoneNumber:[NSString stringWithUTF8String:contact.phoneNumber.c_str()]];
    return cocoaContact;
}
-(void)addContact:(TWContact*)contact{
    sdk.addContact([self getNativeContact:contact]);
    if(self.listener){
        [self.listener onAddContact];
    }
}

-(BOOL)updateContact:(TWContact*)oldContact :(TWContact*)newContact{
    
    sdk.updateContact([self getNativeContact:oldContact], [self getNativeContact:newContact]);
    [self.listener onContactUpdated:oldContact :newContact ];
    return FALSE;
}
-(NSArray*)getContacts{
    NSMutableArray *_contacts = [[NSMutableArray alloc] init];
    set<Contact> contacts = sdk.getContacts();
    for (std::set<Contact>::iterator itr=contacts.begin(); itr!=contacts.end(); ++itr){
        [_contacts addObject:[self getCocoaContact:(*itr)]];
    }
   return [_contacts copy];
}
+(ContactSdk*)getInstance{
    if(!instance){
        instance = [[ContactSdk alloc] init];
    }
    return instance;
}
@end
