package de.tudarmstadt.informatik.fop.breakout.gameobjects.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.gameobjects.Lives;
import eea.engine.action.Action;
import eea.engine.component.Component;
/**
 * 
 * @author Peter Franke
 * 
 * Item that will increase the remaining lives
 *
 */
public class OneUp extends BasicItem {

	public OneUp(Vector2f startPosition) {
		
		// ITEM ID, DURATION, START POSITION, DESPAWNONDEATH, STARTACTION, ENDACTION, FALLING SPEED, LOGO
		super(ITEM_ONEUP_ID, 0, startPosition, false, start, end, 0.3f, ONEUP_LOGO_PATH);
		// TODO Auto-generated constructor stub
	}
	
	private final static Action start = new Action(){

		@Override
		public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
			if(Lives.getLivesAmount() < 3) Lives.incrementLife();
			
		}
		
	};
	
	private final static Action end = null;

}
