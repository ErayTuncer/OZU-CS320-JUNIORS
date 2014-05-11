//
//  DummyInfo.m
//  OzuShuttleV2
//
//  Created by Can.Computer on 5/5/14.
//  Copyright (c) 2014 Can.Computer. All rights reserved.
//

#import "DummyInfo.h"

@implementation DummyInfo

+(NSArray *) getShuttleHoursForDeparture:(NSString *)source  Destination:(NSString *)destination DayType:(NSString *)dayType{
    NSMutableArray *array = [[NSMutableArray alloc] initWithObjects:@"7:00",@"8:00",@"9:00",@"10:00",@"11:00",@"12:00",@"13:00",@"14:00",@"15:00",@"16:00",@"17:00",@"18:00",@"20:00", @"22:00", nil];
    return array;
}

+(NSDictionary *) initStaticJsonData {
    NSMutableArray *weekDays = [[NSMutableArray alloc] initWithObjects:@"7:45",@"8:30",@"9:00",@"10:0",@"11:0",@"12:0",@"13:0",@"14:0",@"15:0",@"16:0",@"17:0",@"18:0",@"19:0",@"20:0",@"21:0",@"22:0",@"23:0",@"0:15", nil];
    NSMutableArray *weekEnds = [[NSMutableArray alloc] initWithObjects:@"7:30",@"8:30",@"10:0",@"12:0",@"14:0",@"16:0",@"17:0",@"19:0",@"21:0",@"23:0",@"0:30", nil];
     NSMutableArray *holidays = [[NSMutableArray alloc] initWithObjects:@"7:30",@"8:30",@"10:0",@"17:3",@"19:0",@"22:0", nil];
    NSDictionary *dict = [[NSDictionary alloc] initWithObjectsAndKeys:
                           weekDays, @"weekdayHours",
                           weekEnds, @"weekendHours",
                           holidays, @"holidayHours",
                           nil];
    return dict;
}

@end