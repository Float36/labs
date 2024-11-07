package truck.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Receiver
public class Truck {
    private List<Coffee> loadedCoffees;
    private double maxVolume;       // Maximum volume in liters (1m³ = 1000 liters)
    private double currentVolume;

    public Truck(double maxVolume) {
        this.loadedCoffees = new ArrayList<>();
        this.maxVolume = maxVolume;
        this.currentVolume = 0;
    }

    // Method to load coffee into the truck
    public void loadTruck(Coffee coffee, int quantity) {
        double coffeeVolume = coffee.getTotalVolume() * quantity;

        if (currentVolume + coffeeVolume <= maxVolume) {
            coffee.setQuantityInTruck(quantity + coffee.getQuantityInTruck());
            loadedCoffees.add(coffee);
            currentVolume += coffeeVolume;
            coffee.setQuantity(coffee.getQuantity() - quantity);
            System.out.println(quantity + " packs of " + coffee.getName() + " loaded. Current volume: " + currentVolume + "/" + maxVolume);
        } else {
            System.out.println("Not enough space for " + quantity + " packs of " + coffee.getName() + ". Current volume: " + currentVolume + "/" + maxVolume);
        }
    }

    // Method to sort coffee by price-to-weight ratio
    public void sortCoffee() {
        if (loadedCoffees.isEmpty()) {
            System.out.println("Truck is empty. No coffee to sort.");
            return;
        }

        loadedCoffees.sort(Comparator.comparingDouble(c -> c.getPrice() / c.getWeight()));

        System.out.println("Coffee sorted by price-to-weight ratio:");
        loadedCoffees.forEach(coffee -> System.out.println(coffee.toStringInTruck()));
    }

    // Method to search for a coffee by name in the truck
    public void searchCoffee() {
        if (loadedCoffees.isEmpty()) {
            System.out.println("Truck is empty. No coffee to search.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coffee name to search: ");
        String coffeeName = scanner.nextLine();

        loadedCoffees.stream()
                .filter(coffee -> coffee.getName().equalsIgnoreCase(coffeeName))
                .findFirst()
                .ifPresentOrElse(
                        coffee -> System.out.println("Coffee found: " + coffee),
                        () -> System.out.println("Coffee with name \"" + coffeeName + "\" not found in the truck.")
                );
    }

    // Getter methods
    public double getCurrentVolume() {
        return currentVolume;
    }

    public List<Coffee> getLoadedCoffees() {
        return loadedCoffees;
    }

    public double getMaxVolume() {
        return maxVolume;
    }
}