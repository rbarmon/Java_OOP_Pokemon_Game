package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.pokemons.Mudkip;
import game.utils.Element;
import game.utils.GameMapManager;
import game.utils.Utils;
/**
 * Waterfall Water Properties 20% Generate Mudkip
 */
public class Waterfall extends SpawningGround {

    /**
     * Constructors
     */
    public Waterfall() {
        super('W', 20);
        this.addCapability(Element.WATER);
    }
    /**
     * @param location The location of the Ground, Current location of this object
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        // if able to generate Pokemon
        if (Utils.chanceTrigger(getSpawnChance()) && !location.containsAnActor() && GameMapManager.checkSurroundingElements(location, Element.WATER, 2)) {
            GameMapManager.addActor(location, new Mudkip());
        }
    }
}
