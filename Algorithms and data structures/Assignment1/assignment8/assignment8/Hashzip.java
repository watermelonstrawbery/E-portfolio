import java.io.BufferedReader;
import java.io.FileReader;

public class Hashzip{

    Node[] data;
    int max;
    Integer[] keys; 

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
    
    public Hashzip(String file){

        data = new Node[100000];
        keys = new Integer[9675];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            String[] row = line.split(",");
            Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
            keys[i++] = code;
            data[i] = new Node(code, row[1], Integer.valueOf(row[2]));
        }
        max = i - 1;
        } catch (Exception e) {
    System.out.println(" file " + file + " not found");
    }
    }

    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[10];

        for (int i = 0; i < max; i++) {
            Integer index = keys[i] % mod;
            cols[data[index]]++;
            data[index]++;
        }
        System.out.print(mod);
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }        

    public String lookup(Integer key){
        if(data[key] == null)
            return null;
        return data[key].name;    
    }



    public static void main(String[] args) {
        
        Hashzip zipdata = new Hashzip("postnummer.csv");
       
        int[] sizes = {10000, 20000, 12345, 13513, 13600, 14000};
        
        System.out.println("Mod\tZero\tOne\tTwo\tThree\tFour\tFive\tSix\tSeven\tEight\tNine");

        for(int mod : sizes){
            zipdata.collisions(mod);
        }
    }



}