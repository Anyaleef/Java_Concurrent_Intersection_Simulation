### **Java Concurrent Intersection Simulation**



This project is a Java concurrency exercise from a Software Engineering course.



The program simulates multiple vehicle threads attempting to cross a shared intersection. Vehicles from the same direction may cross together, vehicles from conflicting directions must wait, and emergency vehicles receive priority.





###### **Task Summary**

The main goal was to implement the `Intersection` class, which controls when vehicles may enter and exit the intersection.



The intersection has four possible entry directions: north, south, east, and west. Each vehicle is represented by a separate thread and has a unique ID, an entry direction, and a flag indicating whether it is an emergency vehicle.



Each vehicle follows three stages:

1\. **Waiting** - the vehicle waits until the intersection allows it to enter.

2\. **Crossing** - the vehicle crosses the intersection for 0.5 seconds.

3\. **Exiting** - the vehicle notifies the intersection that it has left.



The main goal was to implement the `Intersection` class, specifically:

\- `enter(Vehicle vehicle)` — blocks a vehicle until it is allowed to enter the intersection.

\- `exit(Vehicle vehicle)` — updates the intersection state after a vehicle leaves.



The synchronization rules are:

\- Vehicles may enter if the intersection is empty.

\- Vehicles may enter if their direction matches the current active direction of the intersection.

\- The current active direction is determined by the first vehicle that enters after the intersection becomes empty.

\- Vehicles from different directions cannot cross at the same time.

\- Emergency vehicles have priority.

\- When an emergency vehicle arrives from another direction, vehicles that are not already crossing must stop waiting.

\- The emergency vehicle must wait until all vehicles currently crossing leave the intersection.

\- After the emergency vehicle crosses, the next direction does not necessarily return to the previous direction.

\- The initial waiting order of vehicles from the same direction does not have to be preserved.





###### **Concepts Practiced**

\- Java multithreading

\- Synchronization

\- Shared-state management

\- Object-oriented programming

\- Thread coordination

\- Basic simulation logic



###### **Tools**

Java



###### **Files**

\- `src/Intersection.java` - my implementation of the intersection synchronization logic

\- `src/Main.java` - course-provided test runner

\- `src/Vehicle.java` - course-provided vehicle thread class

\- `src/Direction.java` - course-provided direction enum

\- `output/sample\\\_output.txt` - sample program output





###### **Authorship Note**

The files `Main.java`, `Vehicle.java`, and `Direction.java` were provided as part of the course assignment starter code.

My implementation work focused on `Intersection.java`, which contains the synchronization logic controlling vehicle entry, exit, direction switching, and emergency vehicle priority.





###### **How to Run**

Compile:

```bash
javac src/\*.java

```

Run:

```bash
java -cp src Main
```

