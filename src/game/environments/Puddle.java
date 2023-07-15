package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.time.TimePerceptionManager;
import game.time.TimePeriod;
import game.utils.Element;
import game.utils.GameMapManager;
import game.utils.Utils;

public class Puddle extends Ground {
    /**
     * Constructor.
     *
     */
    public Puddle() {
        super('~');
        this.addCapability(Element.WATER);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        if(TimePerceptionManager.getInstance().getCurrentTimePeriod() == TimePeriod.DAY){
            if(Utils.chanceTrigger(10)){
                GameMapManager.destroyToNewGround(location, new Dirt());
            }
        }else{
            if(Utils.chanceTrigger(10)){
                GameMapManager.expand(location, this);
            }
        }
    }
}
