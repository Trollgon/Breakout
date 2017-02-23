package de.tudarmstadt.informatik.fop.breakout.gameevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

public class NoBallLaunchedEvent extends Event implements GameParameters {

	StateBasedEntityManager entityManager;

	public NoBallLaunchedEvent(String id) {
		super(id);

		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {

		return !entityManager.getEntitiesByState(STORY_GAME_STATE).stream().filter(e -> (e instanceof Ball)).anyMatch(b -> ((Ball) b).isLaunched());
	}

}
