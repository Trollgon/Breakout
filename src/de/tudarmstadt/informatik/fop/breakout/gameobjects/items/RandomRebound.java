package de.tudarmstadt.informatik.fop.breakout.gameobjects.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.physics.Physics2D;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;

public class RandomRebound extends BasicItem {

public RandomRebound(Vector2f startPosition) {
		
		// ITEM ID, DURATION, START POSITION, DESPAWNONDEATH, STARTACTION, ENDACTION, FALLING SPEED, LOGO
		super(ITEM_RANDOMREBOUND_ID, 10000, startPosition, true, start, end, 0.3f, RANDOMREBOUND_LOGO_PATH);
		// TODO Auto-generated constructor stub
	}
	
private static final Action start = new Action() {

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		Ball b = (Ball) StateBasedEntityManager.getInstance().getEntity(arg1.getCurrentStateID(), BALL_ID);
		b.stickCollider.clearActions();
		b.stickCollider.addAction(new Action() {
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3){
				float randRot = (float) Math.random() * 178 - 89;
				b.setRotation(randRot);
				b.setPosition(new Vector2f(b.getPosition().getX(), b.getLauncher().getPosition().getY() - 28));
			}
		});
	}
	
	
};

private static final Action end = new Action() {

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		Ball b = (Ball) StateBasedEntityManager.getInstance().getEntity(arg1.getCurrentStateID(), BALL_ID);
		b.stickCollider.clearActions();
		b.stickCollider.addAction((arg0, arg1, arg2, arg3) -> {
			setRotation(Physics2D.bounceStick(getRotation(), (Ball) stickCollider.getOwnerEntity(), getLauncher()));
			});
	}
	
	
};
	
}
