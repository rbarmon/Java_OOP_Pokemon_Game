package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.pokemons.Blaziken;
import game.pokemons.Combusken;
import game.pokemons.PokemonBase;
import game.pokemons.Torchic;
import game.utils.Affection;
import game.utils.AffectionManager;
import game.utils.GameMapManager;

public class EvolutionAction extends Action {
    int xLocation;
    int yLocation;
    Actor target;
    Affection instance;

    public EvolutionAction(int x, int y, GameMap gameMap) {
        super();
        this.xLocation=x;
        this.yLocation=y;
        this.target=gameMap.at(xLocation,yLocation).getActor(); // get the location of evolute Pokemon
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        instance= AffectionManager.getInstance().findPokemon(target);
        if (null == instance) {
            return "";
        }

        PokemonBase oldPokeman = (PokemonBase)instance;
        PokemonBase newPokeman = null;
        if (oldPokeman.getName().equals("Torchic")) {
            newPokeman = new Combusken(); // Torchic evolute to Combusken
        } else if (oldPokeman.getName().equals("Combusken")) {
            newPokeman = new Blaziken();  // Combusken evolute to Blaziken
        }

        if (newPokeman == null) {  // Blaziken can not evolute again
            return "";
        }
        map.removeActor(oldPokeman); // remove the Pokemon before evolute
        GameMapManager.addActor(new Location(map, xLocation, yLocation), newPokeman);
        return "x:" + xLocation + "y:" + yLocation + " pokeman evolute";
    }

    @Override
    public String menuDescription(Actor actor) {
            return "Ash " + "trigger evolution of "+ target;
        }
}
