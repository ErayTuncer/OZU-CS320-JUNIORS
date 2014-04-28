//
//  OzuShuttleV2ViewController.h
//  OzuShuttleV2
//
//  Created by Can.Computer on 4/28/14.
//  Copyright (c) 2014 Can.Computer. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface OzuShuttleV2ViewController : UIViewController <UIPickerViewDataSource, UIPickerViewDelegate>
@property (weak, nonatomic) IBOutlet UIPickerView *pickerView;
- (IBAction)showButtonClicked;

@end
