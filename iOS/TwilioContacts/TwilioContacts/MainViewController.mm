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
    
    TWContact *contact =  [[[ContactSdk getInstance] getContacts] objectAtIndex:indexPath.row];
    [cell.firstNameLabel setText:contact.firstName];
    [cell.lastNameLabel setText:contact.lastName];
    [cell.phoneNumberLabel setText:contact.phoneNumber];
    return cell;
}

- (NSInteger)tableView:(nonnull UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [[[ContactSdk getInstance] getContacts] count];
}
-(void)onContactUpdated:(TWContact*)oldContact :(TWContact*)newContact{
    [self.tableView reloadData];
}
-(void)onAddContact:(TWContact*)contact{
    [self.tableView reloadData];
}
@end
