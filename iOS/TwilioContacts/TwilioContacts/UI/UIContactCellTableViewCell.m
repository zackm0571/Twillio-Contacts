//
//  UIContactCellTableViewCell.m
//  TwilioContacts
//
//  Created by Zack Matthews on 11/22/17.
//  Copyright © 2017 zackmatthews. All rights reserved.
//

#import "UIContactCellTableViewCell.h"

@implementation UIContactCellTableViewCell
@synthesize firstNameLabel;
@synthesize lastNameLabel;
@synthesize phoneNumberLabel;

- (id)initWithIdentifier:(NSString *)identifier {
    if ((self = [super initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier]) != nil) {
        NSArray *arrayOfViews = [[NSBundle mainBundle] loadNibNamed:@"UIContactCellTableViewCell" owner:self options:nil];
        
        if ([arrayOfViews count] >= 1) {
            [self.contentView addSubview:[arrayOfViews objectAtIndex:0]];
        }
    }
    
    return self;
}
- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

-(NSString *)reuseIdentifier{
    return @"contact_cell";
}
- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
