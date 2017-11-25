//
//  ContactSdk.h
//  TwilioContacts
//
//  Created by Zack Matthews on 11/22/17.
//  Copyright © 2017 zackmatthews. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "base-contact-sdk.h"
@protocol ContactListener<NSObject>
-(void)onAddContact;
-(void)onContactUpdated:(Contact) oldContact :(Contact) newContact;
-(void)onContactsRefreshed;
@end

@interface ContactSdk : NSObject
@property id<ContactListener> listener;
-(void)addContact:(Contact)contact;
-(BOOL)updateContact:(Contact)oldContact : (Contact)newContact;
-(vector<Contact>)getContacts;
+(ContactSdk*)getInstance;
@end
