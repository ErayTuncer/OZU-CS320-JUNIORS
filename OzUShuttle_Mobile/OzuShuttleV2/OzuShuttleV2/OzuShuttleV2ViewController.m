//
//  OzuShuttleV2ViewController.m
//  OzuShuttleV2
//
//  Created by Can.Computer on 4/28/14.
//  Copyright (c) 2014 Can.Computer. All rights reserved.
//

#import "OzuShuttleV2ViewController.h"
#import "ShuttleHoursViewController.h"
#import "MultiDialViewController.h"

@interface OzuShuttleV2ViewController ()

@property(nonatomic) NSMutableArray *shutleDays;
@property(nonatomic) NSString *source;
@property(nonatomic) NSString *destination;
@property(nonatomic) NSString *dayType;
@property (strong, nonatomic) IBOutlet UILabel *resultLabel;

@end

@implementation OzuShuttleV2ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self initializeDayOptions];
    [self.pickerView selectRow:1 inComponent:1 animated:YES];
    self.source = @"Altunizade";
    self.destination = @"Çekmeköy";
    self.dayType = @"Weekdays";
    [self initMapImageScrollView];
    
    [self addCustomSpinner];
    

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
- (void) addCustomSpinner{
    self.multiDialController = [[MultiDialViewController alloc] init];
    self.multiDialController.delegate = self;
    self.multiDialController.view.frame = CGRectOffset(self.multiDialController.view.frame, 0.0, 0);
    [self.spinnerView addSubview:self.multiDialController.view];
    [self.spinnerView sizeToFit];
}
#pragma mark MultiDialViewControllerDelegate methods

- (void)multiDialViewController:(MultiDialViewController *)controller didSelectString:(NSString *)string {
   
}


-(void) initializeDayOptions{
    self.shutleDays = [[ NSMutableArray alloc] init];
    [self.shutleDays addObject:@"Weekdays"];
    [self.shutleDays addObject:@"Weekends"];
    [self.shutleDays addObject:@"Holidays"];
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

- (void) initMapImageScrollView
{
    for (UIView* view in self.scrollView.subviews) {
        [view removeFromSuperview];
    }
    
    int startX = 0;
    for (NSString *option in self.shutleDays){
        UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(startX,0,self.scrollView.bounds.size.width,self.scrollView.bounds.size.height)];
        label.numberOfLines = 0;
        [label setTextColor:[UIColor whiteColor]];
        label.text = option;
        CGRect labelFrame = label.frame;
        CGSize size = [label.text sizeWithFont:label.font];
        labelFrame.origin.x += self.scrollView.bounds.size.width/2-size.width/2;
        label.frame = labelFrame;
        startX += label.bounds.size.width;
        label.backgroundColor = [UIColor clearColor];
        [self.scrollView addSubview:label];
    }
    self.scrollView.contentSize = CGSizeMake(startX, self.scrollView.bounds.size.height);
}

- (IBAction)showButtonClicked {

}

-(void) prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    int scrollViewIndex = self.scrollView.contentOffset.x / self.scrollView.frame.size.width;
    self.dayType = [self.shutleDays objectAtIndex:scrollViewIndex];
    if([segue.identifier isEqualToString:@"show"]){
        ShuttleHoursViewController *vc = segue.destinationViewController;
        vc.source = self.source;
        vc.destination = self.destination;
        vc.dayType = self.dayType;
    }
}

@end
