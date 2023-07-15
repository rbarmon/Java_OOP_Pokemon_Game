package game.utils;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.environments.Floor;
import game.environments.Incubator;
import game.environments.SpawningGround;

import java.util.ArrayList;
import java.util.List;

/**
 * Management of the GameMap, where any operations on the game map during the game are performed
 */
public class GameMapManager {

    /**
     * Current game map
     */
    private static GameMap gameMap;

    private static Incubator gameIncu;

    /**
     *
     * Constructors
     *
     * @param gameMap Game maps used
     */
    public GameMapManager(GameMap gameMap) {
        GameMapManager.gameMap = gameMap;
    }

    /**
     *
     *  Using the new map
     *
     * @param gameMap Game Map
     */


    /**
     * @param location At this location
     * @param actor Characters you want to add
     */
    public static void addActor(Location location, Actor actor) {
        // Is it possible to add a role to this position
        if (!gameMap.isAnActorAt(location)) {
            // add
            gameMap.at(location.x(), location.y()).addActor(actor);
        }

    }

    /**
     * @param location item
     * @param item item be added
     */
    public static void addItem(Location location, Item item) {
        gameMap.at(location.x(), location.y()).addItem(item);
    }

    /**
     * @param location item
     * @param item item be added
     */
    public static void addIncu(Location location, Incubator incu) {
        gameMap.at(location.x(), location.y()).addItem(incu);
        gameIncu = incu; //add Incubator
    }

    public static Incubator getIncu() {
        return gameIncu;
    }

    /**
     * @param location My Location
     * @param element The elements I want to judge
     * @param requirement Number of requests
     * @return Does the number of elements around my location meet the requirement
     */
    public static Boolean checkSurroundingElements(Location location, Element element, int requirement) {
        return checkSurroundingElementsHelper(location, element) > requirement;
    }

    /**
     * @param location My Location
     * @param element The elements I want to judge
     * @return  I want to determine the number of elements
     */
    public static int checkSurroundingElementsHelper(Location location, Element element) {
        int count = 0;
        try {
            for (int i = -1; i <= 1; i += 1) {
                for (int j = -1; j <= 1; j += 1) {
                    if (i != 0 && j != 0) {
                        if (checkElementAtPosition(location.x() + i, location.y() + j, element)) {
                            count += 1;
                        }
                    }
                }
            }
        } catch (Exception ignored) {

        }
        return count;
    }

    /**
     * @param x Horizontal coordinate
     * @param y vertical coordinates
     * @param element The elements I want to judge
     * @return Is this the element I want to judge
     */
    public static Boolean checkElementAtPosition(int x, int y, Element element) {
        try {
            List<Element> otherElements = new ArrayList<>();
            otherElements.add(element);
            Ground ground = gameMap.at(x, y).getGround();
            if (ElementsHelper.hasAnySimilarElements(ground, otherElements)) {
                return true;
            }
        } catch (Exception ignored) {

        }
        return false;
    }

    public static Location getActorLocation(Actor actor) {
        return gameMap.locationOf(actor);
    }

    /**
     * @param location Current position
     * @param g ground which I want to expansion
     */
    public static void expand(Location location, Ground g) {
        try {
            Element element = g.findCapabilitiesByType(Element.class).get(0);
            Ground ground;
            List<Element> elementCompare = new ArrayList<>();
            elementCompare.add(element);
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 && j != 0) {
                        ground = gameMap.at(location.x() + i, location.y() + j).getGround();
                        if (!ElementsHelper.hasAnySimilarElements(ground, elementCompare)) {
                            gameMap.at(location.x() + i, location.y() + j).setGround(g);
                        }
                    }
                }
            }

        } catch (Exception ignored) {

        }
    }

    /**
     * @param location Current position
     * @param g ground which I new build
     */
    public static void destroyToNewGround(Location location, Ground g) {
        gameMap.at(location.x(), location.y()).setGround(g);
    }

    public static boolean isAnActorAt(Location location) {
        return gameMap.isAnActorAt(location);
    }

    /**
     * @param actor Current character
     * @param location Where this character is located
     * @return Return to the first attackable target
     */
    public static Actor checkSurroundingActors(Actor actor, Location location) {
        Actor target;
        List<Element> actorElement = new ArrayList<>();
        actorElement.add(actor.findCapabilitiesByType(Element.class).get(0));
        try {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 && j != 0) {
                        target = gameMap.at(location.x() + i, location.y() + j).getActor();
                        if (!ElementsHelper.hasAnySimilarElements(target, actorElement) && !target.hasCapability(Status.IMMUNE)) {
                            return target;
                        } // check surrounding actor
                    }
                }
            }
        } catch (Exception ignored) {

        }
        return null;
    }


    public static Location checkSurroundingHasSpace(Location location) {
        Actor target;
        try {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        target = gameMap.at(location.x() + i, location.y() + j).getActor();
                        if (null == target) {
                            Ground ground = gameMap.at(location.x() + i, location.y() + j).getGround();
                            if (ground instanceof Floor) { // is wall or not?  SpawningGround
                                return gameMap.at(location.x() + i, location.y() + j);
                            } //check surrounding empty
                        }
                    }
                }
            }
        } catch (Exception ignored) {

        }
        return null;
    }

}
