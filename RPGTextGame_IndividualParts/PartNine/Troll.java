import java.util.Random;

public class Troll extends Enemy {
    public Troll(Random rand) {
        super(createRandomTrollName(rand), 100, createTrollAttacks());
    }

    private static String createRandomTrollName(Random rand) {
        String name = "(Troll)";
        switch(rand.nextInt(3)) {
            case 0:
                name = "Zul'jin " + name;
                break;
            case 1:
                name = "Jin'zakk " + name;
                break;
            case 2:
                name = "Vol'jin " + name;
                break;

        }
        return name;
    }

    private static AttackType[] createTrollAttacks() {
        AttackType[] attackTypes = new AttackType[3];
        attackTypes[0] = new AttackType("Spear Throw", 3, 5, 0);
        attackTypes[1] = new AttackType("Voodoo", 3, 5, 0);
        attackTypes[2] = new AttackType("Stealthy Slash", 3, 5, 0);
        return attackTypes;
    }
}
