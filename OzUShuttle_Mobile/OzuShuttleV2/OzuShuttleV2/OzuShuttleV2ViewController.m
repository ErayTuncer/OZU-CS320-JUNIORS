//
//  OzuShuttleV2ViewController.m
//  OzuShuttleV2
//
//  Created by Can.Computer on 4/28/14.
//  Copyright (c) 2014 Can.Computer. All rights reserved.
//

#import "OzuShuttleV2ViewController.h"
#import "ShuttleHoursViewController.h"
@interface OzuShuttleV2ViewController ()
@property(nonatomic) NSString *source;
@property(nonatomic) NSString *destination;

@end

@implementation OzuShuttleV2ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self.pickerView selectRow:1 inComponent:1 animated:YES];
    self.source = @"Altunizade";
    self.destination = @"Çekmeköy";

}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];

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
- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component
{
    if([pickerView selectedRowInComponent:(component+1)%2] == row){
        [pickerView selectRow:(row+1)%2 inComponent:(component+1)%2 animated:YES];
        [self updateDirectionsForRow:(row+1)%2  Component:(component+1)%2];

    }
    [self updateDirectionsForRow:row Component:component];
    
}
-(void) updateDirectionsForRow:(NSInteger)row Component:(NSInteger)component
{
    switch (component) {
        case 0:
            switch (row) {
                case 0:
                    self.source = @"Altunizade";
                    break;
                case 1:
                    self.source = @"Çekmeköy";
                    break;
                default:
                    break;
            }
            break;
        case 1:
            switch (row) {
                case 0:
                    self.destination = @"Altunizade";
                    break;
                case 1:
                    self.destination = @"Çekmeköy";
                    break;
                default:
                    break;
            }
            break;
        default:
            break;
    }

}

- (IBAction)showButtonClicked {
    
    
}
-(void) prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if([segue.identifier isEqualToString:@"show"]){
        ShuttleHoursViewController *vc = segue.destinationViewController;
        vc.source = self.source;
        vc.destination = self.destination;
    }
}
@end
