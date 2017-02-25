package de.tudarmstadt.informatik.fop.breakout.gameevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.gameobjects.Lives;
import eea.engine.event.Event;
/**
 * 
 * @author Peter Franke
 * 
 * event that fires if a Life is lost
 *
 */
public class LifeDeductionEvent extends Event {
	int livesCount;
	public LifeDeductionEvent() {
		super("life_deduction");
			livesCount = Lives.getLivesAmount();
		
	}
	@Override
	protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
		if(Lives.getLivesAmount() < livesCount){	
			livesCount--;
			return true;
			
		}
		if(Lives.getLivesAmount() > livesCount){
			livesCount++;
		}
			return false;
		

	}	
	
}
