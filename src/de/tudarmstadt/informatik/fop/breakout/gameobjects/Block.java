package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameactions.BlockExplosionAction;
import de.tudarmstadt.informatik.fop.breakout.gameactions.PlaySoundAction;
import de.tudarmstadt.informatik.fop.breakout.gameactions.SpawnItemAction;
import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;
import de.tudarmstadt.informatik.fop.breakout.managers.SoundManager;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;

/**
 * Block class to represent any block in the game
 * 
 * @author Jonas Henry Grebe
 *
 */
public class Block extends Entity implements IHitable, GameParameters {

	private int HitsLeft;
	private int score;
	private BlockType type;
	private boolean isDestroyed;

	private CollisionEvent collider;
	private ANDEvent hitByBall;
	private ANDEvent canBeDestroyed;
	private ANDEvent totalDestruction;

	/**
	 * Block class constructor
	 * 
	 * @param type
	 *            BlockType of this block
	 * @param xPos
	 *            x-Position
	 * @param yPos
	 *            y-Position
	 */
	public Block(BlockType type, int xPos, int yPos) {
		super(BLOCK_ID);

		setType(type);
		setPosition(new Vector2f(xPos, yPos));
		setDestroyed(false);

		configureEvents();

		try {
			configureBlock();

		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	/**
	 * configures the block events
	 */
	private void configureEvents() {

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

		// order of this matters!:
		this.addComponent(collider);
		this.addComponent(hitByBall);

		this.addComponent(totalDestruction);
		this.addComponent(canBeDestroyed);
	}

	/**
	 * configures the blocks properties depending on its BlockType
	 */
	private void configureBlock() throws SlickException {

		Image image = null;

		switch (type) {

		case DEFAULT:
			image = new Image(BLOCK_DEFAULT_IMAGE);
			setHitsLeft(BLOCK_DEFAULT_HITSLEFT);
			setScore(BLOCK_DEFAULT_SCORE);
			break;

		case IRON:
			image = new Image(BLOCK_IRON_IMAGE);
			setHitsLeft(BLOCK_IRON_HITSLEFT);
			setScore(BLOCK_IRON_SCORE);
			break;

		case GOLD:
			image = new Image(BLOCK_GOLD_IMAGE);
			setHitsLeft(BLOCK_GOLD_HITSLEFT);
			setScore(BLOCK_GOLD_SCORE);
			break;

		case TNT:
			image = new Image(BLOCK_TNT_IMAGE);
			setHitsLeft(BLOCK_TNT_HITSLEFT);
			setScore(BLOCK_TNT_SCORE);
			this.totalDestruction.addAction(new BlockExplosionAction(getPosition(), BLOCK_TNT_EXPLOSION_RADIUS));
			break;
			
		case SPEEDUP:
			image = new Image(BLOCK_SPEEDUP_IMAGE);
			setHitsLeft(BLOCK_SPEEDUP_HITSLEFT);
			setScore(BLOCK_SPEEDUP_SCORE);
			// this.destroyed.addAction(new
			// SpawnItemAction(ItemType.SPEEDITEM));
			break;

		default:
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

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public BlockType getType() {
		return type;
	}

	public void setType(BlockType type) {
		this.type = type;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int points) {
		this.score = points;
	}

}
