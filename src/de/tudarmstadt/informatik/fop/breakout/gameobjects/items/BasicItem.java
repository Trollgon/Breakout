package de.tudarmstadt.informatik.fop.breakout.gameobjects.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameevents.IDCollisionEvent;
import de.tudarmstadt.informatik.fop.breakout.gameevents.LifeDeductionEvent;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Countdown;
import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveDownAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.TimeEvent;

/**
 * 
 * @author Peter Franke
 *
 *Basic Item class
 */
/*
 * Functionality:
 * 
 * -added using SpawnItemAction()
 * -immediately starts falling down with the given falling speed
 * -destroys itself when falling out
 * - when hitting the stick, it will start a new CountDown with the given startAction and endAction and duration and destroy itself
 * -items like e.g. 1Up with only a one-time effect can use a duration of 0 and null for the endAction
 * - DespawnOnDeath sets if the item will destroy itself if it has not been picked up yet and a life is lost 
 *
 */
public abstract class BasicItem extends Entity implements GameParameters{
	
	protected long duration; // the length of whatever the item does in ms
	protected Action startAction; // this Action will be executed as soon as the item is collected
	protected Action endAction; // this Action will be executed when the duration is over
	protected Event hitStick; //Event to recognize hitting the Stick
	protected Event leftScreen; // fires if the item left the screen
	protected Event spawned; //always true, used for falling movement
	protected ANDEvent despawnEvent; //fires if despawnOnDeath is true and a life is lost
	protected String itemLogoPath; // the path to the item's logo
	protected float fallingSpeed;	// the item's falling speed
	protected Event cancelCondition; //an Event that will stop the running countdown after the item has been picked up, e.g. after a Life was lost and the ball has been reset.
	protected boolean despawnOnDeath; //decides if the item despawns when losing a life
	
	private static int itemNumber = 0; //used to generate a unique ID for each item
	
	public BasicItem(String itemID, long duration, Vector2f startPosition, boolean despawnOnDeath, Action startAction, Action endAction, Event cancelCondition, float fallingSpeed, String itemLogoPath) {
		super(itemID + itemNumber);
		itemNumber++;
		setPosition(startPosition);
		this.fallingSpeed = fallingSpeed;
		this.duration = duration;
		this.startAction = startAction;
		this.endAction = endAction;
		this.cancelCondition = cancelCondition;
		this.despawnOnDeath = despawnOnDeath;
		configureEvents();
		if(!Breakout.getDebug()){ //do not add ImageRenderComponents in debug mode
		try {
			addComponent(new ImageRenderComponent(new Image(itemLogoPath)));
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
		}
		setPassable(false);
		setVisible(true);
		System.out.println("Item created");
	}
	
	public void configureEvents() {
		// Events
		hitStick = new IDCollisionEvent(STICK_ID);
		spawned = new Event("spawnedEvent"){ //used to let the item fall down immediately, always true
		@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return true;
			}
		};
		
		leftScreen = new Event("leftScreen"){
			@Override
				protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
					return (getPosition().getY() > WINDOW_HEIGHT + 30);
				}
			};
		despawnEvent = new ANDEvent(new LifeDeductionEvent(), new Event("despawnondeath") {
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return despawnOnDeath;
				
			}
				});
		
			// Actions
		hitStick.addAction((arg0, arg1, arg2, arg3) -> //create a new Countdown using the given duration, actions and cancelCondition when hitting the Stick
		{StateBasedEntityManager.getInstance().addEntity(arg1.getCurrentStateID(), new Countdown(duration, startAction, endAction, cancelCondition));}
		);
		
		hitStick.addAction(new DestroyEntityAction());
		spawned.addAction(new MoveDownAction(fallingSpeed));
		
		leftScreen.addAction(new DestroyEntityAction());
		despawnEvent.addAction(new DestroyEntityAction());
		
		addComponent(hitStick);
		addComponent(leftScreen);
		addComponent(spawned);
		addComponent(despawnEvent);
	}


	
}
