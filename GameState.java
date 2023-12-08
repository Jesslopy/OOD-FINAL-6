import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState {
    private List<Character> characters;
    //private List<Event> events;
    private List<Observer> observers = new ArrayList<>();

    private double resourceMultiplier;
    private double invasionChance;
    private double famineChance;
    private double plagueChance;

    public GameState() {
        characters = new ArrayList<>();
        //events = new ArrayList<>();
        setResourceMultiplier(1.0);
        setInvasionChance(0.3);
        setFamineChance(0.3);
        setPlagueChance(0.2);
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void processEvent(String eventType, Character character) {
        Event event = EventFactory.createEvent(eventType);
        if (event != null) {
            System.out.println("Event triggered: " + eventType);
            event.triggerEvent(character);
            // Additional details about the event's effects can be printed here
            System.out.println("Effect on " + character.getName() + ":");
            // Example: Print character's current health and other affected attributes
            System.out.println("Health: " + character.getHealth());
            // Add other relevant attributes like strength, wealth, etc.
        } else {
            System.out.println("No such event exists.");
        }
        notifyObservers();
    }

    public void setResourceMultiplier(double multiplier) {
        this.resourceMultiplier = multiplier;
    }


    public void setInvasionChance(double chance) {
        this.invasionChance = chance;
    }

    public void setFamineChance(double chance) {
        this.famineChance = chance;
    }

    public void setPlagueChance(double chance) {
        this.plagueChance = chance;
    }

    public void triggerRandomEvent() {
        Random random = new Random();
        double roll = random.nextDouble();

        if (roll < invasionChance) {
            processEvent("Invasion", getRandomCharacter());
        } else if (roll < invasionChance + famineChance) {
            processEvent("Famine", getRandomCharacter());
        } else if (roll < invasionChance + famineChance + plagueChance) {
            processEvent("Plague", getRandomCharacter());
        }
    }

    private Character getRandomCharacter() {
        if (characters.size() > 0) {
            int index = new Random().nextInt(characters.size());
            return characters.get(index);
        }
        return null;
    }

    // Display current game state
    public void displayStatus() {
        System.out.println("Current Game State:");
        System.out.println("Resource Multiplier: " + resourceMultiplier);
        System.out.println("Invasion Chance: " + invasionChance);
        System.out.println("Famine Chance: " + famineChance);
        System.out.println("Plague Chance: " + plagueChance);
        // Add more status details as needed
    }

    public double getResourceMultiplier() {
        return resourceMultiplier;
    }

    public void triggerWar() {
        System.out.println("War has been triggered!");
        WarEvent warEvent = new WarEvent();
        // Trigger war event for each character or specific characters
        for (Character character : characters) {
            warEvent.triggerEvent(character);
        }
    }

    public double getInvasionChance() {
        // Return the current invasion chance
        return invasionChance;
    }
}
