package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * 
 * @author Lukas Lehmann
 *
 */
public class Score extends Entity implements GameParameters {

	private static int count;
	
	public Score() {
		super(SCORE_ID);
		count = 0;
	}
	
	public int getScoreCount() {
		return count;
	}
	
	public void incScoreCount (int value) {
		count += value;
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
