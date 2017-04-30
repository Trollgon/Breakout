package de.tudarmstadt.informatik.fop.breakout.levels;

import de.tudarmstadt.informatik.fop.breakout.constants.StateParameters;

import java.util.HashMap;

/** Levels-class to manage the existing levels of the game
 * @author Matthias Spoerkmann
 */
public class Levels {

	static HashMap<Integer, String> levels = new HashMap<>();

    public static String getPathByID(int levelID) {
        
    	// filling HashMap
    	// zone 1:
        levels.put(101, "maps/zone1/101.map");
        levels.put(102, "maps/zone1/102.map");
        levels.put(103, "maps/zone1/103.map");
        levels.put(104, "maps/zone1/104.map");

        // zone 2:
        levels.put(201, "maps/zone2/201.map");
        levels.put(202, "maps/zone2/202.map");
        levels.put(203, "maps/zone2/203.map");
        levels.put(204, "maps/zone2/204.map");
        
        // zone 3:
        levels.put(301, "maps/zone3/301.map");
        levels.put(302, "maps/zone3/302.map");
        levels.put(303, "maps/zone3/303.map");
        levels.put(304, "maps/zone3/304.map");
    	
    	
      // returning path
        try {
            return levels.get(levelID);
        }
        catch (Exception e){
            return null;
        }


    }

    public static StateParameters.ZoneType getNextZone(StateParameters.ZoneType zoneType) {
        switch (zoneType) {
            case NORMALZONE:
                return StateParameters.ZoneType.ICEZONE;
            case ICEZONE:
                return StateParameters.ZoneType.MAGMAZONE;
            default:
                return StateParameters.ZoneType.ICEZONE;
        }
    }

}
