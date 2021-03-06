package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameactions.PlaySoundAction;
import de.tudarmstadt.informatik.fop.breakout.gameevents.IDCollisionEvent;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.AbstractBlock;
import de.tudarmstadt.informatik.fop.breakout.managers.SoundManager;
import de.tudarmstadt.informatik.fop.breakout.physics.Physics2D;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.NOTEvent;
import eea.engine.event.OREvent;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;

/**
 * Ball class to represent every ball in the game
 * 
 * @author Jonas Henry Grebe
 *
 */
public class Ball extends Entity implements GameParameters {

	private float speed;
	private boolean isLaunched;

	// launcher of this ball
	private Stick launcher;

	// Entity of the last collision
	private Entity lastCollisionEntity;

	private CollisionEvent collider;
	private IDCollisionEvent blockCollider;

	private IDCollisionEvent topBorderCollider;
	private IDCollisionEvent leftBorderCollider;
	private IDCollisionEvent rightBorderCollider;
	private OREvent borderCollider;

	public IDCollisionEvent stickCollider;

	public Event hasLaunched;
	private NOTEvent hasNotLaunched;
	private ANDEvent launchBall;

	private LeavingScreenEvent leftScreen;

	/**
	 * Ball constructor
	 * 
	 * @param xPos
	 *            of the new Ball
	 * @param yPos
	 *            of the new Ball
	 */
	public Ball(Stick launcher) {
		super(BALL_ID);

		setLauncher(launcher);
		setLaunched(false);
		setLastCollisionEntity(getLauncher());

		setPosition(getLauncher().getLaunchPos());

		configureEvents();

		setSpeed(INITIAL_BALL_SPEED);
		setRotation(0);

		setVisible(true);
		setPassable(false);
		
		// adds colliders
		this.addComponent(collider);
		this.addComponent(blockCollider);
		this.addComponent(topBorderCollider);
		this.addComponent(leftBorderCollider);
		this.addComponent(rightBorderCollider);
		this.addComponent(borderCollider);
		this.addComponent(stickCollider);

		// adds launch events
		this.addComponent(hasLaunched);
		this.addComponent(hasNotLaunched);
		this.addComponent(launchBall);

		// adds leaving screen event
		this.addComponent(leftScreen);

		try {
			this.addComponent(new ImageRenderComponent(new Image(BALL_IMAGE)));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Alternative constructor only for adapter!!!
	 * @param launcher
	 * @param dontcare
	 */
	public Ball(Stick launcher, int dontcare) {
		super(BALL_ID);

		setLauncher(launcher);
		setLaunched(false);
		setLastCollisionEntity(getLauncher());

		setPosition(getLauncher().getLaunchPos());
		setSize(new Vector2f(25, 25));
		configureEvents();

		setSpeed(INITIAL_BALL_SPEED);
		setRotation(0);

		setVisible(true);
		setPassable(false);
		
		// adds colliders
		this.addComponent(collider);
		this.addComponent(blockCollider);
		this.addComponent(topBorderCollider);
		this.addComponent(leftBorderCollider);
		this.addComponent(rightBorderCollider);
		this.addComponent(borderCollider);
		this.addComponent(stickCollider);

		// adds launch events
		this.addComponent(hasLaunched);
		this.addComponent(hasNotLaunched);
		this.addComponent(launchBall);

		// adds leaving screen event
		this.addComponent(leftScreen);

	}

	/**
	 * configures all ball events and adds the actions
	 */
	private void configureEvents() {

		// main collision event:
		collider = new CollisionEvent();

		// block collision event:
		blockCollider = new IDCollisionEvent(BLOCK_ID);

		// border collision event:
		topBorderCollider = new IDCollisionEvent(TOP_BORDER_ID);
		leftBorderCollider = new IDCollisionEvent(LEFT_BORDER_ID);
		rightBorderCollider = new IDCollisionEvent(RIGHT_BORDER_ID);

		// stick collision event:
		stickCollider = new IDCollisionEvent(STICK_ID);

		hasLaunched = new Event("hasLaunchedEvent") {
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return isLaunched();
			}
		};

		hasNotLaunched = new NOTEvent(hasLaunched);

		launchBall = new ANDEvent(hasNotLaunched, new KeyDownEvent(Input.KEY_SPACE));

		leftScreen = new LeavingScreenEvent();

		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////

		// bounces ball when it hits block


		blockCollider.addAction(new Action() {
			
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				if (Physics2D.collidedOnSideEdge(blockCollider.getCollidedEntity(), arg3.getOwnerEntity())) {
					setRotation(Physics2D.bounceYAxis(getRotation()));
				}else setRotation(Physics2D.bounceXAxis(getRotation()));
			}
		});


		// reduces the blocks hitsleft by one when hit
		blockCollider.addAction(
				(arg0, arg1, arg2, arg3) -> ((AbstractBlock) blockCollider.getCollidedEntity()).addHitsLeft(-1));
		// speeds ball up on blockCollision
		blockCollider.addAction((arg0, arg1, arg2, arg3) -> ((Ball) blockCollider.getOwnerEntity()).addSpeed(SPEEDUP_VALUE));

		// plays the hitSound, can�t be managed by PlaySoundAction because the HitSound is variable depending on the hit BlockType
		blockCollider.addAction((arg0, arg1, arg2, arg3) -> SoundManager.playSound(((AbstractBlock) blockCollider.getCollidedEntity()).getHitSound(), 1f));

		
		// bounces ball at borders
		topBorderCollider.addAction((arg0, arg1, arg2, arg3) -> setRotation(Physics2D.bounceXAxis(getRotation())));
		leftBorderCollider.addAction((arg0, arg1, arg2, arg3) -> setRotation(Physics2D.bounceYAxis(getRotation())));
		rightBorderCollider.addAction((arg0, arg1, arg2, arg3) -> setRotation(Physics2D.bounceYAxis(getRotation())));

		borderCollider = new OREvent(topBorderCollider, leftBorderCollider, rightBorderCollider);
		borderCollider.addAction(new PlaySoundAction(BORDER_HIT_SOUND));
		
		// bounces ball at stick
		stickCollider.addAction(new PlaySoundAction(STICK_HIT_SOUND));
		stickCollider.addAction((arg0, arg1, arg2, arg3) -> {
			setRotation(Physics2D.bounceStick(getRotation(), (Ball) stickCollider.getOwnerEntity(), getLauncher()));
			});
		
		// moves ball if launched
		hasLaunched.addAction(new MoveForwardAction(getSpeed()));
		// sets the not launched ball on the launcher's position
		hasNotLaunched.addAction((arg0, arg1, arg2, arg3) -> setPosition(getLauncher().getLaunchPos()));
		// launches the ball
		launchBall.addAction((arg0, arg1, arg2, arg3) -> setLaunched(true));

		// deducts 1 life of the player if the ball gets lost
		leftScreen.addAction((arg0, arg1, arg2, arg3) -> Lives.deductLife());

		// destroys this ball if it leaves the window
		leftScreen.addAction(new DestroyEntityAction());

	}

	public Stick getLauncher() {
		return launcher;
	}

	public void setLauncher(Stick launcher) {
		this.launcher = launcher;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		if (speed > MAX_BALL_SPEED) {
			this.speed = MAX_BALL_SPEED;	
		}else if (speed < MIN_BALL_SPEED) {
			this.speed = MIN_BALL_SPEED;
			
		}else this.speed = speed;
		
		this.hasLaunched.clearActions();
		this.hasLaunched.addAction(new MoveForwardAction(getSpeed()));
	}
	
	public void addSpeed(float value) {
		setSpeed(getSpeed() + value);
	}

	public boolean isLaunched() {
		return isLaunched;
	}

	public void setLaunched(boolean isLaunched) {
		this.isLaunched = isLaunched;
	}

	public Entity getLastCollisionEntity() {
		return lastCollisionEntity;
	}

	public void setLastCollisionEntity(Entity lastCollisionEntity) {
		this.lastCollisionEntity = lastCollisionEntity;
	}

}
