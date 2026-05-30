public class Vehicle extends Thread {
    private int vehicleId;
    private Direction direction;
    private Intersection intersection;
    private boolean isEmergency;

    public Vehicle(int vehicleId, Direction direction, Intersection intersection, boolean isEmergency) {
        this.vehicleId = vehicleId;
        this.direction = direction;
        this.intersection = intersection;
        this.isEmergency = isEmergency;
    }

    @Override
    public void run() {
        try {
            System.out.println("Vehicle " + vehicleId + " approaching from " + direction);
            intersection.enter(this);
            System.out.println("Vehicle " + vehicleId + " crossing intersection from " + direction);
            Thread.sleep(500); // Simulating crossing time
            System.out.println("Vehicle " + vehicleId + " crossed intersection");
            intersection.exit(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmergency() {
        return isEmergency;
    }

    public Direction getDirection() {
        return direction;
    }
}
