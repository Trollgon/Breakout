package de.tudarmstadt.informatik.fop.breakout.constants;

/**
 * Class for holding the game parameters and constants e.g. entity IDs or image
 * paths
 *
 * @author Tobias Otterbein, Benedikt Wartusch
 *
 */
public interface GameParameters extends BlockParameters {

	// Window Settings
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int FRAME_RATE = 60;

	// Game Mode Settings
	public static final float ENDLESS_GAME_SPEED = 0.02f;

	// Sound
	public static final float GAME_MUSIC_VOLUME = 0.02f;
	public static final float GAME_SOUND_VOLUME = 0.02f;
	public static final float RAND_PITCH_TRESHOLD = 0.025f;
	public static final float RAND_VOL_TRESHOLD = 0.05f;

	// Game States
	public enum StateType {
		MAIN_MENU_STATE, ZONE_PICKER_STATE, ICE_ZONE_STATE, STORY_GAME_STATE, ENDLESS_GAME_STATE, HIGHSCORE_STATE, ABOUT_STATE, QUIT_STATE
	};

	// State IDS
	public static final int MAIN_MENU_STATE = 0;
	public static final int ZONE_PICKER_STATE = 1;
	public static final int ZONE_STATE = 2;
	public static final int ICE_ZONE_STATE = 21;
	public static final int NORMAL_ZONE_STATE = 22;
	public static final int STORY_GAME_STATE = 4;
	public static final int ENDLESS_GAME_STATE = 5;
	public static final int HIGHSCORE_STATE = 6;
	public static final int ABOUT_STATE = 7;
	public static final int QUIT_STATE = 8;

	// Zones
	public enum ZoneType {
		NORMALZONE, ICEZONE
	};

	// Backgrounds
	public static final String BACKGROUND_IMAGE = "/images/background.png";
	public static final String MAIN_MENU_IMAGE = "/images/menu.png";
	public static final String DEFAULT_MENU_IMAGE = "/images/menu_blank.png";
	public static final String ENDLESS_BACKGROUND_IMAGE = "/images/background_4.png";

	// Button
	public static final String BUTTON_ID = "button";
	public static final String BUTTON_IMAGE = "/images/entry.png";

	// Borders
	public enum BorderType {
		TOP, LEFT, RIGHT
	};

	public static final String BORDER_HIT_SOUND = "/sounds/borderHit.wav";

	// offset to avoid a perma-collision with the outer blocks
	public static final int BORDER_OFFSET = 0;

	public static final int BORDER_WIDTH = 6;
	public static final String TOP_BORDER_ID = "topBorder";
	public static final String LEFT_BORDER_ID = "leftBorder";
	public static final String RIGHT_BORDER_ID = "rightBorder";

	// Timer
	public static final String STOP_WATCH_ID = "stopWatch";
	public static final String COUNTDOWN_ID = "countdown";

	// Ball
	public static final String BALL_ID = "ball";
	public static final float INITIAL_BALL_SPEED = 1f;
	public static final float MAX_BALL_SPEED = 8f;
	public static final float MIN_BALL_SPEED = 0.5f;
	public static final float SPEEDUP_VALUE = 0.0001f;
	public static final String BALL_IMAGE = "/images/guido.png";

	// Stick
	public static final String COLLECT_ITEM_SOUND = "sounds/itemHitStick.wav";
	public static final String STICK_ID = "stick";
	public static final float STICK_SPEED = 1.5f;
	public static final String STICK_IMAGE = "/images/stick_round.png";

	// Lives
	public static final String LIVES_ID = "lives";
	public static final String HEART_3_IMAGE = "/images/heart_3.png";
	public static final String HEART_2_IMAGE = "/images/heart_2.png";
	public static final String HEART_1_IMAGE = "/images/heart_1.png";
	public static final String HEART_0_IMAGE = "/images/heart_0.png";

	// Score
	public static final String SCORE_ID = "score";

	// Player
	public static final String PLAYER_ID = "player";
	public static final String NO_LIFE_LEFT = "noLifeLeft";

	// Pause
	public static final String PAUSE_ID = "pause";
	public static final String PAUSE_IMAGE = "/images/pause.png";

	// Win
	public static final String WIN_ID = "win";

	// Escape
	public static final String ESCAPE_ID = "escape";

	// Highscore
	public static final String HIGHSCORE_FILE = "highscores/highscore.hsc";

	// Items
	public static final String ITEM_SPEEDUP_ID = "item_speedup";
	public static final String ITEM_ONEUP_ID = "item_oneup";
	public static final String ITEM_MIRRORSTICK_ID = "item_mirrorstick";
	public static final String ITEM_RANDOMREBOUND_ID = "item_randomrebound";
	public static final String ITEM_EXPANDSTICK_ID = "item_expandstick";
	public static final String ITEM_COMPRESSSTICK_ID = "item_compressstick";
	public static final String ITEM_SHOOTPLAYER_ID = "item_shootplayer";

	public static final String SPEEDUP_LOGO_PATH = "images/items/item_speedup.png";
	public static final String ONEUP_LOGO_PATH = "images/items/item_oneup.png";
	public static final String MIRRORSTICK_LOGO_PATH = "images/items/item_mirror.png";
	public static final String RANDOMREBOUND_LOGO_PATH = "images/items/item_randomrebound.png";
	public static final String EXPANDSTICK_LOGO_PATH = "images/items/item_expand.png";
	public static final String COMPRESSSTICK_LOGO_PATH = "images/items/item_compress.png";
	public static final String SHOOTPLAYER_LOGO_PATH = "images/items/item_shootplayer.png";

	public static enum ItemType {
		SPEEDUP, ONEUP, MIRRORSTICK, RANDOMREBOUND, EXPANDSTICK, COMPRESSSTICK, SHOOTPLAYER
	};
}