package de.tudarmstadt.informatik.fop.breakout.blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import eea.engine.action.Action;
import eea.engine.component.Component;

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
		// setHitSound(BLOCK_IMPACT_SOUND);

		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_ICE_IMAGE);

		setHitsLeft(BLOCK_ICE_HITSLEFT);
		setScore(BLOCK_ICE_SCORE);

		// adds the slowDown
		this.totalDestruction.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				Ball b = ((Ball) collider.getCollidedEntity());
				b.setSpeed(BLOCK_ICE_SLOWDOWN * b.getSpeed());
			}
		});
	}

}
