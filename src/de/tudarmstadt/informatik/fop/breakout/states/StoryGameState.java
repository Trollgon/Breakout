package de.tudarmstadt.informatik.fop.breakout.states;

import java.io.IOException;

import de.tudarmstadt.informatik.fop.breakout.levels.Levels;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.factories.BorderFactory;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Lives;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Score;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Stick;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.StopWatch;
import de.tudarmstadt.informatik.fop.breakout.managers.LevelGenerator;
import eea.engine.entity.StateBasedEntityManager;

/**
 * @author Matthias Spoerkmann
 */
public class StoryGameState implements GameParameters, GameState {
	
	private int stateID;
	protected int levelID = 0;
	
	public StateBasedEntityManager entityManager;

	/**
	 * constructor of a new story game state
	 */
	public StoryGameState() {
		this.stateID = STORY_GAME_STATE;
		entityManager = StateBasedEntityManager.getInstance();
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	}

	@Override
	public void mousePressed(int button, int x, int y) {
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
	}

	@Override
	public void mouseWheelMoved(int change) {
	}

	@Override
	public void inputEnded() {
	}

	@Override
	public void inputStarted() {
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input input) {
	}

	@Override
	public void keyPressed(int key, char c) {
	}

	@Override
	public void keyReleased(int key, char c) {
	}

	@Override
	public void controllerButtonPressed(int controller, int button) {
	}

	@Override
	public void controllerButtonReleased(int controller, int button) {
	}

	@Override
	public void controllerDownPressed(int controller) {
	}

	@Override
	public void controllerDownReleased(int controller) {
	}

	@Override
	public void controllerLeftPressed(int controller) {
	}

	@Override
	public void controllerLeftReleased(int controller) {
	}

	@Override
	public void controllerRightPressed(int controller) {
	}

	@Override
	public void controllerRightReleased(int controller) {
	}

	@Override
	public void controllerUpPressed(int controller) {
	}

	@Override
	public void controllerUpReleased(int controller) {
	}

	@Override
	public boolean isRenderPaused() {
		return false;
	}

	@Override
	public boolean isUpdatePaused() {
		return false;
	}

	@Override
	public void pauseRender() {
	}

	@Override
	public void pauseUpdate() {
	}

	@Override
	public void setRenderPaused(boolean arg0) {
	}

	@Override
	public void setUpdatePaused(boolean arg0) {
	}

	@Override
	public void unpauseRender() {
	}

	@Override
	public void unpauseUpdate() {
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public int getID() {
		return STORY_GAME_STATE;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		// adds the games borders: LEFT, TOP and RIGHT
		entityManager.addEntity(getID(), new BorderFactory(BorderType.LEFT).createEntity());
		entityManager.addEntity(getID(), new BorderFactory(BorderType.TOP).createEntity());
		entityManager.addEntity(getID(), new BorderFactory(BorderType.RIGHT).createEntity());
		entityManager.addEntity(getID(), new Stick());
		entityManager.addEntity(getID(), new Ball((Stick) entityManager.getEntity(STORY_GAME_STATE, STICK_ID)));
		entityManager.addEntity(getID(), new Lives());
		entityManager.addEntity(getID(), new Score());
		entityManager.addEntity(getID(), new StopWatch());
		
		// adds the level's blocks to the entityManager:
		if (levelID != 0) {
			try {
				LevelGenerator.parseLevelFromMap(Levels.getPathByID(this.levelID)).stream().forEach(b -> entityManager.addEntity(getID(), b));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.drawImage(new Image(BACKGROUND_IMAGE), 0, 0);

		entityManager.renderEntities(container, game, g);

		// score display
		g.drawString(((Score) entityManager.getEntity(STORY_GAME_STATE, SCORE_ID)).toString(), 100, (WINDOW_HEIGHT - 20));

		// stopwatch display
		g.drawString(((StopWatch) entityManager.getEntity(STORY_GAME_STATE, STOP_WATCH_ID)).toString(), 200,
				(WINDOW_HEIGHT - 20));

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		///////////// UPDATING ALL ENTITIES HERE //////////////
		entityManager.updateEntities(container, game, STORY_GAME_STATE);
		///////////////////////////////////////////////////////

		// creates a new Ball if no ball existing and game not finished
		if (!entityManager.hasEntity(STORY_GAME_STATE, BALL_ID)
				&& (Lives.getLivesAmount() != 0)) {
			entityManager.addEntity(STORY_GAME_STATE,
					new Ball((Stick) entityManager.getEntity(STORY_GAME_STATE, STICK_ID)));
		}

		// player loses the game (his life amount drops to 0) or wins it
		// (destroyed all blocks)
		/*if (((((Lives) entityManager.getEntity(GAMEPLAY_STATE, LIVES_ID)).getLivesAmount() == 0)
				| (!entityManager.hasEntity(GAMEPLAY_STATE, BLOCK_ID))) & !gameFinished) {
			gameFinished = true;
			((StopWatch) entityManager.getEntity(GAMEPLAY_STATE, STOP_WATCH_ID)).pauseStopWatch();
			// ball has to be made unlaunchable here...
			if (((Lives) entityManager.getEntity(GAMEPLAY_STATE, LIVES_ID)).getLivesAmount() == 0) {
				// insert output box of shame here
			} else {
				// insert output box of victory here
			}
			// just a test name... some "Enter your name"-shit needed here
			String playerName = "J�rg";
			// adds the player to the highscore list if the score is good enough
			try {
				if (HighscoreManager.checkIfScoreHighEnough(
						((Score) entityManager.getEntity(GAMEPLAY_STATE, SCORE_ID)).getScoreCount())) {
					HighscoreManager.addPlayerToHighscore(new Player(playerName,
							((Score) entityManager.getEntity(GAMEPLAY_STATE, SCORE_ID)).getScoreCount(),
							((StopWatch) entityManager.getEntity(GAMEPLAY_STATE, STOP_WATCH_ID)).getTime()));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// still needs to end the match...
		}*/
	}

}
