package de.tudarmstadt.informatik.fop.breakout.gameobjects.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.gameevents.LifeDeductionEvent;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;


/**
 * @author Peter Franke
 *
 * Item that will increase the ball's speed for 10 seconds 
 */
public class SlowDownItem extends BasicItem {
	
	
	final static Action start = new Action() {
		
		public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3){
			Ball b =(Ball) StateBasedEntityManager.getInstance().getEntity(arg1.getCurrentStateID(), BALL_ID);
			float newspeed = b.getSpeed() - 0.5f;
			b.setSpeed(newspeed);
			
			
			System.out.println("Slowdown start action");
		}
	};
	final static Action end = new Action() {
		public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3){
			Ball b =(Ball) StateBasedEntityManager.getInstance().getEntity(arg1.getCurrentStateID(), BALL_ID);
			float newspeed = b.getSpeed() + 0.5f;
			
			b.setSpeed(newspeed);
			
			
			System.out.println("end action");
		}
	} ;
	public SlowDownItem(Vector2f startPosition
			) {
		// ITEM ID, DURATION, START POSITION, DESPAWNONDEATH, STARTACTION, ENDACTION, CancelCOndition, FALLING SPEED, LOGO
		super(ITEM_SLOWDOWN_ID, 10000, startPosition,true, start, end, new LifeDeductionEvent(), 0.3f, SLOWDOWN_LOGO_PATH);
		// TODO Auto-generated constructor stub
	}

}
