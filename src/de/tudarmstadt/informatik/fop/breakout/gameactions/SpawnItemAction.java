package de.tudarmstadt.informatik.fop.breakout.gameactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.items.*;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class SpawnItemAction implements Action, GameParameters {

	private ItemType item;
	
	public SpawnItemAction(ItemType item) {
		this.item = item;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
		
		
		Entity i;
		Vector2f itemPos = component.getOwnerEntity().getPosition();
		switch(item){
		case SPEEDUP: i = new SpeedUpItem(itemPos);
						System.out.println("speedup spawned");
						break;
		case ONEUP: i = new OneUp(itemPos);
					break;
		case MIRRORSTICK: i = new MirrorStickItem(itemPos);
						  break;
		case RANDOMREBOUND: i = new RandomRebound(itemPos);
							break;
		case EXPANDSTICK: i = new ExpandStickItem(itemPos);
							break;
		case COMPRESSSTICK: i = new CompressStickItem(itemPos);
							break;
		case SHOOTPLAYER: i = new ShootPlayerItem(itemPos);
							break;
		default: i = new ShootPlayerItem(itemPos);
							
		}
		StateBasedEntityManager.getInstance().addEntity(game.getCurrentStateID(), i);
	}

}
