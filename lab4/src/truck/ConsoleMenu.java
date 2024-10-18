package truck;

import truck.command.*;
import truck.model.*;

import java.util.Scanner;

public class ConsoleMenu{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Truck truck = new Truck();
        Shop shop = new Shop();

        Command loadCommand = new LoadTruckCommand(truck);
        Command searchCommand = new SearchCoffeeCommand(truck);
        Command sortCommand = new SortCoffeeCommand(truck);
        Command addCoffeeCommand = new AddCoffeeCommand(shop);
        Command showCoffeeCommand = new ShowAvailableCoffeeCommand(shop);
        Command exitCommand = new ExitCommand();

        Carrier carrier = new Carrier(loadCommand, searchCommand, sortCommand);
        Seller seller = new Seller(showCoffeeCommand, addCoffeeCommand);

        boolean running = true;
        while (running) {
            System.out.println("Меню:");
            System.out.println("1. Завантажити фургон кавою");
            System.out.println("2. Сортувати каву");
            System.out.println("3. Шукати каву");
            System.out.println("4. Додати каву в магазин");
            System.out.println("5. Показати доступну каву в магазині");
            System.out.println("6. Вихід");

            System.out.print("Оберіть опцію: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    carrier.loadTruck();
                    break;
                case 2:
                    carrier.sortCoffee();
                    break;
                case 3:
                    carrier.searchCoffee();
                    break;
                case 4:
                    seller.addCoffee();
                    break;
                case 5:
                    seller.showCoffee();
                    break;
                case 6:
                    exitCommand.execute();
                    break;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }

        scanner.close();
    }
}


