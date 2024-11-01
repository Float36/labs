package truck.model;

import java.util.*;

// Reciver
public class Truck {
    private List<Coffee> loadedCoffees;
    private double maxVolume;       // в літрах  (1м^3 = 1000л)
    private double currentVolume;

    public Truck(double maxVolume) {
        this.loadedCoffees = new ArrayList<>();
        this.maxVolume = maxVolume;
        this.currentVolume = 0;
    }

    // Метод для завантаження фургону кавою
    public void loadTruck(Coffee coffee, int quantity){
        double coffeeVolume = coffee.getTotalVolume() * quantity;

        if (currentVolume + coffeeVolume <= maxVolume) {
            int currentQuantity = coffee.getQuantity() - quantity;
            coffee.setQuantityInTruck(quantity + coffee.getQuantityInTruck());
            loadedCoffees.add(coffee);
            currentVolume += coffeeVolume;
            System.out.println(quantity + " упаковок " + coffee.getName() + " завантажено у фургон. Поточний об'єм: " + currentVolume + "/" + maxVolume);
            coffee.setQuantity(currentQuantity);
        } else {
            System.out.println("Недостатньо місця у фургоні для " + quantity + " упаковок " + coffee.getName() + ". Поточний об'єм: " + currentVolume + "/" + maxVolume);
        }
    }

    // Метод для сортування кави на основі співвідношення ціни та ваги
    public void sortCoffee(){
        if (loadedCoffees.isEmpty()) {
            System.out.println("Фургон порожній. Немає кави для сортування.");
            return;
        }

        Collections.sort(loadedCoffees, new Comparator<Coffee>() {
            @Override
            public int compare(Coffee c1, Coffee c2) {
                double ratio1 = c1.getPrice() / c1.getWeight();
                double ratio2 = c2.getPrice() / c2.getWeight();
                return Double.compare(ratio1, ratio2);
            }
        });


        System.out.println("Кава відсортована за співвідношенням ціни до ваги:");
        for (Coffee coffee : loadedCoffees) {
            System.out.println(coffee.toStringInTruck());
        }
    }

    // Метод для знаходження товару у фургоні
    public void searchCoffee(){
        if (loadedCoffees.isEmpty()) {
            System.out.println("Фургон порожній. Немає кави для пошуку.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву кави для пошуку: ");
        String coffeeName = scanner.nextLine();

        boolean found = false;
        for (Coffee coffee : loadedCoffees) {
            if (coffee.getName().equalsIgnoreCase(coffeeName)) {
                System.out.println("Кава знайдена: " + coffee);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Кава з назвою \"" + coffeeName + "\" не знайдена у фургоні.");
        }
    }

}
