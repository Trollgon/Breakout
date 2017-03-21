package de.tudarmstadt.informatik.fop.breakout.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.constants.StateParameters;
import de.tudarmstadt.informatik.fop.breakout.factories.BorderFactory;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Lives;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Score;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Stick;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.StopWatch;
import de.tudarmstadt.informatik.fop.breakout.managers.LevelGenerator;
import eea.engine.entity.StateBasedEntityManager;

/**
 * EndlessGameState BasicGameState class
 * @author Jonas Henry Grebe
 *
 */
public class EndlessGameState extends BasicGameState implements GameParameters, StateParameters{

	private StateBasedEntityManager entityManager;
	
	public EndlessGameState() throws SlickException {

		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);

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

		LevelGenerator.getEndlessGameRow().forEach(b -> entityManager.addEntity(ENDLESS_GAME_STATE, b));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(new Image(ENDLESS_BACKGROUND_IMAGE), 0, 0);

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
		
		// endless generator:
		if (LevelGenerator.topRowMissing()) {
			LevelGenerator.getEndlessGameRow().stream().forEach(b -> entityManager.addEntity(ENDLESS_GAME_STATE, b));
		}

		///////////// UPDATING ALL ENTITIES HERE //////////////
		entityManager.updateEntities(container, game, ENDLESS_GAME_STATE);
		///////////////////////////////////////////////////////

		// creates a new Ball if no ball existing
		if (!entityManager.hasEntity(ENDLESS_GAME_STATE, BALL_ID) && (Lives.getLivesAmount() != 0)) {
			entityManager.addEntity(ENDLESS_GAME_STATE,
					new Ball((Stick) entityManager.getEntity(ENDLESS_GAME_STATE, STICK_ID)));
		}

		// stops game if no lifes left
		if (Lives.getLivesAmount() == 0) {
			game.enterState(MAIN_MENU_STATE);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
	}
	
	@Override
	public int getID() {
		return ENDLESS_GAME_STATE;
	}

}
