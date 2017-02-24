package de.tudarmstadt.informatik.fop.breakout.blocks;

import org.newdawn.slick.SlickException;

/**
 * class to represent an Iron block
 * @author Jonas Henry Grebe
 *
 */
public class IronBlock extends AbstractBlock {

	public IronBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		//this.setHitSound(...);
		
		setType(BlockType.SOLID);
		setBlockImage(BLOCK_IRON_IMAGE);
		
		setHitsLeft(BLOCK_IRON_HITSLEFT);
		setScore(BLOCK_IRON_SCORE);
		
	}

}
