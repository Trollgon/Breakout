package de.tudarmstadt.informatik.fop.breakout.states;

import java.io.File;
import java.io.IOException;

import de.tudarmstadt.informatik.fop.breakout.managers.CheckPointManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.entity.StateBasedEntityManager;

public class MainMenuState extends BasicGameState implements GameParameters {

	private int stateID;
	StateBasedEntityManager entityManager;

	public MainMenuState() {

		this.stateID = MAIN_MENU_STATE;
		entityManager = StateBasedEntityManager.getInstance();

		//create save file on first start
		File f = new File("save.txt");
		if (!f.exists()) {
			CheckPointManager.setCheckpoint(0);
		}
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {

		// set up volume
		// start playing the background music
		try {
			SoundStore.get().setMusicVolume(GAME_MUSIC_VOLUME);
			SoundStore.get().setSoundVolume(GAME_SOUND_VOLUME);
			SoundStore.get().getWAV("/sounds/loop_endless.wav").playAsMusic(1f, 1f, true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.drawImage(new Image(MAIN_MENU_IMAGE), 0, 0);

		entityManager.addEntity(getID(), new Button(128, 408, StateType.ZONEPICKER));
		entityManager.addEntity(getID(), new Button(308, 408, StateType.ENDLESS));
		entityManager.addEntity(getID(), new Button(484, 408, StateType.HIGHSCORE));
		entityManager.addEntity(getID(), new Button(660, 408, StateType.QUIT));

		entityManager.renderEntities(container, game, g);
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
