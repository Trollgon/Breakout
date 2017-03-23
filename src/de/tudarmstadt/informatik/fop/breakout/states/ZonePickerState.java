package de.tudarmstadt.informatik.fop.breakout.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.managers.CheckPointManager;
import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.entity.StateBasedEntityManager;

/**
 * @author Matthias Spoerkmann
 */
public class ZonePickerState extends BasicGameState implements GameParameters {

	private StateBasedEntityManager entityManager;
	private Integer checkpoint = 0;

	/**
	 * Constructor of new campaign state.
	 * 
	 * @param stateID
	 *            of this state
	 */
	public ZonePickerState() {
		checkpoint = CheckPointManager.getCheckpoint();
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

		if (!Breakout.getDebug()) {
		
		entityManager.addEntity(getID(), new Button(218, 190, ZoneType.NORMALZONE));
		
		if (checkpoint > 199) {
			entityManager.addEntity(getID(), new Button(218, 310, ZoneType.ICEZONE));
		}
		if (checkpoint > 299) {
			entityManager.addEntity(getID(), new Button(518, 190, ZoneType.MAGMAZONE));
		}
		
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		{
		
		g.drawImage(new Image("/images/backgrounds/background_2.png"), 0, 0);

		entityManager.renderEntities(container, game, g);

		g.drawString("Normal Zone", 150, 180);
		
		if (checkpoint > 199) {
			g.drawString("Ice Zone", 150, 300);
		}
		if (checkpoint > 199) {
			g.drawString("Magma Zone", 450, 180);
		}
		
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

	}

	@Override
	public int getID() {
		return ZONE_PICKER_STATE;
	}

}
