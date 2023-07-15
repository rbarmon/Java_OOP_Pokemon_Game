package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.pokemons.PokemonBase;

/**
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by:
 * <p>
 * TODO: Use this class to store Pokemon's weapons (special attack) permanently.
 * If a Pokemon needs to use a weapon, put it into that Pokemon's inventory.
 * @see Actor#getWeapon() method.
 * @see AttackAction uses getWeapon() in the execute() method.
 */
public class BackupWeapons extends Action {

    protected Actor target;

    public BackupWeapons(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        PokemonBase pokemon = (PokemonBase) actor;
        pokemon.checkWeaponEquipped(target);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "checks the weapon condition.";
    }
}
