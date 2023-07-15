package game.environments;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.items.PokeEgg;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Incubator extends Item {
    /**
     * Constructor.
     *
     */
    public Incubator() {
        super("Incubator", 'x', false);
        myEggs = new ArrayList<>();
    }

    private ArrayList<PokeEgg> myEggs; // create a ArrayList to store the egg

    public void AddEgg(PokeEgg egg) {
        myEggs.add(egg);
    } // Add egg to ArrayList

    public void tick(Location currentLocation) {
        for (int i = 0; i < myEggs.size(); i++) {
            PokeEgg egg = myEggs.get(i);
            egg.tick(currentLocation); // check empty location
            if (egg.hasSpawned()) {
                myEggs.remove(i);  // if have empty location remove the egg
                i--;
            }
        }
    }

}
