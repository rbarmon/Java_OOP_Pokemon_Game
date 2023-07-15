package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.BackupWeapons;
import game.pokemons.Torchic;
import game.utils.Element;
import game.utils.ElementsHelper;
import game.utils.GameMapManager;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class AttackBehaviour implements Behaviour {

    /**
     *  HINT: develop a logic to check surrounding, check elements, and return an action to attack that opponent.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Find surrounding attack targets
        Actor target = GameMapManager.checkSurroundingActors(actor, GameMapManager.getActorLocation(actor));
        // If there is an attack target
        if(target != null) {
            // check attack whether the weapon can be equipped
            Action backupWeapons = new BackupWeapons(target);
            backupWeapons.execute(actor, map);
            // Start the attack
            return new AttackAction(target, "here");
        }
        return null;
    }
}
