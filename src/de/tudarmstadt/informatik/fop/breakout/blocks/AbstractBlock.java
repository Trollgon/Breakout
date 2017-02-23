package de.tudarmstadt.informatik.fop.breakout.blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameactions.PlaySoundAction;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Score;
import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;

/**
 * abstract class for any block in the game
 * @author Jonas Henry Grebe
 *
 */
public abstract class AbstractBlock extends Entity implements IHitable, GameParameters {

	private int hitsLeft;
	private int score;
	private BlockType type;
	private boolean isDestroyed;
	
	private String hitSound;
	private Image blockImage;

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

		setHitSound(BLOCK_DEFAULT_HIT_SOUND);
		
		try {
			configureBlock();
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
		
		this.addComponent(new ImageRenderComponent(getBlockImage()));

		this.addComponent(collider);
		this.addComponent(hitByBall);
		this.addComponent(totalDestruction);
		this.addComponent(canBeDestroyed);
	}

	abstract void configureBlock() throws SlickException;

	/**
	 * adds all block events, for a better overview
	 */
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

	/**
	 * adds all block actions, for a better overview
	 */
	void addActions() {
		
		// action: decrements the blocks hitsleft
		hitByBall.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {

				addHitsLeft(-1);
			}
		});

		hitByBall.addAction(new PlaySoundAction(this.getHitSound(), 0.9f));

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
		totalDestruction.addAction(new Action() {
			
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				Score.incScoreCount(getScore());
			}
		});
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

	/**
	 * determines whether this block is about to be destroyed or not
	 * @return
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}

	/**
	 * sets this block`s property 'isDestroyed'
	 * @param isDestroyed
	 */
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * gets this blocks scorepoints, you earn by destroying it
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * sets this blocks scorepoints
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * gets this blocks BlockType
	 * @return
	 */
	public BlockType getType() {
		return type;
	}

	/**
	 * sets this blocks BlockType
	 * @param type
	 */
	public void setType(BlockType type) {
		this.type = type;
	}

	/**
	 * gets this blocks hitSound-path as a String
	 * @return path of the hitSound as String
	 */
	public String getHitSound() {
		return hitSound;
	}

	/**
	 * sets this blocks hit Sound
	 * @param hitSound String path of the new hitSound
	 */
	public void setHitSound(String hitSound) {
		this.hitSound = hitSound;
	}

	/**
	 * gets this blocks blockImage
	 * @return
	 */
	public Image getBlockImage() {
		return blockImage;
	}

	/**
	 * sets this blocks blockImage by giving the path as a String
	 * @param blockImage
	 */
	public void setBlockImage(String blockImage) {
		try {
			this.blockImage = new Image(blockImage);
		
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
