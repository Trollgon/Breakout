package de.tudarmstadt.informatik.fop.breakout.physics;

import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.blocks.AbstractBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Ball;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Stick;
import eea.engine.entity.Entity;

/**
 * physics 2D class to calculate everything physics-related
 * 
 * @author Jonas Henry Grebe
 * @author Peter Franke
 * 
 */
public class Physics2D {
	static long lastHitStickTime;

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
	 * returns the new rotation when bounced on Stick
	 * 
	 * @author Jonas Henry Grebe & Peter Franke
	 * @param rotation
	 *            old rotation you want to manipulate
	 * @param b
	 *            Ball
	 * @param s
	 *            Stick
	 * @return the newly calculated rotation of the Ball after it hit the Stick
	 */
	public static float bounceStick(float rotation, Ball b, Stick s) {
		float offset = b.getPosition().getX() - s.getPosition().getX();

		if (Math.abs(offset) >= (s.getSize().getX() / 2) + (b.getSize().getX() / (2 * Math.sqrt(2)))) { // detect
																										// if
																										// the
																										// ball
																										// hit
																										// the
																										// stick
																										// on
																										// the
																										// left
																										// or
																										// right
																										// border
			rotation = bounceYAxis(rotation);
			return rotation;
		} else {
			rotation = bounceXAxis(rotation);
			b.setPosition(new Vector2f(b.getPosition().getX(), s.getPosition().getY() - 28));

			if (offset > -20f && offset < 20f) {
				return rotation;
			}

			else {

				float factor = offset / s.getSize().getX() / 2;

				// rotation += (factor * 15);
				float offsetRotation = (float) modulo(rotation + factor * 20, 360);
				if (offsetRotation < 90 || offsetRotation > 270) {
					return offsetRotation;
				} else {
					System.out.println("illegal rotation prevented");
					return rotation;
				}
			}
		}

	}

	/**
	 * returns whether Entity b has hit Entity a on its edge or not 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean collidedOnSideEdge(Entity a, Entity b) {
		
		// absolute difference between a's x-center and b's x-center
		float offset = Math.abs(b.getPosition().getX() - a.getPosition().getX());
		
		return (offset >= b.getSize().getX()/2 + a.getSize().getX()/2);
	}
	
	
	/**
	 * @author Peter Franke
	 * @param x
	 * @param modulus
	 *            double: the modulus
	 * @return x mod modulus (>= 0, <= modulus) as double
	 */
	public static double modulo(double x, double modulus) {
		double xmod = x % modulus;
		if (xmod < 0)
			xmod += modulus;
		return xmod;
	}

}
