public class Selectionsort{

    public static void selectionSort(int[] array){


    for (int i = 0; i < array.length -1; i++) {
         int candidate = array[i];

         for (int j = i; j < array.length ; j++){
             

            
            if(array[j] < candidate){
               int s = array[j];
               array[j] = candidate;
               candidate = s;
               }            
            }             
        }
    }
}