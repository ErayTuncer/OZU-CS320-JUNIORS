//
//  shuttleSelectorViewController.m
//  OzUShuttle
//
//  Created by Alp Kaan Erer on 28/04/14.
//  Copyright (c) 2014 Alp Kaan Erer - CS 320 Group. All rights reserved.
//

#import "shuttleSelectorViewController.h"

@interface shuttleSelectorViewController ()

@end

@implementation shuttleSelectorViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Picker View

// returns the number of 'columns' to display.
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView
{
    return 2; // kalkis - gidis
}

// returns the # of rows in each component..
- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component
{
    return 2;
}

- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component
{
    
    NSMutableArray *shutleArray = [[ NSMutableArray alloc] init];
    [shutleArray addObject:@"Altunizade"];
    [shutleArray addObject:@"Cekmekoy"];
    if (component == 0) {
        return [shutleArray objectAtIndex:row];
    } else {
        return [shutleArray objectAtIndex:row];
    }
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
