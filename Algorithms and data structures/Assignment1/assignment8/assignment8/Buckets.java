import java.io.BufferedReader;
import java.io.FileReader;

public class Buckets{

    Node[] data;
    int mod = 15993;

    private class Node{

        private Integer code;
        private String name;
        private Integer pop;
        private Node next;

        private Node(Integer code, String name, Integer pop){
            this.code   = code;
            this.name   = name;
            this.pop    = pop;
            this.next   = null;
        }
    }

    public Buckets (String file) {
        data = new Node[mod];
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                Node entry = new Buckets.Node(code, row[1], Integer.valueOf(row[2]));
                insert (code , entry);
            }
        }
        catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private void insert (Integer code, Node entry){
        Integer key = code % mod;
        Node nxt = data[key];
        Node prv = null;

        while (nxt!= null){
            if (code.equals(nxt.code)){
                //replace the found entry
                nxt = nxt.next;
                break;
            }
            prv = nxt;
            nxt = nxt.next;
        }
        if (prv != null){
            prv.next = entry;
            entry.next = nxt;
        }
        else{
            data[key] = entry;
            entry.next = nxt;
        }
    }

    public int compares (Integer key){
        Integer indx = key % mod;
        Node nxt = data[indx];
        int cmp = 0;
        while (nxt != null){
            if (key.equals(nxt.code)){
                return cmp;
            }
            cmp++;
            nxt = nxt.next;
        }
        return 0;
    }

    public String lookup (Integer key){
        Integer indx = key % mod;
        Node nxt = data[indx];
        while (nxt != null){
            if(key.equals(nxt.code)){
                return nxt.name;
            }
            nxt = nxt.next;
        }
        return null;
    }

    public static void main(String[] arg){

        Buckets map = new Buckets ("postnummer.csv");
        int k = 100000;

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