package battle;
import droids.Droid;

public class OneOnOneBattle {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLUE = "\u001B[34m";
    public static final String BOLD = "\u001B[1m";

    // битва один на один
    public void fight(Droid droid1, Droid droid2){
        System.out.println("------------------------------------------");
        System.out.println(BOLD + CYAN + droid1.getName() + " vs " + droid2.getName() + RESET);
        System.out.println();

        printHealthStatus(droid1, droid2);

        // битва відбувається поки хтось з дроїдів не помре
        int round = 1;
        while (droid1.isAlive() && droid2.isAlive()){
            System.out.println(BOLD + YELLOW + "Раунд " + round + RESET);

            droid1.attack(droid2);
            printHealthStatus(droid1, droid2);

            if (!droid2.isAlive()){
                break;
            }

            droid2.attack(droid1);
            printHealthStatus(droid1, droid2);

            System.out.println(BOLD + "------------------------------------------" + RESET);
            round++;
        }

        // повертаємо кількість здоров'я дроїдам після бою
        droid1.setHealth(droid1.getMaxHealth());
        droid2.setHealth(droid2.getMaxHealth());

        if (droid1.isAlive()) {
            System.out.println(BOLD + GREEN + droid1.getName() + " переміг!" + RESET);
        } else if (droid2.isAlive()) {
            System.out.println(BOLD + GREEN + droid2.getName() + " переміг!" + RESET);
        } else {
            System.out.println(BOLD + CYAN + "Обидва дроїди загинули, це нічия!" + RESET);
        }
    }


    private void printHealthStatus(Droid droid1, Droid droid2){
        System.out.println(BLUE + droid1.getName() + " [Здоров'я: " + healthBar(droid1.getHealth(), droid1.getMaxHealth()) + " " + droid1.getHealth() + "]" + RESET);
        System.out.println(BLUE + droid2.getName() + " [Здоров'я: " + healthBar(droid2.getHealth(), droid2.getMaxHealth()) + " " + droid2.getHealth() + "]" + RESET);
    }

    // повертаємо String у вигляді кількості здоров'я
    private String healthBar(int health, int maxHealth){
        int totalBars = 20;
        int filledBars = (int) ((health / (double) maxHealth) * totalBars);
        StringBuilder bar = new StringBuilder();

        for (int i = 0; i < filledBars; i++){
            bar.append(GREEN + "|" + RESET);
        }
        for(int i = filledBars; i < totalBars; i++){
            bar.append(RED + "|" + RESET);
        }

        return bar.toString();
    }
}
