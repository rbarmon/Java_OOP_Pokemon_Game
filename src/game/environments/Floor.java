package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Element;
import game.utils.GameType;
import game.utils.GroundCharacteristic;

/**
 * A class that represents the floor inside a building.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
	/**
	 * Constructors
	 */
	public Floor() {
		super('_');
		this.addCapability(GroundCharacteristic.STRUCTURAL);
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(GameType.POKEMON);
	}
}
