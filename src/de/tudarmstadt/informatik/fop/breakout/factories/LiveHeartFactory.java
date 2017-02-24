package de.tudarmstadt.informatik.fop.breakout.factories;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.component.render.ImageRenderComponent;

/**
 * factory for LiveHeart ImageRenderComponents
 * @author Jonas
 *
 */
public class LiveHeartFactory implements GameParameters{

	private int amount;
	
	/**
	 * constructor for LiveHeartFactory
	 * @param amount of lives left (getLivesAmount())
	 */
	public LiveHeartFactory (int amount) {
		this.amount = amount;
	}
	
	/**
	 * creates the ImageRenderComponent for the LiveHeartFactory
	 * @return
	 * @throws SlickException
	 */
	public ImageRenderComponent createLiveHeart() throws SlickException {
		
		Image image;
		
		switch(amount) {
		case 3:
			image = new Image(HEART_3_IMAGE);
			break;
		case 2:
			image = new Image(HEART_2_IMAGE);
			break;
		case 1:
			image = new Image(HEART_1_IMAGE);
			break;
		default:
			image = new Image(HEART_0_IMAGE);
			break;
		}
		
		return new ImageRenderComponent(image);
	}

}
