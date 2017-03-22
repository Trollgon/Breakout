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
 * 
 * @author Peter
 *
 */
public class SpawnRandomItemAction implements GameParameters, Action {
	
	private double chance;
	
	public SpawnRandomItemAction(){
		this(1.0);
	}
	public SpawnRandomItemAction(double chance){
		this.chance = chance;
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		
		if(Math.random() <= chance){
			
			Entity i;
			Vector2f itemPos = arg3.getOwnerEntity().getPosition();
			
			ItemType[] items = ItemType.values();
			Random random = new Random();
			ItemType iType = items[random.nextInt(items.length)];
			
			i = new ItemFactory(iType, itemPos).createEntity();
			
			StateBasedEntityManager.getInstance().addEntity(arg1.getCurrentStateID(), i);
			
			
		}
	}

}
