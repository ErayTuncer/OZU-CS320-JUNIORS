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
@end
