

public class Main {
    public static void main(String[] args) {
        // 10 + 2 * 5
        // 10 2 5 * + in reversed Polish notation
        Item[] expr = {
            new Item(ItemType.VALUE, 10),
            new Item(ItemType.VALUE, 2), 
            new Item(ItemType.VALUE, 5), 
            new Item(ItemType.MUL, 0), 
            new Item(ItemType.ADD, 0), 
            };

        Calculator calc = new Calculator(expr,new StaticStack(1024));
        Calculator calc1 = new Calculator(expr,new DynamicStack());


        int res = calc.run();
        int res1 = calc1.run();
        System.out.println(" Calculator: res = " + res);
        System.out.println(" Calculator: res1 = " + res1);


        long statictime = time(100, 1000, new StaticStack(1024));
        System.out.println("statictime " + statictime);

        long dynamictime = time(100, 1000, new DynamicStack());
        System.out.println("dynamictime " + dynamictime);

        long ratio = statictime/dynamictime;
        System.out.println("Ratio " + (ratio));
        }
    
        public static long time(int loop, int ops, Stack stack){
        
            long t1;
            long t2;
            
            t1 = System.nanoTime();
            Bench.bench(100, 1000, new StaticStack(1024));
            t2= System.nanoTime();


            return ((t2 - t1)/loop);
            
        }
        

}
