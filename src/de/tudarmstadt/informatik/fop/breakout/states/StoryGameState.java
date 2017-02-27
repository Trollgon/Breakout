package de.tudarmstadt.informatik.fop.breakout.states;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.factories.BorderFactory;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Lives;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Score;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Stick;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.StopWatch;
import de.tudarmstadt.informatik.fop.breakout.levels.Levels;
import de.tudarmstadt.informatik.fop.breakout.managers.LevelGenerator;
import eea.engine.entity.StateBasedEntityManager;

/**
 * @author Matthias Spoerkmann
 */
public class StoryGameState extends BasicGameState implements GameParameters, GameState {

	private  int levelID;
	private StateBasedEntityManager entityManager;

	/**
	 * constructor of a new story game state
	 */
	public StoryGameState() {
		this.levelID = 0;
		entityManager = StateBasedEntityManager.getInstance();
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		loadLevel();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

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
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.drawImage(new Image(BACKGROUND_IMAGE), 0, 0);

		entityManager.renderEntities(container, game, g);

		// score display
		g.drawString(((Score) entityManager.getEntity(STORY_GAME_STATE, SCORE_ID)).toString(), 100,
				(WINDOW_HEIGHT - 20));

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
		if (!entityManager.hasEntity(STORY_GAME_STATE, BALL_ID) && (Lives.getLivesAmount() != 0)) {
			entityManager.addEntity(STORY_GAME_STATE,
					new Ball((Stick) entityManager.getEntity(STORY_GAME_STATE, STICK_ID)));
		}

	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	}
	
	public void loadLevel() {

		// adds the level's blocks to the entityManager:
		if (levelID != 0) {

			try {
				LevelGenerator.parseLevelFromMap(Levels.getPathByID(this.levelID)).stream()
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

}
