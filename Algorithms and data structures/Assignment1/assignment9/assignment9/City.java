import java.util.ArrayList;

public class City {
    public String name;
    public ArrayList<Connection> neighbours;

    public City(String nme){
        name = nme;
        neighbours = new ArrayList<>();
    }

    public void connect(City next, int dist){
        neighbours.add(new Connection(next, dist));
    }
    
}
