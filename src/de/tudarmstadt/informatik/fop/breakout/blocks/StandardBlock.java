package de.tudarmstadt.informatik.fop.breakout.blocks;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import eea.engine.component.render.ImageRenderComponent;

/**
 * class to represent a Standard block
 * @author Jonas Henry Grebe
 *
 */
public class StandardBlock extends AbstractBlock {

	public StandardBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {

		this.setType(BlockType.UNSTABLE);
		
		this.addComponent(new ImageRenderComponent(new Image(BLOCK_STANDARD_IMAGE)));
		setHitsLeft(BLOCK_STANDARD_HITSLEFT);
		setScore(BLOCK_STANDARD_SCORE);

	}

}
