public class DiplomaticStrategy implements Strategy {
    @Override
    public void executeStrategy(Character character, GameState gameState) {
        System.out.println(character.getName() + " is engaging in diplomacy.");
        character.increaseInfluence(50);
    }
}