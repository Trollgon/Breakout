package de.tudarmstadt.informatik.fop.breakout.states;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.entity.StateBasedEntityManager;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Matthias Spoerkmann
 */
public class ZonePickerState extends BasicGameState implements GameParameters {

	private StateBasedEntityManager entityManager;

	/**
	 * Constructor of new campaign state.
	 * 
	 * @param stateID
	 *            of this state
	 */
	public ZonePickerState() {
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

		entityManager.addEntity(getID(), new Button(218, 190, ZoneType.NORMALZONE));
		entityManager.addEntity(getID(), new Button(218, 310, ZoneType.ICEZONE));

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.drawImage(new Image("/images/backgrounds/background_2.png"), 0, 0);

		entityManager.renderEntities(container, game, g);

		g.drawString("Normal Zone", 110, 180);
		g.drawString("Ice Zone", 110, 300);
	
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
