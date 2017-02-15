package de.tudarmstadt.informatik.fop.breakout.states;

import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.entity.StateBasedEntityManager;

public class MainMenuState implements GameParameters, GameState {
	
	private int stateID;
	StateBasedEntityManager entityManager;
	
	public MainMenuState(int stateID) {
		
		this.stateID = stateID;
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
		// TODO
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
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.drawImage(new Image(BACKGROUND_IMAGE), 0, 0);
		entityManager.addEntity(this.stateID, new Button("EnterGamePlayState", 218, 190, GAMEPLAY_STATE));
		entityManager.addEntity(this.stateID, new Button("Test1", 218, 300, 0));
		entityManager.addEntity(this.stateID, new Button("Test2", 218, 410, 0));
		entityManager.renderEntities(container, game, g);

		g.drawString("Neues Spiel", 110, 180);
		g.drawString("Highscore", 110, 290);
		g.drawString("Beenden", 110, 400);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	
		entityManager.updateEntities(container, game, stateID);
	}

}
