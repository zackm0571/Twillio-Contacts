//
//  ViewController.h
//  TwilioContacts
//
//  Created by Zack Matthews on 11/22/17.
//  Copyright © 2017 zackmatthews. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MainViewController : UIViewController<UITableViewDelegate>
@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end

