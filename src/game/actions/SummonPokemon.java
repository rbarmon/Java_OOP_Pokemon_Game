package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Pokeball;
import game.utils.GameMapManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Release of Pokemon
 */
public class SummonPokemon extends Action {

    private final Random random = new Random();

    public SummonPokemon() {
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        // find the pokeball with pokemon
        List<Item> inventory = actor.getInventory();
        List<Pokeball> pokeBalls = new ArrayList<>();
        Actor summonPokemon = null;
        for (Item item : inventory) {
            if (item instanceof Pokeball && ((Pokeball) item).isUsed()) {
                pokeBalls.add((Pokeball) item);
            }
        }
        for(Item item: pokeBalls){
            actor.removeItemFromInventory(item);
        }
        if (pokeBalls.size() > 0) {
            // Randomly choose a pokemon to release
            Pokeball selectedPokeball = pokeBalls.get(random.nextInt(pokeBalls.size()));
            pokeBalls.remove(selectedPokeball);
            Location here = map.locationOf(actor);
            boolean flag = false;
            // find the adjacent area at first
            for (Exit exit : here.getExits()){
                Location destination = exit.getDestination();
                summonPokemon = selectedPokeball.getPokemon();
                if(destination.canActorEnter(summonPokemon)){
                    Actor pm = selectedPokeball.summonPokemon();
                    GameMapManager.addActor(destination, pm);
                    flag = true;
                    break;
                }
            }
            // If the release is not successful
            if(!flag){
                pokeBalls.add(selectedPokeball);
            }
            // Put all pokeballs back in inventory
            for (Item item : pokeBalls) {
                actor.addItemToInventory(item);
            }
            if(flag) {
                return "I choose you..." + summonPokemon;
            }
            else{
                return "I cant summon.";
            }

        }
        return "no pokemon was released.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons pokemon";
    }
}
