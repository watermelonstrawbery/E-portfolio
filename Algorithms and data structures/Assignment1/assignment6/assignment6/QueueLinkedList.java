public class QueueLinkedList {
        Node first;
        Node last;

    private class Node {
        Integer item;
        Node next;
    private Node(Integer item, Node list) {
       this.item = item;
       this.next = list;
    }
    private Node (int item){

        this.item = item;
        this.next = null;
     } 
    }

    public QueueLinkedList() {
        this.first = null;
        this.last = null;
    }

    public void addV(int item){

        Node newitem = new Node(item);    
            if(first == null)
            first = newitem;
            else{
                Node current = first;
                while(current.next != null){
    
                    current = current.next;
                }
                current.next = newitem;
            }
    
    }
    public void add(Integer item) {
        Node newnode = new Node(item, null);
        if(last != null)
          last.next = newnode;
        if(first == null)
        first = newnode;  
    }
    public Integer remove() {

        if(first == null)
        return null;
        Integer result = first.item;
        first = first.next;
        return result;
        }



        public static void main(String[] args) {

            QueueLinkedList sequence = new QueueLinkedList();

            int n =  100;
            
            double min = Double.POSITIVE_INFINITY;
            int k = 10;
    
            for(int j = 0;j < k; j++){
        
            long t0 = System.nanoTime();
            
            for (int i = 0; i < n; i++){
                 sequence.add(i);
            }
            
            for (int i = 0; i < n; i++){
                 sequence.remove();
            }
            
            long t1 = System.nanoTime();
            double t = (t1 - t0);
            if(t<min)
                min = t;   
            }
           
    
            System.out.print(min/1000);
        }
}
