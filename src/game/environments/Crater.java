package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.pokemons.Torchic;
import game.utils.Element;
import game.utils.GameMapManager;
import game.utils.Utils;


/**
 * Crater Fire Properties 10%Generate torchic
 */
public class Crater extends SpawningGround {

    /**
     * Constructors
     */
    public Crater() {
        super('C', 10);
        this.addCapability(Element.FIRE);
    }

    /**
     * @param location The location of the Ground , Current location of this object
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        // First determine if this probability is triggered
        if (Utils.chanceTrigger(this.getSpawnChance()) && !location.containsAnActor()) {
            // Generate a TORCHIC in this location
            GameMapManager.addActor(location, new Torchic());
        }
    }
}
