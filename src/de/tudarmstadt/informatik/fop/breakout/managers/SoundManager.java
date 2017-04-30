package de.tudarmstadt.informatik.fop.breakout.managers;

import java.io.IOException;

import org.newdawn.slick.openal.SoundStore;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;

/**
 * class to manage playing of sounds
 * @author Jonas Henry Grebe
 *
 */
public class SoundManager implements GameParameters {

	public static void playSound(String sound, float pitch) {
		
		float pr = pitch + (((float) Math.random() * RAND_PITCH_TRESHOLD * 2) - RAND_PITCH_TRESHOLD); // alter the pitch by up to RAND_PITCH_TRESHOLD in each direction randomly
		
		try {
			SoundStore.get().getWAV(sound).playAsSoundEffect(pr, 1f, false);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}

