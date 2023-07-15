package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Element;
import game.utils.GroundCharacteristic;

public class Wall extends Ground {

	public Wall() {
		super('#');
		this.addCapability(GroundCharacteristic.STRUCTURAL);
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
