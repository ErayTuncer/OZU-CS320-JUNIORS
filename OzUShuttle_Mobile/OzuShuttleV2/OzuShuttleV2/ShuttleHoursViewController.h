//
//  ShuttleHoursViewController.h
//  OzuShuttleV2
//
//  Created by Can.Computer on 4/28/14.
//  Copyright (c) 2014 Can.Computer. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ShuttleHoursViewController : UIViewController <UITableViewDataSource, UITableViewDelegate>
@property(nonatomic) NSString *source;
@property(nonatomic) NSString *destination;
@property(nonatomic) NSString *dayType;

@end
