import java.util.Random;
public class QuickList {
    
    Node first;
    Node last;

    private class Node {
        Integer item;
        Node next;
    private Node(Integer item, Node list) {
       this.item = item;
       this.next = list;
    }
    }

    public QuickList(){
        this.first = null;
        this.last = null;
    }

    public QuickList(int[] sequence ){
       if(sequence.length == 0){
        this.first = null;
        this.last = null;
       } else{
        this.first = new Node(sequence[0], null);
        Node cur = this.first;
        for(int i = 1; i< sequence.length; i++){
            cur.next = new Node(sequence[i], null);
            cur = cur.next;
        }
        this.last = cur;
       }
    }

    public void add(int item){
        Node node = new Node(item, this.first);
        if(this.last == null)
           this.last = node;
        this.first = node;   
    }

    public void append(QuickList tail){
        if(tail != null){
            if(this.last != null)
               this.last.next = tail.first;
            else
               this.first = tail.first;
            if(tail.last != null)
              this.last = tail.last;      
        }
    }

    public void preappend(QuickList front){
        if(front != null){
            if(front.last != null)
              front.last.next = this.first;
        if(this.last == null)
           this.last = front.last;
        if(front.first != null)
           this.first = front.first;
        front.first = null;
        front.last = null;      
        }
    }

    private void cons(Node node){
        if(this.last == null)
          this.last = node;
        node.next = this.first;
        this.first = node;  
    }

    public void sort(){
        if(this.first == null || this.first.next == null)
        return;

        QuickList smaller = new QuickList();
        QuickList larger = new QuickList();

        Node pivot = this.first;
        Node cur = pivot.next;
        pivot.next = null;

        //pivot is now the only element in this linked list
        this.last = pivot;

        int p = pivot.item;

        //This is the partition operation
        while(cur != null){
            Node nxt = cur.next;
            if(p > cur.item){
                smaller.cons(cur);    
            }else{
                larger.cons(cur);
            }
            cur = nxt;
        }

        smaller.sort();
        larger.sort();
        //this holds pivot
        this.append(larger);
        this.preappend(smaller);
    }

    public static void main(String[] args) {
       
        int tries = 20;
        Random rnd = new Random();
        int n = 1000;
        int[] sequence = new int[n];

        for(int i = 0; i < n; i++){
            sequence[i] = rnd.nextInt(n*10);
        }

        double[] time = new double[tries];

        QuickList qlist = new QuickList(sequence);
        
        for(int i = 0; i < tries; i++){
            double start = System.nanoTime();
            qlist.sort();
            double t = System.nanoTime() - start;

            time[i] = t;
             System.out.print((time[i])/1000+ "  ");
        }

       
    }
}
