public class EnhancedPlayer {

    private String fullName;
    private int healthPercentage;
    private String weapon;

    //we can create a new EnhancedPlayer, by just passing the name of the player.
    //And we're guaranteeing now, that the name, the health, and the weapon, are initialized
    //when the class is created.
    public EnhancedPlayer(String fullName) {
        this(fullName, 100, "Sword");
    }

    public EnhancedPlayer(String fullName, int health, String weapon) {
        this.fullName = fullName;
        //this.health = health;
        //to have more control over how a new player is created: - > additional validation
        if (health <= 0) {
            this.healthPercentage = 1;
        } else if (health > 100) {
            this.healthPercentage = 100;
        } else {
            this.healthPercentage = health;
        }

        this.weapon = weapon;
    }

    public void looseHealth(int damage) {
        healthPercentage = healthPercentage - damage;
        if (healthPercentage <= 0) {
            System.out.println("Player knocked out of game");
        }
    }

    public int healthRemaining() {
        return healthPercentage;
    }

    public void restoreHealth(int extraHealth) {
        healthPercentage = healthPercentage + extraHealth;
        if (healthPercentage > 100) {
            System.out.println("Player restored to 100%");
            healthPercentage = 100;
        }
    }
}
