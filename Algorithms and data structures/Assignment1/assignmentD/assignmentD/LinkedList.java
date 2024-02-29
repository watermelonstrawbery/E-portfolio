public class LinkedList{
  
    public static Cell first;

    public class Cell{
      Node head;
      Cell tail;

    Cell(Node val, Cell tl){
        head = val;
        tail = tl;
      }
    }

    public class Node{
      public int key;
      public int value;
      public Node left, right;  

      public Node(int key, int value){
         this.key = key;
         this.value = value;
         this.left = this.right = null; 
      }
    }
    public LinkedList(){
         first = null;

    }

    public LinkedList(Node node) {
      Cell last = null;
      for (int i = 0; i < n; i++) {
      last = new Cell(i, last);
      }
      first = last;
      }

 /*
    public Cell add(int item){
         Cell newcell = new Cell(item, first);
         first = newcell;;
        
         return newcell;
    
    }  
 */
    public void add(Node node){

      Cell newelement = new Cell(node, first);
      first = newelement;
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
/* 
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
*/
   public Node pop(){
    if(first == null)
       throw new IllegalStateException("Stack is empty.");

       Node n = first.head;
       first = first.tail;
       return n;
   }


 
    public static void unlink(Cell cell){

        Cell current = first;
        Cell prv = null;
        while( current != null){
              if(current == cell){
                if(prv != null)
                  prv.tail= current.tail;
                else
                  first = cell;
              }
        prv = current;
        current = current.tail;
        }
    }

    public static void insert(Cell cell){
         

        if(first != null){                 
           cell.tail = first;
           first = cell; 
        } else{
          first = cell;
          cell.tail = null; 
        }   
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

 
    
 
}


  






  




