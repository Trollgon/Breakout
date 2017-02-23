package de.tudarmstadt.informatik.fop.breakout.gameevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

public class NoBallLeftEvent extends Event implements GameParameters {
	
	StateBasedEntityManager entityManager;

	public NoBallLeftEvent(String id) {
		super(id);
	
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
		return !entityManager.getEntitiesByState(STORY_GAME_STATE).stream().anyMatch(e -> (e instanceof Ball));
	}

}
