public class Bench {

    public static void bench(int loop, int ops, Stack stack){
       
        
        for(int i = 0; i < loop; i++){
            
           
            for(int n = 0; n < ops; n++){
                stack.push(n);
            }
            for(int n = 0; n < ops; n++){
            stack.pop();
            }
    
    }
}
}
