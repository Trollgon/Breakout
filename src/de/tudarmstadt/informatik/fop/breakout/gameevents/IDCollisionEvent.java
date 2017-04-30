package de.tudarmstadt.informatik.fop.breakout.gameevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.basicevents.CollisionEvent;

/**
 * IDCollisionEvent-class
 * fires if colliding with an entity which ID contains the given collisionID
 * @author Jonas Henry Grebe
 *
 */
public class IDCollisionEvent extends CollisionEvent {

	private String collisionID;

	/**
	 * IDCollisionEvent-constructor
	 * @param collisionID String which all entities`ID`s contain to collide with
	 * (e.g. "block" in "block1", "block2", ...)
	 */
	public IDCollisionEvent(String collisionID) {

		this.collisionID = collisionID;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		
		if (super.performAction(gc, sb, delta)) {
				
			return getCollidedEntity().getID().contains(collisionID);
		}
		return false;
	}

}
