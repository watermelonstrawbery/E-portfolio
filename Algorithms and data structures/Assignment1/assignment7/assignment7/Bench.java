import java.util.Random;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;

public class Bench {


    public static void main(String[] args) {
        benchList();
        //benchtreeheap();
        //bencharrayheap();

    }
    
    public static int[] unsorted(int n) {
        Random rnd = new Random();	
        int[] array = new int[n];
    
        for (int i = 0; i < n ; i++) {
            int nxt = rnd.nextInt(10000);
            array[i] = nxt;
        }
        return array;
        }

    public static PrioQueue generateList( PrioQueue list, int length){
      
       int[] sequence = unsorted(length);
           for (int i = 0; i < sequence.length; i++) {
            list.add1(i); 
           }
           return list;
    }

    public static Heap generateTreeHeap(Heap treeheap, int length){
        
       int[] sequence = unsorted(length);
           for (int i = 0; i < sequence.length; i++) {
            treeheap.add(i); 
           }
           return treeheap;
    }

    public static void benchList(){
       
        Random rnd = new Random();
        int[] sizes ={100, 200, 400, 800, 1600, 3200};
        int range = 10000;
        
                
        for(int n : sizes){

           PrioQueue list1 = new PrioQueue();
         

           double minadd1 = Double.POSITIVE_INFINITY; 
           double minremove1 = Double.POSITIVE_INFINITY; 

           double minadd2 = Double.POSITIVE_INFINITY; 
           double minremove2 = Double.POSITIVE_INFINITY; 

           for (int i = 0; i < 10; i++) {
            //add1
            long startadd1 = System.nanoTime();
            for (int j = 0; j < n; j++) {
                int newitem = rnd.nextInt(range);
                list1.add1(newitem);
                
            }
            long endadd1 = System.nanoTime();
            long add1time = endadd1 - startadd1;
            if( add1time < minadd1)
              minadd1 = add1time;


            //remove1
            long startremove1 = System.nanoTime();
                list1.remove1();
            long endremove1 = System.nanoTime();
            long remove1time = endremove1 - startremove1;
            if( remove1time < minremove1)
              minremove1 = remove1time;
            
             //add2
            long startadd2 = System.nanoTime();
            for (int j = 0; j < n; j++) {
                int newitem = rnd.nextInt(range);
                list1.add2(newitem);
                
            }
            long endadd2 = System.nanoTime();
            long add2time = endadd2 - startadd2;
            if( add2time < minadd2)
              minadd2 = add2time;
            
            //remove1
            long startremove2 = System.nanoTime();
                list1.remove2();
            long endremove2 = System.nanoTime();
            long remove2time = endremove2 - startremove2;
            if( remove2time < minremove2)
              minremove2 = remove2time;

           }
           
           System.out.printf("| %10d | %14.2f | %13.2f | %16.2f | %18.2f |%n", n, minadd1 / 1000.0,
					minremove1 / 1000.0, minadd2 / 1000.0, minremove2 / 1000.0);
        }
    

    }
 

    public static void benchtreeheap(){
     
        Random rnd = new Random();
        int[] sizes ={1023, 100, 200, 400, 800, 1600, 3200};
        int heapsize = 1023;
        int range = 10000;
        int pushRange = 100;

        
        for( int n : sizes){

        Heap heap = new Heap();
        Heap heap2 =new Heap();
        //heap = generateTreeHeap(heap, sizes[n]); 

        
        double minheap2 = Double.POSITIVE_INFINITY; 
        double minpush = Double.POSITIVE_INFINITY;

        for (int i = 0; i < heapsize; i++) {       
            int item = rnd.nextInt(range);
            heap.add(item);            
        }

        int depth = -1;
        for (int i = 0; i < 100; i++) {

            long startpush = System.nanoTime();    
                       
                int incr = rnd.nextInt(pushRange) + 10;
                depth = heap.push(incr);

            long endpush = System.nanoTime();
            double pushtime = endpush - startpush;
            if(pushtime < minpush)
                minpush = pushtime;
        
            
				long heap2start = System.nanoTime();
				for (int k = 0; k < n; k++) {
					int val = heap2.remove();
					int incr2 = val + rnd.nextInt(pushRange) + 10;
					heap2.add(incr2);
				}
				long heap2end = System.nanoTime();
				double heap2time = heap2end - heap2start;
				if (heap2time < minheap2) {
					minheap2 = heap2time;
				}    

        }
        System.out.printf("%d ..... %.1f ..... %.1f ..... %.1f%n", n, (double)depth, (double)(minpush / 1000.0), (double)(minheap2 / 1000.0));
        

        }

    }

    public static void bencharrayheap(){
        Random rand = new Random();
		int[] arraySizes = {100,200,400,800,1600,32000};
		int element_range = 5001;
		
		for(int n : arraySizes) {
			ArrayHeap heap = new ArrayHeap(n);
			
			double minAddTime = Double.POSITIVE_INFINITY;
			double minRemoveTime = Double.POSITIVE_INFINITY;
			
			for (int j = 0; j < 100; j++) {
				
				long addStartTime = System.nanoTime();
				for(int i = 0; i < n; i++) {
					int randomItem = rand.nextInt(element_range);
					heap.add(randomItem);
				}
				long addEndTime = System.nanoTime();
				double addTotalTime = addEndTime - addStartTime;
				if(addTotalTime < minAddTime) {
					minAddTime = addTotalTime;
				}
				
				
				long removeStartTime = System.nanoTime();
				for (int k = 0; k < n ; k++) {
					heap.remove();
				}
				long removeEndTime = System.nanoTime();
				double removeTotalTime = removeEndTime - removeStartTime;
				if(removeTotalTime < minRemoveTime) {
					minRemoveTime = removeTotalTime;
				}	
			}
			System.out.printf("%d ..... %.1f ..... %.1f%n", n, (double)(minAddTime / 1000.0), (double)(minRemoveTime / 1000.0));
		}	

    }
    
    }

