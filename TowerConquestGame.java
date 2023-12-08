import java.util.Scanner;

public class TowerConquestGame {
    private GameState gameState;
    private Character playerCharacter;
    private int weekCounter = 0;
    private Strategy playerStrategy;
    private int count;

    public TowerConquestGame() {
        gameState = new GameState();
        initializeGameElements();
    }

    private void initializeGameElements() {
        // Create characters
        Character hero = new Character("Hero", 100, 10);
        Character villain = new Character("Villain", 100, 15);

        // Add characters to GameState
        gameState.addCharacter(hero);
        gameState.addCharacter(villain);

        // Initialize player character
        playerCharacter = new Character("Player", 100, 10);
        gameState.addCharacter(playerCharacter);
    }

    private void allocatePoints() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have 100 points to allocate to Health, Strength, Wealth, and Influence.");
        System.out.print("Enter points for Health: ");
        int healthPoints = scanner.nextInt();
        count = count + healthPoints;
        System.out.print("Enter points for Strength: ");
        int strengthPoints = scanner.nextInt();
        count = count + strengthPoints;
        System.out.print("Enter points for Wealth: ");
        int wealthPoints = scanner.nextInt();
        count = count + wealthPoints;
        System.out.print("Enter points for Influence: ");
        int influencePoints = scanner.nextInt();
        count = count + influencePoints;

        if (count <= 100) {
            playerCharacter.setHealth(healthPoints);
            playerCharacter.setStrength(strengthPoints);
            playerCharacter.setWealth(wealthPoints);
            playerCharacter.setInfluence(influencePoints);
        }
        else {
            System.out.println("Your point allocation exceeds 100!");
            count = 0;
            allocatePoints();;
        }

    }

    private void assignStrategy(String strategyName) {
        switch (strategyName.toLowerCase()) {
            case "aggressive":
                playerStrategy = new AggressiveStrategy();
                break;
            case "defensive":
                playerStrategy = new DefensiveStrategy();
                break;
            case "diplomatic":
                playerStrategy = new DiplomaticStrategy();
                break;
            default:
                System.out.println("Invalid strategy. Defaulting to Aggressive.");
                playerStrategy = new AggressiveStrategy();
        }
        // Ensure playerCharacter is not null and then set the strategy
        if (playerCharacter != null) {
            playerCharacter.setStrategy(playerStrategy);
        }
    }

    private void processWeek() {
        // Implement the logic for what happens each week
        playerStrategy.executeStrategy(playerCharacter, gameState);
        gameState.triggerRandomEvent(); // Random events can be triggered here
        gameState.displayStatus(); // Display current status
    }

    private boolean checkGameOver() {
        // Check if any resource falls to zero or below
        if (playerCharacter.getHealth() <= 0 ||
            playerCharacter.getStrength() <= 0 ||
            playerCharacter.getWealth() <= 0 ||
            playerCharacter.getInfluence() <= 0) {
            return true; // Game over condition met
        }
        return false; // Game continues
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tower Conquest!");
        System.out.print("Enter your character's name: ");
        String playerName = scanner.nextLine();
        playerCharacter.setName(playerName);
    
        System.out.println("Enter the name of your kingdom:");
        String kingdomName = scanner.nextLine();
        Location kingdomLocation = new Location(kingdomName); // Create a Location object
        playerCharacter.setCurrentLocation(kingdomLocation);


        allocatePoints();

        // Strategy selection
        System.out.print("Choose your strategy (Aggressive, Defensive, Diplomatic): ");
        String strategyName = scanner.nextLine();
        assignStrategy(strategyName);



        System.out.println("Game starts now. Survive as many weeks as you can!");

        while (true) {
            weekCounter++;
            System.out.println("Week " + weekCounter + ":");
            playerCharacter.displayResources();
            processWeek();
            // Game logic for each week

            if (checkGameOver()) {
                System.out.println("Game Over! You survived " + weekCounter + " weeks.");
                break;
            }

            System.out.println("Enter command for this week: strengthen, heal, mine, debate, exit");
            String command = scanner.nextLine();
            switch (command.toLowerCase()) {
                case "strengthen":
                    playerCharacter.setStrength(playerCharacter.getStrength() + 10); // Correct usage
                    System.out.println("Your army spent the week conditioning +10 Strength");
                    break;
                case "heal":
                    // Assuming you have a method to increase health
                    playerCharacter.increaseHealth(10); // Make sure this method exists in Character class
                    System.out.println("You spent the week recovering +10 Health");
                    break;
                case "mine":
                    playerCharacter.increaseWealth(20); // Assuming a method to increase wealth
                    System.out.println("Your miners found gold +20 Wealth");
                    break;
                case "debate":
                    playerCharacter.increaseInfluence(15); // Assuming a method to increase influence
                    System.out.println("You hosted a successful debate +15 Influence");
                    break;
                case "exit":
                    System.out.println("Exiting game after " + weekCounter + " weeks.");
                    return; // Exit the game loop
                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }

        }

        scanner.close();
    }

    public static void main(String[] args) {
        TowerConquestGame game = new TowerConquestGame();
        game.startGame();
    }
}