package de.tudarmstadt.informatik.fop.breakout.physics;

/**
 * physics 2D class to calculate everything physics-related
 * @author Jonas Henry Grebe
 * 
 *				0°
 *		 315°	|	 45°
 *	 			|
 *	  270°------|--------90°
 *				|
 *		 225°	|	 135°
 *			   180°			
 */
public class Physics2D {
	
	/**
	 * returns the new rotation when bounced on the XAxis
	 * @param rotation of the moment of collision
	 * @return the new rotation as a float
	 * 
	 */
	public static float bounceXAxis(float rotation){
		return (rotation + 2 * (90 - rotation)) % 360;
	}
	
	/**
	 * returns the new rotation when bounced on the YAxis
	 * @param rotation of the moment of collision
	 * @return the new rotation as a float
	 */
	public static float bounceYAxis(float rotation){
		return - rotation;
	}

}
