public class Linear {
    
    public static boolean searchunsorted(int[] array, int key) {
        for (int index = 0; index < array.length ; index++) {
        if (array[index] == key) {
        return true;
        }
        }
        return false;
        }
        public static boolean search_sorted(int[] array, int key){
            for(int index = 0; index < array.length; index++){
            if(array[index] == key)
                return true;
                if(array[index] > key)   
                   return false;    
        }    
        return false;
        } 

}
