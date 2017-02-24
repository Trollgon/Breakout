package de.tudarmstadt.informatik.fop.breakout.blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Lives;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Score;
import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.event.basicevents.LoopEvent;

/**
 * abstract class for any block in the game
 * 
 * @author Jonas Henry Grebe
 *
 */
public abstract class AbstractBlock extends Entity implements IHitable, GameParameters {

	private int hitsLeft;
	private int score;
	private BlockGroup type;
	private boolean isDestroyed;

	private String hitSound;
	private Image blockImage;

	protected CollisionEvent collider;
	private Event canBeDestroyed;
	protected Event totalDestruction;
	protected Event leftScreen;
	public LoopEvent always;

	/**
	 * constructor of a AbstractBlock
	 * 
	 * @param xPos
	 *            of the new block
	 * @param yPos
	 *            of the new block
	 */
	public AbstractBlock(int xPos, int yPos) {
		super(BLOCK_ID);

		setPassable(false);
		setPosition(new Vector2f(xPos, yPos));
		setSize(new Vector2f(BLOCK_WIDTH, BLOCK_HEIGHT));
		setRotation(0);
		
		setDestroyed(false);

		// to be overwritten later:
		setHitSound(BLOCK_DEFAULT_HIT_SOUND);

		addEvents();
		addActions();

		try {
			configureBlock();
		} catch (SlickException e) {

			e.printStackTrace();
		}

		this.addComponent(new ImageRenderComponent(getBlockImage()));

		this.addComponent(always);
		this.addComponent(collider);
		this.addComponent(totalDestruction);
		this.addComponent(canBeDestroyed);
		this.addComponent(leftScreen);
	}

	abstract void configureBlock() throws SlickException;

	/**
	 * adds all block events, for a better overview
	 */
	void addEvents() {

		// basic collision event
		collider = new CollisionEvent();

		// event which fires if the block is hityByBall and has no hits left
		canBeDestroyed = new Event("hasNoHitsLeft") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return !hasHitsLeft();
			}
		};

		// event which fires if the block can be destroyed
		// ! THIS IS JUST AN AUXILIARY EVENT, TO BE ABLE TO DESTROY A BLOCK
		// OUTSIDE THIS CLASS, by using setDestroyed(true)!
		totalDestruction = new Event("totalDestructionEvent") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return isDestroyed();
			}
		};
		
		// auxiliary event for future addons
		always = new LoopEvent();
		
		// leaving screen event
		leftScreen = new Event("reachedBottomBorder") {
			
			@Override
			protected boolean performAction(GameContainer container, StateBasedGame game, int delta) {
				return leftScreen.getOwnerEntity().getPosition().getY() > WINDOW_WIDTH;
			}
		};
	}

	/**
	 * adds all block actions, for a better overview
	 */
	void addActions() {

		// action: tells the block that it can destroy itself
		canBeDestroyed.addAction((arg0, arg1, arg2, arg3) -> setDestroyed(true));

		// action: tells the ball which entity it has destroyed
		canBeDestroyed.addAction(new Action() {

			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				// ((Ball)
				// collider.getCollidedEntity()).setLastCollisionEntity(collider.getOwnerEntity());
			}
		});

		// adds the blocks-scorepoints to the players-score
		totalDestruction.addAction((arg0, arg1, arg2, arg3) -> Score.incScoreCount(getScore()));
		// action: destroys this blocks entity
		totalDestruction.addAction(new DestroyEntityAction());
		
		// destroys block when out of window
		leftScreen.addAction(new DestroyEntityAction());
		leftScreen.addAction((arg0, arg1, arg2, arg3) -> {
			Lives.setLifeAmount(0);
		});
	};

	@Override
	public boolean collides(Entity otherEntity) {
		
		// blocks can only be hit by a ball instance:
		return super.collides(otherEntity) && otherEntity instanceof Ball;
	}
	
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
	 * 
	 * @return
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}

	/**
	 * sets this block`s property 'isDestroyed'
	 * 
	 * @param isDestroyed
	 */
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * gets this blocks scorepoints, you earn by destroying it
	 * 
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * sets this blocks scorepoints
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * gets this blocks BlockType
	 * 
	 * @return
	 */
	public BlockGroup getType() {
		return type;
	}

	/**
	 * sets this blocks BlockType
	 * 
	 * @param type
	 */
	public void setType(BlockGroup type) {
		this.type = type;
	}

	/**
	 * gets this blocks hitSound-path as a String
	 * 
	 * @return path of the hitSound as String
	 */
	public String getHitSound() {
		return hitSound;
	}

	/**
	 * sets this blocks hit Sound
	 * 
	 * @param hitSound
	 *            String path of the new hitSound
	 */
	public void setHitSound(String hitSound) {
		this.hitSound = hitSound;
	}

	/**
	 * gets this blocks blockImage
	 * 
	 * @return
	 */
	public Image getBlockImage() {
		return blockImage;
	}

	/**
	 * sets this blocks blockImage by giving the path as a String
	 * 
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
