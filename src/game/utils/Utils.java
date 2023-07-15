package game.utils;

import java.util.Random;

/**
 * tool class
 */
public class Utils {
    /**
     * @param chance probability int 10
     * @return Is it within the probability
     */
    public static Boolean chanceTrigger(int chance) {
        Random r = new Random();
        return r.nextInt(100) <= chance;
    }

}
