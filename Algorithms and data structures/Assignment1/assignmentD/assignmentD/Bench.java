import java.util.Random;

public class Bench {
    
    
    public static int[] unsorted(int n, int k) {
	Random rnd = new Random();	
	int[] array = new int[k];

	for (int i = 0; i < k ; i++) {
	    int nxt = rnd.nextInt(n);
		array[i] = nxt;
    }
	return array;
    }

    public static void main(String[] arg) {


        int keys = 1000;
        

        int[] arraysize = {1000, 10000, 100000, 1000000};
        
        System.out.printf("#%7s%8s%8s\n", "arraysize", "simple", "doubly");

        for( int n : arraysize){
        
        
            
        int[] randomvalues = unsorted(n, keys);
        Cell[] cellarraydouble = new Cell[n];
        LinkedList.Cell[] cellarraysimple = new LinkedList.Cell[n];
        
        

        LinkedList simpleLinkedList = new LinkedList();
        Doubly doublyLinkedList = new Doubly();
        

        for(int i=0; i < n; i++){

           LinkedList.Cell cellsimple = simpleLinkedList.add(i);
           Cell celldoubly = doublyLinkedList.add(i);
           
           cellarraysimple[i] = cellsimple;
           cellarraydouble[i] = celldoubly;
        }
      
        
         int k = 10;
         double minsimple = Double.POSITIVE_INFINITY;
         

         for (int i = 0; i < k; i++) {
            long t0 = System.nanoTime();
            for(int m = 1 ; m < randomvalues.length; m++){
                
                int random = randomvalues[m];

                LinkedList.unlink(cellarraysimple[random]);
                LinkedList.insert(cellarraysimple[random]);
            }
          


           long t1 = System.nanoTime();
           double t = (t1 - t0);
           if (t < minsimple)
               minsimple = t;          
        }
       System.out.printf("%8.0f", (minsimple/1000));


           double mindouble = Double.POSITIVE_INFINITY;

           for (int i = 0; i < k; i++) {
             long t0 = System.nanoTime();
            for(int m = 0 ; m < randomvalues.length; m++){
                 
                int random = randomvalues[m];
          

                Doubly.unlink(cellarraydouble[random]);
                Doubly.insert(cellarraydouble[random]);
            }
           long t1 = System.nanoTime();
           double t = (t1 - t0);
           if (t < mindouble)
               mindouble = t;
            
          }
  
      System.out.printf("%8.0f", (mindouble/1000));
      
    

        }  
    }  	    
    }
  

