package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.environments.Incubator;
import game.items.PokeEgg;
import game.utils.GameMapManager;

public class DropEggAction extends DropItemAction {

    /**
     * DropEggAction object
     */
    private final Item MyItem;
    public DropEggAction(Item item) {
        super(item);
        MyItem = item; // creative a new item
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println("drop egg");

        actor.removeItemFromInventory(MyItem); // remove PokeEgg from inventory
        Incubator incu = GameMapManager.getIncu();
        incu.AddEgg((PokeEgg) MyItem); // Add egg to ArrayList
        return menuDescription(actor);

    }

}
