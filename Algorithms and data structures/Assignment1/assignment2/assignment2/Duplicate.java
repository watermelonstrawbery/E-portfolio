

public class Duplicate {
 
    public static int duplicateUnsorted(int[] unsortedArray1, int[] unsortedArray2){
        int counter = 0;
        for(int i = 0; i < unsortedArray1.length; i++){
            for(int n = 0; n < unsortedArray2.length ; n++){
                if( unsortedArray1[i] == unsortedArray2[n])
                  counter++;;
                  
            }
            continue;
        }
        return counter;
    }

    public static int duplicateSorted(int[] sortedArray1, int[] sortedArray2){
        
        int counter = 0; 
        for(int i = 0; i < sortedArray1.length; i++){
            for(int n = 0; n < sortedArray2.length ; n++){
                if(sortedArray2[n] >= sortedArray1[i]){
                if(sortedArray2[n] == sortedArray1[i]) 
                   counter++;                    
                } 
            }
        }
    return counter;
    }

    public static int duplicateBinary(int[] sortedArray1, int[] sortedArray2){
         
        int counter = 0;
        for(int i = 0; i < sortedArray1.length; i++){
            if(Binary.search(sortedArray2, sortedArray1[i]))
               {
               counter++;
               }                     
               }
        
               return counter;
        }    
    
        
     public static int betterDuplicate(int[] sortedArray1, int[] sortedArray2){

        int i = 0;
        int n = 0;
        int counter = 0;

       while(i < sortedArray1.length && n < sortedArray2.length){

                
            
                if(sortedArray2[n] == sortedArray1[i])
                {  
                  counter++;
                  i++;
                  n++;
                }
                else if(sortedArray2[n] > sortedArray1[i])
                {
                    i++;
                }
                else if(sortedArray2[n] < sortedArray1[i])  
                {
                    n++;
                }
        }
        return counter;
    }
}



