package de.tudarmstadt.informatik.fop.breakout.blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameactions.PlaySoundAction;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;

public abstract class AbstractBlock extends Entity implements IHitable, GameParameters {

	private int hitsLeft;
	private int score;
	private BlockType type;
	private boolean isDestroyed;

	private CollisionEvent collider;
	private ANDEvent hitByBall;
	private ANDEvent canBeDestroyed;
	private ANDEvent totalDestruction;

	public AbstractBlock(int xPos, int yPos) {
		super(BLOCK_ID);

		setPassable(false);
		setPosition(new Vector2f(xPos, yPos));
		
		setDestroyed(false);
		addEvents();
		addActions();

		try {
			configureBlock();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.addComponent(collider);
		this.addComponent(hitByBall);
		this.addComponent(totalDestruction);
		this.addComponent(canBeDestroyed);
	}

	abstract void configureBlock() throws SlickException;

	void addEvents() {

		// basic collision event
		collider = new CollisionEvent();

		// event which fires if this block collides with a ball
		hitByBall = new ANDEvent(collider, new Event(BALLCOLLISION) {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {

				return collider.getCollidedEntity().getID() == BALL_ID;
			}
		});

		// event which fires if the block is hityByBall and has no hits left
		canBeDestroyed = new ANDEvent(hitByBall, new Event(NOHITSLEFT) {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return !hasHitsLeft();
			}

		});

		// event which fires if the block can be destroyed
		// ! THIS IS JUST AN AUXILIARY EVENT, TO BE ABLE TO DESTROY A BLOCK
		// OUTSIDE THIS CLASS, by using setDestroyed(true)!
		totalDestruction = new ANDEvent(new Event(DESTRUCTION) {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return isDestroyed();
			}
			
		});
	};

	void addActions() {
		
		// action: decrements the blocks hitsleft
		hitByBall.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {

				addHitsLeft(-1);
			}
		});

		hitByBall.addAction(new PlaySoundAction(BLOCK_HIT_SOUND, 0.9f));

		// action: tells the block that it can destroy itself
		canBeDestroyed.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				setDestroyed(true);
			}
		});

		// action: tells the ball which entity it has destroyed
		canBeDestroyed.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				((Ball) collider.getCollidedEntity()).setLastCollision(collider.getOwnerEntity());
			}
		});

		// action: destroys this blocks entity
		totalDestruction.addAction(new DestroyEntityAction());

	};

	@Override
	public void setHitsLeft(int value) {
		this.hitsLeft = value;
	}

	@Override
	public int getHitsLeft() {
		return this.hitsLeft;
	}

	@Override
	public void addHitsLeft(int value) {
		this.hitsLeft += value;
	}

	@Override
	public boolean hasHitsLeft() {
		return this.getHitsLeft() > 0;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public BlockType getType() {
		return type;
	}

	public void setType(BlockType type) {
		this.type = type;
	}

}
