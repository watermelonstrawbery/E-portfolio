import java.util.Random;

public class ArrayAppend{

    public static void append(int[] a, int[] b){

        
        int[] elements = new int[a.length + b.length];

        for(int i = 0; i < a.length; i++){             
            elements[i] = a[i];
            }
        for(int i = 0; i < b.length; i++){
           elements[a.length + i] = b[i];
            }
    

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

        

    public static void main(String[] arg) {

    
        int[] sizeA = {100, 1000, 10000, 100000, 1000000};
        int sizeB = 10000;
  
        for( int n : sizeA){
  
        int[] a = unsorted(n);
        int[] b = unsorted(sizeB);
      
         int k = 100;
         double min = Double.POSITIVE_INFINITY;
  
         for (int i = 0; i < k; i++) {
           long t0 = System.nanoTime();
           append(b,a);
           long t1 = System.nanoTime();
           double t = (t1 - t0);
           if (t < min)
               min = t;
          }
  
      System.out.printf("%8.0f", (min/1000));	    
        }    	    
    }
  }
  
