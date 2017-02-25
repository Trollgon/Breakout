package de.tudarmstadt.informatik.fop.breakout.states;

import java.io.IOException;

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
import de.tudarmstadt.informatik.fop.breakout.levels.Levels;
import de.tudarmstadt.informatik.fop.breakout.managers.LevelGenerator;
import eea.engine.entity.StateBasedEntityManager;

/**
 * EndlessGameState GameState class
 * 
 * @author Jonas Henry Grebe
 */
public class EndlessGameState implements GameParameters, GameState {

	private int stateID;

	public StateBasedEntityManager entityManager;

	/**
	 * constructor of a new story game state
	 */
	public EndlessGameState() {
		this.stateID = ENDLESS_GAME_STATE;
		entityManager = StateBasedEntityManager.getInstance();
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
		return this.stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		// adds the games borders: LEFT, TOP and RIGHT
		entityManager.addEntity(getID(), new BorderFactory(BorderType.LEFT).createEntity());
		entityManager.addEntity(getID(), new BorderFactory(BorderType.TOP).createEntity());
		entityManager.addEntity(getID(), new BorderFactory(BorderType.RIGHT).createEntity());

		entityManager.addEntity(getID(), new Stick());
		entityManager.addEntity(getID(), new Ball((Stick) entityManager.getEntity(ENDLESS_GAME_STATE, STICK_ID)));
		entityManager.addEntity(getID(), new Lives());
		entityManager.addEntity(getID(), new Score());
		entityManager.addEntity(getID(), new StopWatch());

		LevelGenerator.getEndlessGameRow().stream().forEach(b -> entityManager.addEntity(ENDLESS_GAME_STATE, b));
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.drawImage(new Image(BACKGROUND_IMAGE), 0, 0);

		entityManager.renderEntities(container, game, g);

		// score display
		g.drawString(((Score) entityManager.getEntity(ENDLESS_GAME_STATE, SCORE_ID)).toString(), 100,
				(WINDOW_HEIGHT - 20));

		// stopwatch display
		g.drawString(((StopWatch) entityManager.getEntity(ENDLESS_GAME_STATE, STOP_WATCH_ID)).toString(), 200,
				(WINDOW_HEIGHT - 20));

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		if (LevelGenerator.topRowMissing()) {
			LevelGenerator.getEndlessGameRow().stream().forEach(b -> entityManager.addEntity(ENDLESS_GAME_STATE, b));
		}

		///////////// UPDATING ALL ENTITIES HERE //////////////
		entityManager.updateEntities(container, game, ENDLESS_GAME_STATE);
		///////////////////////////////////////////////////////

		// creates a new Ball if no ball existing and game not finished
		if (!entityManager.hasEntity(ENDLESS_GAME_STATE, BALL_ID) && (Lives.getLivesAmount() != 0)) {
			entityManager.addEntity(ENDLESS_GAME_STATE,
					new Ball((Stick) entityManager.getEntity(ENDLESS_GAME_STATE, STICK_ID)));
		}

		// stops game if no lifes left
		if (Lives.getLivesAmount() == 0) {
			// game.pauseUpdate();
		}
	}

}
