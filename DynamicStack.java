public class DynamicStack extends Stack{
    
    int[] dynstack;
    int pointer;
    final int SIZE;

public DynamicStack(){
   SIZE = 4;
   dynstack = new int[SIZE];
   pointer = 0;
}

public void push(int Value){

   if(pointer == SIZE)
    {
    int[] newstack = new int[SIZE * 2];
       for(int i = 0; i < SIZE; i++)
        {
          newstack[i]=dynstack[i];
        }
    dynstack = newstack;
    
    }
    dynstack[pointer++] = Value;
     
}    


public int pop(){
    
    
    if((SIZE - pointer) == (SIZE / 2 )){
        int[] newstack = new int[SIZE-(SIZE/4)];
        for(int i = 0; i< SIZE / 2; i++)
        {
          newstack[i]=dynstack[i];
        }
    dynstack = newstack;
    }

    pointer--;
    return dynstack[pointer];  
}

}
