package de.tudarmstadt.informatik.fop.breakout.blocks;

import org.newdawn.slick.SlickException;

/**
 * class to represent a gold block
 * @author Jonas Henry Grebe
 *
 */
public final class GoldBlock extends AbstractBlock {

	public GoldBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		setHitSound(BLOCK_GOLD_HIT_SOUND);
		
		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_GOLD_IMAGE);
		
		setHitsLeft(BLOCK_GOLD_HITSLEFT);
		setScore(BLOCK_GOLD_SCORE);
	}

}
