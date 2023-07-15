package game.moveactor;

import edu.monash.fit2099.engine.items.Item;

/**
 * A3 Req 2
 * Item class for Doors that can move the Actor to differemt GameMaps and Locations.
 * Extends the Item class as the features are basically the same besides the fact that location
 * action is determined by the single instance of the enterLocationManager and the locationType.
 *
 * @author Rian Barrett
 * @version 1
 */
public class Door extends Item {

  /***
   * Constructor.
   * @param enterActionManager the single instance that manages all EnterActions
   * @param locationType the type of location that is assigned to the EnterAction
   */
  public Door(EnterActionManager enterActionManager, LocationType locationType) {
    super("Door", '=', false);
    this.addAction(enterActionManager.getEnterAction(locationType));
  }
}
