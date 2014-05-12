//
//  DummyInfo.h
//  OzuShuttleV2
//
//  Created by Can.Computer on 5/5/14.
//  Copyright (c) 2014 Can.Computer. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface DummyInfo : NSObject

+(NSArray *) getShuttleHoursForDeparture:(NSString *)source  Destination:(NSString *)destination DayType:(NSString *)dayType;

+(NSDictionary *) initStaticJsonData;

@end
