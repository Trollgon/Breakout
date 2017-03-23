package de.tudarmstadt.informatik.fop.breakout.constants;

/**
 * Class or holding all the state parameters and constants
 *
 */
public interface StateParameters {

	// Game Mode Settings
	public static final float ENDLESS_GAME_SPEED = 0.02f;
	
	// Game States
	public enum StateType {
		MAINMENU, 
		ZONEPICKER, 
		STORYGAME, 
		ENDLESS, 
		HIGHSCORE, 
		ABOUT, 
		QUIT
	};

	// State IDS
	public static final int MAIN_MENU_STATE = 0;
	public static final int ZONE_PICKER_STATE = 1;
	
	public static final int NORMAL_ZONE_STATE = 20;
	public static final int ICE_ZONE_STATE = 21;
	public static final int MAGMA_ZONE_STATE = 22;
	
	public static final int STORY_GAME_STATE = 3;
	public static final int ENDLESS_GAME_STATE = 4;
	public static final int HIGHSCORE_STATE = 5;
	public static final int ABOUT_STATE = 6;
	public static final int QUIT_STATE = 7;

	// Zones
	public enum ZoneType {
		NORMALZONE, ICEZONE, MAGMAZONE
	};

	// Backgrounds
	public static final String BACKGROUND_IMAGE = "/images/backgrounds/background.png";
	public static final String MAIN_MENU_IMAGE = "/images/backgrounds/menu.png";
	public static final String DEFAULT_MENU_IMAGE = "/images/backgrounds/menu_blank.png";
	public static final String ENDLESS_BACKGROUND_IMAGE = "/images/backgrounds/background.png";


}
