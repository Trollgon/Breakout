package de.tudarmstadt.informatik.fop.breakout.constants;

public interface BlockParameters {

	// Blocks
	public enum BlockType {
		DEFAULT, IRON, GOLD, TNT, SPEEDUP
	}
	
	// Block - Sounds
	public static final String BLOCK_HIT_SOUND = "sounds/hitBlock.wav";
	public static final String BLOCK_IMPACT_SOUND = "sounds/hitImpact.wav";
	
	// Block event IDS:
	public static final String NOHITSLEFT = "noHitsLeft";
	public static final String BALLCOLLISION = "BallCollision";
	public static final String DESTRUCTION = "destruction";
	
	public static final String BLOCK_ID = "block";
	public static final String MAP_FILE = "maps/level2.map";
	
	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEIGHT = 30;
	
	// DEFAULT-BLOCK: 1 HIT
	public static final String BLOCK_DEFAULT_IMAGE = "/images/blocks/block_default.png";
	public static final int BLOCK_DEFAULT_HITSLEFT = 1;
	public static final int BLOCK_DEFAULT_SCORE = 100;
	
	// IRON-BLOCK: 2 HITS and IMMUNE to EXPLOSIONS
	public static final String BLOCK_IRON_IMAGE = "/images/blocks/block_iron.png";
	public static final int BLOCK_IRON_HITSLEFT = 2;
	public static final int BLOCK_IRON_SCORE = 200;
	
	// GOLD-BLOCK: 3 HITS and lots of Points!
	public static final String BLOCK_GOLD_IMAGE = "/images/blocks/block_gold.png";
	public static final int BLOCK_GOLD_HITSLEFT = 3;
	public static final int BLOCK_GOLD_SCORE = 300;
	
	// TNT-BLOCK: just 1 HIT but a lot of destruction
	public static final String BLOCK_TNT_IMAGE = "/images/blocks/block_tnt.png";
	public static final int BLOCK_TNT_HITSLEFT = 1;
	public static final int BLOCK_TNT_SCORE = 150;
	public static final float BLOCK_TNT_EXPLOSION_RADIUS = 100f;
	
	// SPEEDUP-BLOCK: 1 HIT, drops a SPEED-ITEM
	public static final String BLOCK_SPEEDUP_IMAGE = "/images/blocks/block_speedup.png";
	public static final int BLOCK_SPEEDUP_HITSLEFT = 1;
	public static final int BLOCK_SPEEDUP_SCORE = 150;
	
	
}