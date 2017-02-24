package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.blocks.AbstractBlock;
import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameactions.SetEntityPosAction;
import de.tudarmstadt.informatik.fop.breakout.gameevents.IDCollisionEvent;
import de.tudarmstadt.informatik.fop.breakout.physics.Physics2D;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.action.basicactions.SetEntityPositionAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.NOTEvent;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;

public class Ball extends Entity implements GameParameters {

	private float speed;
	private boolean isLaunched;

	// launcher of this ball
	private Stick launcher;

	// Entity of the last collision
	private Entity lastCollisionEntity;

	private CollisionEvent collider;
	private IDCollisionEvent blockCollider;

	private IDCollisionEvent topBorderCollider;
	private IDCollisionEvent leftBorderCollider;
	private IDCollisionEvent rightBorderCollider;

	private IDCollisionEvent stickCollider;

	private Event hasLaunched;
	private NOTEvent hasNotLaunched;
	private ANDEvent launchBall;

	private LeavingScreenEvent leftScreen;

	/**
	 * Ball constructor
	 * 
	 * @param xPos
	 *            of the new Ball
	 * @param yPos
	 *            of the new Ball
	 */
	public Ball(Stick launcher) {
		super(BALL_ID);

		setLauncher(launcher);
		setLaunched(false);
		setLastCollisionEntity(getLauncher());

		setPosition(getLauncher().getLaunchPos());
		setSpeed(INITIAL_BALL_SPEED);
		setRotation(0);

		setVisible(true);
		setPassable(false);

		configureEvents();

		// adds colliders
		this.addComponent(collider);
		this.addComponent(blockCollider);
		this.addComponent(topBorderCollider);
		this.addComponent(leftBorderCollider);
		this.addComponent(rightBorderCollider);
		this.addComponent(stickCollider);

		// adds launch events
		this.addComponent(hasLaunched);
		this.addComponent(hasNotLaunched);
		this.addComponent(launchBall);

		// adds leaving screen event
		this.addComponent(leftScreen);

		try {
			this.addComponent(new ImageRenderComponent(new Image(BALL_IMAGE)));
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	/**
	 * configures all ball events and adds the actions
	 */
	private void configureEvents() {

		// main collision event:
		collider = new CollisionEvent();

		// block collision event:
		blockCollider = new IDCollisionEvent(BLOCK_ID);

		// border collision event:
		topBorderCollider = new IDCollisionEvent(TOP_BORDER_ID);
		leftBorderCollider = new IDCollisionEvent(LEFT_BORDER_ID);
		rightBorderCollider = new IDCollisionEvent(RIGHT_BORDER_ID);
		
		// stick collision event:
		stickCollider = new IDCollisionEvent(STICK_ID);

		hasLaunched = new Event("hasLaunchedEvent") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return isLaunched();
			}
		};
		
		hasNotLaunched = new NOTEvent(hasLaunched);

		launchBall = new ANDEvent(hasNotLaunched, new KeyDownEvent(Input.KEY_SPACE));
		
		leftScreen = new LeavingScreenEvent();

		/////////////////////////////////////////////////
		/////////////////////////////////////////////////

		collider.addAction((arg0, arg1, arg2, arg3) -> setLastCollisionEntity(collider.getCollidedEntity()));
		
		// bounces ball when it hits block
		blockCollider.addAction((arg0, arg1, arg2, arg3) -> setRotation(Physics2D.bounceXAxis(getRotation())));
		// reduces the blocks hitsleft by one when hit
		blockCollider.addAction((arg0, arg1, arg2, arg3) -> ((AbstractBlock) blockCollider.getCollidedEntity()).addHitsLeft(-1));
		
		// bounces ball at borders
		topBorderCollider.addAction((arg0, arg1, arg2, arg3) -> setRotation(Physics2D.bounceXAxis(getRotation())));
		leftBorderCollider.addAction((arg0, arg1, arg2, arg3) -> setRotation(Physics2D.bounceYAxis(getRotation())));
		rightBorderCollider.addAction((arg0, arg1, arg2, arg3) -> setRotation(Physics2D.bounceYAxis(getRotation())));

		// bounces ball at stick
		stickCollider.addAction((arg0, arg1, arg2, arg3) -> {
			setRotation(Physics2D.bounceXAxis(getRotation()));
			Physics2D.updateAngleOffset((Ball) stickCollider.getOwnerEntity(), getLauncher());
		});

		// moves ball if launched
		hasLaunched.addAction(new MoveForwardAction(getSpeed()));
		hasNotLaunched.addAction((arg0, arg1, arg2, arg3) -> setPosition(getLauncher().getLaunchPos()));
		launchBall.addAction((arg0, arg1, arg2, arg3) -> setLaunched(true));
		
		leftScreen.addAction((arg0, arg1, arg2, arg3) -> Lives.deductLife());
		
		// destroys this ball if it leaves the window
		leftScreen.addAction(new DestroyEntityAction());
		
	}

	@Override
	public boolean collides(Entity otherEntity) {

		return super.collides(otherEntity);
	}

	public Stick getLauncher() {
		return launcher;
	}

	public void setLauncher(Stick launcher) {
		this.launcher = launcher;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean isLaunched() {
		return isLaunched;
	}

	public void setLaunched(boolean isLaunched) {
		this.isLaunched = isLaunched;
	}

	public Entity getLastCollisionEntity() {
		return lastCollisionEntity;
	}

	public void setLastCollisionEntity(Entity lastCollisionEntity) {
		this.lastCollisionEntity = lastCollisionEntity;
	}

}
