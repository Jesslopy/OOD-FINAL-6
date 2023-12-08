public class FamineEvent implements Event {
    @Override
    public void triggerEvent(Character character) {
        System.out.println("A severe famine has struck " + character.getCurrentLocation().display() + ", weakening everyone.");
        character.decreaseHealth(10);
        character.decreaseStrength(5);
        character.loseResources("food", 20); // Decrease food resources due to famine
    }
}