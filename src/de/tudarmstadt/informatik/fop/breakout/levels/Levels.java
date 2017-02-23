package de.tudarmstadt.informatik.fop.breakout.levels;

import java.util.HashMap;

/**
 * @author Matthias Spoerkmann
 */
public class Levels {

    public static String getPathByID(int levelID) {
        HashMap<Integer, String> levels = new HashMap<>();

        // filling HashMap
        levels.put(101, "maps/101.map");
        levels.put(102, "maps/102.map");

        // returning path
        return levels.get(levelID);
    }

}
