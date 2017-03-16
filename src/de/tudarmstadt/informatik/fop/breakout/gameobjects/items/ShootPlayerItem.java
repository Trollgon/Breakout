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
 * Item that fires a shot and deducts a life if it hits the Stick
 *
 */
public class ShootPlayerItem extends BasicItem {

	
public ShootPlayerItem(Vector2f startPosition) {
		
		// ITEM ID, DURATION, START POSITION, DESPAWNONDEATH, STARTACTION, ENDACTION, FALLING SPEED, LOGO
		super(ITEM_SHOOTPLAYER_ID, 0, startPosition, false, start, end,null, 1f, SHOOTPLAYER_LOGO_PATH);
		
	}

private static final Action start = new Action() {

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		Lives.deductLife();
		
	}
	
};

private static final Action end = null;
}
