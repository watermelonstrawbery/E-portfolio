public class StaticStack extends Stack {
    
    int[] mainstack;
    int pointer;
    int size;

public StaticStack(int size){
   mainstack = new int[size];
   this.size = size;
   pointer = 0;
}

public void push(int Value){

   if(pointer < size)
    { 
    mainstack[pointer] = Value;
    pointer++;
    }
}    

public int pop(){

    int x = 0;
    if(pointer != -1)
     x = mainstack[--pointer];   
     
return x;     
}
}

