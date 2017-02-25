package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;

/**
 * class to represent an ice block
 * 
 * @author Jonas Henry Grebe
 *
 */
public final class IceBlock extends AbstractBlock {

	public IceBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		setHitSound(BLOCK_ICE_HIT_SOUND);

		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_ICE_IMAGE);

		setHitsLeft(BLOCK_ICE_HITSLEFT);
		setScore(BLOCK_ICE_SCORE);

		// adds the slowDown
		this.totalDestruction.addAction((arg0, arg1, arg2, arg3) -> {
			Ball b = ((Ball) collider.getCollidedEntity());
			b.setSpeed(BLOCK_ICE_SLOWDOWN * b.getSpeed());
		});
	}

}
