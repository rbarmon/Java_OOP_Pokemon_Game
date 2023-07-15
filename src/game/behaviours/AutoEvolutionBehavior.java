package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.BackupWeapons;
import game.actions.EvolutionAction;
import game.pokemons.Torchic;
import game.utils.GameMapManager;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class AutoEvolutionBehavior implements Behaviour {

    /**
     *  HINT: develop a logic to check surrounding, check elements, and return an action to evolute.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Find surrounding attack targets
        Torchic toEvo = (Torchic) actor;
        if (!toEvo.getName().equals("Torchic") && !toEvo.getName().equals("Combusken")) {
            return null;
        }
        // If there is an attack target

        if (toEvo.getAliveTurns() >= 20) {
            Location location = map.locationOf(toEvo);
            return new EvolutionAction(location.x(), location.y(), map); // 20 turns alone pokemon evolute
        }

        return null;
    }
}
