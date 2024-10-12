package droids;
import java.util.List;

public class BomberDroid extends Droid {
    public BomberDroid(String name, int health, int damage, double critChace){
        super(name, health, damage, critChace);
    }

    public void attack(List<Droid> enemyTeam) {
        int damageDealt = getDamage();

        if (isCriticalHit()){
            damageDealt *= 2;
            System.out.println(getName() + " атакує критично і наносить кожному по " + damageDealt + " шкоди!");
        }else{
            System.out.println(getName() + " атакує і наносить кожному по " + damageDealt + " шкоди!");
        }

        for (Droid enemy : enemyTeam){
            enemy.takeDamage(damageDealt);
        }
    }

    public void printDroidFeature(){
        System.out.println("Дроїд-бомбер кидає гранати і завдає шкоди усім противникам");
    }
}
