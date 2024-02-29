import java.util.Random;


class DuplicateBench {


       
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


    private static int[] keys(int loop, int n) {
	Random rnd = new Random();	
	int[] indx = new int[loop];
	for (int i = 0; i < loop ; i++) {
	    indx[i] = rnd.nextInt(n*5);
	}	
	return indx;
    }

    
    public static void main(String[] arg) {

	int[] sizes = {100,200,400,800,1600,3200,6400};
//%7s%8s%8s%8s
	System.out.printf("# searching through an array of length n, time in ns\n");
	System.out.printf("#%-7s%-10s%-10s%-10s\n", "n", "unsorted ", " binary", "better");
	for ( int n : sizes) {
        
	    int loop = 1000;
	    
	    int[] array = sorted(n);
	    int[] indx = keys(loop, n);

        int[] sortedArray1 = Benchlast.sorted(n);
        int[] sortedArray2 = Benchlast.sorted(n);
        int[] unsortedArray1 = Benchlast.unsorted(n);
        int[] unsortedArray2 = Benchlast.unsorted(n);

	    System.out.printf("%-7d", n);

	    int k = 1000;
	    
	    double min = Double.POSITIVE_INFINITY;
 /*  
	    for (int i = 0; i < k; i++) {
		long t0 = System.nanoTime();
		Duplicate.duplicateUnsorted(unsortedArray1, unsortedArray2);
		long t1 = System.nanoTime();
		double t = (t1 - t0);
		if (t < min)
		    min = t;
	    }

	    System.out.printf("%.2f\n", (min/loop));	    
 
	    min = Double.POSITIVE_INFINITY;
	     
	    for (int i = 0; i < k; i++) {
		long t0 = System.nanoTime();
		Duplicate.duplicateBinary(sortedArray1, sortedArray2);
		long t1 = System.nanoTime();
		double t = (t1 - t0);
		if (t < min)
		    min = t;
	    }
 
	    System.out.printf("%.2f\n" , (min/loop));	    	    
	 */
 
        min = Double.POSITIVE_INFINITY;
	    
	    for (int i = 0; i < k; i++) {
		long t0 = System.nanoTime();
		Duplicate.betterDuplicate(sortedArray1, sortedArray2);
		long t1 = System.nanoTime();
		double t = (t1 - t0);
		if (t < min)
		    min = t;
	    }

	    System.out.printf("%.2f\n" , (double)(min/loop));
       	    	    
	}

    }
    
}

