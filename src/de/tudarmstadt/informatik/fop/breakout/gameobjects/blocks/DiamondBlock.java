package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.gameactions.SpawnRandomItemAction;

/**
 * class to represent a diamond block
 * @author Jonas Henry Grebe
 *
 */
public class DiamondBlock extends AbstractBlock {

	public DiamondBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		setHitSound(BLOCK_DIAMOND_HIT_SOUND);

		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_DIAMOND_IMAGE);

		setHitsLeft(BLOCK_DIAMOND_HITSLEFT);
		setScore(BLOCK_DIAMOND_SCORE);
		totalDestruction.addAction(new SpawnRandomItemAction(0.4));
	}

}
