public class Enemy {
    private String name;
    private int health;
    private int attackPower;
    private boolean stunned;
    private boolean secondPhase;
    private boolean burning;

    public Enemy(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.stunned = false;
        this.secondPhase = false;
        this.burning = false;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isSecondPhase() {
        return secondPhase;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public int attack() {
        if (stunned) {
            stunned = false;
            return 0;
        }
        return attackPower;
    }

    public void enterSecondPhase() {
        if (!secondPhase) {
            secondPhase = true;
            health = 150; // Increase health
            attackPower += 10; // Increase attack power
            System.out.println(name + " has entered its second phase! It is now stronger!");
        }
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public void reduceAttackPower(int amount) {
        this.attackPower -= amount;
    }

    public void stun() {
        this.stunned = true;
    }
}
