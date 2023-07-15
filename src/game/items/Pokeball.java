package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public class Pokeball extends Item {


    /**
     * pokemon inside
     */
    protected Actor capturedPokemon = null;

    /**
     * @param name Item name
     * @param displayChar Display characters
     * @param portable Is it possible to bring
     */
    public Pokeball(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public Pokeball() {
        super("PokeBall", 'o', true);
    }


    /**
     * @param pokemon Pokemon which I want to store
     */
    public void capturePokemon(Actor pokemon) {
        // If you don't have any Pokemon stored
        if (capturedPokemon == null) {
            capturedPokemon = pokemon;
        }
    }

    /**
     * @return pokemon get the Pokemon from the PokeBall
     */
    public Actor getPokemon() {
        return this.capturedPokemon;
    }

    /**
     * @return Whether the current genie ball is used or not
     */
    public boolean isUsed() {
        return this.capturedPokemon != null;
    }

    /**
     * @return The pokemon from the pokeball
     */
    public Actor summonPokemon() {
        Actor pokemon = this.capturedPokemon;
        this.capturedPokemon = null;
        return pokemon;
    }

}
