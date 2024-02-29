public class Better {

    private static City[] path;
    private static int sp;

    public Better(){
        path = new City[1024];
        sp = 0;
    }

    public static Integer shortest(City from, City to, Integer max){
        if((max != null) && (max < 0)){
            return null;
        }

        if(from == to){
            return 0;
        }

        for (int i = 0; i < sp; i++) {
            if(path[i] == from)
            return null;
        }

        path[sp++] = from;

        Integer shrt = null;

        for(Connection conn : from.neighbours){
            Integer dist = shortest(conn.city, to, (max != null)? max - conn.dist : null);
            if(dist != null)
                if((shrt == null) || (shrt > dist + conn.dist)){
                    shrt = dist + conn.dist;
                    max = shrt;
                }
        }
        path[sp--] = null;
        return shrt;
    }
    
    public static void main(String[] args) {
        Map map = new Map("trains.csv");
        String[] lista = {
            "Malmö,Göteborg,200", 
            "Göteborg,Stockholm,300",
            "Malmö,Stockholm,300",
            "Stockholm,Sundsvall,400",
            "Stockholm,Umeå,600",
            "Göteborg,Sundsvall,600",
            "Sundsvall,Umeå,200",
            "Umeå,Göteborg,1000",
            "Göteborg,Umeå,100"
            };

            for(String citypair : lista){

            String[] cities = citypair.split(",");
            String from = cities[0];
            String to = cities[1];
            Integer max = Integer.parseInt(cities[2]);


            long t0 = System.nanoTime();
            Integer dist = shortest(map.lookup(from), map.lookup(to), max);
            long time = (System.nanoTime() - t0)/1_000_000;

            if(dist!= null){
                System.out.println("Shortest path from" + from + "to" + to + ": " + dist + "minutes" );
            }else {
                System.out.println("No path found from" + from + "to" + to + "");
            }

            System.out.println("shortest: " + dist + " min (" + time + " ms)");
            }
        }
        

}
