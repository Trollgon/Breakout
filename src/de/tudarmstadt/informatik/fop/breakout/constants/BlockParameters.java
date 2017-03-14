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
		// 0 	 1 		  2 	3 	   4	  5		  6    7	 8
		NULL, STANDARD, STONE, IRON, GOLD, DIAMOND, SNOW, ICE, DROPPER,
		// 9 	10 		11    12     13     14        15     16
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

	// SNOW-BLOCK: 1 HIT
	public static final String BLOCK_SNOW_IMAGE = "/images/blocks/block_snow.png";
	public static final int BLOCK_SNOW_HITSLEFT = 1;
	public static final int BLOCK_SNOW_SCORE = 50;
	public static final String BLOCK_SNOW_HIT_SOUND = "/sounds/blockHitEarth.wav";
	
	// ICE-BLOCK: 1 HIT and slows down the ball
	public static final String BLOCK_ICE_IMAGE = "/images/blocks/block_2.png";
	public static final int BLOCK_ICE_HITSLEFT = 1;
	public static final int BLOCK_ICE_SCORE = 75;
	public static final float BLOCK_ICE_SLOWDOWN = 0.98f;
	public static final String BLOCK_ICE_HIT_SOUND = "/sounds/blockHitIce.wav";

	// DROPPER-BLOCK 1 HIT, drops a random Item
	public static final String BLOCK_DROPPER_IMAGE = "/images/blocks/block_dropper.png";
	public static final int BLOCK_DROPPER_HITSLEFT = 1;
	public static final int BLOCK_DROPPER_SCORE = 200;
	public static final String BLOCK_DROPPER_HIT_SOUND = "/sounds/blockHitStandard.wav";

	// EARTH-BLOCK 1 HIT
	public static final String BLOCK_EARTH_IMAGE = "/images/blocks/block_earth.png";
	public static final int BLOCK_EARTH_HITSLEFT = 1;
	public static final int BLOCK_EARTH_SCORE = 50;
	public static final String BLOCK_EARTH_HIT_SOUND = "/sounds/blockHitEarth.wav";
	
	// WOOD-BLOCK 2 HITS
	public static final String BLOCK_WOOD_IMAGE = "/images/blocks/block_wood.png";
	public static final int BLOCK_WOOD_HITSLEFT = 2;
	public static final int BLOCK_WOOD_SCORE = 100;
	public static final String BLOCK_WOOD_HIT_SOUND = "/sounds/blockHitWood.wav";
	
	// JUNGLE-BLOCK 2 HITS but can regenerate by time
	public static final String BLOCK_JUNGLE_FULL_IMAGE = "/images/blocks/block_jungle1.png";
	public static final String BLOCK_JUNGLE_HURT_IMAGE = "/images/blocks/block_jungle2.png";
	public static final int BLOCK_JUNGLE_HITSLEFT = 2;
	public static final int BLOCK_JUNGLE_SCORE = 150;
	public static final long BLOCK_JUNGLE_REGSPEED = 2000;
	public static final String BLOCK_JUNGLE_HIT_SOUND = "/sounds/blockHitWood.wav";
	public static final String BLOCK_JUNGLE_REGENERATION_SOUND = "/sounds/jungleRegeneration.wav";
	
	// FIRE-BLOCK 1 HIT and lits the ball on fire
	public static final String BLOCK_FIRE_IMAGE = "/images/blocks/block_fire.png";
	public static final int BLOCK_FIRE_HITSLEFT = 1;
	public static final int BLOCK_FIRE_SCORE = 75;
	public static final String BLOCK_FIRE_HIT_SOUND = "/sounds/blockHitFire.wav";
	
	// MAGMA-BLOCK 3 HITS and can hurt player by dropping
	public static final String BLOCK_MAGMA_IMAGE = "/images/blocks/block_magma.png";
	public static final int BLOCK_MAGMA_HITSLEFT = 3;
	public static final int BLOCK_MAGMA_SCORE = 200;
	public static final String BLOCK_MAGMA_HIT_SOUND = "/sounds/blockHitStone.wav";
	
	// OBSIDIAN-BLOCK 5 HITS
	public static final String BLOCK_OBSIDIAN_IMAGE = "/images/blocks/block_obsidian.png";
	public static final int BLOCK_OBSIDIAN_HITSLEFT = 5;
	public static final int BLOCK_OBSIDIAN_SCORE = 500;
	public static final String BLOCK_OBSIDIAN_HIT_SOUND = "/sounds/blockHitStone.wav";
	
	// METEOR-BLOCK 2 HITS and ...
	public static final String BLOCK_METEOR_IMAGE = "/images/blocks/block_meteor.png";
	public static final int BLOCK_METEOR_HITSLEFT = 2;
	public static final int BLOCK_METEOR_SCORE = 150;
	public static final String BLOCK_METEOR_HIT_SOUND = "/sounds/blockHitStone.wav";
	
	// GLASS-BLOCK 1 HIT
	public static final String BLOCK_GLASS_IMAGE = "/images/blocks/block_glass.png";
	public static final int BLOCK_GLASS_HITSLEFT = 1;
	public static final int BLOCK_GLASS_SCORE = 50;
	public static final String BLOCK_GLASS_HIT_SOUND = "/sounds/blockHitGlass.wav";
	
	
	// in the future:

	// COLOR BLOCKS? (just for the Look)
	// SNOW - BLOCK (just a STANDARD BLOCK FOR THE ICEZONE)
	// MAGMA - BLOCK (BLOCK THAT SOMETIMES DROPS MAGMA THAT HURTS THE PLAYER)
	// WOOD - BLOCK (just a STANDARD BLOCK FOR OTHER ZONES)
	// JUNGLE - BLOCK (REGENERATES)
	// STONE - BLOCK
	// TNT - BLOCK (destroys every unstable block within a range)
	// METEOR - BLOCK
	// FIRE - BLOCK (lits the Ball on fire so he destroys every unstable block
	// with one hit!
	// GUIDO - BLOCK (5 HITS but destroys all other blocks)
	// PORTAL - BLOCKS (placed only in pairs, teleport blocks from one pos to
	// the other pos, indestructable)
	//
	// GLASS - BLOCK
	// EARTH - BLOCK (just a STANDARD BLOCK)
	// DIAMOND - BLOCK (has 5 Hits, but has a lot of Points)
	// PLASMA - BLOCK
	// GODZILLA - BLOCK (sometimes spits FIRE, can destroy the BALL :D
	// ...
}
