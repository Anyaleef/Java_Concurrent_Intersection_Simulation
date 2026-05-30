import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Intersection {
    private Direction currentDirection = null; //the current direction of the map (a.k.a intersection)
    private Lock lock = new ReentrantLock(); //locks or unlocks current direction
    private Map<Direction, Boolean> directionsInUse = new HashMap<>();
    private boolean emergencyInMap = false; //if there's an emergency vehicle in current direction, it'll be prioritized later

    /**
     * Default Constructor
     */
    public Intersection(){
        for(Direction direction : Direction.values()){ //iterate over direction array
            directionsInUse.put(direction,false); //initializing the map, no cars uses any direction
        }
    }


    /**
     * Helper method to get an entrance permission for vehicle
     * @param vehicle
     * @throws InterruptedException
     */
    public void enter(Vehicle vehicle) throws InterruptedException {

        lock.lock();

        try {
            while (!grantEntrance(vehicle)) {
                //we wait until it's true (lock.wait() wasn't efficient)
            }

            directionsInUse.put(vehicle.getDirection(), true);//gives permission to enter

            if (currentDirection == null || vehicle.isEmergency()) {
                if (vehicle.isEmergency()) {
                    emergencyInMap = true;
                }
                currentDirection = vehicle.getDirection();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Helper method to let vehicle announce others they exited
     * @param vehicle
     */
    public void exit(Vehicle vehicle){

        synchronized(lock){//unlocks current direction
            directionsInUse.put(vehicle.getDirection(),false);//direction is free, car can exit

            if(vehicle.isEmergency()){
                emergencyInMap=false;//the emergency vehicle left the intersection
            }

            lock.notifyAll();//notifies all cars that are in the same direction
        }
    }

    /**
     * Helper method to grant entrance for waiting vehicles
     * @param vehicle
     * @return true if no cars are currently crossing
     */
    private boolean grantEntrance(Vehicle vehicle){
        if(vehicle.isEmergency()){
            currentDirection = vehicle.getDirection();//changes map direction accordingly
            emergencyInMap=true;
            return true;//emergency car prioritized
        }else{
            if(currentDirection==null || currentDirection == vehicle.getDirection()){//intersection is free or matches current direction
                return true;//regular car can enter
            }

            for(Boolean inUse : directionsInUse.values()){//checks if direction is already used
                if(inUse){ return false; }//if it is regular car needs to wait
            }
        }
        return true;//regular car can enter when no direction is used
    }
}
