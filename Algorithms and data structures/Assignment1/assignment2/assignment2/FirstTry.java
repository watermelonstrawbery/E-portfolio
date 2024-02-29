import java.util.Random;

public class FirstTry {
    public static boolean search_unsorted(int[] array, int key) {
        for (int index = 0; index < array.length ; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }

    public static boolean search_sorted(int[] array, int key){
        for(int index = 0; index < array.length; index++){
            if(array[index] >= key)
                if(array[index] == key )
                    return true;
                else 
                    return false;
        }
        return false;
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


    public static int[] keys(int loop, int n) {
	Random rnd = new Random();	
	int[] indx = new int[loop];
	for (int i = 0; i < loop ; i++) {
	    indx[i] = rnd.nextInt(n*5);
	}	
	return indx;
    }


        public static void main(String[] arg) {
            
            int[] array = keys(100, 12);
            int key = 7; 


            int[] sortedarray = sorted(1000000);


            long t0 = System.nanoTime();
            search_sorted(sortedarray, key);
            long t1= System.nanoTime();
            long t = t1 - t0;


            

            System.out.println(t);
        }
}
