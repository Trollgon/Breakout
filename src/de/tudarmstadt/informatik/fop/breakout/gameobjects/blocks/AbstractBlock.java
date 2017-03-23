package de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks;

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
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.event.basicevents.LoopEvent;

/**
 * abstract class for every block in the game
 * 
 * @author Jonas Henry Grebe
 *
 */
public abstract class AbstractBlock extends Entity implements IHitable, GameParameters {

	private int hitsLeft;
	private int score;
	
	// not really used property of AbstractBlock
	// might be useful later
	private BlockGroup type;

	// path of the AbstractBlocks HitSound
	private String hitSound;
	// image of the AbstractsBlock ImageRenderComponent
	private Image blockImage;

	// fires if AbstractBlock is destroyed
	protected Event totalDestruction;
	
	// fire if AbstractBlock left the Window (used only in EndlessGameMode)
	protected Event leftScreen;
	
	// auxiliary event for possible future block types
	public LoopEvent always;

	// id-counter for the different block instaces: e.g. block1, block2 ...
	static int id = 1;

	/**
	 * constructor of a AbstractBlock
	 * 
	 * @param xPos
	 *            of the new block
	 * @param yPos
	 *            of the new block
	 */
	public AbstractBlock(int xPos, int yPos) {
		super(BLOCK_ID + id);
		
		// inc id counter
		id++;

		setPassable(false);
		setPosition(new Vector2f(xPos, yPos));
		setSize(new Vector2f(BLOCK_WIDTH, BLOCK_HEIGHT));

		// to be overwritten later:
		setHitSound(BLOCK_STANDARD_HIT_SOUND);

		addEvents();
		addActions();

		try {
			// method that gets overwritten by the specific block's:
			configureBlock();
		} catch (SlickException e) {

			e.printStackTrace();
		}

		// adds the AbstractBlocks Components:
		this.addComponent(new ImageRenderComponent(getBlockImage()));
		
		this.addComponent(always);
		this.addComponent(totalDestruction);
		this.addComponent(leftScreen);
	}

	/**
	 * abstract method which gets specified by the different BlockTypes.
	 * Used to set the type specific properties!
	 * (e.g. Block-Images, Block-Hit-Sounds ...)
	 * @throws SlickException
	 */
	abstract void configureBlock() throws SlickException;

	/**
	 * adds all block events, for a better overview
	 */
	void addEvents() {

		totalDestruction = new Event("totalDestructionEvent") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return !hasHitsLeft();
			}
		};

		// auxiliary event for possible future features
		always = new LoopEvent();

		// leaving screen event
		leftScreen = new Event("reachedBottomBorder") {

			@Override
			protected boolean performAction(GameContainer container, StateBasedGame game, int delta) {
				return leftScreen.getOwnerEntity().getPosition().getY() > WINDOW_HEIGHT;
			}
		};
	}

	/**
	 * adds all block actions, for a better overview
	 */
	void addActions() {

		// adds the blocks-scorepoints to the players-score
		totalDestruction.addAction((arg0, arg1, arg2, arg3) -> Score.incScoreCount(getScore()));
		// action: destroys this blocks entity
		totalDestruction.addAction(new DestroyEntityAction());

		// destroys block when left window at the bottom
		leftScreen.addAction(new DestroyEntityAction());
		leftScreen.addAction((arg0, arg1, arg2, arg3) -> {
			// sets the players lives to 0
			Lives.setLifeAmount(0);
		});


	};

	@Override
	public boolean collides(Entity otherEntity) {

		// blocks can only be hit by a ball instance:
		return super.collides(otherEntity) && otherEntity instanceof Ball;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {

			super.update(gc, sb, delta);
	}

	///////////////// IHitable-METHODS /////////////////////

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

	///////////////////////////////////////////////////////
	
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
	 * sets this blocks blockImage by giving the path as a String and updates
	 * the ImageRenderComponent
	 * 
	 * @param blockImage String path of the image
	 */
	public void setBlockImage(String blockImage) {
		try {

			this.blockImage = new Image(blockImage);
			this.addComponent(new ImageRenderComponent(getBlockImage()));

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * alternative constructor of AbstractBlock which does not load any Graphics for TESTS ONLY
	 * @param xPos of the Block
	 * @param yPos of the Block
	 * @param dontcare
	 */
	public AbstractBlock(int xPos, int yPos, int dontcare) {
		super(BLOCK_ID + id);
		// inc id counter
		id++;

		setPassable(false);
		setPosition(new Vector2f(xPos, yPos));
		setSize(new Vector2f(BLOCK_WIDTH, BLOCK_HEIGHT));

		// to be overwritten later:
		addEvents();
		addActions();

		this.addComponent(always);
		this.addComponent(totalDestruction);
		this.addComponent(leftScreen);
	}

}
