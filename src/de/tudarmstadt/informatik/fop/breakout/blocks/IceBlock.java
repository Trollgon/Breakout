package de.tudarmstadt.informatik.fop.breakout.blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * Ice block class: has 1 hit but slows down the ball
 * 
 * @author Jonas
 *
 */
public final class IceBlock extends AbstractBlock {

	public IceBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {
		// this.setHitSound(...);

		setType(BlockType.UNSTABLE);
		setBlockImage(BLOCK_ICE_IMAGE);

		setHitsLeft(BLOCK_ICE_HITSLEFT);
		setScore(BLOCK_ICE_SCORE);

		this.totalDestruction.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				if (collider.getCollidedEntity() != null) {

					((Ball) collider.getCollidedEntity()).addSpeed(BLOCK_ICE_SLOWDOWN);
				}
			}
		});
	}

}
