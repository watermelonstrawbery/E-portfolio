/* mipslabmain.c

   This file written 2015 by Axel Isaksson,
   modified 2015, 2017 by F Lundevall

   Latest update 2017-04-21 by F Lundevall

   For copyright and licensing, see file COPYING */

#include <stdint.h>   /* Declarations of uint_32 and the like */
#include <pic32mx.h>  /* Declarations of system-specific addresses etc */
#include "mipslab.h"  /* Declatations for these labs */


int main(void) {
        /*
	  This will set the peripheral bus clock to the same frequency
	  as the sysclock. That means 80 MHz, when the microcontroller
	  is running at 80 MHz. Changed 2017, as recommended by Axel.
	*/
	SYSKEY = 0xAA996655;  /* Unlock OSCCON, step 1 */
	SYSKEY = 0x556699AA;  /* Unlock OSCCON, step 2 */
	while(OSCCON & (1 << 21)); /* Wait until PBDIV ready */
	OSCCONCLR = 0x180000; /* clear PBDIV bit <0,1> */
	while(OSCCON & (1 << 21));  /* Wait until PBDIV ready */
	SYSKEY = 0x0;  /* Lock OSCCON */
	
	/* Set up output pins */
	AD1PCFG = 0xFFFF;
	ODCE = 0x0;
	TRISECLR = 0xFF;
	PORTE = 0x0;
	
	/* Output pins for display signals */
	PORTF = 0xFFFF;
	PORTG = (1 << 9);
	ODCF = 0x0;
	ODCG = 0x0;
	TRISFCLR = 0x70;
	TRISGCLR = 0x200;
	
	/* Set up input pins */
	TRISDSET = (1 << 8);
	TRISFSET = (1 << 1);
	
	/* Set up SPI as master */
	SPI2CON = 0;
	SPI2BRG = 4;
	/* SPI2STAT bit SPIROV = 0; */
	SPI2STATCLR = 0x40;
	/* SPI2CON bit CKP = 1; */
        SPI2CONSET = 0x40;
	/* SPI2CON bit MSTEN = 1; */
	SPI2CONSET = 0x20;
	/* SPI2CON bit ON = 1; */
	SPI2CONSET = 0x8000;
	





	firstPipe.x = 140; 
	firstPipe.y = 0;
	firstPipe.width = 5;
	firstPipe.height = 8;

	

	
	
	secondPipe.x = 200; 
	secondPipe.y = 0;
	secondPipe.width = 5;
	secondPipe.height = 13;

	
	thirdPipe.x = 260; 
	thirdPipe.y = 0;
	thirdPipe.width = 5;
	thirdPipe.height = 3;

	
	fourthPipe.x = 140; 
	fourthPipe.y = 31;
	fourthPipe.width = 5;
	fourthPipe.height = 8;

	

	
	fifthPipe.x = 200; 
	fifthPipe.y = 31;
	fifthPipe.width = 5;
	fifthPipe.height = 5;

	

	
	
	sixthPipe.x = 260; 
	sixthPipe.y = 31;
	sixthPipe.width = 5;
	sixthPipe.height = 11;

	

	

	currentFrog.x= 20;
	currentFrog.y= 15;
	currentFrog.width = 5;
	currentFrog.frogImg = &frog[0][0];
	


	 
	 
	
	 
	
	/* Do any lab-specific initialization */

	
	display_init();
	Gamestart();
	labinit();
		
		
	
	while(1)
	{
		if(Action){
			drawTheGame();
		}
		else{
			Gameover();
		}
	  
	}
	return 0;
}
