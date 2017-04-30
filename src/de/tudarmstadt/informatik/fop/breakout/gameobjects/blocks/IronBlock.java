package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.gameactions.SpawnRandomItemAction;

/**
 * class to represent an Iron block
 * @author Jonas Henry Grebe
 *
 */
public final class IronBlock extends AbstractBlock {

	public IronBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}
	

	@Override
	void configureBlock() throws SlickException {
		setHitSound(BLOCK_IRON_HIT_SOUND);
		
		setType(BlockGroup.SOLID);
		setBlockImage(BLOCK_IRON_IMAGE);
		
		setHitsLeft(BLOCK_IRON_HITSLEFT);
		setScore(BLOCK_IRON_SCORE);
		totalDestruction.addAction(new SpawnRandomItemAction(0.2));
	}
	

	// Alternative Constructor
	public IronBlock(int xPos, int yPos, int dontcare) {
		super(xPos, yPos, dontcare);
		setType(BlockGroup.SOLID);
		setHitsLeft(BLOCK_IRON_HITSLEFT);
		setScore(BLOCK_IRON_SCORE);
	}

}
