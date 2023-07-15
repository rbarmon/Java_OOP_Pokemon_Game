package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Pokeball;
import game.pokemons.PokemonBase;
import game.utils.Affection;
import game.utils.AffectionManager;
import game.utils.Element;
import game.items.Pokeball;
import game.utils.GameMapManager;

import java.util.ArrayList;
import java.util.List;


/**
 * print the information of the Pokedex
 */
public class Pokedex extends Action {
    /**
     * pokeTypes: a list that store the element type of the pokemon we have caught
     */
    public ArrayList<Enum> pokeTypes = new ArrayList<Enum>();

    //public AffectionManager am = new AffectionManager();

    public Actor currentPlayer;
    /**
     * Constructor.
     *
     * @param currentPlayer        current player
     */
    public Pokedex(Actor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    /**
     * iterate the player's inventory to find the used pokeballs
     * then iterate the pokeballs to get the pokemon that store in pokeballs
     * print out the states of each pokemon in for loop at the same time
     */
    public String execute(Actor actor, GameMap map) {
        for (Item i : currentPlayer.getInventory()) {
            if (i instanceof Pokeball && ((Pokeball) i).isUsed()) {
                for(Element e : Element.values()){
                    if(((Pokeball) i).getPokemon().capabilitiesList().contains(e) && !pokeTypes.contains(e)){
                        pokeTypes.add(e);
                    }
                }
            }
        }

        List<Pokeball> pokelist = new ArrayList<>();
        System.out.printf("Pokedex | " + pokeTypes.size() + "/3 | Types caught: " + pokeTypes +"\n");
        for (Item i : currentPlayer.getInventory()) {
            if (i instanceof Pokeball && ((Pokeball) i).isUsed()) {
                pokelist.add((Pokeball) i);
            }
            for (Pokeball p : pokelist) {
                Affection pokemon = AffectionManager.getInstance().findPokemon(p.getPokemon());
                System.out.println("- " + p.getPokemon()
                    + " at " + GameMapManager.getActorLocation(p.getPokemon()) + " with "
                    + AffectionManager.getInstance().getAffectionPoint(pokemon)
                    + " AP");
            }
        }
        if (pokeTypes.size() < 3){
            System.out.println("Capture 3 different types of Pokemons and then visit Professor Oak");
        } else {
            System.out.println("You are ready to visit Professor Oak");
        }
        return "...";
    }

    /**
     * pokedex extends from action, print the hotkey to display screen
     */

    public String hotkey () {
        return "z";
    }
    @Override
    public String menuDescription(Actor actor) {
        return "check Pokedex";
    }
}