package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.action.basicactions.MoveLeftAction;
import eea.engine.action.basicactions.MoveRightAction;
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
	private final int startPosY = WINDOW_HEIGHT - 42;
	private final float speed = STICK_SPEED;
	
	private OREvent leftKeys;
	private OREvent rightKeys;
	private Event leftBorderReached;
	private Event rightBorderReached;
	private ANDEvent moveLeftCondition;
	private ANDEvent moveRightCondition;
	private CollisionEvent collider;
	private ANDEvent hitByItem;
	private boolean mirrored;

	/**
	 * Constructor of the Stick class
	 * 
	 * sets the initial position, size, image and configures the events
	 */
	public Stick() {
		super(STICK_ID);
		setPosition(new Vector2f(startPosX, startPosY));
		setSize(new Vector2f(130, 25));
		mirrored = false;
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

		hitByItem = new ANDEvent(collider, new Event("hitEntityIsItem") {
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return collider.getCollidedEntity().getID().toUpperCase().contains("ITEM");
			}
		});
		
		// Actions
		moveLeftCondition.addAction(new MoveLeftAction(speed));
		moveRightCondition.addAction(new MoveRightAction(speed));

		this.addComponent(moveLeftCondition);
		this.addComponent(moveRightCondition);
		this.addComponent(collider);
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
	
	public void mirrorInput(){
		moveLeftCondition.clearActions();
		moveRightCondition.clearActions();
		if(mirrored){
			moveLeftCondition.addAction(new MoveLeftAction(speed));
			moveRightCondition.addAction(new MoveRightAction(speed));
			
		}
		else {
			moveLeftCondition.addAction(new MoveRightAction(speed));
			moveRightCondition.addAction(new MoveLeftAction(speed));
		}
		mirrored = !mirrored;
	}

}
