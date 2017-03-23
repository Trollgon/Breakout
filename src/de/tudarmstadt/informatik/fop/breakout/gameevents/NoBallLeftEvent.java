package de.tudarmstadt.informatik.fop.breakout.gameevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

/**
 * Event that fires if no ball is left in the entity manager.
 * @author Lukas
 *
 */
public class NoBallLeftEvent extends Event implements GameParameters {
	
	StateBasedEntityManager entityManager;

	/**
	 * Constructor of the event.
	 * @param id
	 */
	public NoBallLeftEvent(String id) {
		super(id);
	
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	protected boolean performAction(GameContainer container, StateBasedGame game, int arg2) {
		
		return !entityManager.getEntitiesByState(game.getCurrentStateID()).stream().anyMatch(e -> (e instanceof Ball));
	}

}
