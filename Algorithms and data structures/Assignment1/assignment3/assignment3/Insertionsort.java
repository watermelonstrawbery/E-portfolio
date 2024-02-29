public class Insertionsort {
    

    public static void swap(int[] array, int i, int j){
    
            if(i != j){
            int s = array[i];
            array[i] = array[j];
            array[j] = s;  
            } 
    }


       

    public static void insertionSort(int[] array){

        for (int i = 1; i < array.length; i++) {
            for (int j = i; j != -1 && array[i]<array[j] ; j--) {
                swap(array, i, j);
            }
            }
            
    }
}
