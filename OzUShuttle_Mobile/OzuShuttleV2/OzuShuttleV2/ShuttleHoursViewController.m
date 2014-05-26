//
//  ShuttleHoursViewController.m
//  OzuShuttleV2
//
//  Created by Can.Computer on 4/28/14.
//  Copyright (c) 2014 Can.Computer. All rights reserved.
//

#import "ShuttleHoursViewController.h"
#import "DummyInfo.h"
#import "HUDHelper.h"
#import "CKRefreshControl.h"
@interface ShuttleHoursViewController ()
@property (strong, nonatomic) NSArray *shuttleHours;
@property (strong, nonatomic) NSDictionary *shuttleInfo;
@property (strong,nonatomic) CKRefreshControl *refreshControl;
@property (strong, nonatomic) NSString *lastUpdate;
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
    //[[NSUserDefaults standardUserDefaults] removePersistentDomainForName:[[NSBundle mainBundle] bundleIdentifier]];
    [self.refreshControl setTintColor:[UIColor orangeColor]];
  //  [self setTitle:[NSString stringWithFormat:@"%@ - %@",[self.departure substringToIndex:3],[self.destination substringToIndex:3]]];
  //  [self loadShuttleInfos];
    //[self loadShuttleInfo];
    [self createRefreshControl];
    [self refresh];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


-(void)createRefreshControl
{
    self.refreshControl=[[CKRefreshControl alloc] init];
    [self.refreshControl setTintColor:[UIColor orangeColor]];
    [self.refreshControl addTarget:self action:@selector(refresh) forControlEvents:UIControlEventValueChanged];
    [self.tableView addSubview:self.refreshControl];
}

-(NSString *) converDayOption: (NSString *) dayOption{
    
    if ([dayOption isEqualToString:@"weekdayHours"]) {
        return @"Weekdays";
    }
    if ([dayOption isEqualToString:@"weekendHours"]) {
        return @"Weekends";
    }
    if ([dayOption isEqualToString:@"holidayHours"]) {
        return @"Holidays";
    }
    return nil;
}

-(void) loadShuttleInfos{
    //self.shuttleHours = [DummyInfo getShuttleHoursForDeparture: self.source Destination: self.destination DayType: self.dayType];
    //self.shuttleInfo = [DummyInfo initStaticJsonData];
}

- (void) refresh
{
    [HUDHelper showHUD];
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0), ^{
        [self loadShuttleInfo];
        dispatch_async(dispatch_get_main_queue(), ^{
            [self.tableView reloadData];
            [self.refreshControl endRefreshing];
            [HUDHelper hideHUD];
        });
    });
}

-(void) loadShuttleInfo{
    NSString *urlString = [NSString stringWithFormat:@"http://1-dot-superb-metric-577.appspot.com/%@-%@",[self.departure lowercaseString], [self.destination lowercaseString]];
    NSURL *url = [[NSURL alloc] initWithString:urlString];
    NSError *error;
    NSData *data = [NSData dataWithContentsOfURL:url];
    NSDictionary *dict;
    if (data) {
        dict = [NSJSONSerialization JSONObjectWithData:data options:kNilOptions error:&error];
        NSDate *currDate = [NSDate date];
        NSDateFormatter *dateFormatter = [[NSDateFormatter alloc]init];
        [dateFormatter setDateFormat:@"dd.MM.YY HH:mm:ss"];
        self.lastUpdate = [dateFormatter stringFromDate:currDate];
        [self saveData:dict lastUpdateTime: self.lastUpdate];
        
    }else{
        dict = [self getSavedData];
        self.lastUpdate = [self getSavedLastUpdateTime];
    }
    
    
    self.shuttleHours = [self filterShuttleHoursFromDict: dict];
    
    //self.shuttleInfo = dict;
    //NSLog(@"Error: %@",error);
    //NSLog(@"Dict: %@", dict);
}

-(void)saveData:(NSDictionary *)dict lastUpdateTime:(NSString *)updateTime{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    [userDefaults setObject:dict forKey:[NSString stringWithFormat:@"%@-%@",self.departure,self.destination]];
    [userDefaults setObject:updateTime forKey:[NSString stringWithFormat:@"updateTime-%@-%@",self.departure,self.destination]];
    [userDefaults synchronize];
}

-(NSDictionary *)getSavedData{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSDictionary *dict =[userDefaults objectForKey:[NSString stringWithFormat:@"%@-%@",self.departure,self.destination]];
    return dict;
}

-(NSString *) getSavedLastUpdateTime{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSString *updateTime = [userDefaults objectForKey:[NSString stringWithFormat:@"updateTime-%@-%@",self.departure,self.destination]];
    return updateTime;
}

-(NSArray *) filterShuttleHoursFromDict:(NSDictionary *)dict {
    NSArray *tempArray = [dict objectForKey:self.dayType];
    NSMutableArray *tempArray2 = [[NSMutableArray alloc] init];
    for (NSString *string in tempArray) {
        if (![string isEqualToString:@""]) {
            [tempArray2 addObject:string];
        }
    }
    return tempArray2;
}
#pragma mark - TableView

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
   // NSLog([NSString stringWithFormat:@"%d",self.shuttleHours.count+1]);
    return [self.shuttleHours count]+1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    if(indexPath.row == 0){
        static NSString *CellIdentifier = @"updateCell";
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
        if(!self.lastUpdate){
        }else{
            cell.textLabel.text = [NSString stringWithFormat:@"Last update %@",self.lastUpdate];
        }
        return cell;
    }else {
        static NSString *CellIdentifier = @"tableCell";
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
        [cell.textLabel setTextColor:[UIColor whiteColor]];
        [cell.detailTextLabel setTextColor:[UIColor greenColor]];
        cell.textLabel.text = self.shuttleHours[indexPath.row-1];
        cell.detailTextLabel.text = [NSString stringWithFormat:@"%@ - %@ (%@)",self.departure,self.destination,[self converDayOption:self.dayType]];
        return cell;
    }
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSString *shuttleInfo = [NSString stringWithFormat:@"Departure Point is %@, arrival Point is %@, in the Day %@", self.departure, self.destination, self.dayType ];
    UIAlertView *detailInfoDisplay = [[UIAlertView alloc] initWithTitle:@"Shuttle"
                                                      message:shuttleInfo
                                                     delegate:self
                                            cancelButtonTitle:@"OK"
                                            otherButtonTitles:nil];
    [detailInfoDisplay show];
}

@end
