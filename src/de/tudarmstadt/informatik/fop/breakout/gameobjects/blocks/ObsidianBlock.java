package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.gameactions.SpawnRandomItemAction;

/**
 * class to represent a snow block
 * @author Jonas Henry Grebe
 *
 */
public class ObsidianBlock extends AbstractBlock {

	public ObsidianBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		setHitSound(BLOCK_OBSIDIAN_HIT_SOUND);

		setType(BlockGroup.SOLID);
		setBlockImage(BLOCK_OBSIDIAN_IMAGE);

		setHitsLeft(BLOCK_OBSIDIAN_HITSLEFT);
		setScore(BLOCK_OBSIDIAN_SCORE);
		totalDestruction.addAction(new SpawnRandomItemAction(0.15));
	}

}
