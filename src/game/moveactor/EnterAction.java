package game.moveactor;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;


/**
 * A3 Req 2
 * An Action that moves the Actor for Door Actions.
 * Extends the MoveActorAction class as the features are basically the same.
 * Overrides menuDesciption to print that the actor "enters" the direction.
 *
 * @author Rian Barrett
 * @version 1
 */
public class EnterAction extends MoveActorAction {

  // Enum locationtype that identifies the location that the MoveActorItem moves the actor to.
  LocationType locationType;

  /**
   * Constructor for the EnterAction class.
   *
   * @param moveToLocation A location on a gamemap to move the actor
   * @param direction A string for the direction the actor is moved
   * @param locationType A locationtype that identifies where the actor is going to be moved.
   */
  public EnterAction(Location moveToLocation, String direction, LocationType locationType) {
    super(moveToLocation, direction);
    this.locationType = locationType;
  }

  /**
   * A getter for the LocationType for the EnterAction
   * @return a LocationType for where the MoveActorItem sends the Actor
   */
  public LocationType getLocationType() {
    return locationType;
  }

  /**
   * Returns a description of this movement suitable to display in the menu.
   * Overrides menuDesciption to print that the actor "enters" the direction.
   * @param actor The actor performing the action.
   * @return a String, e.g. "Player enters Pokemon Center"
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " enters " + direction;
  }
}
