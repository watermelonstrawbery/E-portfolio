public class Stack {
    
    private LinkedList Stack; //importing linkedlist classen in till den här stack klassen

    public void push(int n){
        Stack.add(n);
    }

    public int pop(){
       return  Stack.removefirst();
    }

}
