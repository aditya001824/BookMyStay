/**
 * Main Application Class
 * Filename must be HotelApp.java
 */
public class UC2 {
    public static void main(String[] args) {
        // 1. Initialize Room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        // 2. Static Availability Representation (Separation of State)
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 1;

        System.out.println("=== HOTEL MANAGEMENT SYSTEM ===");
        System.out.println();

        // 3. Displaying information using Polymorphism
        displayInfo(single, singleRoomAvailability);
        displayInfo(doubleRm, doubleRoomAvailability);
        displayInfo(suite, suiteRoomAvailability);

        System.out.println("Application terminated.");
    }

    // This method treats all subclasses as the parent 'Room' type
    public static void displayInfo(Room room, int count) {
        System.out.println("Room Type:  " + room.getType());
        System.out.println("Beds:       " + room.getNumberOfBeds());
        System.out.println("Price:      $" + room.getPrice());
        System.out.println("Available:  " + count);
        room.displayFeatures(); // Calls the specialized subclass behavior
        System.out.println("--------------------------------");
    }
}

/**
 * Key Concept: Abstract Class
 * Defines the domain structure for all rooms.
 */
abstract class Room {
    private String type;
    private int numberOfBeds;
    private double price;

    public Room(String type, int numberOfBeds, double price) {
        this.type = type;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
    }

    // Encapsulation: Accessors
    public String getType() { return type; }
    public int getNumberOfBeds() { return numberOfBeds; }
    public double getPrice() { return price; }

    // Abstract method: Forces subclasses to define their own description
    public abstract void displayFeatures();
}

/**
 * Key Concept: Inheritance
 * Specialized room types extending the base Room class.
 */
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 100.0);
    }

    @Override
    public void displayFeatures() {
        System.out.println("Note: Optimized for solo business travelers.");
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 175.0);
    }

    @Override
    public void displayFeatures() {
        System.out.println("Note: Features a king-sized bed and city view.");
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 350.0);
    }

    @Override
    public void displayFeatures() {
        System.out.println("Note: Includes a private kitchenette and lounge.");
    }
}