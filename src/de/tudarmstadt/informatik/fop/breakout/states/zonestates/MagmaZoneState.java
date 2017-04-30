package de.tudarmstadt.informatik.fop.breakout.states.zonestates;

import de.tudarmstadt.informatik.fop.breakout.managers.CheckPointManager;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.entity.Entity;
import eea.engine.event.basicevents.KeyPressedEvent;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.constants.StateParameters.ZoneType;
import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.entity.StateBasedEntityManager;

/**
 * magmaZoneState-class, GUI for picking the levels of the MagmaZone
 *
 */
public class MagmaZoneState extends BasicGameState implements GameParameters {

	private StateBasedEntityManager entityManager;

	public MagmaZoneState() {
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		if (!Breakout.getDebug()) {

			entityManager.addEntity(getID(), new Button(208, 190, 301, ZoneType.MAGMAZONE));

			if (CheckPointManager.getCheckpoint() > 301) {
				entityManager.addEntity(getID(), new Button(208, 310, 302, ZoneType.MAGMAZONE));
			}
			if (CheckPointManager.getCheckpoint() > 302) {
				entityManager.addEntity(getID(), new Button(598, 190, 303, ZoneType.MAGMAZONE));
			}
			if (CheckPointManager.getCheckpoint() > 303) {
				entityManager.addEntity(getID(), new Button(598, 310, 304, ZoneType.MAGMAZONE));
			}
			
			// back-to-main-menu button
			ChangeStateInitAction back = new ChangeStateInitAction(MAIN_MENU_STATE);
			Image image = new Image("/images/buttons/back_button.png");
			Button b = new Button(750, 550, back, image);
			
			entityManager.addEntity(getID(), b);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.drawImage(new Image("images/backgrounds/background_magma.png"), 0, 0);

		entityManager.renderEntities(container, game, g);

		g.drawString("Level 1", 160, 180);

		if (CheckPointManager.getCheckpoint() > 301) {
			g.drawString("Level 2", 160, 300);
		}

		if (CheckPointManager.getCheckpoint() > 302) {
			g.drawString("Level 3", 550, 180);
		}
		
		if (CheckPointManager.getCheckpoint() > 303) {
			g.drawString("Level 4", 550, 300);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
	}

	@Override
	public int getID() {
		return MAGMA_ZONE_STATE;
	}

}
