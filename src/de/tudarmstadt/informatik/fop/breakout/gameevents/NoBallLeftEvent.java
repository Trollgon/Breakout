package de.tudarmstadt.informatik.fop.breakout.gameevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

/**
 * Event which fires if no ball-entity exists
 * @author Jonas Henry Grebe
 *
 */
public class NoBallLeftEvent extends Event implements GameParameters {
	
	StateBasedEntityManager entityManager;

	public NoBallLeftEvent(String id) {
		super(id);
	
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	protected boolean performAction(GameContainer container, StateBasedGame game, int arg2) {
		
		return !entityManager.getEntitiesByState(game.getCurrentStateID()).stream().anyMatch(e -> (e instanceof Ball));
	}

}
