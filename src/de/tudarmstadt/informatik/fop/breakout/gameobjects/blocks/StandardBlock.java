package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.gameactions.SpawnRandomItemAction;

/**
 * class to represent a Standard block
 * @author Jonas Henry Grebe
 *
 */
public final class StandardBlock extends AbstractBlock {

	public StandardBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}
	
	@Override
	void configureBlock() throws SlickException {
		
		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_STANDARD_IMAGE);
		
		setHitsLeft(BLOCK_STANDARD_HITSLEFT);
		setScore(BLOCK_STANDARD_SCORE);
		totalDestruction.addAction(new SpawnRandomItemAction(0.15));
	}
	
	// Alt. Cons.
	public StandardBlock(int xPos, int yPos, int dontcare) {
		super(xPos, yPos, dontcare);
		setType(BlockGroup.UNSTABLE);
		setHitsLeft(BLOCK_STANDARD_HITSLEFT);
		setScore(BLOCK_STANDARD_SCORE);
	}

}
