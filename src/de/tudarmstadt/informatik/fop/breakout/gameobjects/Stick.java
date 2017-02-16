package de.tudarmstadt.informatik.fop.breakout.gameobjects;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;

import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;
/**
 * 
 * @author Peter Franke
 *
 */
public class Stick extends Entity implements GameParameters {
	
	private int posx = Math.floorDiv(WINDOW_WIDTH, 2);
	private int posy = WINDOW_HEIGHT-20;
	
	
	
	public Stick() {
		super(STICK_ID);
		setPosition(new Vector2f(posx, posy));
		this.setSize(new Vector2f(130, 25));
		try {
			this.addComponent(new ImageRenderComponent(new Image(STICK_IMAGE)));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
