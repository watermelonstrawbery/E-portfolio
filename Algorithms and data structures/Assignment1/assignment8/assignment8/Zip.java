import java.io.BufferedReader;
import java.io.FileReader;


public class Zip {

    Node[] data;
    Integer max;

    public class Node {

    String code;
    String name;
    Integer pop;

    public Node(String code, String name, Integer pop){
        this.code = code; 
        this.name = name;
        this.pop = pop;
    }

    }

    public Zip(String file) {
        data = new Node[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        Integer i = 0;
        while ((line = br.readLine()) != null) {
            String[] row = line.split(",");
            data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
        }
        max = i-1;
        } catch (Exception e) {
    System.out.println(" file " + file + " not found");
    }
    }

    public String linear(String zip){

        for (int i = 0; i < data.length; i++) {
            if(zip.equals((data[i]).code)){
              return (data[i]).name;
            }
        }
        return null;
    }

    public String binary(String zip){
        int mn = 0;
        int mx = max;

        while(true){
            int index = (mn + mx)/2;

            int cmp = zip.compareTo(data[index].code);
            if(cmp == 0){
                return data[index].name;
            }

            if(cmp > 0 && index < mx){
                mn = index +1;
                continue;
            }
            break;
        }
        return null;
    }
   

/*     
    public String binary(String zip){
        
        int start = 0;
        int end = max;

        while(start <= end){
        int mid = (start + end)/2;

        if(zip.equals(data[mid].code))
         return data[mid].name;

        if(data[mid].code.compareTo(zip) < 0) 
           start = mid +1;
        else
           end = mid -1;   
        }
        return null;
       }
*/    

    public static void main(String[] args) {
        Zip zip = new Zip("postnummer.csv");

          
        int k = 1000;
        for (int i = 0; i < k; i++) {
            zip.linear("111 15");
        }

        for (int i = 0; i < k; i++) {
            zip.binary("111 15");
        }

        long t0 = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zip.linear("111 15");
        }
        long t1 = System.nanoTime();
        System.out.println("linear 111 15:" + zip.linear("111 15") + " "+ (t1 - t0)/k + "ms"); 

        long t2 = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zip.binary("111 15");
        }
        long t3 = System.nanoTime();
        System.out.println("binary 111 15:" + zip.binary("111 15") + " "+ (t3 - t2)/k + "ms"); 
       

       
    }

    
}