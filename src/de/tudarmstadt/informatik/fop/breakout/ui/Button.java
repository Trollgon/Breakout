package de.tudarmstadt.informatik.fop.breakout.ui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.states.StoryGameState;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

/**
 * @author Matthias Spoerkmann
 */
public class Button extends Entity implements GameParameters {

	ANDEvent mainEvent;
	Action changeState;
	String path;

	/**
	 * @deprecated Button-class constructor
	 * 
	 * @param xPos
	 *            x-Position
	 * @param yPos
	 *            y-Position
	 * @param stateID
	 *            ID of the entered state by clicking
	 * @param stateParameterID
	 *            ID of a parameter needed for Zones or Levels to load.
	 */
	public Button(int xPos, int yPos, StateType stateID, int stateParameterID) {
		super(BUTTON_ID);

		this.setPosition(new Vector2f(xPos, yPos));

		mainEvent = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());

		// switch stateID, e.g. HIGHSCORE_STATE, MAIN_MENU_STATE, ...
		switch (stateID) {
		case ZONEPICKER:
			path = "/images/buttons/play_button.png";
			changeState = new ChangeStateInitAction(Breakout.ZONE_PICKER_STATE);
			break;

		case STORYGAME:
			path = "/images/buttons/entry.png";
			changeState = new ChangeStateInitAction(Breakout.STORY_GAME_STATE);
			// sets levelID only if this Button is clicked!
			mainEvent.addAction((gameContainer, stateBasedGame, i,
					component) -> ((StoryGameState) Breakout.breakout.getState(STORY_GAME_STATE))
							.setLevelID(stateParameterID));
			break;

		case ENDLESS:
			path = "/images/buttons/endless_button.png";
			changeState = new ChangeStateInitAction(Breakout.ENDLESS_GAME_STATE);
			break;

		case HIGHSCORE:
			path = "/images/buttons/highscore_button.png";
			changeState = new ChangeStateInitAction(Breakout.HIGHSCORE_STATE);
			break;

		case ABOUT:
			path = "/images/buttons/about_button.png";
			changeState = new ChangeStateInitAction(Breakout.ABOUT_STATE);
			break;

		case QUIT:
			path = "/images/buttons/quit_button.png";
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

	/**
	 * constructor of Button
	 * 
	 * @param xPos
	 *            of the new Button
	 * @param yPos
	 *            of the new Button
	 * @param zone
	 *            of the ZoneState you want to enter by clicking
	 */
	public Button(int xPos, int yPos, ZoneType zone) {

		super(BUTTON_ID);

		this.setPosition(new Vector2f(xPos, yPos));
		mainEvent = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());

		switch (zone) {
		case JUNGLEZONE:
			changeState = new ChangeStateInitAction(JUNGLE_ZONE_STATE);
			break;
		case ICEZONE:
			changeState = new ChangeStateInitAction(ICE_ZONE_STATE);
			break;
		default:
		case NORMALZONE:
			changeState = new ChangeStateInitAction(NORMAL_ZONE_STATE);
			break;
		}

		try {
			this.addComponent(new ImageRenderComponent(new Image(BUTTON_IMAGE)));

		} catch (SlickException e) {
			e.printStackTrace();
		}

		mainEvent.addAction(changeState);
		this.addComponent(mainEvent);
	}

	/**
	 * constructor of Button (DO NOT USE FOR ENTERING A ZONESTATE OR
	 * STORYGAMESTATE!)
	 * 
	 * @param xPos
	 *            of the new Button
	 * @param yPos
	 *            of the new Button
	 * @param state
	 *            you just want to enter by clicking
	 */
	public Button(int xPos, int yPos, StateType state) {
		super(BUTTON_ID);

		this.setPosition(new Vector2f(xPos, yPos));
		mainEvent = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());

		switch (state) {
		case ABOUT:
			path = "/images/buttons/about_button.png";
			changeState = new ChangeStateInitAction(ABOUT_STATE);
			break;
		case ENDLESS:
			changeState = new ChangeStateInitAction(ENDLESS_GAME_STATE);
			path = "/images/buttons/endless_button.png";
			break;
		case HIGHSCORE:
			changeState = new ChangeStateInitAction(HIGHSCORE_STATE);
			path = "/images/buttons/highscore_button.png";
			break;
		default:
		case MAINMENU:
			path = "/images/buttons/entry.png";
			changeState = new ChangeStateInitAction(MAIN_MENU_STATE);
			break;
		case QUIT:
			path = "/images/buttons/quit_button.png";
			changeState = new ChangeStateInitAction(QUIT_STATE);
			break;
		case ZONEPICKER:
			changeState = new ChangeStateInitAction(ZONE_PICKER_STATE);
			path = "/images/buttons/play_button.png";
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

	/**
	 * constructor of Button
	 * 
	 * @param xPos
	 *            of the new Button
	 * @param yPos
	 *            of the new Button
	 * @param levelID
	 *            of the storygame-level you want to enter by clicking
	 */
	public Button(int xPos, int yPos, int levelID, ZoneType zone) {
		super(BUTTON_ID);

		this.setPosition(new Vector2f(xPos, yPos));

		mainEvent = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());

		// sets levelID only if this Button is clicked!
		mainEvent.addAction((gameContainer, stateBasedGame, i,
							 component) -> ((StoryGameState) Breakout.breakout.getState(STORY_GAME_STATE)).setLevelID(levelID));

		// tells the StoryGameState the zoneType
		mainEvent.addAction((arg0, arg1, arg2, arg3) -> ((StoryGameState) Breakout.breakout.getState(STORY_GAME_STATE)).setZone(zone));

		try {
			this.addComponent(new ImageRenderComponent(new Image (BUTTON_IMAGE)));

		} catch (SlickException e) {
			e.printStackTrace();
		}

		changeState = new ChangeStateInitAction(Breakout.STORY_GAME_STATE);
		mainEvent.addAction(changeState);

		this.addComponent(mainEvent);
	}
}
