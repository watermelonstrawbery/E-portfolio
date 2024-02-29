import java.util.Arrays;

public class QuickSort{


    public static void quickSort(int[] sequence, int start, int end){


        if(end <= start)//base case
           return;

        int mid = partition(sequence, start, end);   
        quickSort(sequence, start, mid - 1);
        quickSort(sequence, mid + 1, end);

    }


    public static int  partition(int[] sequence, int start, int end){
        
        int pivot = sequence[start];

        int i = start;
        int j = end;
        for(; j > i; j--){
            if(sequence[j] <= pivot){
               i++;
               int temp = sequence[i];  
               sequence[i] = sequence[j];
               sequence[j] = temp;
               j++;        
            }     
        }
        
        int temp = sequence[i];
        sequence[i] = pivot;
        sequence[start] = temp;
 

        return i;
    }

    public static void main(String[] args) {
        
        int[] sequence = {4,6,3,2, 1, 8 ,12, 0, 30};

        quickSort(sequence, 0, sequence.length - 1);

        System.out.println(Arrays.toString(sequence));
    }

}