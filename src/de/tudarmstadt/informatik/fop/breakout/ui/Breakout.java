package de.tudarmstadt.informatik.fop.breakout.ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.states.AboutState;
import de.tudarmstadt.informatik.fop.breakout.states.EndlessGameState;
import de.tudarmstadt.informatik.fop.breakout.states.HighScoreState;
import de.tudarmstadt.informatik.fop.breakout.states.MainMenuState;
import de.tudarmstadt.informatik.fop.breakout.states.QuitState;
import de.tudarmstadt.informatik.fop.breakout.states.StoryGameState;
import de.tudarmstadt.informatik.fop.breakout.states.ZonePickerState;
import de.tudarmstadt.informatik.fop.breakout.states.zonestates.IceZoneState;
import de.tudarmstadt.informatik.fop.breakout.states.zonestates.MagmaZoneState;
import de.tudarmstadt.informatik.fop.breakout.states.zonestates.NormalZoneState;
import eea.engine.entity.StateBasedEntityManager;

public class Breakout extends StateBasedGame implements GameParameters {

	// Remember if the game runs in debug mode
	private static boolean debug = false;

	protected static Breakout breakout;

	/**
	 * Creates a new Breakout instance
	 * 
	 * @param debug
	 *            if true, runs in debug mode
	 */
	public Breakout(boolean debug) {
		super("Breakout");
		Breakout.debug = debug;
	}

	public static boolean getDebug() {
		return debug;
	}

	public static void main(String[] args) throws SlickException {
		// Set the library path depending on the operating system
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/windows");
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/macosx");
		} else {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + "/native/" + System.getProperty("os.name").toLowerCase());
		}

		AppGameContainer app = Breakout.initBreakout();

		// Set the display mode and frame rate
		app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
		app.setTargetFrameRate(FRAME_RATE);

		// init the SoundStore
		SoundStore.get().init();

		// now start the game!
		app.start();
	}

	private static AppGameContainer initBreakout() throws SlickException {
		// Add this StateBasedGame to an AppGameContainer
		breakout = new Breakout(false);
		AppGameContainer app;
		app = new AppGameContainer(breakout);
		return app;
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {

		// Add the game states (the first added state will be started initially)
		// This may look as follows, assuming you use the associated class names
		// and constants:

		addState(new MainMenuState());
		addState(new EndlessGameState());
		addState(new ZonePickerState());

		addState(new NormalZoneState());
		addState(new IceZoneState());
		addState(new MagmaZoneState());

		addState(new StoryGameState());
		addState(new HighScoreState());

		addState(new AboutState());
		addState(new QuitState());

		// Add the states to the StateBasedEntityManager
		StateBasedEntityManager.getInstance().addState(MAIN_MENU_STATE);
		StateBasedEntityManager.getInstance().addState(ENDLESS_GAME_STATE);
		StateBasedEntityManager.getInstance().addState(ZONE_PICKER_STATE);

		StateBasedEntityManager.getInstance().addState(NORMAL_ZONE_STATE);
		StateBasedEntityManager.getInstance().addState(ICE_ZONE_STATE);
		StateBasedEntityManager.getInstance().addState(MAGMA_ZONE_STATE);

		StateBasedEntityManager.getInstance().addState(STORY_GAME_STATE);
		StateBasedEntityManager.getInstance().addState(HIGHSCORE_STATE);

		StateBasedEntityManager.getInstance().addState(QUIT_STATE);
		StateBasedEntityManager.getInstance().addState(ABOUT_STATE);
	}
}