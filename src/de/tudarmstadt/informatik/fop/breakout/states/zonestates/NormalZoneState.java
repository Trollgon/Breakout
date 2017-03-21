package de.tudarmstadt.informatik.fop.breakout.states.zonestates;

import de.tudarmstadt.informatik.fop.breakout.managers.CheckPointManager;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.entity.Entity;
import eea.engine.event.basicevents.KeyPressedEvent;

import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.constants.StateParameters.ZoneType;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.entity.StateBasedEntityManager;

public class NormalZoneState extends BasicGameState implements GameParameters {

	private StateBasedEntityManager entityManager;

	public NormalZoneState() {
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		entityManager.addEntity(getID(), new Button(218, 190, 101, ZoneType.NORMALZONE));
		
		if (CheckPointManager.getCheckpoint() > 101) {
			entityManager.addEntity(getID(), new Button(218, 310, 102, ZoneType.NORMALZONE));
		}
		if (CheckPointManager.getCheckpoint() > 102) {
			entityManager.addEntity(getID(), new Button(218, 430, 103, ZoneType.NORMALZONE));
		}
		if (CheckPointManager.getCheckpoint() > 103) {
			entityManager.addEntity(getID(), new Button(218, 550, 104, ZoneType.NORMALZONE));
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(new Image("images/backgrounds/background_4.png"), 0, 0);

		entityManager.renderEntities(container, game, g);

		g.drawString("Level 1", 110, 180);
		if (CheckPointManager.getCheckpoint() > 101) {
			g.drawString("Level 2", 110, 300);
		}
		if (CheckPointManager.getCheckpoint() > 102) {
			g.drawString("Level 3", 110, 420);
		}
		if (CheckPointManager.getCheckpoint() > 103) {
			g.drawString("Level 4", 110, 540);
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
		return NORMAL_ZONE_STATE;
	}

}
