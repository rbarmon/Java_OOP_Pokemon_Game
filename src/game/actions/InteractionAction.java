package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.Affection;
import game.utils.AffectionManager;
import game.utils.Favorite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Interacting with Pokemon
 */
public class InteractionAction extends Action {

    /**
     * Interactive object
     */
    private final Actor target;

    /**
     * target of AffectionManager
     */
    Affection pokemon;


    public InteractionAction(Actor target) {
        this.target = target;

    }


    @Override
    public String execute(Actor actor, GameMap map) {
        // get pokemon from AffectionManager
        pokemon= AffectionManager.getInstance().findPokemon(target);

        Display display = new Display();
        display.println("input: 1:dancing, 2:singing, 3:chestpounding,");
        char c = display.readChar();
        display.println(""+c);
        Favorite selectedFavorite = null;
        if (c == '1' ){
            selectedFavorite = Favorite.DANCING;
        } else if (c == '2' ) {
            selectedFavorite = Favorite.SINGING;
        }else if (c == '3' ){
            selectedFavorite = Favorite.CHESTPOUNDING;
        }else {
            display.println("Please input again");
            return "";
        }


        // three-choice action and Pokemon interaction
        List<Favorite> favorites = new ArrayList<>();
        favorites.add(Favorite.DANCING);
        favorites.add(Favorite.SINGING);
        favorites.add(Favorite.CHESTPOUNDING);

        System.out.println(actor + " tries " + selectedFavorite + " with " + target);
        // target Preferences
        Favorite targetFavorite = target.findCapabilitiesByType(Favorite.class).get(0);
        if(selectedFavorite.equals(targetFavorite)){
            return AffectionManager.getInstance().increaseAffection((Actor) pokemon, 10);
        }else{
            return AffectionManager.getInstance().decreaseAffection((Actor) pokemon, 20);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " interacts with " + target;
    }
}
