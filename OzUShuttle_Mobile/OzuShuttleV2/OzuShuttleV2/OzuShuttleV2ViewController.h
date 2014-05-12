//
//  OzuShuttleV2ViewController.h
//  OzuShuttleV2
//
//  Created by Can.Computer on 4/28/14.
//  Copyright (c) 2014 Can.Computer. All rights reserved.
//
#import "MultiDialViewController.h"
#import <UIKit/UIKit.h>

@interface OzuShuttleV2ViewController : UIViewController <MultiDialViewControllerDelegate, UIScrollViewDelegate>
@property (weak, nonatomic) IBOutlet UIPickerView *pickerView;
@property (strong, nonatomic) IBOutlet UIScrollView *scrollView;
@property (strong, nonatomic) MultiDialViewController *multiDialController;
@property (weak, nonatomic) IBOutlet UIView *spinnerView;
@property (strong, nonatomic) IBOutlet UILabel *dayLabel;

@property (weak, nonatomic) IBOutlet UIImageView *rightScrollArrow;
@property (weak, nonatomic) IBOutlet UIImageView *leftScrollArrow;
- (IBAction)showButtonClicked;

@end
