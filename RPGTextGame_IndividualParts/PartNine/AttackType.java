import java.util.Random;

public class AttackType {
    private String attackName;
    private int damageValueMin;
    private int damageValueMax;
    private int manaCost;

    public AttackType(String attackName, int damageValueMin, int damageValueMax, int manaCost) {
        this.attackName = attackName;
        this.damageValueMin = damageValueMin;
        this.damageValueMax = damageValueMax;
        this.manaCost = manaCost;
    }

    public String getAttackName() {
        return attackName;
    }

    public int getManaCost() {
        return manaCost;
    }

    @Override
    public String toString() {
        return attackName + " (" + damageValueMin + "-" + damageValueMax + ", costs " + manaCost + ")";
    }

    public int getDamageValueInRange(Random rand) {
        return rand.nextInt(damageValueMax - damageValueMin + 1) + damageValueMin;
    }
}
