package de.tudarmstadt.informatik.fop.breakout.states;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.entity.StateBasedEntityManager;
import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Matthias Spoerkmann
 */
public class ZoneState implements GameParameters, GameState {

    private int stateID;
    protected ZoneType zoneID;
    StateBasedEntityManager entityManager;

    /**
     * Constructor of new zone state
     */
    public ZoneState() {
        this.stateID = ZONE_STATE;
        entityManager = StateBasedEntityManager.getInstance();
    }

    public void setZoneID(ZoneType zoneID) {
        this.zoneID = zoneID;
    }

    @Override
    public int getID() {
        return ZONE_STATE;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        entityManager.addEntity(getID(), new Button(218, 190, 101));
        entityManager.addEntity(getID(), new Button(218, 310, 102));

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        switch (this.zoneID) {
		case NORMALZONE:
			g.drawImage(new Image(BACKGROUND_IMAGE), 0, 0);
			break;
		case ICEZONE:
			g.drawImage(new Image(BACKGROUND_IMAGE), 0, 0);
			break;
		default:
			break;
        }

        entityManager.renderEntities(container, game, g);

        g.drawString("Level 1", 110, 180);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        entityManager.updateEntities(container, game, delta);
    }

    @Override
    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void controllerLeftPressed(int i) {

    }

    @Override
    public void controllerLeftReleased(int i) {

    }

    @Override
    public void controllerRightPressed(int i) {

    }

    @Override
    public void controllerRightReleased(int i) {

    }

    @Override
    public void controllerUpPressed(int i) {

    }

    @Override
    public void controllerUpReleased(int i) {

    }

    @Override
    public void controllerDownPressed(int i) {

    }

    @Override
    public void controllerDownReleased(int i) {

    }

    @Override
    public void controllerButtonPressed(int i, int i1) {

    }

    @Override
    public void controllerButtonReleased(int i, int i1) {

    }

    @Override
    public void keyPressed(int i, char c) {

    }

    @Override
    public void keyReleased(int i, char c) {

    }

    @Override
    public void mouseWheelMoved(int i) {

    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mousePressed(int i, int i1, int i2) {

    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {

    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return false;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }

    @Override
    public void pauseUpdate() {

    }

    @Override
    public void pauseRender() {

    }

    @Override
    public void unpauseUpdate() {

    }

    @Override
    public void unpauseRender() {

    }

    @Override
    public boolean isUpdatePaused() {
        return false;
    }

    @Override
    public boolean isRenderPaused() {
        return false;
    }

    @Override
    public void setUpdatePaused(boolean b) {

    }

    @Override
    public void setRenderPaused(boolean b) {

    }
}
