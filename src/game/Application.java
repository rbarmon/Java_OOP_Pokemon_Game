package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.environments.*;
import game.items.Candy;
import game.items.Pokeball;
import game.moveactor.Door;
import game.moveactor.EnterAction;
import game.moveactor.EnterActionManager;
import game.moveactor.LocationType;
import game.npcs.ProfessorOak;
import game.npcs.Shopkeeper;
import game.pokemons.Mudkip;
import game.pokemons.Torchic;
import game.pokemons.Treecko;
import game.utils.GameMapManager;

import java.util.Arrays;
import java.util.List;

/**
 * The main class to start the game.
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by: Ian K. Felix
 */
public class Application {

    public static void main(String[] args) {


        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
                new Floor(), new Tree(),
                new Lava(), new Puddle(),
                new Crater(), new Hay(), new Waterfall());

        List<String> map = Arrays.asList(
            ".............................................^^^^^^^^^^^^^^",
            "...........,T,................................,T,..^^^^C^^^",
            ".....................................................^^^^^^",
            "........................................................^^^",
            ".........................................,,..............^^",
            "............................###..........,T...............^",
            "..,,,.......................#_#............................",
            "..,T,......~...............................................",
            "...~~~~~~~~................................................",
            "....~~~~~..........,.......................................",
            "~~W~~~~.,.........................,,,......................",
            "~~~~~~.,T,........................,T,......................",
            "~~~~~~~~~.........................,........................");
        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        // New map: Pokemon Center Map
        List<String> pokemoncenterMap = Arrays.asList(
            "#############",
            "#___________#",
            "#___.....___#",
            "#___________#",
            "#___________#",
            "#####___#####");
        GameMap pokemoncenter = new GameMap(groundFactory, pokemoncenterMap);
        world.addGameMap(pokemoncenter);

        // Create a single instance of EnterLocationManager to manage all movement locations
        EnterActionManager enteractionManager = EnterActionManager.getInstance();

        // Add new locations to enterlocation Manager
        enteractionManager.addEnterAction(
            new EnterAction(pokemoncenter.at(6,5), "Pokemon Center", LocationType.POKEMONCENTER),
            new EnterAction(gameMap.at(29,6), "Pallet Town", LocationType.PALLETTOWN));

        // Add Pokemon Center Doors
        Door topokemonCenterDoor = new Door(enteractionManager, LocationType.POKEMONCENTER);
        Door topallettownDoor = new Door(enteractionManager, LocationType.PALLETTOWN);
        gameMap.at(29,6).addItem(topokemonCenterDoor);
        pokemoncenter.at(6,5).addItem(topallettownDoor);

        // Add NPC into Pokemon Center
        Actor professor = new ProfessorOak();
        world.addPlayer(professor, pokemoncenter.at(1, 4));

        Actor shopkeeper = new Shopkeeper();
        world.addPlayer(shopkeeper, pokemoncenter.at(6, 2));

        //Add player - Ash
        Player ash = new Player("Ash", '@', 1, gameMap);
        world.addPlayer(ash, gameMap.at(29, 7));

        GameMapManager.addItem(gameMap.at(29, 9), new Candy());

        Incubator incu = new Incubator();
        GameMapManager.addIncu(gameMap.at(27, 6), incu);

        for (int i = 0; i <= 9; i++) {
            ash.addItemToInventory(new Candy());
        }
        Pokeball pokeball = new Pokeball();
        pokeball.capturePokemon(new Torchic());
        ash.addItemToInventory(pokeball);
        Pokeball pokeball2 = new Pokeball();
        pokeball2.capturePokemon(new Torchic());
        ash.addItemToInventory(pokeball2);

        world.run();

    }
}
