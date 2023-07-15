package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Candy;
import game.pokemons.Treecko;
import game.time.TimePerceptionManager;
import game.time.TimePeriod;
import game.utils.Element;
import game.utils.GameMapManager;
import game.utils.Utils;

public class Tree extends SpawningGround {

    /**
     * Constructor.
     */
    public Tree() {
        super('T', 15);
        this.addCapability(Element.GRASS);
    }

    /**
     * @param location The location of the Ground Current, location of this object
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (Utils.chanceTrigger(getSpawnChance()) && !location.containsAnActor() && GameMapManager.checkSurroundingElements(location, Element.GRASS, 1)) {
            GameMapManager.addActor(location, new Treecko());
        }
        if(TimePerceptionManager.getInstance().getCurrentTimePeriod() == TimePeriod.DAY){
            if(Utils.chanceTrigger(5)){
                GameMapManager.addItem(location, new Candy());
            }
        }else{
            if(Utils.chanceTrigger(10)){
                if(Utils.chanceTrigger(50)){
                    GameMapManager.expand(location, new Tree());
                }else{
                    GameMapManager.expand(location, new Hay());
                }

            }
        }
    }
}
