package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;

public class Block extends Entity implements IHitable, GameParameters {

	private int HitsLeft;
	private BlockType type;

	public Block(BlockType type, int xPos, int yPos) {
		super(BLOCK_ID);
		
		setType(type);
		setPosition(new Vector2f(xPos, yPos));
		
		configureBlock();
	}

	private void configureBlock() throws SlickException {
		
		Image image;
		
		switch(type) {
		default:
		case STANDARD:
			image = new Image(BLOCK_STANDARD_IMAGE);
			this.setHitsLeft(BLOCK_STANDARD_HITSLEFT);
			break;
		case IRON:
			image = new Image(BLOCK_IRON_IMAGE);
			this.setHitsLeft(BLOCK_IRON_HITSLEFT);
			break;
		}
		
		this.addComponent(new ImageRenderComponent(image));
	}

	@Override
	public void setHitsLeft(int value) {
		this.HitsLeft = value;
	}

	@Override
	public int getHitsLeft() {
		return this.HitsLeft;
	}

	@Override
	public void addHitsLeft(int value) {
		this.HitsLeft += value;
	}

	@Override
	public boolean hasHitsLeft() {
		return getHitsLeft() > 0;
	}

	public BlockType getType() {
		return type;
	}

	public void setType(BlockType type) {
		this.type = type;
	}

}
