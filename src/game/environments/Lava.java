package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.time.TimePerceptionManager;
import game.time.TimePeriod;
import game.utils.Element;
import game.utils.GameMapManager;
import game.utils.Utils;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Lava extends Ground {
    /**
     * Constructor.
     */
    public Lava() {
        super('^');
        this.addCapability(Element.FIRE);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        // If it is daytime, Expansion
        if(TimePerceptionManager.getInstance().getCurrentTimePeriod() == TimePeriod.DAY){
            if(Utils.chanceTrigger(10)){
                GameMapManager.expand(location, this);
            }
        }else{
            if(Utils.chanceTrigger(10) && !GameMapManager.isAnActorAt(location)){
                GameMapManager.destroyToNewGround(location, new Dirt());
            }
        }
    }
}
