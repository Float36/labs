package truck.model;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Shop {
    private List<Coffee> availableCoffee;

    public Shop() {
        this.availableCoffee = new ArrayList<>();
        initializeDefaultCoffees();
    }

    private void initializeDefaultCoffees() {
        // Створення кількох видів кави за замовчуванням
        Package package1 = new Package("Банка", 0.5, 1.0);
        Package package2 = new Package("Пакет", 0.2, 0.5);

        availableCoffee.add(new GrainCoffee("Зернова кава", 500, 100, 1, package1, 1000));
        availableCoffee.add(new GroundCoffee("Мелена кава", 260, 50, 0.5, package2, 500));
        availableCoffee.add(new InstantCoffee("Розчинна кава", 100, 30, 0.25, package2, 2000));
    }

    // Завантажує каву з файлу та додає її до списку доступних кав
    public void loadCoffeesFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String name = parts[0];
                    double weight = Double.parseDouble(parts[1]);
                    double price = Double.parseDouble(parts[2]);
                    double volume = Double.parseDouble(parts[3]);
                    String packagingMaterial = parts[4];
                    double packageWeight = Double.parseDouble(parts[5]);
                    double packageVolume = Double.parseDouble(parts[6]);
                    int quantity = Integer.parseInt(parts[7]);

                    Package packaging = new Package(packagingMaterial, packageWeight, packageVolume);
                    Coffee coffee = new GrainCoffee(name, weight, price, volume, packaging, quantity);
                    availableCoffee.add(coffee);
                }
            }
            System.out.println("Каву з файлу добавлено");
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні файлу: " + e.getMessage());
        }
    }

    // Додає новий об'єкт кави до списку доступних кав
    public void addCoffee(Coffee coffee){
        availableCoffee.add(coffee);
        System.out.println("Додана кава: " + coffee);
    }

    // Виводить список доступної кави
    public void showAvailableCoffee(){
        if (availableCoffee.isEmpty()) {
            System.out.println("Кава відсутня.");
        } else {
            System.out.println("Кава на вибір:");
            for (Coffee coffee : availableCoffee) {
                System.out.println(coffee);
            }
        }
    }

    public List<Coffee> getAvailableCoffee() {
        return availableCoffee;
    }
}
