package de.tudarmstadt.informatik.fop.breakout.ui;

import de.tudarmstadt.informatik.fop.breakout.states.HighScoreState;
import de.tudarmstadt.informatik.fop.breakout.states.StoryGameState;
import de.tudarmstadt.informatik.fop.breakout.states.ZoneState;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Matthias Spoerkmann
 */
public class Button extends Entity implements GameParameters{

	ANDEvent mainEvent;
	Action changeState;
	String path;

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
		
		mainEvent = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
		
		// switch stateID, e.g. HIGHSCORE_STATE, MAIN_MENU_STATE, ...
		switch (stateID) {
			case ZONE_PICKER_STATE:
				path = "/images/play_button.png";
				changeState = new ChangeStateInitAction(Breakout.ZONE_PICKER_STATE);
				break;

			case ZONE_STATE:
				path = "/images/entry.png";
				changeState = new ChangeStateInitAction(Breakout.ZONE_STATE);
				// sets levelID only if this Button is clicked!
				mainEvent.addAction((gameContainer, stateBasedGame, i, component) -> ((ZoneState) Breakout.breakout.getState(ZONE_STATE)).setZoneID(stateParameterID));
				break;

			case STORY_GAME_STATE:
				path = "/images/entry.png";
				changeState = new ChangeStateInitAction(Breakout.STORY_GAME_STATE);
				// sets levelID only if this Button is clicked!
				mainEvent.addAction((gameContainer, stateBasedGame, i, component) -> ((StoryGameState) Breakout.breakout.getState(STORY_GAME_STATE)).setLevelID(stateParameterID));
				break;

			case ENDLESS_GAME_STATE:
				path = "/images/endless_button.png";
				changeState = new ChangeStateInitAction(Breakout.ENDLESS_GAME_STATE);
				break;

			case HIGHSCORE_STATE:
				path = "/images/highscore_button.png";
				changeState = new ChangeStateInitAction(Breakout.HIGHSCORE_STATE);
				break;

			case ABOUT_STATE:
				path = "/images/about_button.png";
				changeState = new ChangeStateInitAction(Breakout.ABOUT_STATE);
				break;

			case QUIT_STATE:
				path = "/images/quit_button.png";
				changeState = new ChangeStateInitAction(Breakout.QUIT_STATE);
				break;

			default:
				path = "/images/entry.png";
				changeState = new ChangeStateInitAction(Breakout.MAIN_MENU_STATE);
				break;
		}

		try {
			this.addComponent(new ImageRenderComponent(new Image(path)));

		} catch (SlickException e) {
			e.printStackTrace();
		}

		mainEvent.addAction(changeState);

		this.addComponent(mainEvent);
	}
}
