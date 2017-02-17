package de.tudarmstadt.informatik.fop.breakout.constants;

public interface BlockParameters {

	// Blocks
	public enum BlockType {
		DEFAULT, IRON, GOLD, SPEEDUP
	}
	
	public static final String BLOCK_ID = "block";
	public static final String MAP_FILE = "maps/level1.map";
	
	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEIGHT = 30;
	
	public static final String BLOCK_DEFAULT_IMAGE = "/images/blocks/block_default.png";
	public static final int BLOCK_DEFAULT_HITSLEFT = 1;
	public static final int BLOCK_DEFAULT_SCORE = 100;
	
	public static final String BLOCK_IRON_IMAGE = "/images/blocks/block_iron.png";
	public static final int BLOCK_IRON_HITSLEFT = 2;
	public static final int BLOCK_IRON_SCORE = 200;
	
	public static final String BLOCK_GOLD_IMAGE = "/images/blocks/block_gold.png";
	public static final int BLOCK_GOLD_HITSLEFT = 3;
	public static final int BLOCK_GOLD_SCORE = 300;
	
	public static final String BLOCK_SPEEDUP_IMAGE = "/images/blocks/block_speedup.png";
	public static final int BLOCK_SPEEDUP_HITSLEFT = 1;
	public static final int BLOCK_SPEEDUP_SCORE = 150;
	
	
}
