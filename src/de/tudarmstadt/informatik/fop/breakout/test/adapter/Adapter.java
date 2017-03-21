package de.tudarmstadt.informatik.fop.breakout.test.adapter;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.constants.BlockParameters.BlockType;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Lives;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Stick;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.AbstractBlock;
import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;
import de.tudarmstadt.informatik.fop.breakout.managers.LevelGenerator;
import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.test.TestAppGameContainer;

public class Adapter implements GameParameters {

	/*
	 * the instance of our game, extends StateBasedGame
	 */
	Breakout breakout;

	/**
	 * The TestAppGameContainer for running the tests
	 */
	TestAppGameContainer app;

	Stick stick;
	Ball ball;
	StateBasedEntityManager entityManager;

	/**
	 * Use this constructor to initialize everything you need.
	 */
	public Adapter() {
		breakout = null;

		stick = new Stick(0);
		ball = new Ball(stick, 0);
		entityManager = StateBasedEntityManager.getInstance();
	}

	/*
	 * *************************************************** ********* initialize,
	 * run, stop the game **********
	 * ***************************************************
	 * 
	 * You can normally leave this code as it is.
	 */

	public StateBasedGame getStateBasedGame() {
		return breakout;
	}

	/**
	 * Diese Methode initialisiert das Spiel im Debug-Modus, d.h. es wird ein
	 * AppGameContainer gestartet, der keine Fenster erzeugt und aktualisiert.
	 * 
	 * Sie müssen diese Methode erweitern
	 */
	public void initializeGame() {

		
		// Set the library path depending on the operating system
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/windows");
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/macosx");
		} else {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + "/native/" + System.getProperty("os.name").toLowerCase());
		}

		// Initialize the game in debug mode (no GUI output)
		breakout = new Breakout(true);

		try {
			app = new TestAppGameContainer(breakout);
			app.start(0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stop a running game
	 */
	public void stopGame() {
		if (app != null) {
			app.exit();
			app.destroy();
		}
		StateBasedEntityManager.getInstance().clearAllStates();
		breakout = null;
	}

	public void changeToGameplayState() {
		this.getStateBasedGame().enterState(STORY_GAME_STATE);
		try {
			app.updateGame(1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void changeToHighScoreState() {
		this.getStateBasedGame().enterState(HIGHSCORE_STATE);
		try {
			app.updateGame(1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/*
	 * ***************************************************
	 * ********************** Ball **********************
	 * ***************************************************
	 */

	/**
	 * Returns a new Entity that represents a ball with ID ballID. It was added
	 * for tests, as we do not know what class/package will represent your
	 * "ball" entity.
	 * 
	 * @param ballID
	 *            the ID for the new ball instance
	 * @return an entity representing a ball with the ID passed in as ballID
	 */
	public Entity createBallInstance(String ballID) {

		return new Ball(stick, 0);
	}

	/**
	 * Returns an instance of the IHitable interface that represents a block
	 * with the ID as passed in and the requested number of hits left (1 = next
	 * hit causes the block to vanish, 2 = it takes two hits, ...)
	 * 
	 * @param blockID
	 *            the ID the returns block entity should have
	 * @param hitsUntilDestroyed
	 *            the number of hits (> 0) the block should have left before it
	 *            vanishes (1 = vanishes with next touch by ball)
	 * @return an entity representing a block with the given ID and hits left
	 */
	public IHitable createBlockInstance(String blockID, int hitsUntilDestroyed) {

		BlockType[] types = BlockType.values();
		BlockType type = types[hitsUntilDestroyed];

		return LevelGenerator.getBlockByID(type, 0, 0, 0);
	}

	/**
	 * sets the ball's orientation (angle). Note: the name of the method is
	 * somewhat unfortunate, but is taken from EEA's entity.
	 * 
	 * @param i
	 *            the new orientation angle for the ball (0...360)
	 */
	public void setRotation(int i) {

		ball.setRotation(i);
	}

	/**
	 * returns the ball's orientation (angle). Note: the name of the method is
	 * somewhat unfortunate, but is taken from EEA's entity.
	 * 
	 * @return the orientation angle for the ball (0...360)
	 */
	public float getRotation() {

		return ball.getRotation();
	}

	/**
	 * Sets the ball's position to the coordinate provide
	 * 
	 * @param vector2f
	 *            the target position for the ball
	 */
	public void setPosition(Vector2f vector2f) {
		ball.setPosition(vector2f);
	}

	/**
	 * returns a definition of the ball's size. Typically, the size of the ball
	 * will be constant, but programmers may introduce bonus items that shrink
	 * or enlarge the ball.
	 * 
	 * @return the size of the ball
	 */
	public Vector2f getSize() {

		return ball.getSize();
	}

	/**
	 * returns the current speed of the ball's movement
	 * 
	 * @return the ball's speed
	 */
	public float getSpeed() {
		return ball.getSpeed();
	}

	/**
	 * sets the current speed of the ball to the given value
	 * 
	 * @param speed
	 *            the new speed of the ball
	 */
	public void setSpeed(float speed) {
		ball.setSpeed(speed);
	}

	/**
	 * provide a proper code mapping to a check if your ball entity collides
	 * with 'otherEntity'. You will have to access your ball instance for this
	 * purpose.
	 * 
	 * @param otherEntity
	 *            another entity that the ball may (or may not) collide with
	 * 
	 * @return true if the two entities have collided. Note: your ball should by
	 *         default not collide with itself (or other balls, if there are
	 *         any), null, the background, or "passable" entities (e.g. other
	 *         image you have placed on the screen). It should only collide with
	 *         the stick if the orientation is correct (>90 but <270 degrees,
	 *         else it would "collide with the underside of the stick") but
	 *         should be "gone" then already). It should also collide with the
	 *         borders if the orientation is correct for this, e.g., only
	 *         collide with the top border if the orientation is fitting).
	 */
	public boolean collides(Entity otherEntity) {

		if (otherEntity instanceof Ball) {
			return false;
		}
		return ball.collides(otherEntity);
	}

	/*
	 * ***************************************************
	 * ********************** Player *********************
	 * ***************************************************
	 */

	/**
	 * ensures that the player has "value" additional lives (=additional balls
	 * left).
	 * 
	 * @param value
	 *            the number of additional balls/lives to be added.
	 */
	public void addLives(int value) {
		Lives.setLifeAmount(getLivesLeft() + value);
	}

	/**
	 * ensures that the player has exactly "playerLives" balls/lives left.
	 * 
	 * @param playerLives
	 *            the number of lives/balls the player shall have left
	 */
	public void setLives(int playerLives) {
		Lives.setLifeAmount(playerLives);
	}

	/**
	 * queries your classes for the number of lives/balls the player has left
	 * 
	 * @return the number of lives/balls left
	 */
	public int getLivesLeft() {
		return Lives.getLivesAmount();
	}

	/**
	 * checks if the player still has at least one live/ball left
	 * 
	 * @return true if the player still has at least one live/ball left, else
	 *         false.
	 */
	public boolean hasLivesLeft() {
		return Lives.getLivesAmount() > 0;
	}

	/*
	 * ***************************************************
	 * ********************** Block **********************
	 * ***************************************************
	 */

	/**
	 * Sets a number of necessary hits for degrading this block
	 * 
	 * @param hitsLeft
	 *            number of necessary hits
	 * @param blockID
	 *            blockID ID of the chosen block
	 */
	public void setHitsLeft(int hitsLeft, String blockID) {
		((AbstractBlock) entityManager.getEntity(STORY_GAME_STATE, blockID)).setHitsLeft(hitsLeft);
	}

	/**
	 * Returns the number of necessary hits for degrading this block
	 * 
	 * @param blockID
	 *            ID of the chosen block
	 * @return number of hits
	 */
	public int getHitsLeft(String blockID) {
		return ((AbstractBlock) entityManager.getEntity(STORY_GAME_STATE, blockID)).getHitsLeft();
	}

	/**
	 * Adds a number of necessary hits for degrading this block
	 * 
	 * @param hitsLeft
	 *            number of hits added
	 * @param blockID
	 *            blockID ID of the chosen block
	 */
	public void addHitsLeft(int hitsLeft, String blockID) {
		((AbstractBlock) entityManager.getEntity(STORY_GAME_STATE, blockID)).addHitsLeft(hitsLeft);
	}

	/**
	 * Returns whether the block has hits left
	 * 
	 * @param blockID
	 *            blockID ID of the chosen block
	 * @return true, if block has hits left, else false
	 */
	public boolean hasHitsLeft(String blockID) {
		return ((AbstractBlock) entityManager.getEntity(STORY_GAME_STATE, blockID)).hasHitsLeft();
	}

	/*
	 * ***************************************************
	 * ********************** Stick **********************
	 * ***************************************************
	 */

	/**
	 * returns the current position of the stick
	 * 
	 * @return the current position of the stick
	 */
	public Vector2f getStickPosition() {
		return stick.getPosition();
	}

	/*
	 * ***************************************************
	 * ********************** Input **********************
	 * ***************************************************
	 */

	/**
	 * This Method should emulate the key down event.
	 * 
	 * @param updatetime
	 *            : Zeitdauer bis update-Aufruf
	 * @param input
	 *            : z.B. Input.KEY_K, Input.KEY_L
	 */
	public void handleKeyDown(int updatetime, Integer input) {
		// TODO write code that handles a "key pressed" event
		// note: do not forget to call app.updateGame(updatetime);
	}

	/**
	 * This Method should emulate the pressing of the right arrow key.
	 */
	public void handleKeyDownRightArrow() {
		// TODO write code for handling a "right arrow" key press
		// hint: you may use the above method.
	}

	/**
	 * This Method should emulate the pressing of the left arrow key.
	 */
	public void handleKeyDownLeftArrow() {
		// TODO write code for handling a "left arrow" key press
		// hint: you may use the above method.
	}
}
