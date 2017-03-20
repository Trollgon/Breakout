package de.tudarmstadt.informatik.fop.breakout.states;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.openal.SoundStore;
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
import de.tudarmstadt.informatik.fop.breakout.managers.HighscoreManager;
import de.tudarmstadt.informatik.fop.breakout.managers.LevelGenerator;
import de.tudarmstadt.informatik.fop.breakout.managers.Player;
import eea.engine.entity.StateBasedEntityManager;

/**
 * EndlessGameState BasicGameState class
 * @author Jonas Henry Grebe
 *
 */
public class EndlessGameState extends BasicGameState implements GameParameters {

	private StateBasedEntityManager entityManager;
	
	private TextField enterName, firstRow, secondRow, thirdRow, fourthRow;

	private boolean gameEnded = false;
	
	public EndlessGameState() throws SlickException {

		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		
		// start playing the background Music
		
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
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(new Image(ENDLESS_BACKGROUND_IMAGE), 0, 0);

		entityManager.renderEntities(container, game, g);

		// score display
		g.drawString(((Score) entityManager.getEntity(ENDLESS_GAME_STATE, SCORE_ID)).toString(), 100,
				(WINDOW_HEIGHT - 20));

		// stopwatch display
		g.drawString(((StopWatch) entityManager.getEntity(ENDLESS_GAME_STATE, STOP_WATCH_ID)).toString(), 200,
				(WINDOW_HEIGHT - 20));
		
		if (gameEnded) {
			firstRow.render(container, g);
			secondRow.render(container, g);
			thirdRow.render(container, g);
			fourthRow.render(container, g);
			enterName.render(container, g);
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if (!gameEnded) {

			// endless generator:
			if (LevelGenerator.topRowMissing()) {
				LevelGenerator.getEndlessGameRow().stream()
						.forEach(b -> entityManager.addEntity(ENDLESS_GAME_STATE, b));
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

				gameEnded = true;
				
				entityManager.updateEntities(container, game, ENDLESS_GAME_STATE);
				((StopWatch) entityManager.getEntity(ENDLESS_GAME_STATE, STOP_WATCH_ID)).pauseStopWatch();
				
				firstRow = new AfterMatchDisplayTextField(container, 300, 200, 1, ((Score) entityManager.getEntity(ENDLESS_GAME_STATE, SCORE_ID)).getScoreCount());
				secondRow = new AfterMatchDisplayTextField(container, 300, 225, 2, ((StopWatch) entityManager.getEntity(ENDLESS_GAME_STATE, STOP_WATCH_ID)).getTime());
				thirdRow = new AfterMatchDisplayTextField(container, 300, 250, 0, 0);
				
				try {
					if (HighscoreManager.checkIfScoreHighEnough(((Score) entityManager.getEntity(ENDLESS_GAME_STATE, SCORE_ID)).getScoreCount())) {
						fourthRow = new AfterMatchDisplayTextField(container, 300, 275, 3, 0);
						enterName = new EnterNameTextField(container, 300, 300);
					}
					else {
						fourthRow = new AfterMatchDisplayTextField(container, 300, 275, 4, 0);
						enterName = new AfterMatchDisplayTextField(container, 300, 300, 0, 0);
						enterName.setAcceptingInput(true);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		
		if (gameEnded) {
			if ((enterName instanceof EnterNameTextField) && (((EnterNameTextField) enterName).getPressedEnter())) {
				try {
					HighscoreManager.addPlayerToHighscore(new Player(enterName.getText(),
							((Score) entityManager.getEntity(ENDLESS_GAME_STATE, SCORE_ID)).getScoreCount(),
							((StopWatch) entityManager.getEntity(ENDLESS_GAME_STATE, STOP_WATCH_ID)).getTime()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				game.pauseUpdate();
				game.enterState(MAIN_MENU_STATE);
			}
			if ((enterName instanceof AfterMatchDisplayTextField) && (((AfterMatchDisplayTextField) enterName).getPressedEnter())) {
				game.pauseUpdate();
				game.enterState(MAIN_MENU_STATE);
			}
		}
		
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		SoundStore.get().pauseLoop();
	}
	
	@Override
	public int getID() {
		return ENDLESS_GAME_STATE;
	}

}
