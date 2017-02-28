package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.event.basicevents.TimeEvent;

public class JungleBlock extends AbstractBlock {

	private ImageRenderComponent image;

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

		// adding the regeneration

		TimeEvent regeneration = new TimeEvent(BLOCK_JUNGLE_REGSPEED, true);
		regeneration.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {

				if (getHitsLeft() < BLOCK_JUNGLE_HITSLEFT) {
					addHitsLeft(1);
					System.out.println("regenerated");
				}
			}
		});

		this.always.addAction((arg0, arg1, arg2, arg3) -> {

			try {
				if (getHitsLeft() > 1) {
					image = new ImageRenderComponent(new Image(BLOCK_JUNGLE_FULL_IMAGE));
				} else
					image = new ImageRenderComponent(new Image(BLOCK_JUNGLE_HURT_IMAGE));

			} catch (SlickException e) {
				e.printStackTrace();
			}

			removeComponent(image);
			addComponent(image);

		});

		this.addComponent(regeneration);
	}

}
