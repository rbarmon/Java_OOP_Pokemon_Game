package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;
import game.items.Pokeball;
import game.utils.Element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfessorAction extends Action {

    private final Actor target;

    public ProfessorAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        // Find Pokemon
        List<Item> inventory = actor.getInventory();
        List<Pokeball> pokeBalls = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Pokeball && ((Pokeball) item).isUsed()) {
                pokeBalls.add((Pokeball) item);
            }
        }

        // Several properties of Pokemon
        Set<Element> s = new HashSet<>();
        for (Pokeball pokeball : pokeBalls) {
            List<Element> e = pokeball.getPokemon().findCapabilitiesByType(Element.class);
            if (e.size() > 0) {
                s.add(e.get(0));
            }
        }
        if (s.size() >= 3) {
            return "Congratulations Ash! Here is a Pokedex for you.";
        } else {
            return "I want to see 3 different pokemon Ash! Gotta catch em all!";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " interacts with " + target;
    }
}
