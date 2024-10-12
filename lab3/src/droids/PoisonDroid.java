package droids;

public class PoisonDroid extends Droid{
    private int poisonDamage;

    public PoisonDroid(String name, int health, int damage, double critChance, int poisonDamage) {
        super(name, health, damage, critChance);
        this.poisonDamage = poisonDamage;
    }

    public void attack(Droid target){
        super.attack(target);
        System.out.println(getName() + " отруїв дроїда " + target.getName() + " на " + poisonDamage + " шкоди на 3 наступні ходи дроїда");
        target.setPoisonDuration(3);
        target.setTakenPoisonDamage(poisonDamage);
    }

    public void printDroidFeature(){
        System.out.println("Отруйний дроїд накладає ефект отравлення на кожного, кого він атакує. Яд б'є ворожого дроїда після кожної його атаки 3 рази");
    }

    public int getPoisonDamage() {
        return poisonDamage;
    }
}
