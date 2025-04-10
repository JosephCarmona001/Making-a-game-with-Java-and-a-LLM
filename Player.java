import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private int specialUses;
    private Scanner scanner;
    private Random random;
    private ArrayList<String> inventory;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.specialUses = 2; // Limit on special attack usage
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.inventory = new ArrayList<>();
        inventory.add("Health Potion");
        inventory.add("Attack Buff");
        inventory.add("Defense Buff");
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getSpecialUses() {
        return specialUses;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int attack() {
        System.out.println("Choose an attack style:");
        System.out.println("1. Quick Strike (15 damage, higher speed)");
        System.out.println("2. Heavy Slash (25 damage, slower reaction)");
        System.out.println("3. Precise Stab (20 damage, ignores some defense)");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return 15;
            case 2:
                return 25;
            case 3:
                return 20;
            default:
                return 0;
        }
    }

    public void defend(int enemyAttack) {
        System.out.println("Choose a defense style:");
        System.out.println("1. Block (Reduces damage by 50%)");
        System.out.println("2. Dodge (50% chance to take no damage, otherwise full damage)");
        System.out.println("3. Counterattack (Take full damage but deal 10 damage back)");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                takeDamage(enemyAttack / 2);
                break;
            case 2:
                if (random.nextInt(100) < 50) {
                    System.out.println("You dodged the attack!");
                } else {
                    takeDamage(enemyAttack);
                }
                break;
            case 3:
                takeDamage(enemyAttack);
                System.out.println("You counterattack!");
                break;
            default:
                takeDamage(enemyAttack);
                break;
        }
    }

    public int specialAttack(Enemy enemy) {
        if (specialUses <= 0) {
            System.out.println("You have no special attacks left!");
            return 0;
        }
        
        specialUses--;
        System.out.println("Choose a special attack:");
        System.out.println("1. Fire Blast (30 damage, chance to burn enemy for extra damage over time)");
        System.out.println("2. Ice Spike (20 damage, slows enemy reducing attack power)");
        System.out.println("3. Thunder Strike (25 damage, chance to stun enemy)");
        System.out.println("4. Healing Light (Heals player for 20 health)");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                enemy.takeDamage(30);
                return 0;
            case 2:
                enemy.takeDamage(20);
                return 0;
            case 3:
                enemy.takeDamage(25);
                return 0;
            case 4:
                health += 20;
                return 0;
            default:
                return 0;
        }
    }

    public void useItem() {
        if (inventory.isEmpty()) {
            System.out.println("No items left in inventory!");
            return;
        }
        
        System.out.println("Choose an item to use:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i));
        }
        int choice = scanner.nextInt();
        
        if (choice > 0 && choice <= inventory.size()) {
            String item = inventory.remove(choice - 1);
            if (item.equals("Health Potion")) {
                health += 70;
                System.out.println("You used a Health Potion! +50 HP");
            } else if (item.equals("Attack Buff")) {
                System.out.println("Your attack power has increased for this battle!");
            } else if (item.equals("Defense Buff")) {
                System.out.println("Your defense has increased for this battle!");
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }
}
