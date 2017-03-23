package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

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

	}

}
