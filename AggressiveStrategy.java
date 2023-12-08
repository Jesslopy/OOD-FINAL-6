

public class AggressiveStrategy implements Strategy {
    @Override
    public void executeStrategy(Character character, GameState gameState) {
        System.out.println(character.getName() + " is using an aggressive strategy.");
        character.increaseWealth(10);
        character.increaseInfluence(10);
    }
}
