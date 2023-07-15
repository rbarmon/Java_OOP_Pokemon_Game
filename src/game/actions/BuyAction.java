package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.*;
import game.pokemons.Torchic;
import game.utils.Favorite;

import java.util.ArrayList;
import java.util.List;

public class BuyAction extends Action {

    private final Actor target;

    public BuyAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Check the inventory  get the number of candies
        List<Item> items = actor.getInventory();
        List<Candy> candies = new ArrayList<>();

        Display display = new Display();
        display.println("input: 1:Buy TorchicEgg, 2:Buy TreeckoEgg, 3:Buy MudkipEgg, 4:Buy MasterBall, 5:Buy GreatBall,");// buy Torchic exchange to PokeEgg
        char c = display.readChar();
        display.println("" + c);

        for (Item e : items) {
            if (e instanceof Candy) {
                candies.add((Candy) e);
                if (c == '1') {
                    for (int i = 0; i < 10; i++) {
                        actor.removeItemFromInventory(candies.get(0));
                    }

                    PokeEgg egg = new PokeEgg("TorchicEgg", 'g', true, 4);
                    actor.addItemToInventory(egg);
                    return actor + " buys TorchicEgg."; // buy TorchicEgg
                } else if (c == '2') {
                    for (int i = 0; i < 10; i++) {
                        actor.removeItemFromInventory(candies.get(0));
                    }
                    PokeEgg egg = new PokeEgg("TreeckoEgg", 'g', true, 3);
                    actor.addItemToInventory(egg);
                    return actor + " buys TreeckoEgg."; // buy TreeckoEgg
                } else if (c == '3') {
                    for (int i = 0; i < 10; i++) {
                        actor.removeItemFromInventory(candies.get(0));
                    }
                    PokeEgg egg = new PokeEgg("TorchicEgg", 'g', true, 2);
                    actor.addItemToInventory(egg);
                    return actor + " buys MudkipEgg."; // buy MudkipEgg
                } else if (c == '4') {
                    for (int i = 0; i < 6; i++) {
                        actor.removeItemFromInventory(candies.get(0));
                    }
                    MasterBall masterBall = new MasterBall();
                    actor.addItemToInventory(masterBall);
                    return actor + " buys MasterBall."; // buy MasterBall
                } else if (c == '5') {
                    for (int i = 0; i < 3; i++) {
                        actor.removeItemFromInventory(candies.get(0));
                    }
                    GreatBall greatBall = new GreatBall();
                    actor.addItemToInventory(greatBall);
                    return actor + " buys GreatBall."; // buy GreatBall
                } else {
                    display.println("Please input again"); //wrong input
                    return "";
                }
            }
        } // end of for

        return "Items is Empty";
    } // end of execute()

    @Override
    public String menuDescription(Actor actor) {
        return actor + " visits the " + target; // enter the shopkeeper menu
    }
}


