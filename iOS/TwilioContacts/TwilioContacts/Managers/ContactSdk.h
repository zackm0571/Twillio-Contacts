//
//  ContactSdk.h
//  TwilioContacts
//
//  Created by Zack Matthews on 11/22/17.
//  Copyright © 2017 zackmatthews. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "base-contact-sdk.h"
#import "TWContact.h"
@protocol ContactListener<NSObject>
-(void)onAddContact;
-(void)onContactUpdated:(TWContact*) oldContact :(TWContact*) newContact;
@end

@interface ContactSdk : NSObject
@property id<ContactListener> listener;
-(void)addContact:(TWContact*)contact;
-(BOOL)updateContact:(TWContact*)oldContact : (TWContact*)newContact;
-(NSArray*)getContacts;
+(ContactSdk*)getInstance;
@end
