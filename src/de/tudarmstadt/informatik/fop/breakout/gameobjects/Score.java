package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.entity.Entity;

/**
 * 
 * @author Lukas Lehmann
 *
 */
public class Score extends Entity implements GameParameters {

	private static int count;
	
	public Score() {
		super(SCORE_ID);
		resetCount();
	}
	
	public static int getScoreCount() {
		return count;
	}
	
	public static void incScoreCount(int value) {
		count += value;
	}
	
	public static void resetCount(){
		count = 0;
	}
		
	public String toString() {
		StringBuilder display = new StringBuilder();
		if (count < 100000)
			display.append(0);
		if (count < 10000)
			display.append(0);
		if (count < 1000)
			display.append(0);
		if (count < 100)
			display.append(0);
		if (count < 10)
			display.append(0);
		display.append(count);
		return display.toString();
	}

}
