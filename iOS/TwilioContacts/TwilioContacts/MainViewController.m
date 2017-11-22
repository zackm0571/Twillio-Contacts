//
//  ViewController.m
//  TwilioContacts
//
//  Created by Zack Matthews on 11/22/17.
//  Copyright © 2017 zackmatthews. All rights reserved.
//

#import "MainViewController.h"
#import "UIContactCellTableViewCell.h"
@interface MainViewController ()
@end

@implementation MainViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    UINib *nib = [UINib nibWithNibName:@"UIContactCellTableViewCell" bundle:nil];
    [[self tableView] registerNib:nib forCellReuseIdentifier:@"UIContactCellTableViewCell"];
    // Do any additional setup after loading the view, typically from a nib.
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *editableCellIdentifier = @"UIContactCellTableViewCell";
    UIContactCellTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:editableCellIdentifier];
    if(cell == nil){
        cell = [[UIContactCellTableViewCell alloc] init];
    }
    
    //cell.firstNameLabel setText:[ContactSd]
    return cell;
}
@end
