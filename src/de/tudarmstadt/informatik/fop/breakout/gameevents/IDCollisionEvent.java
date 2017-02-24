package de.tudarmstadt.informatik.fop.breakout.gameevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.basicevents.CollisionEvent;

public class IDCollisionEvent extends CollisionEvent {

	private String collisionID;

	public IDCollisionEvent(String collisionID) {

		this.collisionID = collisionID;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {

		
		if (super.performAction(gc, sb, delta)) {
			System.out.println(getCollidedEntity().getID());
			return getCollidedEntity().getID() == collisionID;
		}
		return false;
	}

}
