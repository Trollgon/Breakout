package de.tudarmstadt.informatik.fop.breakout.states;

import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.constants.StateParameters;
import de.tudarmstadt.informatik.fop.breakout.constants.StateParameters.StateType;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.ClearHighscoreButton;
//import de.tudarmstadt.informatik.fop.breakout.gameobjects.ClearHighscoreButton;
import de.tudarmstadt.informatik.fop.breakout.managers.HighscoreManager;
import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.entity.StateBasedEntityManager;

/**
 * HighscoreState-class, GUI for the highscores of the game
 * @author Lukas Lehmann
 *
 */
public class HighScoreState extends BasicGameState implements GameParameters {

	private int stateID;
	private StateBasedEntityManager entityManager;
	private TrueTypeFont tFont;
	private Font font;

	public HighScoreState() {
		this.stateID = HIGHSCORE_STATE;
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		if (!Breakout.getDebug()) {

			font = new Font("Monospaced", Font.BOLD, 25);
			tFont = new TrueTypeFont(font, true);

			entityManager.addEntity(getID(), new ClearHighscoreButton(600, 550));
			
			ChangeStateInitAction back = new ChangeStateInitAction(MAIN_MENU_STATE);
			Image image = new Image("/images/buttons/back_button.png");
			Button b = new Button(750, 550, back, image);
			
			entityManager.addEntity(getID(), b);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		if (!Breakout.getDebug()) {

			g.drawImage(new Image(BACKGROUND_IMAGE), 0, 0);

			entityManager.renderEntities(container, game, g);
			
			tFont.drawString(555, 530, "Löschen");
			
			try {
				tFont.drawString(50, 50, HighscoreManager.displayHighscore()[0]);
				int lineNumber = 90;
				for (int i = 1; i <= 10; i++) {
					if (HighscoreManager.displayHighscore()[i] == null)
						break;
					tFont.drawString(50, lineNumber, HighscoreManager.displayHighscore()[i]);
					lineNumber += 30;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		entityManager.updateEntities(container, game, HIGHSCORE_STATE);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}
