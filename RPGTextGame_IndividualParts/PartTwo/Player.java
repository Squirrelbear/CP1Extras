public class Player {
    private String playerName;
    private int playerHealth;
    private int playerMaxHealth;
    private int playerMana;
    private int playerMaxMana;

    public Player(String playerName) {
        playerHealth = playerMaxHealth = playerMaxMana = playerMana = 100;
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return "Player Status: " + playerName + " has "
                + playerHealth + "/" + playerMaxHealth
                + " HP and " + playerMana + "/" + playerMaxMana
                + " Mana.";
    }
}
