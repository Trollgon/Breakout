package de.tudarmstadt.informatik.fop.breakout.ui;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;

/**
 * @author Matthias Spörkmann
 */
public class Button extends Entity implements GameParameters{

	ANDEvent mainEvent;
	Action changeState;
	
	/**
	 * Button-class constructor
	 * @param buttonID
	 * @param xPos x-Position
	 * @param yPos y-Position
	 * @param stateID ID of the entered state by clicking
	 */
	public Button(int xPos, int yPos, int stateID) {
		super(BUTTON_ID);

		this.setPosition(new Vector2f(xPos, yPos));
		
		try {
			this.addComponent(new ImageRenderComponent(new Image(BUTTON_IMAGE)));
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		mainEvent = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
		
		// switch stateID, e.g. GAMEPLAY_STATE, MAINMENU_STATE, ...
		switch (stateID) {
		case GAMEPLAY_STATE:
			changeState = new ChangeStateInitAction(Breakout.GAMEPLAY_STATE);
			break;
			
		default:
			changeState = new ChangeStateInitAction(Breakout.MAINMENU_STATE);
			break;
		}
		
		mainEvent.addAction(changeState);
		this.addComponent(mainEvent);
	}
}
