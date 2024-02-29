import java.util.Arrays;
import java.util.Random;

public class QuickSortBenchmark {

    public static void main(String[] args) {
        int tries = 20; // Number of times to run the sort for benchmarking
        int n = 3200;  // Size of the array to sort
        int[] sequence = new int[n];
        double[] time = new double[tries];
        double totalExecutionTime = 0.0;

        Random rnd = new Random();

        for (int i = 0; i < tries; i++) {
            // Initialize the array with random values
            for (int j = 0; j < n; j++) {
                sequence[j] = rnd.nextInt(n * 10);
            }

            // Make a copy of the original sequence to sort
            int[] copy = Arrays.copyOf(sequence, sequence.length);

            // Record the start time
            long startTime = System.nanoTime();

            // Call the quickSort function to sort the copy
            QuickSort.quickSort(copy, 0, copy.length - 1);

            // Record the end time
            long endTime = System.nanoTime();

            // Calculate and store the elapsed time in microseconds
            time[i] = (endTime - startTime) / 1000.0;
            
            totalExecutionTime += time[i];
        }

        // Calculate the average time
        double averageTime = totalExecutionTime / tries;

        // Print the sorted array (for verification)
        System.out.println("Sorted array: " + Arrays.toString(sequence));

        // Print the benchmark results
        for (int i = 0; i < tries; i++) {
            System.out.println("Time taken for attempt " + (i + 1) + ": " + time[i] + " microseconds");
        }

        // Print the average time
        System.out.println("Average time for " + tries + " tries: " + averageTime + " microseconds");
    }
}
