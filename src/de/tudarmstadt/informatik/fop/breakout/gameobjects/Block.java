package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
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
 * Block class to represent any block in the game
 * @author Jonas Henry Grebe
 *
 */
public class Block extends Entity implements IHitable, GameParameters {

	private int HitsLeft;
	private int score;
	private BlockType type;

	private CollisionEvent collider;
	private ANDEvent hitByBall;
	private ANDEvent destroyed;
	
	/**
	 * Block class constructor
	 * @param type BlockType of this block
	 * @param xPos x-Position
	 * @param yPos y-Position
	 */
	public Block(BlockType type, int xPos, int yPos) {
		super(BLOCK_ID);
		
		setType(type);
		setPosition(new Vector2f(xPos, yPos));
		
		try {
			configureBlock();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		configureEvents();
	}
	
	/**
	 * configures the blocks properties depending on its BlockType
	 */
	private void configureBlock() throws SlickException {
		
		Image image;
		
		switch(type) {
		
		default:
		case DEFAULT:
			image = new Image(BLOCK_DEFAULT_IMAGE);
			setHitsLeft(BLOCK_DEFAULT_HITSLEFT);
			setScore(BLOCK_DEFAULT_SCORE);
			break;
		
		case DEFAULT_TWO:
			image = new Image(BLOCK_DEFAULT_TWO_IMAGE);
			setHitsLeft(BLOCK_DEFAULT_TWO_HITSLEFT);
			setScore(BLOCK_DEFAULT_TWO_SCORE);
			break;
			
		case DEFAULT_THREE:
			image = new Image(BLOCK_DEFAULT_THREE_IMAGE);
			setHitsLeft(BLOCK_DEFAULT_THREE_HITSLEFT);
			setScore(BLOCK_DEFAULT_THREE_SCORE);
			break;
		}
		
		this.addComponent(new ImageRenderComponent(image));
	}

	/**
	 * configures the block events
	 */
	private void configureEvents() {
		
		collider = new CollisionEvent();
		
		hitByBall = new ANDEvent(collider, new Event("ballCollision") {
			
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
								
				return collider.getCollidedEntity().getID() == BALL_ID;
			}
		});
		
		hitByBall.addAction(new Action() {
			
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				try {
					new Sound("sounds/hitBlock.wav").play(0.8f, 1);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				addHitsLeft(-1);
			}
		});
		
		destroyed = new ANDEvent(hitByBall, new Event("destruction") {

			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return !hasHitsLeft();
			}
			
		});
		
		destroyed.addAction(new Action() {
			
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				((Ball) collider.getCollidedEntity()).setLastCollision(collider.getOwnerEntity());
			}
		});
		destroyed.addAction(new DestroyEntityAction());
		
		// order of this matters!:
		this.addComponent(collider);
		this.addComponent(hitByBall);
		this.addComponent(destroyed);
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

	public int getScore() {
		return score;
	}

	public void setScore(int points) {
		this.score = points;
	}

}
