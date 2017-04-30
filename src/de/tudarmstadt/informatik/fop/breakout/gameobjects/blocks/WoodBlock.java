package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.gameactions.SpawnRandomItemAction;

/**
 * class to represent a wood block
 * @author Jonas Henry Grebe
 *
 */
public class WoodBlock extends AbstractBlock {

	public WoodBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		
		this.setHitSound(BLOCK_WOOD_HIT_SOUND);
		
		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_WOOD_IMAGE);
		
		setHitsLeft(BLOCK_WOOD_HITSLEFT);
		setScore(BLOCK_WOOD_SCORE);
		totalDestruction.addAction(new SpawnRandomItemAction(0.12));

	}

}
