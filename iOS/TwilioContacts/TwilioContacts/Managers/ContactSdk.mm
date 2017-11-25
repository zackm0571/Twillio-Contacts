//
//  ContactSdk.m
//  TwilioContacts
//
//  Created by Zack Matthews on 11/22/17.
//  Copyright © 2017 zackmatthews. All rights reserved.
//

#import "ContactSdk.h"
@interface ContactSdk()
@property vector<Contact> contacts;
@end
@implementation ContactSdk
static BaseContactSdk sdk;
static ContactSdk *instance;
-(void)addContact:(Contact)contact{
    sdk.addContact(contact);
    if(self.listener){
        [self.listener onAddContact];
    }
}

-(BOOL)updateContact:(Contact)oldContact :(Contact)newContact{
    sdk.updateContact(oldContact, newContact);
    [self.listener onContactUpdated:oldContact :newContact ];
    return FALSE;
}
-(vector<Contact>)getContacts{
    return sdk.getContacts();
}
+(ContactSdk*)getInstance{
    if(!instance){
        instance = [[ContactSdk alloc] init];
    }
    return instance;
}
@end
