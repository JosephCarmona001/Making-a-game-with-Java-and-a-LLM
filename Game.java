import java.util.Scanner;

public class Game {
    private Player player;
    private Enemy boss;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Enter your hero's name: ");
        String name = scanner.nextLine();
        player = new Player(name);
        boss = new Enemy("Final Boss", 120, 15);
        
        System.out.println("Welcome, " + player.getName() + "! Your final battle begins now!");
        battle();
    }

    private void battle() {
        while (player.isAlive() && boss.getHealth() > 0) {
            System.out.println("\nYour Health: " + player.getHealth());
            System.out.println(boss.getName() + " Health: " + boss.getHealth());
            System.out.println("Choose an action: (1) Attack (2) Special Attack (3) Defend (4) Use Item");
            int choice = scanner.nextInt();

            if (choice == 1) {
                boss.takeDamage(player.attack());
            } else if (choice == 2) {
                player.specialAttack(boss);
            } else if (choice == 3) {
                player.defend(boss.attack());
            } else if (choice == 4) {
                player.useItem();
            }

            if (boss.getHealth() <= 0 && !boss.isSecondPhase()) {
                boss.enterSecondPhase();
            }
            
            if (boss.getHealth() > 0) {
                player.takeDamage(boss.attack());
            }
        }
        
        System.out.println(player.isAlive() ? "You win!" : "You lose.");
    }
}

