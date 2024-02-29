import java.util.Random;

public class Heap {
    
    Node root;

    public Heap(){

      root = null;
    }
    
    private class Node{

        int prio;
        Node left, right;

        private Node(int p){
            this.prio = p;
            this.left = null;
            this.right = null;

        }

         private void add(int pr){
          
           if(pr < prio){
            int tmp = prio;
            prio = pr;
            pr = tmp;
           }

            if(right != null){
                Node tmp = left;
                right.add(pr);
                left = right;
                right = tmp;
            }else
                right = new Node(pr);

            }


            private Node remove(){
              
              if(right == null)
                  return left;
                else if(left == null)
                       return right;
                       else if(left.prio < right.prio){
                              prio = left.prio;
                              left = left.remove();
                            }else{
                              prio = right.prio;
                              right = right.remove();                                   
                            }
                return this;
            } 
            }
        

        public void add(int pr){

        if(root == null)
          root = new Node(pr);
        else
           root.add(pr);  
        }

    public int remove(){

      if(root == null)
        return -1;

      int result = root.prio;
      root.remove();
      return result;
    }
    
    public int push(int incr){      
      if(root == null){
        root = new Node(incr);
        return 0;
      }

      int newvalue = root.prio + incr;
      int depth = 0;

      if(root.left == null && root.right == null)
        root.prio = newvalue;
        else{
         Node prv = null;
         Node current = root;
        
       
    while((current.left != null && current.left.prio < newvalue) || (current.right != null && current.right.prio < newvalue)){
         if(current.right != null &&(current.left == null ||  current.left.prio >= current.right.prio)){
          prv = current;
          current = current.right;
         } else {
           prv = current;
           current = current.left;
         }
         //swap
         prv.prio = current.prio;
         current.prio = newvalue;
         depth++;

       }
     }
    return depth; 

    }
   
}
