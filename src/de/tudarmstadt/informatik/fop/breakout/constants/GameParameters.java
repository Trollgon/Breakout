package de.tudarmstadt.informatik.fop.breakout.constants;

/**
 * Class for holding the game parameters and constants e.g. entity IDs or image
 * paths
 *
 * @author Tobias Otterbein, Benedikt Wartusch
 *
 */
public interface GameParameters extends StateParameters, BlockParameters, ItemParameters {

	// Window Settings
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int FRAME_RATE = 60;

	// Sound
	public static final float GAME_MUSIC_VOLUME = 0.02f;
	public static final float GAME_SOUND_VOLUME = 0.02f;
	public static final float RAND_PITCH_TRESHOLD = 0.025f;
	public static final float RAND_VOL_TRESHOLD = 0.05f;
	
	// Button
	public static final String BUTTON_ID = "button";
	public static final String BUTTON_IMAGE = "/images/buttons/entry.png";
	public static final String BUTTON_CLICK_SOUND = "/sounds/buttonClick.wav";

	// Borders
	public enum BorderType {
		TOP, LEFT, RIGHT
	};

	public static final int BORDER_WIDTH = 6;
	public static final String TOP_BORDER_ID = "topBorder";
	public static final String LEFT_BORDER_ID = "leftBorder";
	public static final String RIGHT_BORDER_ID = "rightBorder";
	public static final String BORDER_HIT_SOUND = "/sounds/borderHit.wav";
	
	// Timer
	public static final String STOP_WATCH_ID = "stopWatch";
	public static final String COUNTDOWN_ID = "countdown";

	// Ball
	public static final String BALL_ID = "ball";
	public static final float INITIAL_BALL_SPEED = 2f;
	public static final float MAX_BALL_SPEED = 15f;
	public static final float MIN_BALL_SPEED = 0.5f;
	public static final float SPEEDUP_VALUE = 0.0001f;
	public static final String BALL_IMAGE = "/images/guido.png";

	// Stick
	public static final String COLLECT_ITEM_SOUND = "sounds/itemHitStick.wav";
	public static final String STICK_ID = "stick";
	public static final float STICK_SPEED = 1.5f;
	public static final String STICK_IMAGE = "/images/stick.png";

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
	public static final String CLEAR_HIGHSCORE_BUTTON_ID = "clearHighscoreButton";
	public static final String CLEAR_HIGHSCORE_BUTTON_IMAGE = "/images/buttons/clear_highscore_button.png";

}