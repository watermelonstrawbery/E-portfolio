import java.util.Stack;
import java.util.Iterator;


public class BinaryTree implements Iterable<Integer>{


    Node root;

    public BinaryTree(){
        root = null;
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
 
        private void add ( Integer key , Integer value ) {
            if ( this . key == key ) {
                 this . value = value ;
                return;
            }
            if ( this . key > key )
            if ( this . left != null )
                this . left . add ( key , value );
                else
                this . left = new Node ( key , value );

                if(this.key < key)
                if(this.right != null)
                  this.right.add(key, value);
                  else
                  this.right = new Node(key, value);
        }  

        
        public void print() {
            if(left != null)
            left.print();
            System.out.println(" key: " + key + "\tvalue: " + value);
            if(right != null)
            right.print();
            }      
    }

    

    public void add(int key, int value){
        if ( root == null )
            root = new Node ( key , value );
        else
            root.add ( key , value );      
    }
    
    public int lookup ( Integer key ) {

        Node cur = this . root ;
        while ( cur != null ) {
            if ( cur . key == key )
            return cur . value ;
            if ( cur . key < key )
            cur = cur . right ;
            else
            cur = cur . left ;
        }
        return -1;
    }



    public Iterator<Integer> iterator() {
        return new TreeIterator(root);    

    }
    public class TreeIterator implements Iterator<Integer> {


   
    //attributer
    private Node next;
    private Stack<Node> stack;

    public TreeIterator(Node root) {

        this.stack = new Stack<>();  //vi skapar en ny stack av typen node.
        this.next = root;
    }

  

    @Override
    public boolean hasNext() {
       if(!stack.isEmpty() || next != null){
        return true;
       }
       return false;
    }


    @Override
    public Integer next() {
       while( next != null){
           stack.push(next);
           next = next.left;
       }

       Node current = stack.pop();
       next = current.right;

       return current.key;  //returneras för att ha koll på vilka element vi har varit på

    }
    @Override
    public void remove() {
    throw new UnsupportedOperationException();
    }

    
    
}
    

}