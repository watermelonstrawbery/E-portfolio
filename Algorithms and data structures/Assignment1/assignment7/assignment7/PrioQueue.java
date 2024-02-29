public class PrioQueue{
    Node root;

    private class Node{

        int item;
        Node next;

        private Node(int item){
            this.item = item;
            this.next = null;

        }
    }

    public PrioQueue(){

        root = null;
    }

    //O(1), unsorted list, just add the value to the beginning
    public void add1(int item){

        Node newnode = new Node(item);

        newnode.next = root;
        root = newnode;
        /*if(root == null)
           root = new Node(item);
        else{
            Node newnode = new Node(item);
            newnode.next = root;
            root = newnode;
        }
        */   
    }
    
    //O(n), find the smallest element and remove it
    public int remove1(){
        if(root == null)
           throw new IndexOutOfBoundsException(null);
        else{
            Node min = root; 
            Node current = root;
            Node prv = null;
            while(current.next != null){
                if(current.next.item < min.item){
                    min = current.next;
                    prv = current;

                } 
                current = current.next;       
            }
            if(min == root){
              root = root.next;
            }else{
                prv.next = min.next; //min.next eftersom current kommer gå igenom hela listan men min
            }                        //stannar på minsta elementet.
            return min.item;
        }
    }
    
    //O(n), the list is sorted and the value should be inserted at the right spot
    public void add2(int item){

        Node newnode = new Node(item);

        if(root == null || item < root.item){
          newnode.next = root;
          root = newnode;
          return; //end the program
        }
        else{
            Node current = root;
            while( current.next != null && item > current.next.item){
                if(newnode.item > current.item){
                current = current.next;
                }
            }
                          
                newnode.next = current.next;
                current.next = newnode;            
        }  
    }


    //O(1), eftersom listan är sorterad tar vi bort första elementet
    public int remove2(){
        int result = root.item;

        if(root == null)
           return -1;
        else{          
           root = root.next;   
           }
    return result;
    }

   public static void main(String[] args) {
    PrioQueue heap = new PrioQueue();

    heap.add2(23);
    heap.add2(12);
    heap.add2(20);
    heap.add2(19);
    heap.add2(28);
    heap.add2(14);
    heap.add2(32);
    heap.add2(10);
    heap.add2(38);
    heap.add2(41);

    System.out.println(heap.remove2());
   

     
   }


}