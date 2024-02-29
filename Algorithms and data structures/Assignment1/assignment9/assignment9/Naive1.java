public class Naive1 {
    
    public static Integer shortest(City from, City to, Integer max){
        if(max < 0)
        return null;

        if(from == to)
        return 0;

        Integer shrt = null;
       

        for( Connection conn : from.neighbours){
            Integer dist = shortest(conn.city, to, max - conn.dist);
            if(dist != null){
                if((shrt == null) || (shrt > dist + conn.dist)){
                    shrt = dist + conn.dist;
                } 
            }
        }
        return shrt; 
    }


    public static void main(String[] args) {
        Map map = new Map("trains.csv");
        String[] lista = {
            "Malmö,Göteborg,400", 
            "Göteborg,Stockholm,400",
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
