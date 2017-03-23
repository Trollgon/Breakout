package de.tudarmstadt.informatik.fop.breakout.states;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.factories.BorderFactory;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.AfterMatchDisplayTextField;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.EnterNameTextField;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Lives;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Score;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Stick;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.StopWatch;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.AbstractBlock;
import de.tudarmstadt.informatik.fop.breakout.levels.Levels;
import de.tudarmstadt.informatik.fop.breakout.managers.CheckPointManager;
import de.tudarmstadt.informatik.fop.breakout.managers.LevelGenerator;
import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.entity.StateBasedEntityManager;

/**
 * @author Matthias Spoerkmann
 */
public class StoryGameState extends BasicGameState implements GameParameters {

	private int levelID;
	protected ZoneType zone;
	private StateBasedEntityManager entityManager;
	private boolean gameOver = false;
	private TextField enterName, firstRow, secondRow, thirdRow, fourthRow;

	/**
	 * constructor of a new story game state
	 */
	public StoryGameState() {
		this.levelID = 0;
		this.zone = ZoneType.NORMALZONE;
		entityManager = StateBasedEntityManager.getInstance();
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	public void setZone(ZoneType zone) {
		this.zone = zone;
	}

	public int getZoneStateID() {
		switch (this.zone) {
		case NORMALZONE:
			return NORMAL_ZONE_STATE;
		case ICEZONE:
			return ICE_ZONE_STATE;
		case MAGMAZONE:
			return MAGMA_ZONE_STATE;
		default:
			return MAIN_MENU_STATE;
		}
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		if (!Breakout.getDebug()) {
			loadLevel();
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		if (!Breakout.getDebug()) {
		
		// adds the games borders: LEFT, TOP and RIGHT
		entityManager.addEntity(getID(), new BorderFactory(BorderType.LEFT).createEntity());
		entityManager.addEntity(getID(), new BorderFactory(BorderType.TOP).createEntity());
		entityManager.addEntity(getID(), new BorderFactory(BorderType.RIGHT).createEntity());

		// adds the gameObjects:
		entityManager.addEntity(getID(), new Stick());
		entityManager.addEntity(getID(), new Ball((Stick) entityManager.getEntity(STORY_GAME_STATE, STICK_ID)));
		entityManager.addEntity(getID(), new Lives());
		entityManager.addEntity(getID(), new Score());
		entityManager.addEntity(getID(), new StopWatch());
		
		} else
			try {
				LevelGenerator.parseLevelFromMap("maps/default.map", 0).stream()
				.forEach(b -> entityManager.addEntity(getID(), b));
			} catch (IOException e) {

				e.printStackTrace();
			}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		if (!Breakout.getDebug()) {
		
		g.drawImage(getZoneBackground(this.zone), 0, 0);

		entityManager.renderEntities(container, game, g);

		// score display
		g.drawString(((Score) entityManager.getEntity(STORY_GAME_STATE, SCORE_ID)).toString(), 100,
				(WINDOW_HEIGHT - 20));

		// stopwatch display
		g.drawString(((StopWatch) entityManager.getEntity(STORY_GAME_STATE, STOP_WATCH_ID)).toString(), 200,
				(WINDOW_HEIGHT - 20));
		}
		
		if (gameOver) {
			firstRow.render(container, g);
			secondRow.render(container, g);
			thirdRow.render(container, g);
			fourthRow.render(container, g);
			enterName.render(container, g);
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		if (!Breakout.getDebug()) {

			// if game is over:
			if (!gameOver) {
				
				///////////// UPDATING ALL ENTITIES HERE //////////////
				entityManager.updateEntities(container, game, STORY_GAME_STATE);
				///////////////////////////////////////////////////////

				// creates a new Ball if no ball existing and game not finished
				if (!entityManager.hasEntity(STORY_GAME_STATE, BALL_ID) && (Lives.getLivesAmount() != 0)) {
					entityManager.addEntity(STORY_GAME_STATE,
							new Ball((Stick) entityManager.getEntity(STORY_GAME_STATE, STICK_ID)));
				}

				// render buttons for restart or menu if number of lives is
				// equal to
				// 0
				/*if (Lives.getLivesAmount() == 0) {

					// entityManager.addEntity(STORY_GAME_STATE, new Button(218,
					// 190, this.levelID, this.zone));
					entityManager.addEntity(STORY_GAME_STATE, new Button(218, 190, StateType.MAINMENU));
					gameOver = true;
				}*/

				// render button for next level/zone if all blocks are destroyed
				if (!entityManager.getEntitiesByState(this.getID()).stream()
						.anyMatch(e -> e instanceof AbstractBlock)) {
					Integer checkpoint = 0;

					if (Levels.getPathByID(this.levelID + 1) != null) {
						//entityManager.addEntity(STORY_GAME_STATE, new Button(218, 190, this.zone));
						checkpoint = this.levelID + 1;

					} else if (Levels.getPathByID(this.levelID + 101 - this.levelID % 100) != null) {
						//entityManager.addEntity(STORY_GAME_STATE, new Button(218, 190, Levels.getNextZone(this.zone)));
						checkpoint = this.levelID + 101 - this.levelID % 100;
					}

					CheckPointManager.setCheckpoint(checkpoint);
					//entityManager.addEntity(STORY_GAME_STATE, new Button(218, 310, StateType.MAINMENU));

					gameOver = true;
					
					entityManager.updateEntities(container, game, STORY_GAME_STATE);
					((StopWatch) entityManager.getEntity(STORY_GAME_STATE, STOP_WATCH_ID)).pauseStopWatch();
					
					firstRow = new AfterMatchDisplayTextField(container, 300, 200, 1,
							((Score) entityManager.getEntity(STORY_GAME_STATE, SCORE_ID)).getScoreCount());
					secondRow = new AfterMatchDisplayTextField(container, 300, 225, 2,
							((StopWatch) entityManager.getEntity(STORY_GAME_STATE, STOP_WATCH_ID)).getTime());
					thirdRow = new AfterMatchDisplayTextField(container, 300, 250, 0, 0);
					fourthRow = new AfterMatchDisplayTextField(container, 300, 275, 5, 0);
					enterName = new AfterMatchDisplayTextField(container, 300, 300, 0, 0);
					enterName.setAcceptingInput(true);
				}
				
				// stops game if no lifes left
				if (Lives.getLivesAmount() == 0) {

					gameOver = true;
					
					entityManager.updateEntities(container, game, STORY_GAME_STATE);
					((StopWatch) entityManager.getEntity(STORY_GAME_STATE, STOP_WATCH_ID)).pauseStopWatch();
					
					firstRow = new AfterMatchDisplayTextField(container, 300, 200, 1,
							((Score) entityManager.getEntity(STORY_GAME_STATE, SCORE_ID)).getScoreCount());
					secondRow = new AfterMatchDisplayTextField(container, 300, 225, 2,
							((StopWatch) entityManager.getEntity(STORY_GAME_STATE, STOP_WATCH_ID)).getTime());
					thirdRow = new AfterMatchDisplayTextField(container, 300, 250, 0, 0);
					fourthRow = new AfterMatchDisplayTextField(container, 300, 275, 6, 0);
					enterName = new AfterMatchDisplayTextField(container, 300, 300, 0, 0);
					enterName.setAcceptingInput(true);
				}

			}
			
			// stops user-interaction when game is over
			if (gameOver) {
				// this.inputEnded();
				if (((AfterMatchDisplayTextField) enterName).getPressedEnter()) {
					game.enterState(MAIN_MENU_STATE);
				}
			}
			
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	}

	public void loadLevel() {

		// adds the level's blocks to the entityManager:
		if (levelID != 0) {

			try {
				LevelGenerator.parseLevelFromMap(Levels.getPathByID(this.levelID))
						.forEach(b -> entityManager.addEntity(getID(), b));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getID() {
		return STORY_GAME_STATE;
	}

	public Image getZoneBackground(ZoneType zone) throws SlickException {

		switch (zone) {
		case MAGMAZONE:
			return new Image("/images/backgrounds/background_magma.png");
		case ICEZONE:
			return new Image("/images/backgrounds/background_ice.png");
		default:
		case NORMALZONE:
			return new Image("/images/backgrounds/background.png");
		}
	}
}
