package battle;

import droids.BomberDroid;
import droids.Droid;
import droids.HealerDroid;

import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ThreeOnThreeBattle {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLUE = "\u001B[34m";
    public static final String BOLD = "\u001B[1m";

    private Random rand = new Random();

    // початок битви 3 на 3
    public void fight(List<Droid> team1, List<Droid> team2, int strategy){
        System.out.println("------------------------------------------");
        System.out.println(BOLD + CYAN + "Бій між командами " + team1.get(0).getName() + " та " + team2.get(0).getName() + RESET);
        System.out.println();

        int round = 1;
        System.out.println(BOLD + YELLOW + "Команда 1:"+ RESET);
        for (Droid droid : team1) {
            printHealthStatus(droid);
        }
        System.out.println();

        System.out.println(BOLD + YELLOW + "Команда 2:"+ RESET);
        for (Droid droid : team2) {
            printHealthStatus(droid);
        }
        System.out.println();

        while(!team1.isEmpty() && ! team2.isEmpty()){
            System.out.println(BOLD + YELLOW + "Раунд " + round + RESET);

            // цикл проходження по всіх дроїдах в обох командах
            for (int i = 0; i < Math.max(team1.size(), team2.size()); i++){
                if (!team1.isEmpty() && !team2.isEmpty()){
                    Droid attacker;
                    Droid defender;

                    if (i <= team1.size()){
                        // додаткові перевірки через out of baunce
                        if (i == team1.size() && i != 0){
                            attacker = team1.get(i-1);
                        } else {
                            attacker = team1.get(i);
                        }
                        defender = chooseDefender(team2, strategy);

                        // якщо лікар то шукає тімейта з найменшою кількістю здоров'я
                        if (attacker instanceof HealerDroid){
                            Droid minHealthDroid = team1.get(0);
                            for (Droid droid : team1){
                                if (droid.getHealth() < minHealthDroid.getHealth()){
                                    minHealthDroid = droid;
                                }
                            }
                            ((HealerDroid) attacker).heal(minHealthDroid);
                        }

                        if (attacker instanceof BomberDroid) {
                            // Якщо дроїд є підривником, викликаємо bombAttack
                            ((BomberDroid) attacker).attack(team2);
                        } else {
                            // Звичайна атака для інших типів дроїдів
                            attacker.attack(defender);
                        }

                        // цикл для перевірки чи не помер дроїд під час бою
                        for (Droid droid : team2) {
                            if (!droid.isAlive()) {
                                System.out.println(BOLD + RED + droid.getName() + " впав у бою!" + RESET);
                                droid.setHealth(droid.getMaxHealth());
                                team2.remove(droid);
                                break;
                            }
                        }
                    }

                    // якщо одна з команд пуста, виходимо з циклу
                    if (team1.isEmpty() || team2.isEmpty()) {
                        break;
                    }

                    if (i <= team2.size()){
                        if (i == team2.size() && i != 0){
                            attacker = team2.get(i-1);
                        } else {
                            attacker = team2.get(i);
                        }
                        defender = chooseDefender(team1, strategy);

                        // якщо лікар то шукає тімейта з найменшою кількістю здоров'я
                        if (attacker instanceof HealerDroid){
                            Droid minHealthDroid = team2.get(0);
                            for (Droid droid : team2){
                                if (droid.getHealth() < minHealthDroid.getHealth()){
                                    minHealthDroid = droid;
                                }
                            }
                            ((HealerDroid) attacker).heal(minHealthDroid);
                        }
                        if (attacker instanceof BomberDroid) {
                            // Якщо дроїд є підривником, викликаємо bombAttack
                            ((BomberDroid) attacker).attack(team1);
                        } else {
                            // Звичайна атака для інших типів дроїдів
                            attacker.attack(defender);
                        }
                        // цикл для перевірки чи не помер дроїд під час бою
                        for (Droid droid : team1) {
                            if (!droid.isAlive()) {
                                System.out.println(BOLD + RED + droid.getName() + " впав у бою!" + RESET);
                                droid.setHealth(droid.getMaxHealth());
                                team1.remove(droid);
                                break;
                            }
                        }
                    }
                }
            }

            System.out.println();
            System.out.println(BOLD + YELLOW + "Команда 1:"+ RESET);
            for (Droid droid : team1) {
                printHealthStatus(droid);
            }
            System.out.println();

            System.out.println(BOLD + YELLOW + "Команда 2:"+ RESET);
            for (Droid droid : team2) {
                printHealthStatus(droid);
            }
            System.out.println();

            if (team1.isEmpty() || team2.isEmpty()) {
                break;
            }

            System.out.println(BOLD + "------------------------------------------" + RESET);
            round++;
        }

        // повертаємо здоров'я дроїдам після закінчення бою
        for (Droid droid : team1){
            droid.setHealth(droid.getMaxHealth());
        }

        for (Droid droid : team2){
            droid.setHealth(droid.getMaxHealth());
        }

        if (team1.isEmpty()) {
            System.out.println(BOLD + GREEN + "Команда 2 перемогла!" + RESET);
        } else {
            System.out.println(BOLD + GREEN + "Команда 1 перемогла!" + RESET);
        }
    }

    // та сама битва 3 на 3 але записана у файл
    public void fightToFile(List<Droid> team1, List<Droid> team2, int strategy) {
        // Вказуємо ім'я файлу для запису
        File outputFile = new File("battle_results.txt");
        PrintStream originalOut = System.out; // Зберігаємо стандартний вихід
        try {
            // Створюємо PrintStream для запису у файл
            PrintStream fileOut = new PrintStream(outputFile);
            System.setOut(fileOut); // Перенаправляємо стандартний вихід у файл

            System.out.println("------------------------------------------");
            System.out.println("Бій між командами " + team1.get(0).getName() + " та " + team2.get(0).getName());
            System.out.println();

            int round = 1;
            System.out.println("Команда 1:");
            for (Droid droid : team1) {
                System.out.println(droid.getName() + " [Здоров'я: " + droid.getHealth() + "]");
            }
            System.out.println();

            System.out.println("Команда 2:");
            for (Droid droid : team2) {
                System.out.println(droid.getName() + " [Здоров'я: " + droid.getHealth() + "]");
            }
            System.out.println();

            while (!team1.isEmpty() && !team2.isEmpty()) {
                System.out.println("Раунд " + round);

                for (int i = 0; i < Math.max(team1.size(), team2.size()); i++) {
                    if (!team1.isEmpty() && !team2.isEmpty()) {
                        Droid attacker;
                        Droid defender;

                        // Атаки команди 1
                        if (i < team1.size()) {
                            attacker = team1.get(i);
                            defender = chooseDefender(team2, strategy);

                            // якщо лікар то шукає тімейта з найменшою кількістю здоров'я
                            if (attacker instanceof HealerDroid){
                                Droid minHealthDroid = team1.get(0);
                                for (Droid droid : team1){
                                    if (droid.getHealth() < minHealthDroid.getHealth()){
                                        minHealthDroid = droid;
                                    }
                                }
                                ((HealerDroid) attacker).heal(minHealthDroid);
                            }

                            if (attacker instanceof BomberDroid) {
                                ((BomberDroid) attacker).attack(team2);
                            } else {
                                attacker.attack(defender);
                            }

                            for (Droid droid : team2) {
                                if (!droid.isAlive()) {
                                    System.out.println(droid.getName() + " впав у бою!");
                                    droid.setHealth(droid.getMaxHealth());
                                    team2.remove(droid);
                                    break;
                                }
                            }
                        }

                        // Атаки команди 2
                        if (i < team2.size()) {
                            attacker = team2.get(i);
                            defender = chooseDefender(team1, strategy);

                            // якщо лікар то шукає тімейта з найменшою кількістю здоров'я
                            if (attacker instanceof HealerDroid){
                                Droid minHealthDroid = team2.get(0);
                                for (Droid droid : team2){
                                    if (droid.getHealth() < minHealthDroid.getHealth()){
                                        minHealthDroid = droid;
                                    }
                                }
                                ((HealerDroid) attacker).heal(minHealthDroid);
                            }

                            if (attacker instanceof BomberDroid) {
                                ((BomberDroid) attacker).attack(team1);
                            } else {
                                attacker.attack(defender);
                            }

                            for (Droid droid : team1) {
                                if (!droid.isAlive()) {
                                    System.out.println(droid.getName() + " впав у бою!");
                                    droid.setHealth(droid.getMaxHealth());
                                    team1.remove(droid);
                                    break;
                                }
                            }
                        }
                    }
                }

                System.out.println();
                System.out.println("Команда 1:");
                for (Droid droid : team1) {
                    System.out.println(droid.getName() + " [Здоров'я: " + droid.getHealth() + "]");
                }
                System.out.println();

                System.out.println("Команда 2:");
                for (Droid droid : team2) {
                    System.out.println(droid.getName() + " [Здоров'я: " + droid.getHealth() + "]");
                }
                System.out.println();

                if (team1.isEmpty() || team2.isEmpty()) {
                    break;
                }

                System.out.println("------------------------------------------");
                round++;
            }

            // повертаємо здоров'я дроїдам
            for (Droid droid : team1){
                droid.setHealth(droid.getMaxHealth());
            }

            for (Droid droid : team2){
                droid.setHealth(droid.getMaxHealth());
            }

            if (team1.isEmpty()) {
                System.out.println("Команда 2 перемогла!");
            } else {
                System.out.println("Команда 1 перемогла!");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Виводимо помилку у консоль, якщо файл не знайдено
        } finally {
            // Повертаємо стандартний вихід у консоль
            System.setOut(originalOut);
            System.out.println("Результати бою записано у файл: " + outputFile.getAbsolutePath());
        }
    }


    // метод який в залежності від обраної тактики вибирає ціль для атаки
    private Droid chooseDefender(List<Droid> team, int strategy){
        if (strategy == 1){
            return team.get(rand.nextInt(team.size()));
        } else if (strategy == 2){
            Droid minHealthDroid = team.get(0);
            for (Droid droid : team){
                if (droid.getHealth() < minHealthDroid.getHealth()){
                    minHealthDroid = droid;
                }
            }
            return minHealthDroid;
        }
        return null;
    }

    private void printHealthStatus(Droid droid) {
        System.out.println(BOLD + BLUE + droid.getName() + " [Здоров'я: " + healthBar(droid.getHealth(), droid.getMaxHealth()) + " " + droid.getHealth() + "]" + RESET);
    }

    // повертаємо String у вигляді кількості здоров'я
    private String healthBar(int health, int maxHealth){
        int totalBars = 20;
        int filledBars = (int) ((health / (double) maxHealth) * totalBars);
        StringBuilder bar = new StringBuilder();

        for (int i = 0; i < filledBars; i++){
            bar.append(BOLD + GREEN + "|" + RESET);
        }
        for(int i = filledBars; i < totalBars; i++){
            bar.append(BOLD + RED + "|" + RESET);
        }

        return bar.toString();
    }
}
