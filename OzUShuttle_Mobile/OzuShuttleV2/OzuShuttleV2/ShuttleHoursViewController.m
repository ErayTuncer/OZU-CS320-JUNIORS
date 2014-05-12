//
//  ShuttleHoursViewController.m
//  OzuShuttleV2
//
//  Created by Can.Computer on 4/28/14.
//  Copyright (c) 2014 Can.Computer. All rights reserved.
//

#import "ShuttleHoursViewController.h"
#import "DummyInfo.h"
@interface ShuttleHoursViewController ()
@property (nonatomic,strong) NSArray *shuttleHours;
@property (nonatomic, strong) NSDictionary *shuttleInfo;
@end

@implementation ShuttleHoursViewController

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
    [self setTitle:[NSString stringWithFormat:@"%@ - %@",[self.source substringToIndex:3],[self.destination substringToIndex:3]]];
    [self loadShuttleInfos];

	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void) loadShuttleInfos{
    //self.shuttleHours = [DummyInfo getShuttleHoursForDeparture: self.source Destination: self.destination DayType: self.dayType];
    self.shuttleInfo = [DummyInfo initStaticJsonData];
}

#pragma mark - TableView

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    
    if([self.dayType isEqualToString:@"Weekdays"]){
        self.shuttleHours = [self.shuttleInfo objectForKey:@"weekdayHours"];
        return [self.shuttleHours count];
    }else if ([self.dayType isEqualToString:@"Weekends"]){
        self.shuttleHours = [self.shuttleInfo objectForKey:@"weekendHours"];
        return [self.shuttleHours count];
    }else if ([self.dayType isEqualToString:@"Holidays"]) {
        self.shuttleHours = [self.shuttleInfo objectForKey:@"holidayHours"];
        return [self.shuttleHours count];
    }else {
        return 5;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *CellIdentifier = @"tableCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    cell.textLabel.text = self.shuttleHours[indexPath.row];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSString *shuttleInfo = [NSString stringWithFormat:@"Departure Point is %@, arrival Point is %@, in the Day %@", self.source, self.destination, self.dayType ];
    UIAlertView *detailInfoDisplay = [[UIAlertView alloc] initWithTitle:@"Shuttle"
                                                      message:shuttleInfo
                                                     delegate:self
                                            cancelButtonTitle:@"OK"
                                            otherButtonTitles:nil];
    [detailInfoDisplay show];
}

@end
