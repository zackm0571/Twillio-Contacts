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
//    UINib *nib = [UINib nibWithNibName:@"UIContactCellTableViewCell" bundle:nil];
//    [[self tableView] registerNib:nib forCellReuseIdentifier:@"contact_cell"];
    [self.tableView setDelegate:self];
    [self.tableView setDataSource:self];
    // Do any additional setup after loading the view, typically from a nib.
}

-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    [[ContactSdk getInstance] setListener:self];
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
