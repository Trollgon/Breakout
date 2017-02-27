package de.tudarmstadt.informatik.fop.breakout.levels;

import java.util.HashMap;

/**
 * @author Matthias Spoerkmann
 */
public class Levels {

    public static String getPathByID(int levelID) {
        HashMap<Integer, String> levels = new HashMap<>();

        // filling HashMap
        levels.put(101, "maps/zone1/101.map");
        levels.put(102, "maps/zone1/102.map");

        levels.put(201, "maps/zone2/201.map");
        levels.put(202, "maps/zone2/202.map");

        // returning path
        return levels.get(levelID);
    }

}
