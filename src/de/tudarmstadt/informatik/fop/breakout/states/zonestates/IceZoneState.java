package de.tudarmstadt.informatik.fop.breakout.states.zonestates;

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
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.entity.StateBasedEntityManager;

/**
 * iceZoneState-class, GUI for picking the levels of the IceZone
 *
 */
public class IceZoneState extends BasicGameState implements GameParameters {

	private StateBasedEntityManager entityManager;

	public IceZoneState() {
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		if (!Breakout.getDebug()) {

			entityManager.addEntity(getID(), new Button(208, 190, 201, ZoneType.ICEZONE));

			if (CheckPointManager.getCheckpoint() > 201) {
				entityManager.addEntity(getID(), new Button(208, 310, 202, ZoneType.ICEZONE));
			}
			if (CheckPointManager.getCheckpoint() > 202) {
				entityManager.addEntity(getID(), new Button(598, 190, 203, ZoneType.ICEZONE));
			}
			if (CheckPointManager.getCheckpoint() > 203) {
				entityManager.addEntity(getID(), new Button(598, 310, 204, ZoneType.ICEZONE));
			}
			
			ChangeStateInitAction back = new ChangeStateInitAction(MAIN_MENU_STATE);
			Image image = new Image("/images/buttons/back_button.png");
			Button b = new Button(750, 550, back, image);
			
			entityManager.addEntity(getID(), b);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		if (!Breakout.getDebug()) {

			g.drawImage(new Image("images/backgrounds/background_ice.png"), 0, 0);

			entityManager.renderEntities(container, game, g);

			g.drawString("Level 1", 160, 180);

			if (CheckPointManager.getCheckpoint() > 201) {
				g.drawString("Level 2", 160, 300);
			}

			if (CheckPointManager.getCheckpoint() > 202) {
				g.drawString("Level 3", 550, 180);
			}
			if (CheckPointManager.getCheckpoint() > 203) {
				g.drawString("Level 4", 550, 300);
			}
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
		return ICE_ZONE_STATE;
	}

}
