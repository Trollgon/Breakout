package de.tudarmstadt.informatik.fop.breakout.physics;

import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;

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
	public static void updateAngleOffset(Ball b, Vector2f stickPosition) {
		float diff = stickPosition.getX() - b.getPosition().getX();
		float o;




		if (diff > 20) {
			o = -(diff - 20) / 3; // offset angle continuously gets bigger the
									// further from the center the stick is hit
		} else if (diff < -20) {
			o = -(diff + 20) / 3;
		} else
			o = 0; // if the stick is hit in a range of 20px around the middle,
					// don't offset the angle

		b.setRotation(b.getRotation() + o);


	}

}
