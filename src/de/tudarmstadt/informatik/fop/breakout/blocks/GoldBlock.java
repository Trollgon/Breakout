package de.tudarmstadt.informatik.fop.breakout.blocks;

import org.newdawn.slick.SlickException;

public final class GoldBlock extends AbstractBlock {

	public GoldBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		//this.setHitSound(...);
		
		setType(BlockType.UNSTABLE);
		setBlockImage(BLOCK_GOLD_IMAGE);
		
		setHitsLeft(BLOCK_GOLD_HITSLEFT);
		setScore(BLOCK_GOLD_SCORE);
	}

}
