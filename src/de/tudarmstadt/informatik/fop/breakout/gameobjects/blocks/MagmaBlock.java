package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.factories.ItemFactory;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.TimeEvent;

public class MagmaBlock extends AbstractBlock {


	Random random;
	TimeEvent drop;
	
	public MagmaBlock(int xPos, int yPos) {
		super(xPos, yPos);
		
	}

	@Override
	void configureBlock() throws SlickException {
		
		setHitSound(BLOCK_MAGMA_HIT_SOUND);

		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_MAGMA_IMAGE);

		setHitsLeft(BLOCK_MAGMA_HITSLEFT);
		setScore(BLOCK_MAGMA_SCORE);
	
		// adds random magma dropping
		random = new Random();
		drop = new TimeEvent(750, true);

		StateBasedEntityManager entityManager = StateBasedEntityManager.getInstance();
			
		drop.addAction(new Action() {
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				// drops by 25% chance
				if (random.nextBoolean() && random.nextBoolean()) {
					
					entityManager.addEntity(arg1.getCurrentStateID(), new ItemFactory(ItemType.SHOOTPLAYER, getPosition()).createEntity());	
				}
			}
		});
		
		this.addComponent(drop);
	}
	

}
