package de.tudarmstadt.informatik.fop.breakout.states;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.managers.CheckPointManager;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.KeyPressedEvent;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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

		Entity escListener = new Entity("ESC_Listener");
		KeyPressedEvent escPressed = new KeyPressedEvent(Input.KEY_ESCAPE);
		escPressed.addAction(new ChangeStateInitAction(MAIN_MENU_STATE));
		escListener.addComponent(escPressed);
		entityManager.addEntity(this.getID(), escListener);

		entityManager.addEntity(getID(), new Button(218, 190, ZoneType.NORMALZONE));
		if (checkpoint > 199) {
			entityManager.addEntity(getID(), new Button(218, 310, ZoneType.ICEZONE));
		}
		if (checkpoint > 299) {
			entityManager.addEntity(getID(), new Button(218, 430, ZoneType.JUNGLEZONE));
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.drawImage(new Image("/images/backgrounds/background_2.png"), 0, 0);

		entityManager.renderEntities(container, game, g);

		g.drawString("Normal Zone", 110, 180);
		if (checkpoint > 199) {
			g.drawString("Ice Zone", 110, 300);
		}
		if (checkpoint > 199) {
			g.drawString("Jungle Zone", 110, 420);
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
