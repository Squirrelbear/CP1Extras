import java.util.Random;

public class Wolf extends Enemy {
    public Wolf(Random rand) {
        super(createRandomWolfName(rand), 100, createWolfAttacks());
    }

    private static String createRandomWolfName(Random rand) {
        String name = "(Wolf)";
        switch(rand.nextInt(3)) {
            case 0:
                name = "Shadow " + name;
                break;
            case 1:
                name = "Goremaw " + name;
                break;
            case 2:
                name = "Ironjaw " + name;
                break;

        }
        return name;
    }

    private static AttackType[] createWolfAttacks() {
        AttackType[] attackTypes = new AttackType[3];
        attackTypes[0] = new AttackType("Bite", 3, 5, 0);
        attackTypes[1] = new AttackType("Vicious Bite", 3, 5, 0);
        attackTypes[2] = new AttackType("Howl", 3, 5, 0);
        return attackTypes;
    }
}
