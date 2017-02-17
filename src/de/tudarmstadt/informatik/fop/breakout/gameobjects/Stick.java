package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.physics.Physics2D;
import eea.engine.action.Action;
import eea.engine.action.basicactions.MoveLeftAction;
import eea.engine.action.basicactions.MoveRightAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.NOTEvent;
import eea.engine.event.OREvent;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.KeyDownEvent;

/**
 * 
 * @author Peter Franke
 *
 */
public class Stick extends Entity implements GameParameters {

	private final int startPosX = Math.floorDiv(WINDOW_WIDTH, 2);
	private final int startPosY = WINDOW_HEIGHT - 20;
	private final float speed = STICK_SPEED * 16; // 0.5 speed is way too slow
	private float angleOffset;
	
	// private Entity lastHitEntity;

	private OREvent leftKeys;
	private OREvent rightKeys;
	private Event leftBorderReached;
	private Event rightBorderReached;
	private ANDEvent moveLeftCondition;
	private ANDEvent moveRightCondition;
	private Event hitByBall;
	private CollisionEvent collider;

	/**
	 * Constructor of the Stick class
	 * 
	 * sets the initial position, size, image and configures the events
	 */
	public Stick() {
		super(STICK_ID);
		setPosition(new Vector2f(startPosX, startPosY));
		setSize(new Vector2f(130, 25));
		try {
			this.addComponent(new ImageRenderComponent(new Image(STICK_IMAGE)));
		} catch (SlickException e) {

			e.printStackTrace();
		}
		configureEvents();
		setVisible(true);
		setPassable(false);

	}

	/**
	 * Configures all the Events, adds their actions and adds the necessary
	 * Components
	 */
	private void configureEvents() {
		// Events

		// fires if A or the left arrow key is pressed
		leftKeys = new OREvent(new KeyDownEvent(Input.KEY_LEFT), new KeyDownEvent(Input.KEY_A));

		// fires if d or the right arrow key is pressed
		rightKeys = new OREvent(new KeyDownEvent(Input.KEY_RIGHT), new KeyDownEvent(Input.KEY_D));

		// fires if the stick has hit the left Border
		leftBorderReached = new Event("hitLeftBorder") {
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return (getPosition().getX() - getSize().getX() / 2) <= 0;
			}
		};

		// fires if the stick has hit the right border
		rightBorderReached = new Event("hitRightBorder") {
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return (getPosition().getX() + getSize().getX() / 2) >= WINDOW_WIDTH;
			}
		};
		// fires if the left border is not reached yet, a left key is pressed,
		// no right key is pressed
		moveLeftCondition = new ANDEvent(new NOTEvent(leftBorderReached), leftKeys, new NOTEvent(rightKeys));

		// fires if the right border is not reached yet, a right key is pressed,
		// no left key is pressed
		moveRightCondition = new ANDEvent(new NOTEvent(rightBorderReached), rightKeys, new NOTEvent(leftKeys));

		// basic collider
		collider = new CollisionEvent();

		// fires if the ball is hit
		hitByBall = new ANDEvent(collider, new Event("hitEntityIsBall") {
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return collider.getCollidedEntity() instanceof Ball;
			}

		});
		// Actions

		moveLeftCondition.addAction(new MoveLeftAction(speed));
		moveRightCondition.addAction(new MoveRightAction(speed));

		
	/*	collider.addAction(new Action(){
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3){
				setLastHitEntity(collider.getCollidedEntity());
			}
		}); */
		hitByBall.addAction(new Action(){

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
			//	Physics2D.updateAngleOffset((Ball) collider.getCollidedEntity(), (Stick) collider.getOwnerEntity());
			
			try {
				new Sound("sounds/hitStick.wav").play(0.9f, 1); //ersetzen durch https://www.freesound.org/people/Mrguff/sounds/369711/
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});

		this.addComponent(moveLeftCondition);
		this.addComponent(moveRightCondition);
		this.addComponent(collider);
		this.addComponent(hitByBall);
	}

	// service for Ball
	/**
	 * Used to give a ball its launching position
	 * 
	 * @return the launching position for the ball
	 */
	public Vector2f getLaunchPos() {
		return new Vector2f(getPosition().getX(), getPosition().getY() - 26);
	}

	@Deprecated
	/**
	 * - not working anymore - previously used to get the angle offset, not
	 * needed anymore as the angle is automatically adjusted in the method in
	 * the Physics2D class
	 * 
	 * 
	 * @return the angleOffset
	 */
	public float getAngleOffset() {
		return angleOffset;
	}
}
