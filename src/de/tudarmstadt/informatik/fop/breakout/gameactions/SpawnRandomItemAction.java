package de.tudarmstadt.informatik.fop.breakout.gameactions;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.constants.ItemParameters.ItemType;
import de.tudarmstadt.informatik.fop.breakout.factories.ItemFactory;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
/**
 * SpawnRandomItemAction class, used to spawn random items
 * @author Peter
 *
 */
public class SpawnRandomItemAction implements GameParameters, Action {
	
	private double chance; //the probability of dropping any item (0.0 - 1.0, negative values count as 0, values >1 count as 1)
	
	/**
	 * alternative constructor
	 * spawns a random item with a probability of 100%
	 * 
	 */
	public SpawnRandomItemAction(){
		this(1.0);
	}
	
	/**
	 * Constructor for SpawnRandomItemAction
	 * spawns a random item at the given probability
	 *
	 * @param chance the probability of dropping an item
	 */
	public SpawnRandomItemAction(double chance){
		this.chance = chance;
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		
		Random random = new Random();
		
		if(random.nextDouble() <= chance){
			
			Entity i;									
			Vector2f itemPos = arg3.getOwnerEntity().getPosition(); //get the owner Entity's position, the item will spawn here
			
			ItemType[] items = ItemType.values();				//select a random ItemType from the enum in ItemParameters
			ItemType iType = items[random.nextInt(items.length)];
			
			i = new ItemFactory(iType, itemPos).createEntity(); //create an item of this type using the ItemFactory
			
			StateBasedEntityManager.getInstance().addEntity(arg1.getCurrentStateID(), i); //add the Entity to the EntityManager
		
		}
	}

}
