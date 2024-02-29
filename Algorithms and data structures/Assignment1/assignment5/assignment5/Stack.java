
/* 
public class Stack<Node> extends LinkedList {
    
    private LinkedList stack; //importing linkedlist classen in till den här stack klassen

    
    public Stack(){

        stack = new LinkedList();
    }
    
    public void push(Node n){
        stack.add(n);
    }

    public int popout(){
       return  stack.pop();
    }


  public static void main(String [] args){

    BinaryTree.Node newnode = new BinaryTree.Node(3,5);
    BinaryTree.Node newnode1 = new BinaryTree.Node(6,2);
    BinaryTree.Node newnode2 = new BinaryTree.Node(4,8);
    BinaryTree.Node newnode3 = new BinaryTree.Node(7,3);

    Stack stack = new Stack();
    stack.add(newnode);
    stack.add(newnode1);
    stack.add(newnode2);
    stack.add(newnode3);

    

    System.out.println(stack.popout());
   

    
  }

}
*/