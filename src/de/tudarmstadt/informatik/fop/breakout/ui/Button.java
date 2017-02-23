package de.tudarmstadt.informatik.fop.breakout.ui;

import de.tudarmstadt.informatik.fop.breakout.states.StoryGameState;
import de.tudarmstadt.informatik.fop.breakout.states.ZoneState;
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
 * @author Matthias Spoerkmann
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
	 * @param stateParameterID ID of a parameter needed for Zones or Levels to load.
	 */
	public Button(int xPos, int yPos, int stateID, int stateParameterID) {
		super(BUTTON_ID);

		this.setPosition(new Vector2f(xPos, yPos));
		
		try {
			this.addComponent(new ImageRenderComponent(new Image(BUTTON_IMAGE)));
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		mainEvent = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
		
		// switch stateID, e.g. GAMEPLAY_STATE, MAIN_MENU_STATE, ...
		switch (stateID) {
			case ZONE_PICKER_STATE:
				changeState = new ChangeStateInitAction(Breakout.ZONE_PICKER_STATE);
				break;

			case ZONE_STATE:
				((ZoneState) Breakout.breakout.getState(ZONE_STATE)).setZoneID(stateParameterID);
				changeState = new ChangeStateInitAction(Breakout.ZONE_STATE);
				break;

			case STORY_GAME_STATE:
				((StoryGameState) Breakout.breakout.getState(STORY_GAME_STATE)).setLevelID(stateParameterID);
				changeState = new ChangeStateInitAction(Breakout.STORY_GAME_STATE);
				break;

			default:
				changeState = new ChangeStateInitAction(Breakout.MAIN_MENU_STATE);
				break;
		}
		
		mainEvent.addAction(changeState);
		this.addComponent(mainEvent);
	}
}
