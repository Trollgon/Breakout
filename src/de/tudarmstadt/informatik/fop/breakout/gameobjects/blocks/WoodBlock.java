package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

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

	}

}
