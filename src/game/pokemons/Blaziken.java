package game.pokemons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.Blaze;
import game.weapons.Ember;
import game.weapons.FireSpin;
import game.utils.Element;
import game.utils.GameMapManager;

import java.util.Random;

public class Blaziken extends Torchic{
    /**
     * Constructor.
     *

     */
    public Blaziken() {
        super("Blaziken", 'z', 250);
    }

    private final Random rand = new Random();
    private int weaponType = 0;


    public void toggleWeapon(boolean isEquipping) {
        if (isEquipping) {
            weaponType = rand.nextInt(3);
            if (0 == weaponType) {
                addItemToInventory(new Ember());
            } else if (1 == weaponType) {
                addItemToInventory(new Blaze());
            } else if (2 == weaponType) {
                addItemToInventory(new FireSpin());
            } // random weapon
        } else {
            if (0 == weaponType) {
                removeItemFromInventory(new Ember());
            } else if (1 == weaponType) {
                removeItemFromInventory(new Blaze());
            } else if (2 == weaponType) {
                removeItemFromInventory(new FireSpin());
            } // remove weapon

        }
    }

    @Override
    public void checkWeaponEquipped(Actor actor) {
        Location location = GameMapManager.getActorLocation(this);
        toggleWeapon(GameMapManager.checkElementAtPosition(location.x(), location.y(), Element.FIRE));
    } // check weapon
}
