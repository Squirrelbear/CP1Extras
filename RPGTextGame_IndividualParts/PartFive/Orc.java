import java.util.Random;

public class Orc extends Enemy {
    public Orc(Random rand) {
        super(createRandomOrcName(rand), 100, createOrcAttacks());
    }

    private static String createRandomOrcName(Random rand) {
        String name = "(Orc)";
        switch(rand.nextInt(3)) {
            case 0:
                name = "Draka " + name;
                break;
            case 1:
                name = "Thrall " + name;
                break;
            case 2:
                name = "Garrosh " + name;
                break;

        }
        return name;
    }

    private static AttackType[] createOrcAttacks() {
        AttackType[] attackTypes = new AttackType[3];
        attackTypes[0] = new AttackType("Swing", 3, 5, 0);
        attackTypes[1] = new AttackType("Thwack", 3, 5, 0);
        attackTypes[2] = new AttackType("Roar", 3, 5, 0);
        return attackTypes;
    }
}
