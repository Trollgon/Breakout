package de.tudarmstadt.informatik.fop.breakout.physics;

import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Stick;

/**
 * physics 2D class to calculate everything physics-related
 * 
 * @author Jonas Henry Grebe
 * 
 */
public class Physics2D {

	/**
	 * returns the new rotation when bounced on the XAxis
	 * 
	 * @param rotation
	 *            of the moment of collision
	 * @return the new rotation as a float
	 * 
	 */
	public static float bounceXAxis(float rotation) {
		return (rotation + 2 * (90 - rotation)) % 360;
	}

	/**
	 * returns the new rotation when bounced on the YAxis
	 * 
	 * @param rotation
	 *            of the moment of collision
	 * @return the new rotation as a float
	 */
	public static float bounceYAxis(float rotation) {
		return -rotation;
	}

	/**
	 * @author Peter Franke
	 * 	Used to adjust the angle of the ball after it was
	 *  hit by the the Stick
	 *
	 * @param b
	 *            the Ball hit by the Stick
	 * @param stickPosition
	 *            the Position of the Stick
	 */
	public static void updateAngleOffset(Ball b, Stick s) {
		float diff = s.getPosition().getX() - b.getPosition().getX();
		float o = 0;
		if(Math.abs(diff) > (s.getSize().getX() / 2) + (b.getSize().getX() / 2)){	//detect if the ball hits the stick on the left or right border
			b.setRotation(b.getRotation() -180);
		}
		else if (diff > 20) {
			o = -(diff - 20) / 2.6f; // offset angle continuously gets bigger the
									// further from the center the stick is hit
			
		} else if (diff < -20) {
			o = -(diff + 20) / 2.6f;
			
		}
		 // if the stick is hit in a range of 20px around the middle,
					// don't offset the angle
		
		
		float newRot = b.getRotation() + o;
		for(;newRot < 0; newRot += 360){ //Java thinks -90 % 360 = -90 and not 270
			}
		
		if(newRot % 360 < 90 || newRot % 360 > 270){	//prevent the ball falling through the stick after adding offset in many hits
		b.setRotation(newRot);
		}
		
		

	}

}
