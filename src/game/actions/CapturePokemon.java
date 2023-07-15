package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Candy;
import game.items.GreatBall;
import game.items.MasterBall;
import game.items.Pokeball;
import game.utils.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Catch Pokemon
 */
public class CapturePokemon extends Action {

    Actor target;

    public CapturePokemon(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        if (!target.hasCapability(Status.CATCHABLE)) {
            return "cant catch " + target;
        }

        // Find the goodness of the target Pokemon
        Affection pokemon = AffectionManager.getInstance().findPokemon(target);
        int affectionPoint = AffectionManager.getInstance().getAffectionPoint(pokemon);

        // find Pokeball
        List<Item> inventory = actor.getInventory();
        // Currently available Pokeball
        List<Pokeball> pokeBalls = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Pokeball && !((Pokeball) item).isUsed()) {
                pokeBalls.add((Pokeball) item);
            }
        }
        for(Item item: pokeBalls){
            actor.removeItemFromInventory(item);
        }
        boolean capture = false;
        if (affectionPoint >= 60) {
            Pokeball pokeball = new Pokeball();
            pokeball.capturePokemon(target);
            GameMapManager.addItem(GameMapManager.getActorLocation(target), new Candy());
            map.removeActor(target);
            pokeBalls.add(pokeball);
            for (Pokeball item : pokeBalls) {
                actor.addItemToInventory(item);
            }

            return actor + " captured " + target + " successfully";
        } else if (affectionPoint >= 20) {
            Pokeball selectPokeBall = null;
            for (Pokeball pokeball : pokeBalls) {
                if ((pokeball instanceof GreatBall || pokeball instanceof MasterBall) && !pokeball.isUsed()) {
                    selectPokeBall = pokeball;
                    pokeBalls.remove(selectPokeBall);
                    break;
                }
            }
            if (selectPokeBall != null) {
                selectPokeBall.capturePokemon(target);
                GameMapManager.addItem(GameMapManager.getActorLocation(target), new Candy());
                map.removeActor(target);
                pokeBalls.add(selectPokeBall);
            }
            for (Pokeball item : pokeBalls) {
                actor.addItemToInventory(item);
            }
            return actor + " captured " + target + " successfully";
        } else {
            Pokeball selectPokeBall = null;
            for (Pokeball pokeball : pokeBalls) {
                if ((pokeball instanceof MasterBall) && !pokeball.isUsed()) {
                    selectPokeBall = pokeball;
                    pokeBalls.remove(selectPokeBall);
                    break;
                }
            }
            if(selectPokeBall == null){
                for (Pokeball item : pokeBalls) {
                    actor.addItemToInventory(item);
                }
                AffectionManager.getInstance().decreaseAffection(target, 20);
                return actor + " captured " + target + " failed.";
            }
            selectPokeBall.capturePokemon(target);
            GameMapManager.addItem(GameMapManager.getActorLocation(target), new Candy());
            map.removeActor(target);
            pokeBalls.add(selectPokeBall);
            for (Pokeball item : pokeBalls) {
                actor.addItemToInventory(item);
            }
            return actor + " captured " + target + " successfully";
        }


    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " captures " + target;
    }
}
