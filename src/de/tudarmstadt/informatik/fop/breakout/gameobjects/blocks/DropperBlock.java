package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import java.util.Random;

import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.gameactions.SpawnItemAction;
import de.tudarmstadt.informatik.fop.breakout.gameactions.SpawnRandomItemAction;

public class DropperBlock extends AbstractBlock {

	public DropperBlock(int xPos, int yPos) {
		super(xPos, yPos);
		// TODO Auto-generated constructor stub
	}

	@Override
	void configureBlock() throws SlickException {
		setHitSound(BLOCK_DROPPER_HIT_SOUND);
		
		setType(BlockGroup.SOLID);
		setBlockImage(BLOCK_DROPPER_IMAGE);
		
		setHitsLeft(BLOCK_DROPPER_HITSLEFT);
		setScore(BLOCK_DROPPER_SCORE);
		
		this.totalDestruction.addAction(new SpawnRandomItemAction());
	}

}
