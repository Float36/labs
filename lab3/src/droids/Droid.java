package droids;

import java.util.Random;

public class Droid {
    private String name;                        // ім'я дроїда
    private int health;                         // поточне здоров'я
    private int damage;                         // шкода дроїда
    private int maxHealth;                      // максимальна кількість здоров'я
    private double critChance;                  // шанс критичного удару
    // ефекти на дроїді
    private int poisonDuration = 0;              // кількість ударів отрутою
    private int takenPoisonDamage = 0;           // кількість шкоди від отруєння
    private int weaknessDuration = 0;            // кількість ослаблених ударів
    private double takenDamageReduction = 0;     // відсоток на який зменшується сила удару

    public Droid(String name, int health, int damage, double critChance){
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.maxHealth = health;
        this.critChance = critChance;
        this.poisonDuration = 0;
        this.takenPoisonDamage = 0;
        this.weaknessDuration = 0;
        this.takenDamageReduction = 0;
    }

    // повертає чи дроїд вдарив уритично
    public boolean isCriticalHit() {
        Random rand = new Random();
        return rand.nextDouble() < critChance;
    }

    // мотод отримання шкоди
    public void takeDamage(int damage){
        health -= damage;
        if (health <= 0)
            health = 0;
    }

    // метод отримання шкоди від яду
    public void takePoisonDamage(int poisonDamage){
        health -= poisonDamage;
        if (health <= 0)
            health = 0;
        setPoisonDuration(getPoisonDuration() - 1);
    }

    // метод атаки дроїда
    public void attack(Droid target) {
        int damageDealt = damage;
        if (getWeaknessDuration() > 0){
            System.out.print("Атака слабша на " + getTakenDamageReduction()*100 + "%. ");
            damageDealt = (int) (damageDealt * (1 - getTakenDamageReduction()));
            setWeaknessDuration(getWeaknessDuration() - 1);
        }

        // Якщо критичний удар, додаємо подвоєну шкоду
        if (isCriticalHit()) {
            damageDealt *= 2;
            System.out.println(name + " атакує критично і наносить " + damageDealt + " шкоди дроїду " + target.getName());
        } else {
            System.out.println(name + " атакує і наносить " + damageDealt + " шкоди дроїду " + target.getName());
        }

        target.takeDamage(damageDealt);
        // після кожного удару, якщо дрон отруєний, він буде отримувати шкоду
        if (getPoisonDuration() > 0){
            takePoisonDamage(getTakenPoisonDamage());
        }

    }

    public void setHealth(int health) {
        this.health = health; // максимальне здоров'я обмежене 100
        if (this.health > maxHealth){
            this.health = maxHealth;
        }
    }

    public boolean isAlive(){
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getPoisonDuration() {
        return poisonDuration;
    }

    public int getTakenPoisonDamage() {
        return takenPoisonDamage;
    }

    public int getWeaknessDuration() {
        return weaknessDuration;
    }

    public double getTakenDamageReduction() {
        return takenDamageReduction;
    }

    public void setPoisonDuration(int poisonDuration){
        this.poisonDuration = poisonDuration;
    }

    public void setTakenPoisonDamage(int takenPoisonDamage) {
        this.takenPoisonDamage = takenPoisonDamage;
    }

    public void setWeaknessDuration(int weaknessDuration) {
        this.weaknessDuration = weaknessDuration;
    }

    public void setTakenDamageReduction(double takenDamageReduction) {
        this.takenDamageReduction = takenDamageReduction;
    }

    public void showDroidInfo() {
        System.out.println(getName() + " - Здоров'я: " + getMaxHealth() + " Шкода: " + getDamage() + " Шанс критичного удару: " + critChance * 100 + "%.");
    }
}
