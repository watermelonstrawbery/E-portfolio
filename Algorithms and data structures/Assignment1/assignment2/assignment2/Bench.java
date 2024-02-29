import java.util.Random;


class Bench {
     
    private static void linear(int[] array, int[] keys) {
	for (int i = 0; i < keys.length ; i++) {
	    Linear.searchunsorted(array, keys[i]);
	}
    }

    public static double time_linear(int[] array, int[] keys){

        int tries = 10;
        double min = Double.POSITIVE_INFINITY;

        for(int i = 0; i < tries; i++) {
            long t0 = System.nanoTime();
            linear(array, keys);      
            long t1 = System.nanoTime();
            double t = (t1 - t0);
            if( t < min)
              min = t;
          }
          return min;
    }
    
 
    private static void binary(int[] array, int[] keys) {
	for (int i = 0; i < keys.length ; i++) {
	    Binary.search(array, keys[i]);
	}
    }

   public static double time_binary(int[] array, int[] keys){

        int tries = 10;
        double min = Double.POSITIVE_INFINITY;

        for(int i = 0; i < tries; i++) {
            long t0 = System.nanoTime();
            binary(array, keys);      
            long t1 = System.nanoTime();
            double t = (t1 - t0);
            if( t < min)
              min = t;
          }
          return min;
    }

    private static int[] sorted(int n) {
	Random rnd = new Random();	
	int[] array = new int[n];
	int nxt = 0;

	for (int i = 0; i < n ; i++) {
	    array[i] = nxt;
	    nxt += rnd.nextInt(10) + 1 ;
	}	
	return array;
    }

/* 
    private static int[] unsorted(int n){
    Random rnd = new Random();
    int[] array = new int[n];
    int nxt = 0;

    for(int i = 0; i < n; i++){
        array[i] = nxt;
        nxt = rnd.nextInt();
    }
    }

*/
    private static int[] keys(int loop, int n) {  //n är hur stor arrayenh vi ska söka i
	Random rnd = new Random();	
	int[] keys = new int[loop];
	for (int i = 0; i < loop ; i++) {
	    keys[i] = rnd.nextInt(n*5);
	}	
	return keys;
    }

    
    public static void main(String[] arg) {

	int[] sizes = {100,200,300,400,500,600,700,800,900,1000,1100,1200,1300,1400,1500,1600};

	System.out.printf("# searching through an array of length n, time in ns\n");
	System.out.printf("#%7s%8s%8s%8s\n", "n", "linear", "binary", "  sorted search");
	for ( int n : sizes) {

	    int loop = 10000; //antal sökningar

        
	    
	    int[] array = sorted(n); //skapar array som är 800 stor
        
	    int[] keys = keys(loop, n);
        
        double lin_t = time_linear(array, keys);

        double bin_t = time_binary(array, keys);

	    System.out.printf("%8d %8.2f %8.2f\n", n , (lin_t/loop), (bin_t/loop));
/* 
	    int k = 1000;
	    
	    double min = Double.POSITIVE_INFINITY;

	    for (int i = 0; i < k; i++) {
		long t0 = System.nanoTime();
		linear(array, keys);
		long t1 = System.nanoTime();
		double t = (t1 - t0);
		if (t < min)                          //min is the minimal time it takes to search with linear
		    min = t;                          //method. AKA the fastest time.
	    }

	    System.out.printf("%8.0f\n", (min/loop));	    
/* 
	    min = Double.POSITIVE_INFINITY;
	    
	    for (int i = 0; i < k; i++) {
		long t0 = System.nanoTime();
		binary(array, keys);
		long t1 = System.nanoTime();
		double t = (t1 - t0);
		if (t < min)
		    min = t;
	    }
        
        System.out.printf("%8.0f\n", (min/loop));	 
*/
         
        /* 

        for(int i = 0; i < k; i++){
        long t0 = System.nanoTime();
        search_unsorted(array, key);
        long t1 = System.nanoTime();
        double t = (t1 - t0);
        if (t < min)
            min = t;

        }

	    System.out.printf("%8.0f\n" , (min/loop));	
        */    	    
	}
    }
}



//vi kan ha graf där x axeln är sizez från arrayen. 100, 200 ... .