package de.tudarmstadt.informatik.fop.breakout.gameobjects;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;

import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
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
	
	private int posx = Math.floorDiv(WINDOW_WIDTH, 2);
	private int posy = WINDOW_HEIGHT-20;
	
	private OREvent leftKeys;
	private OREvent rightKeys; 
	private Event leftBorderReached;
	private Event rightBorderReached;
	private ANDEvent moveLeftCondition;
	private ANDEvent moveRightCondition;
	
	public Stick() {
		super(STICK_ID);
		setPosition(new Vector2f(posx, posy));
		setSize(new Vector2f(130, 25));
		setPassable(false);
		try{
			this.addComponent(new ImageRenderComponent(new Image(STICK_IMAGE)));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configureEvents();
	}
	
	private void configureEvents(){
		//Events
		
		leftKeys = new OREvent(new KeyDownEvent(Input.KEY_LEFT), new KeyDownEvent(Input.KEY_A));
		
		rightKeys = new OREvent(new KeyDownEvent(Input.KEY_RIGHT), new KeyDownEvent(Input.KEY_D));
		
		leftBorderReached = new Event("hitLeftBorder"){
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return (getPosition().getX() - getSize().getX() / 2) <= 0;
			}
		};
		
		rightBorderReached = new Event("hitRightBorder"){
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return (getPosition().getX() + getSize().getX() / 2) >= WINDOW_WIDTH;
			}
		};
		
		moveLeftCondition = new ANDEvent(new NOTEvent(leftBorderReached), leftKeys, new NOTEvent(rightKeys));
		
		moveRightCondition = new ANDEvent(new NOTEvent(rightBorderReached), rightKeys, new NOTEvent(leftKeys));
		
		//Actions
		
		moveLeftCondition.addAction(new MoveLeftAction(STICK_SPEED * 16));
		moveRightCondition.addAction(new MoveRightAction(STICK_SPEED * 16));
	
	/*	this.addComponent(leftKeys);
		this.addComponent(rightKeys);
		this.addComponent(leftBorderReached);
		this.addComponent(rightBorderReached);*/
		this.addComponent(moveLeftCondition);
		this.addComponent(moveRightCondition);
	}

}
