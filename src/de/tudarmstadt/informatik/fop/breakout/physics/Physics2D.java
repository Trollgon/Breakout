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
	 * @author Jonas Henry Grebe
	 * @param rotation old rotation you want to manipulate
	 * @param b Ball 
	 * @param s Stick
	 * @return the newly calculated rotation of the Ball after it hit the Stick
	 */
	public static float bounceStick(float rotation, Ball b, Stick s) {
		float offset = b.getPosition().getX() - s.getPosition().getX();
		
		if( Math.abs(offset) >= (s.getSize().getX() / 2) + (b.getSize().getX() / (2 * Math.sqrt(2)) ) ){	//detect if the ball hit the stick on the left or right border
			rotation = bounceYAxis(rotation);
			return rotation;
		}
		else {
			rotation = bounceXAxis(rotation);
			b.setPosition(new Vector2f(b.getPosition().getX(), s.getPosition().getY() - 28));
		
			if (offset > - 20f && offset < 20f) {
				return rotation;
			}
		
			else {
			
				float factor = offset / s.getSize().getX() / 2;
			
				//rotation += (factor * 15);
				float offsetRotation = (float) modulo(rotation + factor * 20, 360);
				if(offsetRotation < 90 || offsetRotation > 270) {
					return offsetRotation;
				}
				else {
					System.out.println("illegal rotation prevented"); 
					return rotation; 
				}
		}
		}
		
	}
	
	/**
	 * 
	 * 	Used to adjust the angle of the ball after it was
	 *  hit by the the Stick
	 * @author Peter Franke
	 * @param ball
	 *            the Ball hit by the Stick
	 * @param s
	 *            the Stick hitting the ball
	 */
	public static void updateAngleOffset(Ball ball, Stick s) {
		System.out.println(ball.getPosition().getY());
		if(System.currentTimeMillis() > lastHitStickTime + 100){
			lastHitStickTime = System.currentTimeMillis();
		float diff = s.getPosition().getX() - ball.getPosition().getX();
		float rotationOffset = 0;
		if(Math.abs(diff) >= (s.getSize().getX() / 2) + (ball.getSize().getX() / (2 * Math.sqrt(2)))){	//detect if the ball hit the stick on the left or right border
			ball.setRotation(ball.getRotation() -180); //  send it in the opposite direction
			System.out.println("y achse stick");
		}
		else if (diff > 20) {
			rotationOffset = -(diff - 20) / 2.6f; // offset angle continuously gets bigger the
									// further from the center the stick is hit
			System.out.println("x achse stick links");
			
		} else if (diff < -20) {
			rotationOffset = -(diff + 20) / 2.6f;
			System.out.println("x achse stick rechts");
		}
		 // if the stick is hit in a range of 20px around the middle,
					// don't offset the angle
		else System.out.println("Stick mitte");
		
	float newRot = (float) /*ball.getRotation() + rotationOffset % 360;*/modulo(ball.getRotation() + rotationOffset, 360);
	/*	for(;newRot < 0; newRot += 360){ //Java thinks -90 % 360 = -90 and not 270
			} */
		
		if(newRot < 90 || newRot > 270){	//prevent the ball falling through the stick due to rotation pointing downwards after adding offset multiple times
			ball.setRotation(newRot);
			ball.setPosition(new Vector2f(ball.getPosition().getX(), s.getPosition().getY() - 28));
		}
		System.out.println(ball.getPosition().getY());
		
		}
		else System.out.println("can only use this every 100 ms");

	}
	/**
	 * @author Peter Franke
	 * @param x
	 * @param modulus double: the modulus
	 * @return x mod modulus (>= 0, <= modulus) as double
	 */
	public static double modulo(double x, double modulus){
		double xmod = x % modulus;
		if(xmod <0) xmod += modulus;
		return xmod;
	}
	
	/**
	 * determines whether a given Entity hitter has hit another Entity e on its EDGE or not
	 * 
	 * @param e Entity that was hit
	 * @param hitter Entity that hit e
	 * @return TRUE if hit on EDGE, else FALSE
	 */
	public static boolean collidedOnEdge(Entity e, Entity hitter) {
		final float SENSITIVITY = 2f;
		
		float offset = e.getPosition().x - hitter.getPosition().x;

		return (offset < -(e.getSize().x / 2) + SENSITIVITY || (e.getSize().x / 2) - SENSITIVITY > offset);
	}
	

}
