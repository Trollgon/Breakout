package de.tudarmstadt.informatik.fop.breakout.states.zonestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.Button;
import eea.engine.entity.StateBasedEntityManager;

public class IceZoneState extends BasicGameState implements GameParameters {

	private StateBasedEntityManager entityManager;
	
	public IceZoneState() {
		entityManager = StateBasedEntityManager.getInstance();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
        entityManager.addEntity(getID(), new Button(218, 190, 201));
        entityManager.addEntity(getID(), new Button(218, 310, 202));
    
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(new Image("images/background_1.png"), 0, 0);

		entityManager.renderEntities(container, game, g);
		
		g.drawString("Level 1", 110, 180);
		g.drawString("Level 2", 110, 300);
		

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return ICE_ZONE_STATE;
	}

}
