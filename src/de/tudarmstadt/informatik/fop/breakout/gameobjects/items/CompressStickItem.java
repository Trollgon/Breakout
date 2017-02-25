package de.tudarmstadt.informatik.fop.breakout.gameobjects.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.gameobjects.Stick;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;

public class CompressStickItem extends BasicItem {

	
public CompressStickItem(Vector2f startPosition) {
		
		// ITEM ID, DURATION, START POSITION, DESPAWNONDEATH, STARTACTION, ENDACTION, FALLING SPEED, LOGO
		super(ITEM_COMPRESSSTICK_ID, 10000, startPosition, true, start, end, 0.3f, COMPRESSSTICK_LOGO_PATH);
		
	}
private static final Action start = new Action(){

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		Stick s = (Stick) StateBasedEntityManager.getInstance().getEntity(arg1.getCurrentStateID(), STICK_ID);
		s.setScale(2/3f);
	}
	
};

private static final Action end = new Action(){

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		Stick s = (Stick) StateBasedEntityManager.getInstance().getEntity(arg1.getCurrentStateID(), STICK_ID);
		s.setScale(1.5f);
	}
	
};
	
}
