package game.time;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that gives time perception  on the affected instances.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by:
 */
public class TimePerceptionManager {
    /**
     * A list of polymorph instances (any classes that implements TimePerception,
     * such as, a Charmander implements TimePerception, it will be stored in here)
     */
    private final List<TimePerception> timePerceptionList;

    private int turn;

    private TimePeriod shift; // DAY or NIGHT

    /**
     * A singleton instance
     */
    private static TimePerceptionManager instance;

    /**
     * Get the singleton instance of time perception manager
     *
     * @return TimePerceptionManager singleton instance
     * <p>
     */
    public static TimePerceptionManager getInstance() {
        if (instance == null) {
            instance = new TimePerceptionManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private TimePerceptionManager() {
        timePerceptionList = new ArrayList<>();
        turn = 0;
        shift = TimePeriod.DAY;
    }

    /**
     * Traversing through all instances in the list and execute them
     * By doing this way, it will avoid using `instanceof` all over the place.
     * <p>
     */
    public void run() {
        if (shift.equals(TimePeriod.DAY)) {
            System.out.format("It is a Day-time (Turn %d)%n", turn);
            for (TimePerception t : timePerceptionList)
                t.dayEffect();
            if ((turn + 1) % 5 == 0) {
                shift = TimePeriod.NIGHT;
            }
        } else {
            System.out.format("It is a Night-time (Turn %d)%n", turn);
            for (TimePerception t : timePerceptionList)
                t.nightEffect();
            if ((turn + 1) % 5 == 0) {
                shift = TimePeriod.DAY;
            }
        }
        turn += 1;
    }


    /**
     * Add the TimePerception instance to the list
     *
     * @param objInstance any instance that implements TimePerception
     */
    public void append(TimePerception objInstance) {
        timePerceptionList.add(objInstance);
    }


    /**
     * Remove a TimePerception instance from the list
     * <p>
     *
     * @param objInstance object instance
     */
    public void cleanUp(TimePerception objInstance) {
        for (int i = timePerceptionList.size() - 1; i >= 0; i--) {
            TimePerception item = timePerceptionList.get(i);
            if (item.equals(objInstance)) {
                timePerceptionList.remove(item);
                break;
            }
        }
    }

    public TimePeriod getCurrentTimePeriod(){
        return shift;
    }
}
