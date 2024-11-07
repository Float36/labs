package truck;

import truck.command.*;
import truck.model.*;
import truck.model.Package;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Truck truck = new Truck(2000);
        Shop shop = new Shop();

        LoadTruckCommand loadCommand = new LoadTruckCommand(truck);
        Command searchCommand = new SearchCoffeeCommand(truck);
        Command sortCommand = new SortCoffeeCommand(truck);
        AddCoffeeCommand addCoffeeCommand = new AddCoffeeCommand(shop);
        Command showCoffeeCommand = new ShowAvailableCoffeeCommand(shop);
        Command exitCommand = new ExitCommand();

        Carrier carrier = new Carrier(loadCommand, searchCommand, sortCommand);
        Seller seller = new Seller(showCoffeeCommand, addCoffeeCommand);

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("Меню:");
            System.out.println("1. Завантажити фургон кавою");
            System.out.println("2. Показати відсортовану каву в фургоні");
            System.out.println("3. Шукати каву");
            System.out.println("4. Додати каву в магазин");
            System.out.println("5. Показати доступну каву в магазині");
            System.out.println("6. Завнтажити дані про каву з файлу");
            System.out.println("7. Вихід");

            System.out.print("Оберіть опцію: ");

            int choice = -1;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 7) {
                        System.out.println("Будь ласка, виберіть число від 1 до 7.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Помилка: Введіть коректне число.");
                    scanner.next();
                }
            }

            switch (choice) {
                case 1:
                    Coffee load = selectCoffee(scanner, shop);
                    if (load != null) {
                        System.out.print("Введіть суму на яку хочете зробити замовлення: ");
                        int sum = scanner.nextInt();
                        int quantity = (int) (sum / load.getPrice());
                        if (quantity > load.getQuantity()){
                            System.out.println("Недостатньо кави в магазині");
                            break;
                        }
                        loadCommand.setCoffee(load, quantity);
                        loadCommand.execute();
                    } else {
                        System.out.println("Помилка: Кава не вибрана.");
                    }
                    break;
                case 2:
                    carrier.sortCoffee();
                    break;
                case 3:
                    carrier.searchCoffee();
                    break;
                case 4:
                    Coffee coffee = createCoffee(scanner);
                    if (coffee != null) {
                        addCoffeeCommand.setCoffee(coffee);
                        seller.addCoffee();
                    }
                    break;
                case 5:
                    seller.showCoffee();
                    break;
                case 6:
                    shop.loadCoffeesFromFile("NotCoffees.txt");
                    break;
                case 7:
                    exitCommand.execute();
                    break;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }

        scanner.close();
    }

    private static Coffee selectCoffee(Scanner scanner, Shop shop){
        // Виведення списку доступної кави
        System.out.println("Доступна кава в магазині:");
        List<Coffee> availableCoffee = shop.getAvailableCoffee();

        if (availableCoffee.isEmpty()) {
            System.out.println("Кава відсутня.");
            return null;
        }

        // Відображення кави з індексами для вибору
        for (int i = 0; i < availableCoffee.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, availableCoffee.get(i));
        }

        int choice = -1;
        while (true) {
            System.out.print("Оберіть номер кави для завантаження у фургон: ");
            try {
                choice = scanner.nextInt();
                if (choice > 0 && choice <= availableCoffee.size()) {
                    return availableCoffee.get(choice - 1);
                } else {
                    System.out.println("Невірний вибір. Будь ласка, виберіть номер від 1 до " + availableCoffee.size() + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введіть коректне число.");
                scanner.next();
            }
        }
    }

    private static Coffee createCoffee(Scanner scanner) {
        System.out.println("Виберіть тип кави: ");
        System.out.println("1. Grain Coffee");
        System.out.println("2. Ground Coffee");
        System.out.println("3. Instant Coffee");

        int typeChoice = -1;
        while (true) {
            try {
                typeChoice = scanner.nextInt();
                if (typeChoice < 1 || typeChoice > 3) {
                    System.out.println("Невірний вибір. Будь ласка, виберіть тип кави від 1 до 3.");
                } else {
                    break; // Вихід з циклу, якщо введено коректне число
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введіть коректне число.");
                scanner.next(); // Очищення буфера
            }
        }

        scanner.nextLine(); // Очищення буфера після введення числа

        System.out.print("Введіть назву кави: ");
        String name = scanner.nextLine();

        double weight = -1;
        while (weight < 0) {
            System.out.print("Введіть вагу кави (в грамах): ");
            try {
                weight = scanner.nextDouble();
                if (weight < 0) {
                    System.out.println("Вага не може бути негативною. Спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введіть коректну вагу.");
                scanner.next(); // Очищення буфера
            }
        }

        double price = -1;
        while (price < 0) {
            System.out.print("Введіть ціну кави (в грн): ");
            try {
                price = scanner.nextDouble();
                if (price < 0) {
                    System.out.println("Ціна не може бути негативною. Спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введіть коректну ціну.");
                scanner.next(); // Очищення буфера
            }
        }

        double volume = -1;
        while (volume < 0) {
            System.out.print("Введіть об'єм кави (в літрах): ");
            try {
                volume = scanner.nextDouble();
                if (volume < 0) {
                    System.out.println("Об'єм не може бути негативним. Спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введіть коректний об'єм.");
                scanner.next(); // Очищення буфера
            }
        }

        // Вибір типу упаковки
        System.out.println("Виберіть тип упаковки: ");
        System.out.println("1. Банка");
        System.out.println("2. Пакет");

        int packagingChoice = -1;
        while (true) {
            try {
                packagingChoice = scanner.nextInt();
                if (packagingChoice < 1 || packagingChoice > 2) {
                    System.out.println("Невірний вибір типу упаковки. Будь ласка, виберіть 1 або 2.");
                } else {
                    break; // Вихід з циклу, якщо введено коректне число
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введіть коректне число.");
                scanner.next(); // Очищення буфера
            }
        }

        String packagingMaterial = (packagingChoice == 1) ? "Банка" : "Пакет";

        double packageWeight = -1;
        while (packageWeight < 0) {
            System.out.print("Введіть вагу упаковки (в грамах): ");
            try {
                packageWeight = scanner.nextDouble();
                if (packageWeight < 0) {
                    System.out.println("Вага упаковки не може бути негативною. Спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введіть коректну вагу упаковки.");
                scanner.next(); // Очищення буфера
            }
        }

        double packageVolume = -1;
        while (packageVolume < 0) {
            System.out.print("Введіть об'єм упаковки (в літрах): ");
            try {
                packageVolume = scanner.nextDouble();
                if (packageVolume < 0) {
                    System.out.println("Об'єм упаковки не може бути негативним. Спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введіть коректний об'єм упаковки.");
                scanner.next(); // Очищення буфера
            }
        }

        int quantity = -1;
        while (quantity < 0) {
            System.out.print("Введіть кількість упаковок: ");
            try {
                quantity = scanner.nextInt();
                if (quantity < 0) {
                    System.out.println("Кількість не може бути негативною. Спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введіть коректну кількість упаковок.");
                scanner.next(); // Очищення буфера
            }
        }

        Package packaging = new Package(packagingMaterial, packageWeight, packageVolume);

        switch (typeChoice) {
            case 1:
                return new GrainCoffee(name, weight, price, volume, packaging, quantity);
            case 2:
                return new GroundCoffee(name, weight, price, volume, packaging, quantity);
            case 3:
                return new InstantCoffee(name, weight, price, volume, packaging, quantity);
            default:
                System.out.println("Невірний вибір типу кави.");
                return null;
        }
    }
}


