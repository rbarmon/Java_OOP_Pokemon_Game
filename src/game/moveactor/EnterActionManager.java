package game.moveactor;

import java.util.HashMap;
import java.util.Map;


/**
 * A3 Req 2
 * A single instance of the EnterActionManager that manages all EnterActions.
 *
 * @author Rian Barrett
 * @version 1
 */
public class EnterActionManager {

  static EnterActionManager enterActionManager = null;

  private EnterActionManager(){}

  /**
   * factory method for the EnterLocationManager class
   */
  public static EnterActionManager getInstance() {
    //new Clinic();
    if (enterActionManager == null) {
      enterActionManager = new EnterActionManager();
    }
    return enterActionManager;
  }

  //HashMap to Keep track of all EnterActions
  private Map<LocationType, EnterAction> map = new HashMap<>();


  /**
   *
   * Takes a collection of EnterAction and puts them into a look up table that will allow a single
   * look up table to manage all the EnterActions that an Actor can be sent to.
   *
   * @param enterActions A collection of all types of EnterActions
   */
  public void addEnterAction(EnterAction... enterActions) {
    for (EnterAction enterAction : enterActions) {
      map.put(enterAction.getLocationType(), enterAction);
    }
  }

    /**
   * Given a LocationType returns the EnterAction that is assigned to it in the HashMap.
   *
   * @param locationType an enum type that identifies the Location
   * @return an instance of a EnterAction
   */
  public EnterAction getEnterAction(LocationType locationType){
    return map.get(locationType);
  }

}
