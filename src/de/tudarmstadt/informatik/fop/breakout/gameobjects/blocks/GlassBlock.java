package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

/**
 * class to represent a Glass block
 * @author Jonas Henry Grebe
 *
 */
public class GlassBlock extends AbstractBlock {

	public GlassBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		setHitSound(BLOCK_GLASS_HIT_SOUND);

		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_GLASS_IMAGE);

		setHitsLeft(BLOCK_GLASS_HITSLEFT);
		setScore(BLOCK_GLASS_SCORE);

	}

}
