package de.tudarmstadt.informatik.fop.breakout.states;

import java.io.File;
import java.io.IOException;

import de.tudarmstadt.informatik.fop.breakout.managers.CheckPointManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.KeyPressedEvent;

public class MainMenuState extends BasicGameState implements GameParameters {

	private int stateID;
	StateBasedEntityManager entityManager;

	public MainMenuState() {

		this.stateID = MAIN_MENU_STATE;
		entityManager = StateBasedEntityManager.getInstance();

		// create save file on first start
		File f = new File("save.txt");
		if (!f.exists()) {
			CheckPointManager.setCheckpoint(0);
		}
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		if (!Breakout.getDebug()) {
		
		// set up volume
		// start playing the background music
		try {
			SoundStore.get().setMusicVolume(GAME_MUSIC_VOLUME);
			SoundStore.get().setSoundVolume(GAME_SOUND_VOLUME);
			SoundStore.get().getWAV("/sounds/music/loop_endless.wav").playAsMusic(1f, 1f, true);

		} catch (IOException e) {

			e.printStackTrace();
		}

		// adds the ESC-Listeners to all States
		Entity escListener = new Entity("ESC_Listener");
		KeyPressedEvent escPressed = new KeyPressedEvent(Input.KEY_ESCAPE);
		escPressed.addAction(new ChangeStateAction(MAIN_MENU_STATE));
		escListener.addComponent(escPressed);

		entityManager.addEntity(STORY_GAME_STATE, escListener);
		entityManager.addEntity(ENDLESS_GAME_STATE, escListener);
		entityManager.addEntity(ZONE_PICKER_STATE, escListener);
		entityManager.addEntity(MAGMA_ZONE_STATE, escListener);
		entityManager.addEntity(ICE_ZONE_STATE, escListener);
		entityManager.addEntity(NORMAL_ZONE_STATE, escListener);
		entityManager.addEntity(HIGHSCORE_STATE, escListener);

		entityManager.addEntity(getID(), new Button(128, 408, StateType.ZONEPICKER));
		entityManager.addEntity(getID(), new Button(308, 408, StateType.ENDLESS));
		entityManager.addEntity(getID(), new Button(484, 408, StateType.HIGHSCORE));
		entityManager.addEntity(getID(), new Button(660, 408, StateType.QUIT));

		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		if (!Breakout.getDebug()) {
		
		g.drawImage(new Image(MAIN_MENU_IMAGE), 0, 0);

		entityManager.renderEntities(container, game, g);
		
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}
