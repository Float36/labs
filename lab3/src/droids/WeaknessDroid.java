package droids;

public class WeaknessDroid extends Droid {
    private double damageReduction;

    public WeaknessDroid(String name, int health, int damage, double critChance, double damageReduction){
        super(name, health, damage, critChance);
        this.damageReduction = damageReduction;
    }

    public void attack(Droid target){
        super.attack(target);
        System.out.println(getName() + " наклав слабкість на " + target.getName() + " на 3 наступні ходи");
        target.setWeaknessDuration(3);
        target.setTakenDamageReduction(damageReduction);
    }

    public void printDroidFeature(){
        System.out.println("Дроїд-ослаблювач після своєї атаки накладає на ворога ефект слабкості на 3 його наступні атаки (зменшує шкоду яку завдає ворог на певну кількість %)");
    }
}
