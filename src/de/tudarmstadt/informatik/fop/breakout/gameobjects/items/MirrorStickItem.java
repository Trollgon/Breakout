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
 * Item that will mirror the player's stick movement input
 *
 */
public class MirrorStickItem extends BasicItem {

public MirrorStickItem(Vector2f startPosition) {
		
		// ITEM ID, DURATION, START POSITION, DESPAWNONDEATH, STARTACTION, ENDACTION, FALLING SPEED, LOGO
		super(ITEM_MIRRORSTICK_ID, 10000, startPosition, true, start, end, 0.3f, MIRRORSTICK_LOGO_PATH);
		// TODO Auto-generated constructor stub
	}
	private final static Action start = new Action() {

		@Override
		public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
			// TODO Auto-generated method stub
			Stick s = (Stick) StateBasedEntityManager.getInstance().getEntity(arg1.getCurrentStateID(), STICK_ID);
			s.mirrorInput();
		}
		
	};

	private final static Action end = start;

}
