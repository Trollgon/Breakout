package de.tudarmstadt.informatik.fop.breakout.levels;

import de.tudarmstadt.informatik.fop.breakout.constants.StateParameters;

import java.util.HashMap;

/**
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
        levels.put(105, "maps/zone1/105.map");
        levels.put(106, "maps/zone1/106.map");

        // zone 2:
        levels.put(201, "maps/zone2/201.map");
        levels.put(202, "maps/zone2/202.map");
        // ... 3 ... 4 ... 5 ... 6
        
        // zone 3:
        levels.put(301, "maps/zone3/301.map");
        levels.put(302, "maps/zone3/302.map");
        // ... 3 ... 4 ... 5 ... 6
    	
    	
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
                return StateParameters.ZoneType.JUNGLEZONE;
            default:
                return StateParameters.ZoneType.ICEZONE;
        }
    }

}
