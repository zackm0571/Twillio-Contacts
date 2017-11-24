//
//  ViewController.m
//  TwilioContacts
//
//  Created by Zack Matthews on 11/22/17.
//  Copyright © 2017 zackmatthews. All rights reserved.
//

#import "MainViewController.h"
#import "ContactSdk.h"
#import "UIContactCellTableViewCell.h"
@interface MainViewController ()
@end

@implementation MainViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self.tableView setDelegate:self];
    [self.tableView setDataSource:self];
}

-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    [[ContactSdk getInstance] setListener:self];
    [self testAddContact];
}

-(void)testAddContact{
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0), ^{
        sleep(3);
        Contact contact;
        contact.firstName = "Robert";
        contact.lastName = "Paulson";
        contact.phoneNumber = "+18005555555";
        [[ContactSdk getInstance] addContact:contact];
    });
}

-(void)testUpdateContact{
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0), ^{
        sleep(3);
        Contact contact = [[ContactSdk getInstance] getContacts].at(0);
        Contact newContact = contact;
        newContact.firstName = "Magic";
        newContact.lastName = "Mike";
        newContact.phoneNumber = "+17277777777";
        [[ContactSdk getInstance] updateContact:contact :newContact];
    });
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (nonnull UITableViewCell *)tableView:(nonnull UITableView *)tableView cellForRowAtIndexPath:(nonnull NSIndexPath *)indexPath {
    NSString *editableCellIdentifier = @"contact_cell";
    UIContactCellTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:editableCellIdentifier];
    if(!cell){
        cell = [[UIContactCellTableViewCell alloc] initWithIdentifier:editableCellIdentifier];
    }
    
    int row = indexPath.row;
    Contact contact =  [[ContactSdk getInstance] getContacts].at(row);
    NSString *firstName = [[NSString alloc] initWithUTF8String:contact.firstName.c_str()];
    NSString *lastName = [[NSString alloc] initWithUTF8String:contact.lastName.c_str()];
    NSString *phoneNumber = [[NSString alloc] initWithUTF8String:contact.phoneNumber.c_str()];
    [cell.firstNameLabel setText:firstName];
    [cell.lastNameLabel setText:lastName];
    [cell.phoneNumberLabel setText:phoneNumber];
    return cell;
}

- (NSInteger)tableView:(nonnull UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    
    return [[ContactSdk getInstance] getContacts].size();
}
-(void)onContactUpdated:(Contact)oldContact :(Contact)newContact{
    
}
-(void)onContactsRefreshed{
    
}
-(void)onAddContact{
    dispatch_sync(dispatch_get_main_queue(), ^{
            [self.tableView reloadData];
    });
    
}
@end
