import java.util.HashMap;
import java.util.Map;

/**
 * Main Application Class
 */
public class UC3 {
    public static void main(String[] args) {
        // Initialize the Inventory Component (Single Source of Truth)
        RoomInventory inventory = new RoomInventory();

        System.out.println("=== INITIAL INVENTORY STATE ===");
        inventory.displayInventory();

        // Demonstrate controlled updates
        System.out.println("\n--- Processing Bookings ---");
        inventory.updateAvailability("Single", -1); // One single room booked
        inventory.updateAvailability("Suite", -1);  // One suite booked

        // Demonstrate checking specific availability (O(1) Lookup)
        String searchType = "Double";
        System.out.println("Current " + searchType + " rooms available: " + inventory.getAvailability(searchType));

        System.out.println("\n=== UPDATED INVENTORY STATE ===");
        inventory.displayInventory();
    }
}

/**
 * Key Concept: Encapsulation of Inventory Logic
 * This class manages HOW many rooms are available, separating state from the Room domain.
 */
class RoomInventory {
    // HashMap<String, Integer> maps Room Type (Key) to Available Count (Value)
    private Map<String, Integer> availabilityMap;

    public RoomInventory() {
        this.availabilityMap = new HashMap<>();
        initializeInventory();
    }

    /**
     * Requirement: Initialize room availability using a constructor.
     * This replaces scattered variables with a centralized structure.
     */
    private void initializeInventory() {
        availabilityMap.put("Single", 10);
        availabilityMap.put("Double", 8);
        availabilityMap.put("Suite", 2);
    }

    /**
     * Requirement: Provide methods to retrieve current availability.
     * Demonstrates O(1) Lookup complexity.
     */
    public int getAvailability(String roomType) {
        // Using getOrDefault to handle room types that might not exist safely
        return availabilityMap.getOrDefault(roomType, 0);
    }

    /**
     * Requirement: Support controlled updates to room availability.
     * Encapsulation ensures logic (like not going below zero) is handled in one place.
     */
    public void updateAvailability(String roomType, int change) {
        if (availabilityMap.containsKey(roomType)) {
            int currentCount = availabilityMap.get(roomType);
            int newCount = currentCount + change;

            if (newCount < 0) {
                System.out.println("Error: Cannot book " + roomType + ". No vacancy.");
            } else {
                availabilityMap.put(roomType, newCount);
                System.out.println("Update: " + roomType + " inventory changed by " + change);
            }
        } else {
            System.out.println("Error: Room type '" + roomType + "' does not exist.");
        }
    }

    /**
     * Requirement: The current inventory state is displayed when requested.
     */
    public void displayInventory() {
        System.out.println("Current Inventory Status:");
        for (Map.Entry<String, Integer> entry : availabilityMap.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " available");
        }
    }
}