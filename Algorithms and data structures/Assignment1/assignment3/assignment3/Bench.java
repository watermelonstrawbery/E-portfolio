import java.util.Arrays;
import java.util.Random;

public class Bench {


	public static int[] sorted(int n) {
		Random rnd = new Random();	
		int[] array = new int[n];
		int nxt = rnd.nextInt(10);
	
		for (int i = 0; i < n ; i++) {
			array[i] = nxt;
			nxt += rnd.nextInt(10) + 1 ;
		}	
		return array;
		}

     public static int[] unsorted(int n) {
	Random rnd = new Random();	
	int[] array = new int[n];

	for (int i = 0; i < n ; i++) {
	    int nxt = rnd.nextInt(1000);
		array[i] = nxt;
    }
	return array;
    }


	public static int[] unsortedarray2(int n){
		
        Random rnd = new Random(); 
		int[] array = new int[n];
		 for(int i = 0; i < i; i++){
			array[i] = rnd.nextInt(n*10);

		 }
		return array;
	}

	public static int[] unsortedarray3(int[] array, int size){
		Arrays.sort(array, 0, size -10);
         return array;

		 }
		
	


    public static void main(String[] args) {
       
    //int[] size= {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 12000, 14000, 16000};

    int loop = 1000;
	int tries = 10;	//antal gånger vi ska loopa igenom bench mark

    System.out.printf("#%7s%8s%8s%8s\n","n ", "selectionsort ", " insertionsort ", " mergesort");   
    

    for(int n = 1000; n <= 16000; n = n*2){

        int[] array = new int[n];
        //int[] array = unsortedarray3(unsortedarray2(n), n);
        //int[] array = sorted(n);
    	System.out.printf("%8d ", n);

    	double min = Double.POSITIVE_INFINITY;
    
    for (int i = 0; i < tries; i++) {
		long t0 = System.nanoTime();
		for(int j = 0; j < loop; j++){
			int[] clone = array.clone();
            Selectionsort.selectionSort(clone);
		}
		
		long t1 = System.nanoTime();
		double t = (t1 - t0);
		if (t < min)
		    min = t;
	    }

	    System.out.printf("%8.0f", (min));	
    

    min = Double.POSITIVE_INFINITY;

    for (int i = 0; i < tries; i++) {
		long t0 = System.nanoTime();
		for(int j = 0; j < loop; j++){
			int[] clone = array.clone();
            Insertionsort.insertionSort(clone);
		}
		long t1 = System.nanoTime();
		double t = (t1 - t0);
		if (t < min)
		    min = t;
	}
	
	System.out.printf("   %8.0f", (min));	

    min = Double.POSITIVE_INFINITY;

	for (int i = 0; i < tries; i++) {
	long t0 = System.nanoTime();
        for(int j = 0; j < loop; j++){
			int[] clone = array.clone();
            Mergesort.sort(clone); 
		}
		long t1 = System.nanoTime();
		double t = (t1 - t0);
		if (t < min)
		    min = t;
	}	

     System.out.printf("   %8.0f\n", (min));


}
    }





}
