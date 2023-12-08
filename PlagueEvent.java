public class PlagueEvent implements Event {
    @Override
    public void triggerEvent(Character character) {
        System.out.println("A severe plague has struck " + character.getCurrentLocation().display() + ", weakening everyone in it's wake.");
        character.decreaseHealth(10);
        character.decreaseStrength(5);
    }
}