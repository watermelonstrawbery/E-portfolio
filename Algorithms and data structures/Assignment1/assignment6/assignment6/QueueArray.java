public class QueueArray {
    
    int[] queue;
    int first, last;
    int size;
    
    public QueueArray(){

        size = 4;
        queue = new int[size];

        first = 0;
        last = 0;
    }


    public boolean empty(){
      
      return first == last;
    }

    public void enqueue(int item){
     
       queue[last] = item;
       last = (last + 1) % size;
        if(last == first){
           int[] copy = new int[size * 2];
           int c = 0;
        for(int i = first; i < size; i++){
            copy[c] = queue[i];
            c++;   
        }
        for(int i = 0; i < last; i++){
            copy[c] = queue[i];
            c++;
        }

        size = size *2;
        first = 0;
        last = c;
        queue = copy;

       }
       }
    

    public int dequeue(){
        if(first == last){
            return -1;
        }
        int result = queue[first];
        first = (first + 1) % size;
        return result;
    }

    public static void main(String[] args) {
        
        QueueArray sequence = new QueueArray();

        int n = 100;

        double min = Double.POSITIVE_INFINITY;
        int k = 10;

        for(int j = 0;j < k; j++){

        long t0 = System.nanoTime();
        
        for (int i = 0; i < n; i++){
             sequence.enqueue(i);
        }
        
        for (int i = 0; i < n; i++){
             sequence.dequeue();
        }
        
        long t1 = System.nanoTime();
        double t = (t1 - t0);
        if(t<min)
            min = t;   
        }
       

        System.out.print(min);
    }
   

}