package game.utils;

/**
 * Actor type
 */
public enum GameType {
    NPC_OAK("NPC_OAK"),
    NPC_SHOPKEEPER("NPC_ShopKeeper"),
    Player("Player"),
    POKEMON("Pokemon");

    private final String label;

    GameType(String label) {
        this.label = label;
    }

    /**
     * @return the label text
     */
    @Override
    public String toString() {
        return label;
    }
}
