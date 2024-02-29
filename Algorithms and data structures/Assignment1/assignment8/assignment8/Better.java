import java.io.BufferedReader;
import java.io.FileReader;

public class Better {

    Node[] data;
    int mod = 15993;
    
    private class Node{
        private Integer code;
        private String name;
        private Integer pop;


        private Node(Integer code, String name, Integer pop){
            this.code = code;
            this.name = name;
            this.pop = pop;
        }
    }

    public Better (String file) {

        data = new Node[20000];
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                Node entry = new Better.Node(code, row[1], Integer.valueOf(row[2]));
                insert (code,entry);
            }
        }
        catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private void insert (Integer code, Node entry){
        Integer key = code % mod;
        while (data[key]!= null){
            key++;
        }
        data[key] = entry;
    }

    public int compares (Integer key){
        Integer indx = key % mod;
        int cmp = 0;
        while (data[indx] != null){
            if (key.equals(data[indx].code)){
                return cmp;
            }
            cmp++;
            indx++;
        }
        return 0;
    }

    public String lookup (Integer key){
        Integer indx = key % mod;
        while (data[indx] != null){
            if(key.equals(data[indx].code)){
                return data[indx].name;
            }
            indx++;
        }
        return null;
    }

    public static void main(String[] arg){
       // int mod = Integer.valueOf(arg[0]);
        Better map = new Better ("postnummer.csv");
        int k = 1000;
        // warm up
        for (int i = 0; i < k*100; i++){
            map.lookup(11115);
        }
        

        double t0 = System.nanoTime();
        for (int i = 0; i < k; i++){
            map.lookup(11115);
        }
        double t1 = System.nanoTime();

        System.out.println("lookup 111 15: " + map.lookup(11115)+ " (" + map.compares(11115)+ ") " + (t1-t0)/k + " ns");

        t0 = System.nanoTime();
        for (int i = 0; i < k; i++){
            map.lookup(44391);
        }
        t1 = System.nanoTime();

        System.out.println("lookup 443 91: " + map.lookup(44391)+ " (" + map.compares(44391)+ ") " + (t1-t0)/k + " ns");

        t0 = System.nanoTime();
        for (int i = 0; i < k; i++){
            map.lookup(98599);
        }
        t1 = System.nanoTime();

        System.out.println("lookup 984 99: " + map.lookup(98499)+ " (" + map.compares(98499)+ ") " + (t1-t0)/k + " ns");

        t0 = System.nanoTime();
        for (int i = 0; i < k; i++){
            map.lookup(99978);
        }
        t1 = System.nanoTime();

        System.out.println("lookup 999 78: " + map.lookup(99978)+ " (" + map.compares(99978)+ ") " + (t1-t0)/k + " ns");
    }
}

