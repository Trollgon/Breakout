package de.tudarmstadt.informatik.fop.breakout.gameactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.blocks.AbstractBlock;
import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Block;
import de.tudarmstadt.informatik.fop.breakout.managers.SoundManager;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;

/**
 * Action which destroys every other Block in a given radius around a center
 * @author Jonas Henry Grebe
 */
public class BlockExplosionAction implements Action, GameParameters {
	
	private Vector2f center;
	private float radius;
	StateBasedEntityManager entityManager;
	
	/**
	 * constructor of BlockExplosionAction
	 * @param center of the explosion
	 * @param radius of the explosion
	 */
	public BlockExplosionAction (Vector2f center, float radius) {
		
		this.center = center;
		this.radius = radius;
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		
		// auxiliary Circle-Shape to get Entities which intersect with it
		Circle explosion = new Circle(center.x, center.y, radius);

		entityManager.getEntitiesByState(STORY_GAME_STATE).stream()
		.filter(e -> e instanceof AbstractBlock)
		.filter(b -> b.getShape().intersects(explosion) && ((AbstractBlock)b).getType() != BlockType.SOLID)
		.forEach(b -> ((AbstractBlock)b).setDestroyed(true)); SoundManager.playSound(BLOCK_IMPACT_SOUND, 0.3f, GAME_VOLUME);

	}

}
