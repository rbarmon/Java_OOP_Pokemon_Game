package game.pokemons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.Blaze;
import game.weapons.Ember;
import game.utils.Element;
import game.utils.GameMapManager;

import java.util.Random;

public class Combusken extends Torchic{
    /**
     * Constructor.
     *

     */
    public Combusken() {
        super("Combusken", 'k', 150);
    }

    private final Random rand = new Random();
    private boolean isEmberEquipped = false;


    public void toggleWeapon(boolean isEquipping) {
        if (isEquipping) {
            if (rand.nextBoolean()) {
                addItemToInventory(new Ember());
                isEmberEquipped = true;
            } else {
                addItemToInventory(new Blaze());
                isEmberEquipped = false;
            } // random weapon
        } else {
            if (isEmberEquipped) {
                removeItemFromInventory(new Ember());
            } else {
                removeItemFromInventory(new Blaze());
            } // remove weapon
        }
    }

    @Override
    public void checkWeaponEquipped(Actor actor) {
        Location location = GameMapManager.getActorLocation(this);
        toggleWeapon(GameMapManager.checkElementAtPosition(location.x(), location.y(), Element.FIRE));
    } // check weapon


}
