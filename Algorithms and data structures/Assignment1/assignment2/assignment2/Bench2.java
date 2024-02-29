import java.util.Random;


class Bench2 {

    private static void unsorted(int[] array, int[] indx) {
	for (int i = 0; i < indx.length ; i++) {
	    FirstTry.search_unsorted(array, i);
	}
    }
    

    private static void sorted(int[] array, int[] indx) {
	for (int i = 0; i < indx.length ; i++) {
	    FirstTry.search_sorted(array, i);
	}
    }

       
    private static int[] sorted(int n) {
	Random rnd = new Random();	
	int[] array = new int[n];
	int nxt = rnd.nextInt(10);

	for (int i = 0; i < n ; i++) {
	    array[i] = nxt;
	    nxt += rnd.nextInt(10) + 1 ;
	}	
	return array;
    }


    private static int[] sorted2(int n) {
	Random rnd = new Random();	
	int[] array = new int[n];

	for (int i = 0; i < n ; i++) {
        int nxt = rnd.nextInt(1000);
	    array[i] = nxt;
	}	
	return array;
    }


    private static int[] keys(int loop, int n) {
	Random rnd = new Random();	
	int[] indx = new int[loop];
	for (int i = 0; i < loop ; i++) {
	    indx[i] = rnd.nextInt(n*5);
	}	
	return indx;
    }

    
    public static void main(String[] arg) {

	int[] sizes = {100,200,400,600,800,1000,1200,1400,1600,1800,2000, 2200, 2400, 2600};

	System.out.printf("# searching through an array of length n, time in ns\n");
	System.out.printf("#%7s%8s%8s\n", "n", "Unsorted", "Sorted");
	for ( int n : sizes) {

	    int loop = 10000;
	    
	    int[] sortedArray = sorted(n);
        int[] unsortedArray = sorted2(n);

	    int[] indx = keys(loop, n);

	    System.out.printf("%8d", n);

	    int k = 1000;
	    
	    double min = Double.POSITIVE_INFINITY;

	    for (int i = 0; i < k; i++) {
		long t0 = System.nanoTime();
		unsorted(unsortedArray, indx);
		long t1 = System.nanoTime();
		double t = (t1 - t0);
		if (t < min)
		    min = t;
	    }

	    System.out.printf("%8.0f", (min/loop));	    

	    min = Double.POSITIVE_INFINITY;
	    
	    for (int i = 0; i < k; i++) {
		long t0 = System.nanoTime();
		sorted(sortedArray, indx);
		long t1 = System.nanoTime();
		double t = (t1 - t0);
		if (t < min)
		    min = t;
	    }

	    System.out.printf("%8.0f\n" , (min/loop));	    	    
	}
    }
}