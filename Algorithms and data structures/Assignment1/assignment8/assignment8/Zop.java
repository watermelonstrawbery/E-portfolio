import java.io.BufferedReader;
import java.io.FileReader;

public class Zop {

    Node[] data;
    Integer max;

    public class Node {

    Integer key;
    String name;
    int pop;

    public Node(Integer key, String name, int pop){

        this.key = key;
        this.name = name;
        this.pop = pop;

      }
    }  
    
    public Zop(String file){

        data = new Node[100000];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] row = line.split(",");
            Integer key = Integer.valueOf(row[0].replaceAll("\\s",""));
            data[key] = new Node(key, row[1], Integer.valueOf(row[2]));
        }
        } catch (Exception e) {
    System.out.println(" file " + file + " not found");
    }
    }

    public String lookup(Integer key){
        if(data[key] == null)
            return null;
        return data[key].name;    
    }


    public static void main(String[] args) {
        Zop zop = new Zop("postnummer.csv");

          
        int k = 1000;
        for (int i = 0; i < k; i++) {
            zop.lookup(11115);
        }

        for (int i = 0; i < k; i++) {
            zop.lookup(98499);
        }

    
        long t0 = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zop.lookup(11115);
        }
        long t1 = System.nanoTime();
        System.out.println("lookup 111 15:" + zop.lookup(11115) + " "+ (t1 - t0)/k + "ms"); 
        
          long t2 = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zop.lookup(98499);
        }
        long t3 = System.nanoTime();
        System.out.println("lookup 111 15:" + zop.lookup(98499) + " "+ (t3 - t2)/k + "ms"); 

       
    }

}