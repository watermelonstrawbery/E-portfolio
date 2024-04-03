/* mipslabwork.c

   This file written 2015 by F Lundevall
   Updated 2017-04-21 by F Lundevall

   This file should be changed by YOU! So you must
   add comment(s) here with your name(s) and date(s):

   This file modified 2017-04-31 by Ture Teknolog 

   For copyright and licensing, see file COPYING */

#include <stdint.h>   /* Declarations of uint_32 and the like */
#include <pic32mx.h>  /* Declarations of system-specific addresses etc */
#include "mipslab.h"  /* Declatations for these labs */




int timeoutcounter;
int score = 0;

// Written by Ruth & Saina
/* Interrupt Service Routine */
void user_isr( void )
{
    
  if( (IFS(0) & 0x100)){
        timeoutcounter++;
	}
	IFSCLR(0) = 0x100;
	if (timeoutcounter >= 4)
	{
		firstPipe.x -= 1;
		secondPipe.x -= 1;
		thirdPipe.x -= 1;
		fourthPipe.x -= 1;
		fifthPipe.x -= 1;
		sixthPipe.x -= 1;

        
		

		jump();
		collisions();
        Givenscore();
        
       


	    if((firstPipe.x + firstPipe.width +1 ) == 0){
	 	    firstPipe.x = 180;
  		}

	    if((secondPipe.x + secondPipe.width +1 ) == 0){
	 	secondPipe.x = 180;
  		}

	    if((thirdPipe.x + thirdPipe.width +1 ) == 0){
	 	thirdPipe.x = 180;
  		}	

	    if((fourthPipe.x + fourthPipe.width +1 ) == 0){
	 	fourthPipe.x = 180;
  		}

	    if((fifthPipe.x + fifthPipe.width +1 ) == 0){
	 	fifthPipe.x = 180;
  		}

	    if((sixthPipe.x + sixthPipe.width +1 ) == 0){
	 	sixthPipe.x = 180;
  		}	

    timeoutcounter = 0;
    
  }


    
  return;
}


// Written by Ruth & Saina
/* Lab-specific initialization goes here */
void labinit( void )
{
   
    // Intilizaing process following the referance manual steps
    T2CONCLR =0x8000;// bit 15 is cleared to disable the timer
    T2CONCLR=0x2; // Stating the usage of the internal peripheral clock
    T2CON = 0x70;
    TMR2 =0x00;
    PR2 = 6250;
    IFSCLR(0) = 0x100;
    IPCSET(2)= 0x1F;
    IECSET(0) = 0x100;
    T2CONSET = 0x8000;

    enable_interrupts();
}


    
    


