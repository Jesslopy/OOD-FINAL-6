public class DefensiveStrategy implements Strategy {
    @Override
    public void executeStrategy(Character character, GameState gameState) {
        System.out.println(character.getName() + " is using a defensive strategy.");
        character.increaseMorale(20);
        character.decreaseStrength(5);
        character.loseResources("gold", 10);
    }
}