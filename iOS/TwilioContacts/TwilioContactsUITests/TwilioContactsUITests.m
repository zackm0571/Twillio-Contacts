//
//  TwilioContactsUITests.m
//  TwilioContactsUITests
//
//  Created by Zack Matthews on 11/28/17.
//  Copyright © 2017 zackmatthews. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "MainViewController.h"
#import "ContactSdk.h"
@interface TwilioContactsUITests : XCTestCase

@end

@implementation TwilioContactsUITests
XCUIApplication *app;
- (void)setUp {
    [super setUp];
    self.continueAfterFailure = NO;
    app = [[XCUIApplication alloc] init];
    [app launch];
}

-(void)testAddContact{
    sleep(3);
    NSUInteger count = [[ContactSdk getInstance] getContacts].count;
    TWContact *contact = [[TWContact alloc] init];
    contact.firstName = @"Robert";
    contact.lastName = @"Paulson";
    contact.phoneNumber = @"+18005555555";
    BOOL isSuccessful = [[ContactSdk getInstance] addContact:contact];
    
    XCTAssertTrue(isSuccessful);
    XCTAssertEqual([ContactSdk getInstance].getContacts.count, count+1);
    sleep(3);
}

-(void)testUpdateContact{
    sleep(3);
    TWContact *contact = [[[ContactSdk getInstance] getContacts] objectAtIndex:0];
    TWContact *newContact = [[TWContact alloc] init];
    newContact.firstName = @"AAAAA";
    newContact.lastName = @"Mike";
    newContact.phoneNumber = @"+17277777777";
    BOOL isSuccessful = [[ContactSdk getInstance] updateContact:contact :newContact];
    
    contact = [[[ContactSdk getInstance] getContacts] objectAtIndex:0];
    
    XCTAssertTrue(isSuccessful);
    XCTAssertEqualObjects(contact.firstName, newContact.firstName);
    XCTAssertEqualObjects(contact.lastName, newContact.lastName);
    XCTAssertEqualObjects(contact.phoneNumber, newContact.phoneNumber);
    sleep(3);
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}
@end
