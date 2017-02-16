package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.physics.Physics2D;
import eea.engine.action.Action;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.NOTEvent;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.KeyDownEvent;

/**
 * ball class to represent any ball in the game
 * 
 * @author Jonas Henry Grebe
 *
 */
public class Ball extends Entity implements GameParameters {

	// balls speed:
	private float speed;
	private boolean isLaunched;

	// private Stick launcher;
	private Entity lastCollisionEntity = null;

	// ball events:
	private CollisionEvent collider;
	private Event launched;
	private NOTEvent notLaunched;
	private ANDEvent launch;
	private Event otherCollision;
	private ANDEvent XAxisCollision;
	private ANDEvent YAxisCollision;

	/**
	 * constructor of ball class
	 * 
	 * @param entityID
	 *            of the new ball
	 */
	public Ball(/* , Stick launcher */) {
		super(BALL_ID);

		setPosition(new Vector2f(WINDOW_WIDTH/2, WINDOW_HEIGHT - 30));
		setSpeed(INITIAL_BALL_SPEED);
		setRotation(0);

		// setPosition(getLauncher().getLaunchPos());
		setLaunched(false);

		try {
			addComponent(new ImageRenderComponent(new Image(BALL_IMAGE)));
		} catch (SlickException e) {

			e.printStackTrace();
		}

		setVisible(true);
		setPassable(false);
		
		configureEvents();
	}

	/**
	 * configures the balls events
	 */
	private void configureEvents() {

		//////////////////////////////// EVENTS ////////////////////////////////
		
		// main collision event
		collider = new CollisionEvent();

		// event which fires if the ball has been launched
		launched = new ANDEvent(new Event("ballIsLaunched") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return isLaunched();
			}
		});

		// event which fires if the ball has not been launched yet
		notLaunched = new NOTEvent(launched);

		// event which fires if the ball isn't launched and the SPACE-KEY is pressed -> launch
		launch = new ANDEvent(new NOTEvent(launched), new KeyDownEvent(Input.KEY_SPACE));

		// event which fires if the current collidedEntity is not the same as last collision
		otherCollision = new ANDEvent(collider, new Event("otherCollisionAsLastTime") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return getLastCollision() != collider.getCollidedEntity();
			}
		});

		// event which fires if the ball should bounce at the x-axis
		XAxisCollision = new ANDEvent(collider, otherCollision, new Event("xAxisCollision") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				String id = collider.getCollidedEntity().getID();
				return (id == BLOCK_ID || id == TOP_BORDER_ID || id == STICK_ID);
			}

		});

		// event which fires if the ball should bounce at the y-axis
		YAxisCollision = new ANDEvent(collider, otherCollision, new Event("yAxisCollision") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				String id = collider.getCollidedEntity().getID();
				return (id == LEFT_BORDER_ID || id == RIGHT_BORDER_ID);
			}
		});

		//////////////////////////////// ACTIONS ////////////////////////////////
		
		// remember the current collided entity for next collision
		collider.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				setLastCollision(collider.getCollidedEntity());
			}
		});

		// moves the ball in its direction
		launched.addAction(new MoveForwardAction(getSpeed()));

	// places the not yet launched ball at the launch-position
		notLaunched.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				// setPosition(getLauncher().getLaunchPos());
			}
		});

		// launches the ball
		launch.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				setLaunched(true);
			}
		});

		// bounces the ball on the x-axis
		XAxisCollision.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				setRotation(Physics2D.bounceXAxis(getRotation()));
			}
		});

		// bounces the ball on the y-axis
		YAxisCollision.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {

				setRotation(Physics2D.bounceYAxis(getRotation()));
			}
		});

		// adds all events with their actions
		this.addComponent(collider);
		this.addComponent(launch);
		this.addComponent(launched);
		this.addComponent(notLaunched);
		this.addComponent(otherCollision);
		
		this.addComponent(XAxisCollision);
		this.addComponent(YAxisCollision);

	}

	/**
	 * returns if this ball has been launched yet
	 * 
	 * @return TRUE, if launched; FALSE, if not launched
	 */
	public boolean isLaunched() {
		return isLaunched;
	}

	/**
	 * sets if the ball is launched or not
	 * 
	 * @param isLaunched
	 */
	public void setLaunched(boolean isLaunched) {
		this.isLaunched = isLaunched;
	}

	/**
	 * gets the current movement speed of the ball
	 * 
	 * @return float value f current speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * sets the speed of this ball
	 * 
	 * @param speed
	 *            float value of speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * gets the last entity the ball has collided with
	 * 
	 * @return
	 */
	public Entity getLastCollision() {
		return lastCollisionEntity;
	}

	/**
	 * sets the last entity the ball has been collided with
	 * 
	 * @param lastCollision
	 */
	public void setLastCollision(Entity lastCollision) {
		this.lastCollisionEntity = lastCollision;
	}

}
