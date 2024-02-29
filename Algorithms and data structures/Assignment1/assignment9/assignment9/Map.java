import java.io.BufferedReader;
import java.io.FileReader;

public class Map {

    private City[] cities;
    private final int mod = 541;
    private int size;

    public Map(String file) {

        cities = new City[mod];
        size = 0;
        
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
        i++;
        String[] row = line.split(",");
        City one = lookup(row[0]);
        City two = lookup(row[1]);
        Integer dist = Integer.valueOf(row[2]);
        one.connect(two, dist);
        two.connect(one, dist);
        }

        } catch (Exception e) {
            System.out.println(" file " + file + " not found or corrupt");
        }
    }

    private Integer hash(String name){
        return Math.floorMod(name.hashCode(), mod);
    }


    public City lookup(String name){
        Integer indx = hash(name);

        while(true){
            if(cities[indx] == null){
                size++;
                City city = new City(name);
                cities[indx] = city;
                return city;
            }
            if(cities[indx].name.equals(name)){
                return cities[indx];
            }
            indx = (indx + 1) % mod;
        }
    }
    
    /* 
    public static void main(String[] args) {
        
        Map map = new Map("trains.csv");

        for (int i = 0; i < map.mod; i++) {
            City city = map.cities[i];
            if(city != null){
                int cnx = city.neighbours.size();
            }
            
        }
    }
*/
}
