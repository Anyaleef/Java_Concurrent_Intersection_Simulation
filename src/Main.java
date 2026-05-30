public class Main {

    public static void main(String[] args){
        for (int i=1; i <= 5; i++) {    // output is non deterministic
            try {
                System.out.println("Test 1, iteration " + i + " starts");
                test1();
                System.out.println("Test 1, iteration " + i + " done");
                System.out.println("--------------------------------------------");
            } catch (Exception e) {
                System.out.println("exception " + e);
            }
        }

        for (int i=1; i <= 5; i++) {    // output is non deterministic
            try {
                System.out.println("Test 2, iteration " + i + " starts");
                test2();
                System.out.println("Test 2, iteration " + i + " done");
                System.out.println("--------------------------------------------");
            } catch (Exception e) {
                System.out.println("exception " + e);
            }
        }
    }

    public static void test1(){
        Intersection intersection = new Intersection();

        Vehicle[] vehicles = new Vehicle[8];

        vehicles[0] = new Vehicle(1, Direction.NORTH, intersection, false);
        vehicles[1] = new Vehicle(2, Direction.NORTH, intersection, false);
        vehicles[2] = new Vehicle(3, Direction.WEST, intersection, false);
        vehicles[3] = new Vehicle(4, Direction.WEST, intersection, false);
        vehicles[4] = new Vehicle(5, Direction.EAST, intersection, false);
        vehicles[5] = new Vehicle(6, Direction.EAST, intersection, false);
        vehicles[6] = new Vehicle(7, Direction.SOUTH, intersection, false);
        vehicles[7] = new Vehicle(8, Direction.SOUTH, intersection, false);

        for (Vehicle vehicle: vehicles){
            vehicle.start();
        }

        try {
            for (Vehicle vehicle : vehicles) {
                vehicle.join();
            }
        }catch (InterruptedException e){ }
    }

    public static void test2() throws InterruptedException{
        Intersection intersection = new Intersection();

        Vehicle[] vehicles = new Vehicle[6];

        vehicles[0] = new Vehicle(1, Direction.NORTH, intersection, false);
        vehicles[1] = new Vehicle(2, Direction.NORTH, intersection, false);
        vehicles[2] = new Vehicle(3, Direction.WEST, intersection, true);
        vehicles[3] = new Vehicle(4, Direction.NORTH, intersection, false);
        vehicles[4] = new Vehicle(5, Direction.NORTH, intersection, false);
        vehicles[5] = new Vehicle(6, Direction.WEST, intersection, false);

        vehicles[0].start();
        vehicles[1].start();
        Thread.sleep(100);
        vehicles[2].start();
        Thread.sleep(50);
        vehicles[3].start();
        vehicles[4].start();
        vehicles[5].start();

        for (Vehicle vehicle : vehicles) {
            vehicle.join();
        }
    }
}