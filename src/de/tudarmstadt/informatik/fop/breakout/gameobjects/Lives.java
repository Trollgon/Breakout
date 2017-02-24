package de.tudarmstadt.informatik.fop.breakout.gameobjects;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.factories.LiveHeartFactory;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.basicevents.LoopEvent;

/**
 * This Lives class, as an inheritor of Entity, is used to represent the amount of lives left on the Gameplay screen.
 * 
 * @author Lukas Lehmann
 */
public class Lives extends Entity implements GameParameters {

	private static int livesAmount;
	private ImageRenderComponent image;
	private LoopEvent updateBar;
	
	/**
	 * The constructor of the class initializes a Lives display with an image of full three hearts. 
	 */
	public Lives() {
		super(LIVES_ID);
		livesAmount = 3;
		
		
		setPosition(new Vector2f(45, (WINDOW_HEIGHT - 13)));
		setPassable(true);
		
		try {
			image = new LiveHeartFactory(getLivesAmount()).createLiveHeart();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		updateBar = new LoopEvent();
		
		updateBar.addAction(new Action() {
			
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				updateLifeBar();
			}
		});
		
		this.addComponent(updateBar);
	}
	
	/**
	 * get the amount of lives left
	 * @return 
	 * 
	 * @return the amount of lives as an integer
	 */
	public static int getLivesAmount() {
		return livesAmount;
	}
	
	public static void deductLife() {
		livesAmount--;
	}
	
	/**
	 * Deduct the amount of lives left by one and change the image representation.
	 */
	public void updateLifeBar() {
		
		this.removeComponent(image);
		try {
			image = new LiveHeartFactory(getLivesAmount()).createLiveHeart();
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
		this.addComponent(image);
		
	}

}
