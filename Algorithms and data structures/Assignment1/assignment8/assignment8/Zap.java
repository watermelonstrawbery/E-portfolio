import java.io.BufferedReader;
import java.io.FileReader;

public class Zap {
    
    Node[] data;
    int max;

    public class Node {

    int code;
    String name;
    int pop;

    public Node(int code, String name, int pop){

        this.code = code;
        this.name = name;
        this.pop = pop;

      }

    }

    public Zap(String file){

        data = new Node[10000];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            String[] row = line.split(",");
            int code = Integer.valueOf(row[0].replaceAll("\\s",""));
            data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
        }
        max = i-1;
        } catch (Exception e) {
    System.out.println(" file " + file + " not found");
    }
    }

    
    public String linear(int zap){

        for (int i = 0; i < data.length; i++) {
            if(zap == (data[i]).code)
              return (data[i]).name;
        }
        return null;
    }

    public String binary(Integer zap){
        int mn = 0;
        int mx = max;

        while(true){
            int index = (mn + mx)/2;

            if(zap == data[index].code){
                return data[index].name;
            }

            if(zap > 0 && index < mx){
                mn = index +1;
                continue;
            }
            break;
        }
        return null;
    }

       
       public static void main(String[] args) {
        Zap zap = new Zap("postnummer.csv");

          
        int k = 1000;
        for (int i = 0; i < k; i++) {
            zap.linear(11115);
        }

        for (int i = 0; i < k; i++) {
            zap.binary(11115);
        }

        long t0 = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zap.linear(11115);
        }
        long t1 = System.nanoTime();
        System.out.println("linear 111 15:" + zap.linear(11115) + " "+ (t1 - t0)/k + "ms"); 

        long t2 = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zap.binary(11115);
        }
        long t3 = System.nanoTime();
        System.out.println("binary 111 15:" + zap.binary(11115) + " "+ (t3 - t2)/k + "ms"); 

       
    }

    
}
