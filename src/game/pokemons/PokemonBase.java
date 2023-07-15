package game.pokemons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.CapturePokemon;
import game.actions.InteractionAction;
import game.behaviours.Behaviour;
import game.time.TimePerception;
import game.utils.Affection;
import game.utils.AffectionManager;
import game.utils.GameType;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * pokemon class, actor, Can be affected by time, will be feeling effect
 */
public abstract class PokemonBase extends Actor implements Affection, TimePerception {
    protected final Map<Integer, Behaviour> behaviours = new LinkedHashMap<>();
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public PokemonBase(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(GameType.POKEMON);
    }

    /**
     * If I attack this object, check will I be equipped with a weapon?
     *
     * @param actor Target of the attack
     */
    public abstract void checkWeaponEquipped(Actor actor);

    @Override
    public String toString() {
        return name + printHp(); //+ "(AP:" + AffectionManager.getInstance().getAffectionPoint(this) + ")";
    }

    public String getName() {
        return name;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new InteractionAction(this));
        actions.add(new CapturePokemon(this));
        return actions; // Interaction with pokemon to improve ap
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action; // choose the behaviour next
        }
        return new DoNothingAction();
    }

}
