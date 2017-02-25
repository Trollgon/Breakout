package de.tudarmstadt.informatik.fop.breakout.blocks;

import org.newdawn.slick.SlickException;

/**
 * Ice block class: has 1 hit but slows down the ball
 * 
 * @author Jonas
 *
 */
public final class IceBlock extends AbstractBlock {

	public IceBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		//setHitSound(BLOCK_IMPACT_SOUND);

		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_ICE_IMAGE);

		setHitsLeft(BLOCK_ICE_HITSLEFT);
		setScore(BLOCK_ICE_SCORE);
	}

}
