//
//  ContactSdk.h
//  TwilioContacts
//
//  Created by Zack Matthews on 11/22/17.
//  Copyright © 2017 zackmatthews. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Contact.h"
@interface ContactSdk : NSObject
@property NSMutableArray *contacts;
-(void)addContact:(Contact*)contact;
@end
