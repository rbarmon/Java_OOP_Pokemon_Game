package game.utils;

/**
 * Pokemon's favorite action
 */
public enum Favorite {

    SINGING("Singing"), CHESTPOUNDING("Chest Pounding"), DANCING("Dancing");

    private final String label;

    Favorite(String label) {
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
