package de.tudarmstadt.informatik.fop.breakout.constants;

public interface BlockParameters {

	// Blocks
	
	// Block types:
	// solid -> blocks that can´t be destroyed by other blocks (e.g. explosions)
	// unstable -> blocks that can be destroyed by other blocks
	// ... -> ...
	public enum BlockGroup {
		SOLID, UNSTABLE
	}
	
	// Block - Sounds
	public static final String BLOCK_DEFAULT_HIT_SOUND = "sounds/hitBlock.wav";
	public static final String BLOCK_IMPACT_SOUND = "sounds/hitImpact.wav";
	
	// Block event IDS:
	public static final String NOHITSLEFT = "noHitsLeft";
	public static final String BALLCOLLISION = "BallCollision";
	public static final String DESTRUCTION = "destruction";
	
	public static final String BLOCK_ID = "block";
	public static final String MAP_FILE = "maps/level2.map";
	
	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEIGHT = 30;
	
	// STANDARD-BLOCK: 1 HIT
	public static final String BLOCK_STANDARD_IMAGE = "/images/blocks/block_default.png";
	public static final int BLOCK_STANDARD_HITSLEFT = 1;
	public static final int BLOCK_STANDARD_SCORE = 50;
	public static final String BLOCK_STANDARD_HIT_SOUND = "";
	
	// IRON-BLOCK: 2 HITS and SOLID
	public static final String BLOCK_IRON_IMAGE = "/images/blocks/block_iron.png";
	public static final int BLOCK_IRON_HITSLEFT = 2;
	public static final int BLOCK_IRON_SCORE = 150;
	public static final String BLOCK_IRON_HIT_SOUND = "";
	
	// GOLD-BLOCK: 3 HITS and lots of Points!
	public static final String BLOCK_GOLD_IMAGE = "/images/blocks/block_gold.png";
	public static final int BLOCK_GOLD_HITSLEFT = 3;
	public static final int BLOCK_GOLD_SCORE = 300;
	public static final String BLOCK_GOLD_HIT_SOUND = "";
	
	// ICE-BLOCK: 1 HIT and slows down the ball
	public static final String BLOCK_ICE_IMAGE = "/images/blocks/block_ice.png";
	public static final int BLOCK_ICE_HITSLEFT = 1;
	public static final int BLOCK_ICE_SCORE = 75;
	public static final float BLOCK_ICE_SLOWDOWN = -1f;
	public static final String BLOCK_ICE_HIT_SOUND = "";
	
	// TNT-BLOCK: just 1 HIT but a lot of destruction
	public static final String BLOCK_TNT_IMAGE = "/images/blocks/block_tnt.png";
	public static final int BLOCK_TNT_HITSLEFT = 1;
	public static final int BLOCK_TNT_SCORE = 150;
	public static final float BLOCK_TNT_EXPLOSION_RADIUS = 100f;
	public static final String BLOCK_TNT_HIT_SOUND = "";
	
}
