//
//  HUDHelper.h
//  SosyoTV
//
//  Created by Gun Makinabakan on 10/4/12.
//  Copyright (c) 2012 iMobileCode. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "MBProgressHUD.h"

@interface HUDHelper : NSObject

@property (nonatomic, strong) MBProgressHUD* sharedHUD;
@property (nonatomic) BOOL isHUDOnScreen;

+ (void) showHUD;
+ (void) showHUDWithLabel:(NSString*) hudLabel;
+ (void) hideHUD;

+ (HUDHelper*)sharedInstance;
+ (MBProgressHUD*) activeHUD;
@end
