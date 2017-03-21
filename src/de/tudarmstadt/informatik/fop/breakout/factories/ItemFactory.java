package de.tudarmstadt.informatik.fop.breakout.factories;

import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.items.CompressStickItem;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.items.ExpandStickItem;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.items.MirrorStickItem;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.items.OneUp;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.items.RandomRebound;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.items.ShootPlayerItem;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.items.SlowDownItem;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.items.SpeedUpItem;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

/**
 * ItemFactory class: helps to create a new Item by a ItemType
 * @author Jonas
 *
 */
public class ItemFactory implements IEntityFactory, GameParameters {

	private ItemType item;
	private Vector2f pos;
	
	/**
	 * constructor of ItemFactory
	 * @param item ItemType of the item this factory is going to create
	 * @param pos of the item(s) this factory is going to create
	 */
	public ItemFactory(ItemType item, Vector2f pos) {
		this.item = item;
		this.pos = pos;
	}
	
	@Override
	public Entity createEntity() {
		
		switch(item) {
		case COMPRESSSTICK:
			return new CompressStickItem(pos);
		case EXPANDSTICK:
			return new ExpandStickItem(pos);
		case MIRRORSTICK:
			return new MirrorStickItem(pos);
		case ONEUP:
			return new OneUp(pos);
		case RANDOMREBOUND:
			return new RandomRebound(pos);
		case SLOWDOWN:
			return new SlowDownItem(pos);
		case SHOOTPLAYER:
			return new ShootPlayerItem(pos);
		default:
		case SPEEDUP:
			return new SpeedUpItem(pos);
		}
	}

}
