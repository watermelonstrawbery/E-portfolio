/* mipslabfunc.c
   This file written 2015 by F Lundevall
   Some parts are original code written by Axel Isaksson

   For copyright and licensing, see file COPYING */

#include <stdint.h>   /* Declarations of uint_32 and the like */
#include <pic32mx.h>  /* Declarations of system-specific addresses etc */
#include "mipslab.h"  /* Declatations for these labs */

/* Declare a helper function which is local to this file */
static void num32asc( char * s, int ); 

#define DISPLAY_CHANGE_TO_COMMAND_MODE (PORTFCLR = 0x10)
#define DISPLAY_CHANGE_TO_DATA_MODE (PORTFSET = 0x10)

#define DISPLAY_ACTIVATE_RESET (PORTGCLR = 0x200)
#define DISPLAY_DO_NOT_RESET (PORTGSET = 0x200)

#define DISPLAY_ACTIVATE_VDD (PORTFCLR = 0x40)
#define DISPLAY_ACTIVATE_VBAT (PORTFCLR = 0x20)

#define DISPLAY_TURN_OFF_VDD (PORTFSET = 0x40)
#define DISPLAY_TURN_OFF_VBAT (PORTFSET = 0x20)




int score;
char * Highscore;
uint8_t OLED_Screen1[128 * 32];
unsigned char Action = TRUE;





/* quicksleep:
   A simple function to create a small delay.
   Very inefficient use of computing resources,
   but very handy in some special cases. */
void quicksleep(int cyc) {
	int i;
	for(i = cyc; i > 0; i--);
}

/* tick:
   Add 1 to time in memory, at location pointed to by parameter.
   Time is stored as 4 pairs of 2 NBCD-digits.
   1st pair (most significant byte) counts days.
   2nd pair counts hours.
   3rd pair counts minutes.
   4th pair (least significant byte) counts seconds.
   In most labs, only the 3rd and 4th pairs are used. */
void tick( unsigned int * timep )
{
  /* Get current value, store locally */
  register unsigned int t = * timep;
  t += 1; /* Increment local copy */
  
  /* If result was not a valid BCD-coded time, adjust now */

  if( (t & 0x0000000f) >= 0x0000000a ) t += 0x00000006;
  if( (t & 0x000000f0) >= 0x00000060 ) t += 0x000000a0;
  /* Seconds are now OK */

  if( (t & 0x00000f00) >= 0x00000a00 ) t += 0x00000600;
  if( (t & 0x0000f000) >= 0x00006000 ) t += 0x0000a000;
  /* Minutes are now OK */

  if( (t & 0x000f0000) >= 0x000a0000 ) t += 0x00060000;
  if( (t & 0x00ff0000) >= 0x00240000 ) t += 0x00dc0000;
  /* Hours are now OK */

  if( (t & 0x0f000000) >= 0x0a000000 ) t += 0x06000000;
  if( (t & 0xf0000000) >= 0xa0000000 ) t = 0;
  /* Days are now OK */

  * timep = t; /* Store new value */
}

/* display_debug
   A function to help debugging.

   After calling display_debug,
   the two middle lines of the display show
   an address and its current contents.

   There's one parameter: the address to read and display.

   Note: When you use this function, you should comment out any
   repeated calls to display_image; display_image overwrites
   about half of the digits shown by display_debug.
*/   
void display_debug( volatile int * const addr )
{
  display_string( 1, "Addr" );
  display_string( 2, "Data" );
  num32asc( &textbuffer[1][6], (int) addr );
  num32asc( &textbuffer[2][6], *addr );
  display_update();
}
// not changed from lab
uint8_t spi_send_recv(uint8_t data) {
	while(!(SPI2STAT & 0x08));
	SPI2BUF = data;
	while(!(SPI2STAT & 1));
	return SPI2BUF;
}
// not changed from lab
void display_init(void) {
        DISPLAY_CHANGE_TO_COMMAND_MODE;
	quicksleep(10);
	DISPLAY_ACTIVATE_VDD;
	quicksleep(1000000);
	
	spi_send_recv(0xAE);
	DISPLAY_ACTIVATE_RESET;
	quicksleep(10);
	DISPLAY_DO_NOT_RESET;
	quicksleep(10);
	
	spi_send_recv(0x8D);
	spi_send_recv(0x14);
	
	spi_send_recv(0xD9);
	spi_send_recv(0xF1);
	
	DISPLAY_ACTIVATE_VBAT;
	quicksleep(10000000);
	
	spi_send_recv(0xA1);
	spi_send_recv(0xC8);
	
	spi_send_recv(0xDA);
	spi_send_recv(0x20);
	
	spi_send_recv(0xAF);
}

// not changed from lab
void display_string(int line, char *s) {
	int i;
	if(line < 0 || line >= 4)
		return;
	if(!s)
		return;
	
	for(i = 0; i < 16; i++)
		if(*s) {
			textbuffer[line][i] = *s;
			s++;
		} else
			textbuffer[line][i] = ' ';
}


// Saina
void ClearDisplay(){
  int k;
  for (k = 0; k < 512; k++){
  
    OLED_Screen1[k] = 0;
  }
}

//  Written by Ruth & Saina
void display_screen(uint8_t OLED_Screen[]) {
	int i, j;
	

  for(i = 0; i < 4; i++) {
    DISPLAY_CHANGE_TO_COMMAND_MODE;

    spi_send_recv(0x22);
    spi_send_recv(i);
    
    
    spi_send_recv(0x10);
  
    
    DISPLAY_CHANGE_TO_DATA_MODE;
    
    
    for(j=0; j < 128;j++){

      spi_send_recv(OLED_Screen[i * 128 + j]);// går igenom varje pixel på displayen. Skickar värdet till Chipkit display.

    }
    
    
  
  }
  
}


// Written  by saina & ruth
void TurnOnPixel(int x, int y)
{
  if (x < 128 && y < 32 ){

    int index = (( y/ 8) * 128) + x;// ger den en specific x-koordinat i en specific sida

    OLED_Screen1[index] = (OLED_Screen1[index] | (0x1 << (y % 8)));// Givet en x koordinat (index) så vet man vilken pixel i en byte (0-7) som ska tändas.
    

  }
  

}

// Written by Saina
void Givenscore(){

  if(Action){
    if((firstPipe.x + firstPipe.width) == currentFrog.x){
    score++;
      
      }
    if((secondPipe.x + secondPipe.width) == currentFrog.x){
    score++;
        
    }
    if((thirdPipe.x + thirdPipe.width) == currentFrog.x){
        score++;
        
    }

  }
  
}





// not changed from lab
void display_update(void) {
	int i, j, k;
	int c;
	for(i = 0; i < 4; i++) {
		DISPLAY_CHANGE_TO_COMMAND_MODE;
		spi_send_recv(0x22);
		spi_send_recv(i);
		
		spi_send_recv(0x0);
		spi_send_recv(0x10);
		
		DISPLAY_CHANGE_TO_DATA_MODE;
		
		for(j = 0; j < 16; j++) {
			c = textbuffer[i][j];
			if(c & 0x80)
				continue;
			
			for(k = 0; k < 8; k++)
				spi_send_recv(font[c*8 + k]);
		}
	}
}

// Written by Ruth
int getbtn4(){
  TRISD |= 0x80;// index 7 på både TRISD och PORTD
  volatile int button4 = (PORTD >> 7);
  button4 &= 0x00000001;
  return button4;
}

// Ruth
void jump(){

	if(getbtn4()){
	
    currentFrog.y -= 2;
	}
	else{
		currentFrog.y += 1;
	}

}


// Written by Saina and Ruth
void collisions(){
  // Rör marken
	if(currentFrog.y + currentFrog.width >= 31){
		Action = FALSE;
    
	}
  // Kolliderar vänster ifrån med Pipe ett
	if((currentFrog.x + currentFrog.width ) == firstPipe.x){
		if( currentFrog.y < firstPipe.height || currentFrog.y > (fourthPipe.y - fourthPipe.height)){
			Action = FALSE;
      
		}
	}

  // Kolliderar vänster ifrån med Pipe två
	if((currentFrog.x + currentFrog.width ) == secondPipe.x){
		if( currentFrog.y < secondPipe.height || currentFrog.y > (fifthPipe.y - fifthPipe.height)){
			Action = FALSE;
      
		}
	}

 // Kolliderar vänster ifrån med Pipe tre
  if((currentFrog.x + currentFrog.width ) == thirdPipe.x){
      if( currentFrog.y < thirdPipe.height || currentFrog.y > (sixthPipe.y - sixthPipe.height)){
        Action = FALSE;
        
      }
  }


 //Om grodans huvud kolliderar med det övre och undre röret. Pipe ett
	if(currentFrog.x + currentFrog.width > firstPipe.x){
		if(currentFrog.y == firstPipe.height){
			Action = FALSE;
      
		}

		if(currentFrog.y == (fourthPipe.y - fourthPipe.height)){
			Action = FALSE;
      

		}
	}


 //Om grodans huvud kolliderar med det övre och undre röret. Pipe två
	if(currentFrog.x + currentFrog.width > secondPipe.x){
		if(currentFrog.y == secondPipe.height){
			Action = FALSE;
      
		}

		if(currentFrog.y == (fifthPipe.y - fifthPipe.height)){
			Action = FALSE;
      
		}
	}

//Om grodans huvud kolliderar med det övre och undre röret. Pipe tre
	if(currentFrog.x + currentFrog.width > thirdPipe.x){
		if(currentFrog.y == thirdPipe.height){
			Action = FALSE;
      
		}

		if(currentFrog.y == (sixthPipe.y - sixthPipe.height)){
			Action = FALSE;
      
		}
	}

  

}

//Written by Ruth
void Gamestart(){
 

  display_string(0,"Flappy Frog");
  display_string(1,"Ruth & Saina");
  display_update();
  quicksleep(10000000);
  ClearDisplay();
  
  

}
//  Written by Saina 
void Gameover(){

 
  ClearDisplay();
	display_screen(OLED_Screen1);

			
	Highscore = itoaconv(score);
	display_string(0,"GameOver");
  display_string(1,"HighScore");
  display_string(2,Highscore);

  display_update();
	quicksleep(10000000);


}

 // Written By Ruth & Saina
void drawTheGame(){
 
  ClearDisplay();
  int i,j;

  
  for (i = 0; i < currentFrog.width; i++) {
    for (j = 0; j < currentFrog.width; j++) {
      if ((currentFrog.x + i > 0) && (currentFrog.x + i < 128) &&(currentFrog.y + j > 0) && (currentFrog.y + j < 32)) {// kollar att grodan befinner sig inom displayens ram.
        int x = currentFrog.x + i;
        int y = currentFrog.y + j;

        if (currentFrog.frogImg[i + j * currentFrog.width] != 0) {// Går igenom Frog arrayen i mipslabdata för att se vilka pixlar som ska sättas på.
          TurnOnPixel(x, y);
        }
      }
    }
  }

  for(i= 0; i< firstPipe.width; i++){
            
    for(j = 0; j < firstPipe.height; j++){

      if((firstPipe.x + i > 0) && (firstPipe.x + i < 128)){

        int x = firstPipe.x +i;
        int y= firstPipe.y + j;

        TurnOnPixel(x,y);
        

      }
        
    }

  }

  for(i= 0; i< secondPipe.width; i++){
          
    for(j = 0; j < secondPipe.height; j++){

      if((secondPipe.x + i > 0) && (secondPipe.x + i < 128)){ 
      
        int x = secondPipe.x +i;
        int y= secondPipe.y + j;

        TurnOnPixel(x,y);
        

      }
        
    }

  }


  for(i= 0; i< thirdPipe.width; i++){
          
    for(j = 0; j < thirdPipe.height; j++){

      if((thirdPipe.x + i > 0) && (thirdPipe.x + i < 128)){

        int x = thirdPipe.x +i;
        int y= thirdPipe.y + j;

        TurnOnPixel(x,y);
        

      }
        
    }

  }

   
  
  

  for(i= 0; i< fourthPipe.width; i++){
      
    for(j = 0; j < fourthPipe.height; j++){

      if((fourthPipe.x + i > 0) && (fourthPipe.x + i< 128)){

        int x = fourthPipe.x +i;
        int y= fourthPipe.y - j;

        TurnOnPixel(x,y);
        

      }
    
    }

  }

    
  for(i= 0; i< fifthPipe.width; i++){
      
    for(j = 0; j < fifthPipe.height; j++){

      if((fifthPipe.x + i > 0) && (fifthPipe.x + i < 128)){

        int x = fifthPipe.x +i;
        int y= fifthPipe.y - j;

        TurnOnPixel(x,y);
        

      }
    
    }

  }

    
  for(i= 0; i< sixthPipe.width; i++){
      
    for(j = 0; j < sixthPipe.height; j++){

      if((sixthPipe.x + i > 0) && (sixthPipe.x + i< 128)){

        int x = sixthPipe.x +i;
        int y= sixthPipe.y - j;

        TurnOnPixel(x,y);
        

      }
    
    }

  }
   

  display_screen(OLED_Screen1);
 
}





/* Helper function, local to this file.
   Converts a number to hexadecimal ASCII digits. */
static void num32asc( char * s, int n ) 
{
  int i;
  for( i = 28; i >= 0; i -= 4 )
    *s++ = "0123456789ABCDEF"[ (n >> i) & 15 ];
}

/*
 * nextprime
 * 
 * Return the first prime number larger than the integer
 * given as a parameter. The integer must be positive.
 */
#define PRIME_FALSE   0     /* Constant to help readability. */
#define PRIME_TRUE    1     /* Constant to help readability. */
int nextprime( int inval )
{
   register int perhapsprime = 0; /* Holds a tentative prime while we check it. */
   register int testfactor; /* Holds various factors for which we test perhapsprime. */
   register int found;      /* Flag, false until we find a prime. */

   if (inval < 3 )          /* Initial sanity check of parameter. */
   {
     if(inval <= 0) return(1);  /* Return 1 for zero or negative input. */
     if(inval == 1) return(2);  /* Easy special case. */
     if(inval == 2) return(3);  /* Easy special case. */
   }
   else
   {
     /* Testing an even number for primeness is pointless, since
      * all even numbers are divisible by 2. Therefore, we make sure
      * that perhapsprime is larger than the parameter, and odd. */
     perhapsprime = ( inval + 1 ) | 1 ;
   }
   /* While prime not found, loop. */
   for( found = PRIME_FALSE; found != PRIME_TRUE; perhapsprime += 2 )
   {
     /* Check factors from 3 up to perhapsprime/2. */
     for( testfactor = 3; testfactor <= (perhapsprime >> 1) + 1; testfactor += 1 )
     {
       found = PRIME_TRUE;      /* Assume we will find a prime. */
       if( (perhapsprime % testfactor) == 0 ) /* If testfactor divides perhapsprime... */
       {
         found = PRIME_FALSE;   /* ...then, perhapsprime was non-prime. */
         goto check_next_prime; /* Break the inner loop, go test a new perhapsprime. */
       }
     }
     check_next_prime:;         /* This label is used to break the inner loop. */
     if( found == PRIME_TRUE )  /* If the loop ended normally, we found a prime. */
     {
       return( perhapsprime );  /* Return the prime we found. */
     } 
   }
   return( perhapsprime );      /* When the loop ends, perhapsprime is a real prime. */
} 

/*
 * itoa
 * 
 * Simple conversion routine
 * Converts binary to decimal numbers
 * Returns pointer to (static) char array
 * 
 * The integer argument is converted to a string
 * of digits representing the integer in decimal format.
 * The integer is considered signed, and a minus-sign
 * precedes the string of digits if the number is
 * negative.
 * 
 * This routine will return a varying number of digits, from
 * one digit (for integers in the range 0 through 9) and up to
 * 10 digits and a leading minus-sign (for the largest negative
 * 32-bit integers).
 * 
 * If the integer has the special value
 * 100000...0 (that's 31 zeros), the number cannot be
 * negated. We check for this, and treat this as a special case.
 * If the integer has any other value, the sign is saved separately.
 * 
 * If the integer is negative, it is then converted to
 * its positive counterpart. We then use the positive
 * absolute value for conversion.
 * 
 * Conversion produces the least-significant digits first,
 * which is the reverse of the order in which we wish to
 * print the digits. We therefore store all digits in a buffer,
 * in ASCII form.
 * 
 * To avoid a separate step for reversing the contents of the buffer,
 * the buffer is initialized with an end-of-string marker at the
 * very end of the buffer. The digits produced by conversion are then
 * stored right-to-left in the buffer: starting with the position
 * immediately before the end-of-string marker and proceeding towards
 * the beginning of the buffer.
 * 
 * For this to work, the buffer size must of course be big enough
 * to hold the decimal representation of the largest possible integer,
 * and the minus sign, and the trailing end-of-string marker.
 * The value 24 for ITOA_BUFSIZ was selected to allow conversion of
 * 64-bit quantities; however, the size of an int on your current compiler
 * may not allow this straight away.
 */
#define ITOA_BUFSIZ ( 24 )
char * itoaconv( int num )
{
  register int i, sign;
  static char itoa_buffer[ ITOA_BUFSIZ ];
  static const char maxneg[] = "-2147483648";
  
  itoa_buffer[ ITOA_BUFSIZ - 1 ] = 0;   /* Insert the end-of-string marker. */
  sign = num;                           /* Save sign. */
  if( num < 0 && num - 1 > 0 )          /* Check for most negative integer */
  {
    for( i = 0; i < sizeof( maxneg ); i += 1 )
    itoa_buffer[ i + 1 ] = maxneg[ i ];
    i = 0;
  }
  else
  {
    if( num < 0 ) num = -num;           /* Make number positive. */
    i = ITOA_BUFSIZ - 2;                /* Location for first ASCII digit. */
    do {
      itoa_buffer[ i ] = num % 10 + '0';/* Insert next digit. */
      num = num / 10;                   /* Remove digit from number. */
      i -= 1;                           /* Move index to next empty position. */
    } while( num > 0 );
    if( sign < 0 )
    {
      itoa_buffer[ i ] = '-';
      i -= 1;
    }
  }
  /* Since the loop always sets the index i to the next empty position,
   * we must add 1 in order to return a pointer to the first occupied position. */
  return( &itoa_buffer[ i + 1 ] );
}
