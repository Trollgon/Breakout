package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.gameactions.SpawnRandomItemAction;

/**
 * class to represent a Earth block
 * @author Jonas Henry Grebe
 *
 */
public class EarthBlock extends AbstractBlock {


	public EarthBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		
		this.setHitSound(BLOCK_EARTH_HIT_SOUND);
		
		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_EARTH_IMAGE);
		
		setHitsLeft(BLOCK_EARTH_HITSLEFT);
		setScore(BLOCK_EARTH_SCORE);
		totalDestruction.addAction(new SpawnRandomItemAction(0.05));
	}


}
