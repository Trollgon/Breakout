package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.entity.Entity;

/**
 * This entity represents a score display during a match.
 * 
 * @author Lukas Lehmann
 *
 */
public class Score extends Entity implements GameParameters {

	private static int count;
	
	/**
	 * Constructor of the score sets its value at 0.
	 */
	public Score() {
		super(SCORE_ID);
		resetCount();
	}
	
	/**
	 * Returns the actual score.
	 * @return
	 */
	public static int getScoreCount() {
		return count;
	}
	
	/**
	 * Increments the score by value.
	 * @param value
	 */
	public static void incScoreCount(int value) {
		count += value;
	}
	
	/**
	 * Sets score at 0.
	 */
	public static void resetCount(){
		count = 0;
	}
		
	@Override
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
