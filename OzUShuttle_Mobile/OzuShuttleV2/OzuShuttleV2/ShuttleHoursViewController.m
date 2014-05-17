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
@property (nonatomic) NSArray *shuttleHours;
@property (nonatomic) NSDictionary *shuttleInfo;
@property (nonatomic) UIRefreshControl *refreshControl;
@property (strong, nonatomic) IBOutlet UITableView *tableView;
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
    self.refreshControl = [[UIRefreshControl alloc] init];
    [self setTitle:[NSString stringWithFormat:@"%@ - %@",[self.source substringToIndex:3],[self.destination substringToIndex:3]]];
  //  [self loadShuttleInfos];
    //[self loadShuttleInfo];
    [self.refreshControl addTarget:self action:@selector(refresh) forControlEvents:UIControlEventValueChanged];
    [self.tableView addSubview:self.refreshControl];
    [self refresh];
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void) loadShuttleInfos{
    //self.shuttleHours = [DummyInfo getShuttleHoursForDeparture: self.source Destination: self.destination DayType: self.dayType];
    //self.shuttleInfo = [DummyInfo initStaticJsonData];
}

- (void) refresh
{
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0), ^{
        [self loadShuttleInfo];
        dispatch_async(dispatch_get_main_queue(), ^{
            [self.tableView reloadData];
            [self.refreshControl endRefreshing];
        });
    });
}

-(void) loadShuttleInfo{
    NSString *urlString = [NSString stringWithFormat:@"http://1-dot-superb-metric-577.appspot.com/%@-%@",[self.source lowercaseString], [self.destination lowercaseString]];
    NSURL *url = [[NSURL alloc] initWithString:urlString];
    NSError *error;
    NSData *data = [NSData dataWithContentsOfURL:url];
    NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:data options:kNilOptions error:&error];
    self.shuttleInfo = dict;
    NSLog(@"Error: %@",error);
    //NSLog(@"Dict: %@", dict);
}

#pragma mark - TableView

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // +1 for the initial info
    if([self.dayType isEqualToString:@"Weekdays"]){
        self.shuttleHours = [self.shuttleInfo objectForKey:@"weekdayHours"];
        return [self.shuttleHours count] +1;
    }else if ([self.dayType isEqualToString:@"Weekends"]){
        self.shuttleHours = [self.shuttleInfo objectForKey:@"weekendHours"];
        return [self.shuttleHours count] +1;
    }else if ([self.dayType isEqualToString:@"Holidays"]) {
        self.shuttleHours = [self.shuttleInfo objectForKey:@"holidayHours"];
        return [self.shuttleHours count] +1;
    }else {
        return 5;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    if(indexPath.row == 0){
        NSDate *currDate = [NSDate date];
        NSDateFormatter *dateFormatter = [[NSDateFormatter alloc]init];
        [dateFormatter setDateFormat:@"dd.MM.YY HH:mm:ss"];
        NSString *dateString = [dateFormatter stringFromDate:currDate];
        static NSString *CellIdentifier = @"updateCell";
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
        cell.textLabel.text = [NSString stringWithFormat:@"Last update %@",dateString];
        return cell;
    }else {
        static NSString *CellIdentifier = @"tableCell";
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
        [cell.textLabel setTextColor:[UIColor whiteColor]];
        [cell.detailTextLabel setTextColor:[UIColor greenColor]];
        cell.textLabel.text = self.shuttleHours[indexPath.row-1];
        cell.detailTextLabel.text = [NSString stringWithFormat:@"%@ - %@",self.source,self.destination];
        return cell;
    }
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
