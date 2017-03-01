package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.event.basicevents.TimeEvent;

public class JungleBlock extends AbstractBlock {

	public JungleBlock(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	void configureBlock() throws SlickException {

		this.setHitSound(BLOCK_JUNGLE_HIT_SOUND);

		setType(BlockGroup.UNSTABLE);
		setBlockImage(BLOCK_JUNGLE_FULL_IMAGE);

		setHitsLeft(BLOCK_JUNGLE_HITSLEFT);
		setScore(BLOCK_JUNGLE_SCORE);

		// regeneration
		TimeEvent regeneration = new TimeEvent(BLOCK_JUNGLE_REGSPEED, false);
		regeneration.addAction((arg0, arg1, arg2, arg3) -> {

			if (getHitsLeft() < BLOCK_JUNGLE_HITSLEFT) {

				addHitsLeft(1);
				System.out.println("regenerated");

				// removes the regeneration event
				// if block has fully regenerated
				if (getHitsLeft() == BLOCK_JUNGLE_HITSLEFT) {
					removeComponent(arg3);
				}
			}
		});

		// action: starts regeneration
		Action startRegeneration = new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				addComponent(regeneration);
			}
		};

		// always update the BlockImage depending on the HitsLeft
		this.always.addAction((arg0, arg1, arg2, arg3) -> {

			// change image representation:
			if (getHitsLeft() > 1) {
				setBlockImage(BLOCK_JUNGLE_FULL_IMAGE);
			} else
				setBlockImage(BLOCK_JUNGLE_HURT_IMAGE);
		});

		// starts the regeneration if block is hurt by ball
		this.collider.addAction(startRegeneration);
	}
}
