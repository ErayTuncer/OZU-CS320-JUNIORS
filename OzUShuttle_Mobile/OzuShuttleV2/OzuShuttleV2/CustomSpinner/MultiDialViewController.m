//
//  Created by Dimitris Doukas on 25/03/2011.
//  Copyright 2011 doukasd.com. All rights reserved.
//

#import "MultiDialViewController.h"
#import "GlowLabel.h"
#import "DialController.h"

#define DIAL_OFFSET_X               30
#define DIAL_OFFSET_Y               0
#define DIAL_WIDTH                  120
#define DIAL_HEIGHT                 220


@implementation MultiDialViewController

@synthesize presetStrings, delegate;
@synthesize dial1, dial2;


- (void)viewDidLoad {
    [super viewDidLoad];
    
    //subscribe to accelerometer calls
    [[UIAccelerometer sharedAccelerometer] setDelegate:self];
    [[UIAccelerometer sharedAccelerometer] setUpdateInterval:1.0/60.0];
    
    //add dials and populate with these values...
    NSArray *numbers = [NSArray arrayWithObjects:@"Altunizade", @"Cekmekoy", @"Kadikoy", @"Bostanci", @"Umraniye", @"Taksim", nil];
    
    self.dial1 = [[[DialController alloc] initWithDialFrame:CGRectMake(0,DIAL_OFFSET_Y, DIAL_WIDTH, DIAL_HEIGHT) strings:numbers] autorelease];
    self.dial1.delegate = self;
    [self.view addSubview:self.dial1.view];
    
    self.dial2 = [[[DialController alloc] initWithDialFrame:CGRectMake(DIAL_OFFSET_X + DIAL_WIDTH, DIAL_OFFSET_Y, DIAL_WIDTH, DIAL_HEIGHT) strings:numbers] autorelease];
    self.dial2.delegate = self;
    [self.view addSubview:self.dial2.view];
    
    //select initial value
    //[self spinToRandomString:NO];
}

- (void)viewDidUnload {
    self.presetStrings = nil;
    self.delegate = nil;
    [[UIAccelerometer sharedAccelerometer] setDelegate:nil];
    [super viewDidUnload];
}

//spin to random string
//is "preset" is true, spin to random preset string
- (void)spinToRandomString:(BOOL)preset {
    if (preset && self.presetStrings != nil) {
        //spin to preset
        int selectedStringIndex = random() % [self.presetStrings count];
        NSString *selectedString = [self.presetStrings objectAtIndex:selectedStringIndex];
        NSLog(@"autoselecting string %@", selectedString);
        
        //call all the dials
        [self.dial1 spinToString:[selectedString substringWithRange:NSMakeRange(0, 1)]];
        [self.dial2 spinToString:[selectedString substringWithRange:NSMakeRange(1, 1)]];
    }
    else {
        //spin to random
        [self.dial1 spinToRandomString];
        [self.dial2 spinToRandomString];
    }
}

#pragma mark DialControllerDelegate methods

- (void)dialControllerDidSpin:(DialController *)dial {
    //...
}

- (void)dialController:(DialController *)dial didSnapToString:(NSString *)value {
    if (!self.dial1.isSpinning && !self.dial2.isSpinning) {
        NSArray *selectedStrings = [[NSArray alloc] initWithObjects:self.dial1.selectedString, self.dial2.selectedString, nil];
        
        [[self delegate] multiDialViewController:self didSelectString:selectedStrings withDial:dial];
        
    }
}

#pragma mark -

//add shake to spin to random value
- (void)accelerometer:(UIAccelerometer *)accelerometer didAccelerate:(UIAcceleration *)acceleration {
	if (fabs(acceleration.x) + fabs(acceleration.y) + fabs(acceleration.z) > 4.0) {
        [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(spinToRandomYear) object:nil];
        [self performSelector:@selector(spinToRandomString:) withObject:nil afterDelay:0.5];
    }
}

#pragma -

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (void)dealloc {
    self.presetStrings = nil;
    self.dial1 = self.dial2 = nil;
    [super dealloc];
}

@end