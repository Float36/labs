package menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import battle.ThreeOnThreeBattle;
import droids.*;
import battle.OneOnOneBattle;

public class MainMenu {
    private ArrayList<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public MainMenu() {
        initializeDefaultDroids();
    }

    // метод для вивведення меню
    public void showMenu(){
        while(true) {
            System.out.println("---------------------------------------");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати всіх дроїдів");
            System.out.println("3. Почати битву 1 на 1");
            System.out.println("4. Почати битву 3 на 3");
            System.out.println("5. Записати битву 3 на 3 у файл");
            System.out.println("6. Відтворити битву з файлу");
            System.out.println("7. Вийти");

            int choice = -1; // Ініціалізуємо choice з недопустимого значення
            boolean validInput = false; // Прапорець для перевірки правильності введення

            // Цикл для отримання правильного вводу
            while (!validInput) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Очищуємо буфер
                    validInput = true; // Якщо ввід коректний, змінюємо прапорець
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Будь ласка, введіть число від 1 до 7.");
                    scanner.nextLine(); // Очищуємо буфер вводу
                }
            }

            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startBattle();
                    break;
                case 4:
                    startThreeOnThreeBattle();
                    break;
                case 5:
                    startThreeOnThreeBattleToFile();
                    break;
                case 6:
                    replayBattleFromFile();
                    break;
                case 7:
                    return;
            }
        }
    }

    // створюємо декілька дроїдів по дефолту
    private void initializeDefaultDroids() {
        droids.add(new PoisonDroid("Viper", 500, 50, 0.5, 43));
        droids.add(new BomberDroid("Bomber", 450, 40, 0.3));
        droids.add(new WeaknessDroid("Weak", 630, 130, 0.25, 0.2));
        droids.add(new Droid("Optimus", 600, 125, 0.34));
        droids.add(new HealerDroid("Bumblebee", 550, 110, 0.9,  60));
        droids.add(new Droid("Magnus", 400, 80, 0.23));
    }

    // створюємо дрона з перевіркою на правильність введення
    private void createDroid() {
        System.out.println("Якого ви хочете створити дроїда: ");
        System.out.println("1 - Бойовий дроїд.    \"Дроїд з простими характеристиками\"");
        System.out.println("2 - Дроїд-лікар.      \"Дроїд-лікар перед кожною своєю атакою лікує дроїда з найменшою кількістю здоров'я\"");
        System.out.println("3 - Отруйний дроїд.   \"Отруйний дроїд накладає ефект отравлення на кожного, кого він атакує. Яд б'є ворожого дроїда після кожної його атаки 3 рази\"");
        System.out.println("4 - Дроїд-підривник.  \"Дроїд-бомбер кидає гранати і завдає шкоди усім противникам\"");
        System.out.println("5 - Дроїд-ослаблювач. \"Дроїд-ослаблювач після своєї атаки накладає на ворога ефект слабкості на 3 його наступні атаки (зменшує шкоду яку завдає ворог на певну кількість %)\"");

        int type = -1;
        boolean validInput = false;

        // Цикл для отримання правильного вводу типу дроїда
        while (!validInput) {
            try {
                type = scanner.nextInt();
                scanner.nextLine(); // Очищення буфера
                if (type < 1 || type > 5) {
                    System.out.println("Будь ласка, введіть число від 1 до 5.");
                } else {
                    validInput = true; // Якщо ввід коректний, змінюємо прапорець
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть число від 1 до 5.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        System.out.println("Введіть назву дроїда: ");
        String name = scanner.nextLine();

        int health = 0;
        int damage = 0;
        double critChance = 0.0;

        // Цикл для отримання правильного вводу здоров'я
        while (true) {
            try {
                System.out.println("Введіть здоров'я дроїда: ");
                health = scanner.nextInt();
                if (health <= 0) {
                    System.out.println("Здоров'я має бути більше 0. Спробуйте ще раз.");
                    continue; // Продовжуємо запит
                }
                break; // Якщо ввід коректний, виходимо з циклу
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число для здоров'я.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        // Цикл для отримання правильного вводу шкоди
        while (true) {
            try {
                System.out.println("Введіть шкоду дроїда: ");
                damage = scanner.nextInt();
                if (damage < 0) {
                    System.out.println("Шкода не може бути від'ємною. Спробуйте ще раз.");
                    continue; // Продовжуємо запит
                }
                break; // Якщо ввід коректний, виходимо з циклу
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число для шкоди.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        // Цикл для отримання правильного вводу шансу критичної шкоди
        while (true) {
            try {
                System.out.println("Введіть шанс критичної шкоди (0.0 - 0.99): ");
                critChance = scanner.nextDouble();
                if (critChance < 0.0 || critChance >= 1.0) {
                    System.out.println("Шанс критичної шкоди має бути в діапазоні від 0.0 до 0.99. Спробуйте ще раз.");
                    continue; // Продовжуємо запит
                }
                break; // Якщо ввід коректний, виходимо з циклу
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число для шансу критичної шкоди.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        switch (type) {
            case 1:
                droids.add(new Droid(name, health, damage, critChance));
                break;
            case 2:
                int healAmount = 0;
                // Цикл для отримання правильного вводу healAmount
                while (true) {
                    try {
                        System.out.println("Введіть число, на яке може полікувати дроїд: ");
                        healAmount = scanner.nextInt();
                        if (healAmount < 0) {
                            System.out.println("Кількість лікування не може бути від'ємною. Спробуйте ще раз.");
                            continue; // Продовжуємо запит
                        }
                        break; // Якщо ввід коректний, виходимо з циклу
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Будь ласка, введіть дійсне число для лікування.");
                        scanner.nextLine(); // Очищення буфера вводу
                    }
                }
                droids.add(new HealerDroid(name, health, damage, critChance, healAmount));
                break;
            case 3:
                int poisonDamage = 0;
                // Цикл для отримання правильного вводу poisonDamage
                while (true) {
                    try {
                        System.out.println("Введіть кількість отрути, яку може застосувати дроїд: ");
                        poisonDamage = scanner.nextInt();
                        if (poisonDamage < 0) {
                            System.out.println("Кількість отрути не може бути від'ємною. Спробуйте ще раз.");
                            continue; // Продовжуємо запит
                        }
                        break; // Якщо ввід коректний, виходимо з циклу
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Будь ласка, введіть дійсне число для отрути.");
                        scanner.nextLine(); // Очищення буфера вводу
                    }
                }
                droids.add(new PoisonDroid(name, health, damage, critChance, poisonDamage));
                break;
            case 4:
                droids.add(new BomberDroid(name, health, damage, critChance));
                break;
            case 5:
                double weaknessChance = 0.0;
                // Цикл для отримання правильного вводу weaknessChance
                while (true) {
                    try {
                        System.out.println("Введіть ймовірність застосування слабкості (0.0 - 0.99): ");
                        weaknessChance = scanner.nextDouble();
                        if (weaknessChance < 0.0 || weaknessChance >= 1.0) {
                            System.out.println("Ймовірність має бути в діапазоні від 0.0 до 0.99. Спробуйте ще раз.");
                            continue; // Продовжуємо запит
                        }
                        break; // Якщо ввід коректний, виходимо з циклу
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Будь ласка, введіть дійсне число для ймовірності.");
                        scanner.nextLine(); // Очищення буфера вводу
                    }
                }
                droids.add(new WeaknessDroid(name, health, damage, critChance, weaknessChance));
                break;
            default:
                System.out.println("Неправильний тип дроїда. Спробуйте ще раз.");
                break;
        }

        System.out.println("Дроїд " + name + " успішно створений!");
    }


    private void showDroids() {
        int i = 1;
        for (Droid droid : droids){
            System.out.print(i + ") ");
            droid.showDroidInfo();
            i++;
        }
    }

    // битва один на один
    private void startBattle() {
        if (droids.size() < 2) {
            System.out.println("Недостатньо дроїдів для битви.");
            return;
        }

        int droid1Index = -1;
        int droid2Index = -1;

        // Цикл для вибору першого дроїда
        while (true) {
            System.out.println("Виберіть першого дроїда для битви: ");
            showDroids();
            try {
                droid1Index = scanner.nextInt() - 1;
                if (droid1Index < 0 || droid1Index >= droids.size()) {
                    System.out.println("Некоректний індекс дроїда. Будь ласка, спробуйте ще раз.");
                    continue; // Якщо індекс некоректний, продовжуємо запит
                }
                break; // Виходимо з циклу, якщо ввід коректний
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        // Цикл для вибору другого дроїда
        while (true) {
            System.out.println("Виберіть другого дроїда для битви: ");
            try {
                droid2Index = scanner.nextInt() - 1; // Вводимо дроїда (зменшуємо на 1 для індексації)
                if (droid2Index < 0 || droid2Index >= droids.size() || droid2Index == droid1Index) {
                    System.out.println("Некоректний індекс дроїда або дроїд вже обраний. Будь ласка, спробуйте ще раз.");
                    continue; // Якщо індекс некоректний, продовжуємо запит
                }
                break; // Виходимо з циклу, якщо ввід коректний
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        // Отримання дроїдів
        Droid droid1 = droids.get(droid1Index);
        Droid droid2 = droids.get(droid2Index);

        // Розпочинаємо бій
        OneOnOneBattle battle = new OneOnOneBattle();
        battle.fight(droid1, droid2);
    }


    // старт битви 3 на 3
    private void startThreeOnThreeBattle() {
        if (droids.size() < 6) {
            System.out.println("Недостатньо дроїдів для битви 3 на 3.");
            return;
        }

        showDroids();
        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        // Цикл для вибору дроїдів першої команди
        System.out.println("Виберіть дроїдів для першої команди: ");
        while (team1.size() < 3) {
            System.out.println("Оберіть дроїда для першої команди: ");
            try {
                int index = scanner.nextInt() - 1;
                if (index < 0 || index >= droids.size() || team1.contains(droids.get(index))) {
                    System.out.println("Некоректний індекс дроїда або дроїд вже обраний. Будь ласка, спробуйте ще раз.");
                    continue; // Якщо індекс некоректний, продовжуємо запит
                }
                team1.add(droids.get(index));
                System.out.println(droids.get(index).getName() + " добавлено в першу команду");
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        // Цикл для вибору дроїдів другої команди
        System.out.println("Виберіть дроїдів для другої команди: ");
        while (team2.size() < 3) {
            System.out.println("Оберіть дроїда для другої команди: ");
            try {
                int index = scanner.nextInt() - 1; // Вводимо дроїда (зменшуємо на 1 для індексації)
                if (index < 0 || index >= droids.size() || team2.contains(droids.get(index)) || team1.contains(droids.get(index))) {
                    System.out.println("Некоректний індекс дроїда або дроїд вже обраний. Будь ласка, спробуйте ще раз.");
                    continue; // Якщо індекс некоректний, продовжуємо запит
                }
                team2.add(droids.get(index));
                System.out.println(droids.get(index).getName() + " добавлено в другу команду");
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        // Вибір тактики бою
        int battleStrategy;
        while (true) {
            System.out.println("Виберіть тактику бою 3 на 3:");
            System.out.println("1. Випадкова атака");
            System.out.println("2. Атака по слабкому (менше здоров'я)");

            try {
                battleStrategy = scanner.nextInt();
                if (battleStrategy < 1 || battleStrategy > 2) {
                    System.out.println("Некоректний вибір тактики. Будь ласка, спробуйте ще раз.");
                    continue; // Якщо вибір некоректний, продовжуємо запит
                }
                break; // Виходимо з циклу, якщо ввід коректний
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        // Розпочинаємо бій
        ThreeOnThreeBattle battle = new ThreeOnThreeBattle();
        battle.fight(team1, team2, battleStrategy);
    }

    // записати битву 3 на 3 у файл
    private void startThreeOnThreeBattleToFile() {
        if (droids.size() < 6) {
            System.out.println("Недостатньо дроїдів для битви 3 на 3.");
            return;
        }

        showDroids();
        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        // Цикл для вибору дроїдів першої команди
        System.out.println("Виберіть дроїдів для першої команди: ");
        while (team1.size() < 3) {
            System.out.println("Оберіть дроїда для першої команди: ");
            try {
                int index = scanner.nextInt() - 1; // Вводимо дроїда (зменшуємо на 1 для індексації)
                if (index < 0 || index >= droids.size() || team1.contains(droids.get(index))) {
                    System.out.println("Некоректний індекс дроїда або дроїд вже обраний. Будь ласка, спробуйте ще раз.");
                    continue; // Якщо індекс некоректний, продовжуємо запит
                }
                team1.add(droids.get(index));
                System.out.println(droids.get(index).getName() + " добавлено в першу команду");
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        // Цикл для вибору дроїдів другої команди
        System.out.println("Виберіть дроїдів для другої команди: ");
        while (team2.size() < 3) {
            System.out.println("Оберіть дроїда для другої команди: ");
            try {
                int index = scanner.nextInt() - 1; // Вводимо дроїда (зменшуємо на 1 для індексації)
                if (index < 0 || index >= droids.size() || team2.contains(droids.get(index)) || team1.contains(droids.get(index))) {
                    System.out.println("Некоректний індекс дроїда або дроїд вже обраний. Будь ласка, спробуйте ще раз.");
                    continue; // Якщо індекс некоректний, продовжуємо запит
                }
                team2.add(droids.get(index));
                System.out.println(droids.get(index).getName() + " добавлено в другу команду");
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        // Вибір тактики бою
        int battleStrategy;
        while (true) {
            System.out.println("Виберіть тактику бою 3 на 3:");
            System.out.println("1. Випадкова атака");
            System.out.println("2. Атака по слабкому (менше здоров'я)");

            try {
                battleStrategy = scanner.nextInt();
                if (battleStrategy < 1 || battleStrategy > 2) {
                    System.out.println("Некоректний вибір тактики. Будь ласка, спробуйте ще раз.");
                    continue; // Якщо вибір некоректний, продовжуємо запит
                }
                break; // Виходимо з циклу, якщо ввід коректний
            } catch (java.util.InputMismatchException e) {
                System.out.println("Будь ласка, введіть дійсне число.");
                scanner.nextLine(); // Очищення буфера вводу
            }
        }

        // Розпочинаємо бій
        ThreeOnThreeBattle battle = new ThreeOnThreeBattle();
        battle.fightToFile(team1, team2, battleStrategy);
    }


    // відтворити битву 3 на 3 з файлу
    private void replayBattleFromFile() {
        System.out.println("Введіть ім'я файлу для відтворення битви: ");   // C:\LP\семестр 3\прикладне програмування\lab3\battle_results.txt
        String filename = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Відтворення битви з файлу: " + filename);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
