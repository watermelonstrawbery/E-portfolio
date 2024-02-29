import java.util.Random;


public class Bench {

    
    public static void main( String[] args){
   

       Random rnd = new Random();
       int range = 10000; 
       int[] sizes ={1000, 2000, 4000, 8000, 16000, 32000};

       for(int k : sizes){

        BinaryTree tree = new BinaryTree();

        for(int i = 0; i<k; i++){
            tree.add(rnd.nextInt(range), rnd.nextInt(range));
        }
        /* 
        for(int i : tree){
              System.out.println(i);
        }
       */


       double min = Double.POSITIVE_INFINITY;
       int m = 10;

       for(int j = 0; j < m; j++){
          
        long t0 = System.nanoTime();
        tree.lookup(rnd.nextInt(range));
        long t1 = System.nanoTime();
        double t = (t1 - t0);
        if(t < min)
           min = t;
        }
        System.out.printf("%8.0f", min);   
     }
    }
}

