package de.tudarmstadt.informatik.fop.breakout.constants;

/**
 * Class for holding the game parameters and constants e.g. entity IDs or image
 * paths
 *
 * @author Tobias Otterbein, Benedikt Wartusch
 *
 */
public interface GameParameters extends BlockParameters{

	// Window Settings
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int FRAME_RATE = 60;
	
	// Game Modes
	public static final float ENDLESS_GAME_SPEED = 0.02f;

	//Sound
	/*
	 * This game uses these sounds from freesound:
	Hit Impact by MrGuff (http://freesound.org/people/mrguff )
	 */
	public static final float GAME_VOLUME = 0.6f;
	public static final float RAND_PITCH_TRESHOLD = 0.05f;
	public static final float RAND_VOL_TRESHOLD = 0.08f;

	// Game States
	public static final int MAIN_MENU_STATE = 1;
	public static final int ZONE_PICKER_STATE = 2;
	public static final int ZONE_STATE = 3;
	public static final int STORY_GAME_STATE = 4;
	public static final int ENDLESS_GAME_STATE = 5;
	public static final int HIGHSCORE_STATE = 6;
	public static final int ABOUT_STATE = 7;
	public static final int QUIT_STATE = 8;

	// BackgroundS
	public static final String BACKGROUND_IMAGE = "/images/background.png";
	public static final String MAIN_MENU_IMAGE = "/images/menu.png";
	public static final String DEFAULT_MENU_IMAGE = "/images/menu_blank.png";

	// Button
	public static final String BUTTON_ID = "button";
	public static final String BUTTON_IMAGE = "/images/entry.png";

	// Borders
	public enum BorderType {
		TOP, LEFT, RIGHT
	};

	// offset to avoid a perma-collision with the outer blocks
	public static final int BORDER_OFFSET = 6;

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
	public static final float SPEEDUP_VALUE = 0.0001f;
	public static final String BALL_IMAGE = "/images/ball.png";

	// Stick
	//public static final String STICK_HIT_SOUND = "sounds/hitStick.wav";
	public static final String STICK_HIT_SOUND = "sounds/hitImpact.wav"; //credit: freesound.org - MrGuff -"Hit Impact"
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

}