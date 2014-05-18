//
//  HUDHelper.m
//  SosyoTV
//
//  Created by Gun Makinabakan on 10/4/12.
//  Copyright (c) 2012 iMobileCode. All rights reserved.
//

#import "HUDHelper.h"
#import "OzuShuttleV2AppDelegate.h"


@implementation HUDHelper

+(HUDHelper*)sharedInstance
{
    static dispatch_once_t pred;
    static HUDHelper *sharedInstance = nil;
    dispatch_once(&pred, ^{
        sharedInstance = [[[self class] alloc] init];
    });
    return sharedInstance;
}

+ (void) showHUD
{
    if (![HUDHelper sharedInstance].isHUDOnScreen) {
        [self showHUDWithLabel:@"Loading ..."];
        [HUDHelper sharedInstance].isHUDOnScreen = YES;
    }
}

+(void) showHUDWithLabel:(NSString*) hudLabel
{
  //  hudLabel = @"";
    OzuShuttleV2AppDelegate* appDelegate = (OzuShuttleV2AppDelegate*) [[UIApplication sharedApplication] delegate];
 //   [[AppDelegate_iPhone getDelegate].loadingView startAnimation];
    
    MBProgressHUD* HUD = [HUDHelper sharedInstance].sharedHUD;
    HUD = [[MBProgressHUD alloc] initWithView:appDelegate.window];
    
   // [AppDelegate_iPhone getDelegate].loadingView.frame = CGRectMake(0, 0, 72, 72);
  //  HUD.customView = [AppDelegate_iPhone getDelegate].loadingView;
   
    HUD.mode = MBProgressHUDModeIndeterminate;
    
	[appDelegate.window addSubview:HUD];
	
	HUD.dimBackground = YES;
    HUD.labelText = hudLabel;
	// Regiser for HUD callbacks so we can remove it from the window at the right time
	//HUD.delegate = self;
	
	// Show the HUD while the provided method executes in a new thread
	[HUD show:YES];
    
}

+ (void) hideHUD
{
    OzuShuttleV2AppDelegate* appDelegate = (OzuShuttleV2AppDelegate*) [[UIApplication sharedApplication] delegate];
    
    [MBProgressHUD hideHUDForView:appDelegate.window animated:YES];
    //[[Fashion_FailAppDelegate getDelegate].loadingView stopAnimation];
    [HUDHelper sharedInstance].isHUDOnScreen = NO;
}

+ (MBProgressHUD*) activeHUD
{
    OzuShuttleV2AppDelegate* appDelegate = (OzuShuttleV2AppDelegate*) [[UIApplication sharedApplication] delegate];
    
    return [MBProgressHUD HUDForView:appDelegate.window];
}





@end
