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
@property(nonatomic) NSString *currentDay;

@end

@implementation OzuShuttleV2ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self initializeDayOptions];
    self.source = @"Altunizade";
    self.destination = @"Çekmeköy";
    self.dayType = @"weekdayHours";
    [self initMapImageScrollView];
    self.leftScrollArrow.hidden = YES;
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
    if (self.currentDay ==nil) {
        [self.multiDialController.dial1 spinToString:@"Altunizade"];
        [self.multiDialController.dial2 spinToString:@"Cekmekoy"];
    }
    
    [self learnCurrentDay];

    

}

- (void) addCustomSpinner{
    self.multiDialController = [[MultiDialViewController alloc] init];
    self.multiDialController.delegate = self;
    self.multiDialController.view.frame = CGRectOffset(self.multiDialController.view.frame, 0.0, 0);
    [self.spinnerView addSubview:self.multiDialController.view];
    [self.spinnerView sizeToFit];
    
}

#pragma mark MultiDialViewControllerDelegate methods

- (void)multiDialViewController:(MultiDialViewController *)controller didSelectString:(NSArray *)strings withDial:(DialController *)dial{
    NSString *departure = strings[0];
    NSString *destination = strings[1];
    if ([departure isEqualToString:destination]) {
        if (dial == controller.dial1) {
            [controller.dial2 spintoNextString];

        }
        if (dial == controller.dial2) {
            [controller.dial1 spintoNextString];
            
        }
    }
    else{
        self.source = strings[0];
        self.destination = strings[1];
    }
}


-(void) initializeDayOptions{
    self.shutleDays = [[ NSMutableArray alloc] init];
    [self.shutleDays addObject:@"Weekdays"];
    [self.shutleDays addObject:@"Weekends"];
    [self.shutleDays addObject:@"Holidays"];
}

-(void) learnCurrentDay{
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"EEEE"];
    self.currentDay = [dateFormatter stringFromDate:[NSDate date]];
    NSDate *currentDate = [NSDate date];
    NSCalendar* calendar = [NSCalendar currentCalendar];
    NSDateComponents* components = [calendar components:NSYearCalendarUnit|NSMonthCalendarUnit|NSDayCalendarUnit fromDate:currentDate]; // Get necessary date components
    self.dayLabel.text = [NSString stringWithFormat:@"Today: %ld.%ld.%ld - %@", (long)[components day], (long)[components month], (long)[components year], self.currentDay];
    [self updateScrollView];
    
}

-(void) updateScrollView{
    if ([self.currentDay isEqualToString:@"Sunday"] || [self.currentDay isEqualToString:@"Saturday"]) {
        CGPoint weekendIndex = CGPointMake((self.scrollView.contentSize.width - self.scrollView.bounds.size.width*2), 0);
        [self.scrollView setContentOffset: weekendIndex animated:YES];
        self.dayType = @"weekendHours";

    }
    else{
        
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
    if (!self.multiDialController.dial1.isSpinning && !self.multiDialController.dial2.isSpinning) {
        [self performSegueWithIdentifier:@"show" sender:self];
    }
}

-(void) prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if([segue.identifier isEqualToString:@"show"]){
        ShuttleHoursViewController *vc = segue.destinationViewController;
        vc.departure = self.source;
        vc.destination = self.destination;
        vc.dayType = self.dayType;
    }
}

-(void) scrollViewDidScroll:(UIScrollView *)scrollView{
    int scrollViewIndex = self.scrollView.contentOffset.x / self.scrollView.frame.size.width;
    self.leftScrollArrow.hidden=NO;
    self.rightScrollArrow.hidden=NO;
    
    if (scrollViewIndex == 0) {
        self.leftScrollArrow.hidden = YES;
        self.dayType = @"weekdayHours";
    }
    if (scrollViewIndex == 1) {
        self.dayType = @"weekendHours";
    }
    if (scrollViewIndex == 2) {
        self.rightScrollArrow.hidden = YES;
        self.dayType = @"holidayHours";
    }
}


@end
