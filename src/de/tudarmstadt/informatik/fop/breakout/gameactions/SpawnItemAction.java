package de.tudarmstadt.informatik.fop.breakout.gameactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.factories.ItemFactory;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.items.*;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class SpawnItemAction implements Action, GameParameters {

	private ItemType type;
	
	public SpawnItemAction(ItemType type) {
		this.type = type;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
		
		
		Entity i;
		Vector2f itemPos = component.getOwnerEntity().getPosition();
		
		i = new ItemFactory(type, itemPos).createEntity();
		
		StateBasedEntityManager.getInstance().addEntity(game.getCurrentStateID(), i);
	}

}
