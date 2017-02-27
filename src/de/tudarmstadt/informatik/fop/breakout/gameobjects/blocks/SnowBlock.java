package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

/**
 * class to represent a snow block
 * @author Jonas Henry Grebe
 *
 */
public class SnowBlock extends AbstractBlock {

	public SnowBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		setHitSound(BLOCK_SNOW_HIT_SOUND);

		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_SNOW_IMAGE);

		setHitsLeft(BLOCK_SNOW_HITSLEFT);
		setScore(BLOCK_SNOW_SCORE);

	}

}
