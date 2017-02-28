package de.tudarmstadt.informatik.fop.breakout.constants;

public interface BlockParameters {

	// Blocks

	// Block Groups:
	// solid -> blocks that can´t be destroyed by other blocks (e.g. explosions)
	// unstable -> blocks that can be destroyed by other blocks
	// ... -> ...
	public enum BlockGroup {
		SOLID, UNSTABLE, INDESTRUCTIBLE
	}

	public static final String BLOCK_ID = "block";

	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEIGHT = 30;

	// order of these enum elements specify their IDS:
	// e.g. 0 is NULL, 1 is STANDARD, 2 is IRON, ...
	
	public enum BlockType {
		// 0      1       2      3     4      5       6    7     8
		NULL, STANDARD, STONE, IRON, GOLD, DIAMOND, SNOW, ICE, DROPPER,
		// 9	10	   11	  12    13 	     14		  15	 16
		EARTH, WOOD, JUNGLE, FIRE, MAGMA, OBSIDIAN, METEOR, GLASS 
	};

	// NULL-BLOCK
	
	// STANDARD-BLOCK: 1 HIT
	public static final String BLOCK_STANDARD_IMAGE = "/images/blocks/block_default.png";
	public static final int BLOCK_STANDARD_HITSLEFT = 1;
	public static final int BLOCK_STANDARD_SCORE = 50;
	public static final String BLOCK_STANDARD_HIT_SOUND = "/sounds/blockHitStandard.wav";

	// STONE-BLOCK: 2 HITS
	public static final String BLOCK_STONE_IMAGE = "/images/blocks/block_stone.png";
	public static final int BLOCK_STONE_HITSLEFT = 2;
	public static final int BLOCK_STONE_SCORE = 100;
	public static final String BLOCK_STONE_HIT_SOUND = "/sounds/blockHitStone.wav";
	
	// IRON-BLOCK: 3 HITS and SOLID
	public static final String BLOCK_IRON_IMAGE = "/images/blocks/block_iron.png";
	public static final int BLOCK_IRON_HITSLEFT = 3;
	public static final int BLOCK_IRON_SCORE = 150;
	public static final String BLOCK_IRON_HIT_SOUND = "/sounds/blockHitMetal.wav";

	// GOLD-BLOCK: 4 HITS, SOLID, and lots of Points!
	public static final String BLOCK_GOLD_IMAGE = "/images/blocks/block_gold.png";
	public static final int BLOCK_GOLD_HITSLEFT = 4;
	public static final int BLOCK_GOLD_SCORE = 300;
	public static final String BLOCK_GOLD_HIT_SOUND = "/sounds/blockHitMetal.wav";

	// DIAMOND-BLOCK: 5 HITS, SOLID
	public static final String BLOCK_DIAMOND_IMAGE = "/images/blocks/block_diamond.png";
	public static final int BLOCK_DIAMOND_HITSLEFT = 5;
	public static final int BLOCK_DIAMOND_SCORE = 750;
	public static final String BLOCK_DIAMOND_HIT_SOUND = "/sounds/blockHitMetal.wav";

	// ICE-BLOCK: 1 HIT and slows down the ball
	public static final String BLOCK_ICE_IMAGE = "/images/blocks/block_ice.png";
	public static final int BLOCK_ICE_HITSLEFT = 1;
	public static final int BLOCK_ICE_SCORE = 75;
	public static final float BLOCK_ICE_SLOWDOWN = 0.98f;
	public static final String BLOCK_ICE_HIT_SOUND = "/sounds/blockHitIce.wav";
	
	// SNOW-BLOCK: 1 HIT
	public static final String BLOCK_SNOW_IMAGE = "/images/blocks/block_snow.png";
	public static final int BLOCK_SNOW_HITSLEFT = 1;
	public static final int BLOCK_SNOW_SCORE = 50;
	public static final String BLOCK_SNOW_HIT_SOUND = "/sounds/blockHitEarth.wav";
	
	// DROPPER-BLOCK 1 HIT, drops a random Item
	public static final String BLOCK_DROPPER_IMAGE = "/images/blocks/block_dropper.png";
	public static final int BLOCK_DROPPER_HITSLEFT = 1;
	public static final int BLOCK_DROPPER_SCORE = 200;
	public static final String BLOCK_DROPPER_HIT_SOUND = "/sounds/blockHitStandard.wav";

	// in the future:

	// COLOR BLOCKS? (just for the Look)
	// SNOW - BLOCK (just a STANDARD BLOCK FOR THE ICEZONE)
	// MAGMA - BLOCK (BLOCK THAT SOMETIMES DROPS MAGMA THAT HURTS THE PLAYER)
	// WOOD - BLOCK (just a STANDARD BLOCK FOR OTHER ZONES)
	// JUNGLE - BLOCK (REGENERATES)
	// STONE - BLOCK
	// TNT - BLOCK (destroys every unstable block within a range)
	// METEOR - BLOCK
	// FIRE - BLOCK (lits the Ball on fire so he destroys every unstable block with one hit!
	// GUIDO - BLOCK (5 HITS but destroys all other blocks)
	// PORTAL - BLOCKS (placed only in pairs, teleport blocks from one pos to the other pos, indestructable)
	// 
	// GLASS - BLOCK
	// EARTH - BLOCK (just a STANDARD BLOCK)
	// DIAMOND - BLOCK (has 5 Hits, but has a lot of Points)
	// PLASMA - BLOCK
	// GODZILLA - BLOCK (sometimes spits FIRE, can destroy the BALL :D
	// ...
}
