package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.utils.AffectionManager;

public class MasterBall extends Pokeball {
    public MasterBall() {
        super("MasterBall", '0', true);
    }

    /**
     *
     *  The Master Ball will raise the affection of the captured Pokemon to100
     *
     * @param pokemon Pokemon which I want to store
     *
     */
    @Override
    public void capturePokemon(Actor pokemon) {
        super.capturePokemon(pokemon);
        AffectionManager.getInstance().increaseAffection(pokemon, 100);
    }
}
