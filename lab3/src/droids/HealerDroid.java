package droids;

public class HealerDroid extends Droid{
    private int healAmount;

    public HealerDroid(String name, int health, int damage, double critChance, int healAmount){
        super(name, health, damage, critChance);
        this.healAmount = healAmount;
    }

    public void heal(Droid target){
        if (target.isAlive()){
            target.setHealth(target.getHealth() + healAmount);
            System.out.println(this.getName() + " полікував " +  target.getName() + " на " + healAmount + " здоров'я");
        }
    }

    public int getHealAmount(){
        return healAmount;
    }

    public void showDroidInfo() {
        System.out.println(getName() + " - Здоров'я: " + getMaxHealth() + " Щкода: " + getDamage() + " Лікування: " + getHealAmount());
    }

    public void printDroidFeature(){
        System.out.println("Дроїд-лікар перед кожною своєю атакою лікує дроїда з найменшою кількістю здоров'я");
    }
}
