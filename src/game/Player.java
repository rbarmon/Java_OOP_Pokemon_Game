package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.*;
import game.items.Pokeball;
import game.time.TimePerceptionManager;
import game.utils.*;

import java.util.List;
import java.util.Objects;

/**
 * Class representing the Player.
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by:
 */
public class Player extends Actor {

    private final Menu menu = new Menu();

    private TimePerceptionManager timePerceptionManager = null;

    private AffectionManager affectionManager = null;

    private GameMapManager gameMapManager = null;


    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, GameMap gameMap) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.IMMUNE);

        this.affectionManager = AffectionManager.getInstance();
        this.affectionManager.registerTrainer(this);
        this.timePerceptionManager = TimePerceptionManager.getInstance();
        this.gameMapManager = new GameMapManager(gameMap);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        timePerceptionManager.run();
        Location location = map.locationOf(this);
        List<Item> items = map.at(location.x(), location.y()).getItems();

        if (items.size() != 0) {
            for (Item item : items) {
                Action pickup = item.getPickUpAction(this);
                if(pickup !=null){
                    actions.add(pickup);
                }
            }
        }

        if (hasPokemon()) {
            actions.add(new SummonPokemon());
        }

        try {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 && j != 0) {
                        Actor actor = map.at(location.x() + i, location.y() + j).getActor();
                        if (actor.hasCapability(GameType.POKEMON) && actor.hasCapability(Status.CATCHABLE)) {
                            actions.add(new CapturePokemon(actor));
                        }
                        if (actor.hasCapability(GameType.NPC_SHOPKEEPER)) {
                            actions.add(new BuyAction(actor));
                        }
                        if (actor.hasCapability(GameType.NPC_OAK)) {
                            actions.add(new ProfessorAction(actor));
                        }
                        if (actor.hasCapability(GameType.POKEMON)) {
                            actions.add(new InteractionAction(actor));
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }

        actions.add(new Pokedex(this));


        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();
        // return/print the console menu
        return menu.showMenu(this, actions, display);
    }

    @Override
    public char getDisplayChar() {
        return super.getDisplayChar();
    }

    /**
     * @return Do I have a Pokemon
     */
    private boolean hasPokemon() {
        List<Item> inventory = this.getInventory();
        for (Item item : inventory) {
            if (item instanceof Pokeball && ((Pokeball) item).isUsed()) {
                return true;
            }
        }
        return false;
    }

}
