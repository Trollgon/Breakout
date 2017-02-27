package de.tudarmstadt.informatik.fop.breakout.gameobjects.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.gameobjects.Stick;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;

/**
 * 
 * @author Peter Franke
 * 
 * Item that will expand the Stick
 *
 */
public class ExpandStickItem extends BasicItem {

public ExpandStickItem(Vector2f startPosition) {
		
		// ITEM ID, DURATION, START POSITION, DESPAWNONDEATH, STARTACTION, ENDACTION, FALLING SPEED, LOGO
		super(ITEM_EXPANDSTICK_ID, 10000, startPosition, true, start, end, 0.3f, EXPANDSTICK_LOGO_PATH);
		// TODO Auto-generated constructor stub
	}
private static final Action start = new Action(){

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		Stick s = (Stick) StateBasedEntityManager.getInstance().getEntity(arg1.getCurrentStateID(), STICK_ID);
		//s.setScale(1.5f);
		s.setSize(new Vector2f(s.getSize().getX() * 1.5f, s.getSize().getY()));
		s.updateImage();
		System.out.println("stick expanded");
	}
	
};

private static final Action end = new Action(){

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		Stick s = (Stick) StateBasedEntityManager.getInstance().getEntity(arg1.getCurrentStateID(), STICK_ID);
		//s.setScale(2/3f);
		s.setSize(new Vector2f(s.getSize().getX() * 2/3f, s.getSize().getY()));
		s.updateImage();
	}
	
};
}
