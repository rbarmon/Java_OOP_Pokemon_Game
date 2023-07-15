package game.utils;

import game.time.TimePerceptionManager;

public interface Affection {
    default void registerAffection() {
        AffectionManager.getInstance().registerPokemon(this);
    }
}
