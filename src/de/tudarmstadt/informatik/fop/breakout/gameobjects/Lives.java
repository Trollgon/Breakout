package de.tudarmstadt.informatik.fop.breakout.gameobjects;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;

/**
 * This Lives class, as an inheritor of Entity, is used to represent the amount of lives left on the Gameplay screen.
 * 
 * @author Lukas Lehmann
 */
public class Lives extends Entity implements GameParameters {

	private int livesAmount;
	
	/**
	 * The constructor of the class initializes a Lives display with an image of full three hearts. 
	 */
	public Lives() {
		super(LIVES_ID);
		this.livesAmount = 3;
		setPosition(new Vector2f(45, (WINDOW_HEIGHT - 13)));
		setPassable(true);
		try {
			this.addComponent(new ImageRenderComponent(new Image(HEART_3_IMAGE)));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get the amount of lives left
	 * 
	 * @return the amount of lives as an integer
	 */
	public int getLivesAmount() {
		return this.livesAmount;
	}
	
	/**
	 * Deduct the amount of lives left by one and change the image representation.
	 */
	public void deductLife() {
		this.livesAmount--;
		try {
			if (this.livesAmount == 2)
				this.addComponent(new ImageRenderComponent(new Image(HEART_2_IMAGE)));
			if (this.livesAmount == 1)
				this.addComponent(new ImageRenderComponent(new Image(HEART_1_IMAGE)));
			if (this.livesAmount == 0)
				; // missing image!
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
