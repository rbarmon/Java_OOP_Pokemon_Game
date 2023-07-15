package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DropEggAction;
import game.pokemons.Mudkip;
import game.pokemons.PokemonBase;
import game.pokemons.Torchic;
import game.pokemons.Treecko;
import game.utils.GameMapManager;


public class PokeEgg extends Item {

    private int EggTime;
    private int NowTime;

    public boolean hasSpawned() {
        return NowTime >= EggTime;
    }

    public PokeEgg(String name, char displayChar, boolean portable, int inEggTime) {
        super(name, displayChar, portable);

        this.EggTime = inEggTime;
        this.NowTime = 0; //define incubation time
    }


    @Override
    public void tick(Location currentLocation){
        super.tick(currentLocation);
        NowTime++; // Calculate incubation time

        if (NowTime < EggTime) {
            return;
        }

        Location validLocation = GameMapManager.checkSurroundingHasSpace(currentLocation);
        if(validLocation != null){
            GameMapManager.addActor(validLocation, SpawnPokemon()); // SpawnPokemon from egg
        } else {
            System.out.println("surrounding no space at all!!"); // check surrounding empty space
        }
    }
    public PokemonBase SpawnPokemon()
    {
        if (toString().equals("TorchicEgg")) {
            return new Torchic();
        } else if (toString().equals("TreeckoEgg")) {
            return new Treecko();
        } else if (toString().equals("MudkipEgg")) {
            return new Mudkip();
        } // get Pokemon from PokeEgg

        return null;
    }

    public DropItemAction getDropAction(Actor actor) {
        return new DropEggAction(this);
    }
}

