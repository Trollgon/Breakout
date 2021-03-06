package de.tudarmstadt.informatik.fop.breakout.gameactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.factories.ItemFactory;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * class of SpawnItemAction
 * @author Peter Franke
 * @author Jonas Henry Grebe
 *
 */
public class SpawnItemAction implements Action, GameParameters {

	private ItemType type; 
	private double chance; //the chance of an item dropping
	
	/**
	 * alternative Constructor of the SpawnItemAction
	 * spawns the given item with 100% probability
	 * @param type the item type to drop
	 */
	public SpawnItemAction(ItemType type) { 
		this(type, 1.0);
	}
	
	/**
	 * Constructor for the SpawnItemAction
	 * Spawns an Item of the given type at the given drop chance
	 * @param type the item type to drop
	 * @param chance the chance of an item dropping
	 */
	public SpawnItemAction(ItemType type, double chance){
		this.type = type;
		this.chance = chance;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
		if(Math.random() <= chance){
			Entity i;
			Vector2f itemPos = component.getOwnerEntity().getPosition();
		
			i = new ItemFactory(type, itemPos).createEntity();
		
			StateBasedEntityManager.getInstance().addEntity(game.getCurrentStateID(), i);
		}
	}

}
