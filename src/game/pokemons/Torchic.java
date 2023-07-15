package game.pokemons;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.CapturePokemon;
import game.actions.EvolutionAction;
import game.actions.InteractionAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.AutoEvolutionBehavior;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.weapons.Ember;
import game.utils.*;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by: Ian K. Felix
 */
public class Torchic extends PokemonBase {
    /**
     * behavior of pokemon
     */

    private int aliveTurns;

    /**
     * Constructor.
     */
    public Torchic() {
        this("Torchic", 'c', 100);
    }

    public Torchic(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Element.FIRE);
        this.addCapability(Favorite.SINGING);
        this.addCapability(Status.EVOLUTIONABLE);
        this.addCapability(Status.CATCHABLE);

        // HINT: add more relevant behaviours here
        this.behaviours.put(0, new AutoEvolutionBehavior());
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(10, new WanderBehaviour());

        aliveTurns = 0;
        this.registerInstance();
        this.registerAffection();
    }

    /**
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        Location location = map.locationOf(this);
        if (AffectionManager.getInstance().getAffectionPoint((PokemonBase)this) >= 100) {
            actions.add(new EvolutionAction(location.x(), location.y(), map));
        }
        return actions;
    }

    /**
     * By using behaviour loops, it will decide what will be the next action automatically.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        aliveTurns ++;
        System.out.println("-------alive turns change to " + aliveTurns);
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * @param isEquipping FIXME: develop a logic to toggle weapon (put a selected weapon to the inventory - used!);
     */
    public void toggleWeapon(boolean isEquipping) {
        if (isEquipping) {
            addItemToInventory(new Ember());
        } else {
            removeItemFromInventory(new Ember());
        }
    }

    @Override
    public void checkWeaponEquipped(Actor actor) {
        Location location = GameMapManager.getActorLocation(this);
        toggleWeapon(GameMapManager.checkElementAtPosition(location.x(), location.y(), Element.FIRE));
    }


    @Override
    public void dayEffect() {
        this.heal(20);
    }

    @Override
    public void nightEffect() { this.hurt(15);}

    public int getAliveTurns() {return aliveTurns;}

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratch");
    }
}
