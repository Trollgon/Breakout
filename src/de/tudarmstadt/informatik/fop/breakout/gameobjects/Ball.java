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
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.NOTEvent;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;

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

	private Stick launcher;

	// entity of last collision:
	private Entity lastCollisionEntity = null;

	// ball events:
	private CollisionEvent collider;
	private Event launched;
	private NOTEvent notLaunched;
	private ANDEvent launch;
	private Event differentCollision;
	private ANDEvent XAxisCollision;
	private ANDEvent YAxisCollision;
	private LeavingScreenEvent leftScreen;

	/**
	 * constructor of ball class
	 * 
	 * @param entityID
	 *            of the new ball
	 */
	public Ball(Stick launcher) {
		super(BALL_ID);

		setPosition(new Vector2f(WINDOW_WIDTH / 2, WINDOW_HEIGHT - 30));
		setSpeed(INITIAL_BALL_SPEED);
		setRotation(0);

		// when Stick class has been implemented with a method getLaunchPos()
		// which delivers the launching position of the Stick:

		setLauncher(launcher);
		setPosition(getLauncher().getLaunchPos());

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

		// event which fires if the ball isn't launched and the SPACE-KEY is
		// pressed -> launch
		launch = new ANDEvent(new NOTEvent(launched), new KeyDownEvent(Input.KEY_SPACE));

		// event which fires if the current collidedEntity is not the same as
		// last collision
		differentCollision = new ANDEvent(collider, new Event("otherCollisionAsLastTime") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return getLastCollision() != collider.getCollidedEntity() || getLastCollision() == null;
			}
		});

		// event which fires if the ball should bounce at the x-axis
		XAxisCollision = new ANDEvent(collider, differentCollision, new Event("xAxisCollision") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				Entity e = collider.getCollidedEntity();

				// if e is a block and has been hit on edge, return true, else
				// false
				if (e instanceof Block) {
					return collidedOnEdge((Block) e);
				}

				return (e.getID() == TOP_BORDER_ID || e.getID() == STICK_ID);
			}

		});

		// event which fires if the ball should bounce at the y-axis
		YAxisCollision = new ANDEvent(collider, differentCollision, new Event("yAxisCollision") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				Entity e = collider.getCollidedEntity();

				// if e is a block and has been hit on edge, return false, else
				// true
				if (e instanceof Block) {
					return !collidedOnEdge((Block) e);
				}

				return (e.getID() == LEFT_BORDER_ID || e.getID() == RIGHT_BORDER_ID);
			}
		});

		// event which fires if the ball left the screen
		leftScreen = new LeavingScreenEvent();

		//////////////////////////////// ACTIONS ////////////////////////////
		
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
				setPosition(getLauncher().getLaunchPos());
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
				
				if(collider.getCollidedEntity().getID() == STICK_ID){
					Physics2D.updateAngleOffset((Ball)collider.getOwnerEntity(), launcher);
				}
			}
		});

		// bounces the ball on the y-axis
		YAxisCollision.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {

				setRotation(Physics2D.bounceYAxis(getRotation()));
			}
		});

		// destroys ball when it left the screen
		leftScreen.addAction(new DestroyEntityAction());

		// adds all events with their actions
		this.addComponent(collider);
		this.addComponent(launch);
		this.addComponent(launched);
		this.addComponent(notLaunched);
		this.addComponent(differentCollision);

		this.addComponent(XAxisCollision);
		this.addComponent(YAxisCollision);

	}

	/**
	 * determines whether this ball has hit the given Block on an edge or not
	 * 
	 * @param block
	 * @return TRUE if hit on EDGE, else FALSE
	 */
	public boolean collidedOnEdge(Block block) {
		final float SENSITIVITY = 1f;
		float offset = block.getPosition().x - this.getPosition().x;

		return (offset < -(block.getSize().x / 2) + SENSITIVITY || (block.getSize().x / 2) - SENSITIVITY > offset);
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

	public void speedUp() {
		setSpeed(getSpeed() + SPEEDUP_VALUE);
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

	private void setLauncher(Stick launcher) {
		this.launcher = launcher;
	}

	private Stick getLauncher() {
		return launcher;
	}
}
