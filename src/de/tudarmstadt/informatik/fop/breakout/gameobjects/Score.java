package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * 
 * @author Lukas
 *
 */
public class Score extends Entity implements GameParameters {

	private int count;
	
	public Score() {
		super(SCORE_ID);
		this.count = 0;
	}
	
	public int getScoreCount() {
		return this.count;
	}
	
	public void incScoreCount (int value) {
		this.count += value;
	}
		
	public String toString() {
		StringBuilder display = new StringBuilder();
		if (this.count < 100000)
			display.append(0);
		if (this.count < 10000)
			display.append(0);
		if (this.count < 1000)
			display.append(0);
		if (this.count < 100)
			display.append(0);
		if (this.count < 10)
			display.append(0);
		display.append(this.count);
		return display.toString();
	}

}
