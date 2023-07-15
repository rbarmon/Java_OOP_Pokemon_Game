package game.environments;

import edu.monash.fit2099.engine.positions.Ground;

/**
 *  ground which able to generate Pokemon
 */
public abstract class SpawningGround extends Ground {

    /**
     * Probability of generating Pokemon
     */
    private final int spawnChance;


    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar, int spawnChance) {
        super(displayChar);
        this.spawnChance = spawnChance;
    }


    /**
     * @return Probability of generating Pokemon
     */
    public int getSpawnChance() {
        return spawnChance;
    }

}
