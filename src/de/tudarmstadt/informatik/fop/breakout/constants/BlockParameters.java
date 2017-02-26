package de.tudarmstadt.informatik.fop.breakout.constants;

public interface BlockParameters {

	// Blocks

	// Block Groups:
	// solid -> blocks that can´t be destroyed by other blocks (e.g. explosions)
	// unstable -> blocks that can be destroyed by other blocks
	// ... -> ...
	public enum BlockGroup {
		SOLID, UNSTABLE
	}

	public static final String BLOCK_ID = "block";

	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEIGHT = 30;

	// VOLUME
	public static final float BLOCK_HIT_VOLUME = 0.05f;

	// order of these enum elements specify their IDS:
	// e.g. 0 is NULL, 1 is STANDARD, 2 is IRON, ...
	public enum BlockType {NULL, STANDARD, IRON, GOLD, ICE, DROPPER};
	
	// STANDARD-BLOCK: 1 HIT
	public static final String BLOCK_STANDARD_IMAGE = "/images/blocks/block_default.png";
	public static final int BLOCK_STANDARD_HITSLEFT = 1;
	public static final int BLOCK_STANDARD_SCORE = 50;
	public static final String BLOCK_STANDARD_HIT_SOUND = "/sounds/blockHitStandard.wav";

	// IRON-BLOCK: 2 HITS and SOLID
	public static final String BLOCK_IRON_IMAGE = "/images/blocks/block_iron.png";
	public static final int BLOCK_IRON_HITSLEFT = 2;
	public static final int BLOCK_IRON_SCORE = 150;
	public static final String BLOCK_IRON_HIT_SOUND = "/sounds/blockHitMetal.wav";

	// GOLD-BLOCK: 3 HITS and lots of Points!
	public static final String BLOCK_GOLD_IMAGE = "/images/blocks/block_gold.png";
	public static final int BLOCK_GOLD_HITSLEFT = 3;
	public static final int BLOCK_GOLD_SCORE = 300;
	public static final String BLOCK_GOLD_HIT_SOUND = "/sounds/blockHitMetal.wav";

	// ICE-BLOCK: 1 HIT and slows down the ball
	public static final String BLOCK_ICE_IMAGE = "/images/blocks/block_ice.png";
	public static final int BLOCK_ICE_HITSLEFT = 1;
	public static final int BLOCK_ICE_SCORE = 75;
	public static final float BLOCK_ICE_SLOWDOWN = 0.95f;
	public static final String BLOCK_ICE_HIT_SOUND = "/sounds/blockHitIce.wav";

	// TNT-BLOCK: just 1 HIT but a lot of destruction
	public static final String BLOCK_TNT_IMAGE = "/images/blocks/block_tnt.png";
	public static final int BLOCK_TNT_HITSLEFT = 1;
	public static final int BLOCK_TNT_SCORE = 150;
	public static final float BLOCK_TNT_EXPLOSION_RADIUS = 100f;
	public static final String BLOCK_TNT_HIT_SOUND = "";

	// DROPPER-BLOCK drops a random Item 
	public static final String BLOCK_DROPPER_IMAGE = "/images/blocks/block_dropper.png";
	public static final int BLOCK_DROPPER_HITSLEFT = 1;
	public static final int BLOCK_DROPPER_SCORE = 200;
	public static final String BLOCK_DROPPER_HIT_SOUND = "/sounds/blockHitStandard.wav";

	// in the future:
	
	// SNOW - BLOCK (just a STANDARD BLOCK FOR THE ICEZONE)
	// MAGMA - BLOCK (BLOCK THAT SOMETIMES DROPS MAGMA THAT HURTS THE PLAYER)
	// WOOD - BLOCK (just a STANDARD BLOCK FOR OTHER ZONES)
	// JUNGLE - BLOCK 
	// EARTH - BLOCK (just a STANDARD BLOCK)
	// DIAMOND - BLOCK (has 5 Hits, but has a lot of Points)
	// PLASMA - BLOCK
	// GODZILLA - BLOCK (just or FUN)
	// ...
}
