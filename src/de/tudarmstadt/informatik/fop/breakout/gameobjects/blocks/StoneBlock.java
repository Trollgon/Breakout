package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.gameactions.SpawnRandomItemAction;

/**
 * class to represent a stone block
 * @author Jonas Henry Grebe
 *
 */
public class StoneBlock extends AbstractBlock {

	public StoneBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		
		setHitSound(BLOCK_STONE_HIT_SOUND);
		
		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_STONE_IMAGE);
		
		setHitsLeft(BLOCK_STONE_HITSLEFT);
		setScore(BLOCK_STONE_SCORE);
		totalDestruction.addAction(new SpawnRandomItemAction(0.2));
	}
	
	//Alternative Cons.
	public StoneBlock(int xPos, int yPos, int dontcare) {
		super(xPos, yPos, dontcare);
		setType(BlockGroup.UNSTABLE);
		setHitsLeft(BLOCK_STONE_HITSLEFT);
		setScore(BLOCK_STONE_SCORE);
	}

}
