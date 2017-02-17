package de.tudarmstadt.informatik.fop.breakout.constants;

/**
 * Class for holding the game parameters and constants e.g. entity IDs or image
 * paths
 * 
 * @author Tobias Otterbein, Benedikt Wartusch
 * 
 */
public interface GameParameters {

	// Window Settings
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int FRAME_RATE = 60;

	// Game States
	public static final int MAINMENU_STATE = 0;
	public static final int GAMEPLAY_STATE = 1;
	public static final int HIGHSCORE_STATE = 2;

	// Background
	public static final String BACKGROUND_ID = "background";
	public static final String BACKGROUND_IMAGE = "/images/background.png";

	// Button
	public static final String BUTTON_ID = "button";
	public static final String BUTTON_IMAGE = "/images/entry.png";

	// Borders
	public enum BorderType {
		TOP, LEFT, RIGHT
	};

	// offset to avoid a perma-collision with the outer blocks
	public static final int BORDER_OFFSET = 7;
	
	public static final int BORDER_WIDTH = 6;
	public static final String TOP_BORDER_ID = "topBorder";
	public static final String LEFT_BORDER_ID = "leftBorder";
	public static final String RIGHT_BORDER_ID = "rightBorder";

	// Blocks
	public enum BlockType {
		DEFAULT, DEFAULT_TWO, DEFAULT_THREE
	}
	
	public static final String BLOCK_ID = "block";
	public static final String MAP_FILE = "maps/level1.map";
	
	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEIGHT = 30;
	
	public static final String BLOCK_DEFAULT_IMAGE = "/images/blocks/block_1.png";
	public static final int BLOCK_DEFAULT_HITSLEFT = 1;
	public static final int BLOCK_DEFAULT_SCORE = 100;
	
	public static final String BLOCK_DEFAULT_TWO_IMAGE = "/images/blocks/block_2.png";
	public static final int BLOCK_DEFAULT_TWO_HITSLEFT = 2;
	public static final int BLOCK_DEFAULT_TWO_SCORE = 200;
	
	public static final String BLOCK_DEFAULT_THREE_IMAGE = "/images/blocks/block_3.png";
	public static final int BLOCK_DEFAULT_THREE_HITSLEFT = 3;
	public static final int BLOCK_DEFAULT_THREE_SCORE = 300;
	
	public static final String BLOCK_3_IMAGE = "/images/block_3.png";

	// Timer
	public static final String STOP_WATCH_ID = "stopWatch";

	// Ball
	public static final String BALL_ID = "ball";
	public static final float INITIAL_BALL_SPEED = 4f;
	public static final float SPEEDUP_VALUE = 0.0001f;
	public static final String BALL_IMAGE = "/images/ball.png";

	// Stick
	public static final String STICK_ID = "stick";
	public static final float STICK_SPEED = 0.5f;
	public static final String STICK_IMAGE = "/images/stick.png";

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
