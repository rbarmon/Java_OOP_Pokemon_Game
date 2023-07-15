package game.pokemons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.CapturePokemon;
import game.actions.InteractionAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.weapons.WaterBlast;
import game.time.TimePerception;
import game.utils.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class Mudkip extends PokemonBase {


    public Mudkip() {
        super("Mudkip", 's', 100);
        this.addCapability(Element.WATER);
        this.addCapability(Favorite.CHESTPOUNDING);
        this.addCapability(Status.CATCHABLE);
        this.addCapability(Status.EVOLUTIONABLE);

        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(10, new WanderBehaviour());

        this.registerInstance();
        this.registerAffection();
    }




    @Override
    public void dayEffect() {
        this.hurt(15);
    }

    @Override
    public void nightEffect() {
        this.heal(10);
    }

    public void toggleWeapon(boolean isEquipping) {
        if (isEquipping) {
            addItemToInventory(new WaterBlast());
        } else {
            removeItemFromInventory(new WaterBlast());
        }
    }

    public void checkWeaponEquipped(Actor actor) {
        Location location = GameMapManager.getActorLocation(this);
        toggleWeapon(GameMapManager.checkElementAtPosition(location.x(), location.y(), Element.WATER) || actor.hasCapability(Element.FIRE));
    }

    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackle");
    }
}
