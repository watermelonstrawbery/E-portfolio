import java.util.Random;

public class QuickListBenchmark {
    public static void main(String[] args) {
        int tries = 20; // Number of times to run the sorting
        Random rnd = new Random();
        int n = 32000; // Adjust the size of the input data as needed

        double[] time = new double[tries];

        for (int i = 0; i < tries; i++) {
            int[] sequence = new int[n];

            // Generate random input data
            for (int j = 0; j < n; j++) {
                sequence[j] = rnd.nextInt(n * 10);
            }

            QuickList qlist = new QuickList(sequence);

            double start = System.nanoTime();
            qlist.sort();
            double t = (System.nanoTime() - start) ; 

            time[i] = t;
            System.out.print(t/1000 + "micro seconds  ");
        }

        // Calculate and print the average time
        double sum = 0.0;
        for (double t : time) {
            sum += t;
        }
        double averageTime = sum / tries;
        System.out.println("\nAverage Time: " + averageTime/1000 + "micro s");
    }
}

