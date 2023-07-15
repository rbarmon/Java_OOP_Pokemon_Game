package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Element;

public class Hay extends Ground {

    public Hay() {
        super(',');
        this.addCapability(Element.GRASS);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
    }
}
