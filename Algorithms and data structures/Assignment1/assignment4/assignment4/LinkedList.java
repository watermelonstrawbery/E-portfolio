public class LinkedList{
  
    public Cell first;

    public class Cell{
      int head;
      Cell tail;

    Cell(int val, Cell tl){
        head = val;
        tail = tl;
      }
    }
     
    public LinkedList(){
         first = null;

    }

    public LinkedList(int n) {
      Cell last = null;
      for (int i = 0; i < n; i++) {
      last = new Cell(i, last);
      }
      first = last;
      }


    public void add(int item){
         Cell newitem = new Cell(item, first);
         newitem.tail = first;
         first = newitem;

    }  


    public int length(){

        Cell begin = first;
        int counter = 0;
        while(begin != null){
            counter++;
            begin = begin.tail;
        }
    return counter;
    }


    public boolean find(int item){
      Cell current = first;
      while(current != null){
      if(current.head == item)
        return true;
        else
        current = current.tail;
      }
      return false;
    }
    

    public void remove(int item){
       Cell current = first;
       Cell prv = null;
       while(current != null){
       if(current.head == item){
       if(prv != null)
         prv.tail = current.tail;
        else 
         first = current.tail; 
         return; 
       }
       prv = current;
       current = current.tail;
      }
    
    }

    public int removefirst(){
       Cell current = first;
       Cell prv = null;
       int result = 0;
      while(current != null){
       if(prv == null){
        result = first.head;
        first = current.tail;
       }
       
      }
       return result;

    }
    
    public void append(LinkedList b) {
       

      if( this.first == null){
        this.first= b.first;
      }
      else{
        Cell nxt = this.first;
        while(nxt.tail != null){
          nxt = nxt.tail;
        }
        nxt.tail= b.first;
      }
      b.first = null;
      /* 
      Cell nxt = this.first;
        while (nxt.tail != null) {
              nxt = nxt.tail;
        }
        if(nxt.tail == null){
           nxt.tail = b.first;
           b.first = null;
          }
          */
      }

 
    
  public static void main(String[] arg) {

    
      int[] elements = {1000, 16000, 32000, 640000, 2560000};
      int element = 10000;

      for( int m : elements){

      LinkedList a = new LinkedList(element);
      LinkedList b = new LinkedList(m);
    
       int k = 100;
       double min = Double.POSITIVE_INFINITY;

       for (int i = 0; i < k; i++) {
         long t0 = System.nanoTime();
         a.append(b);
         long t1 = System.nanoTime();
         double t = (t1 - t0);
         if (t < min)
             min = t;
        }

    System.out.printf("%8.0f", (min/1000));	    
      }    	    
  }
}

  






  



