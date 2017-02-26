package de.tudarmstadt.informatik.fop.breakout.managers;

import java.io.IOException;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.SoundStore;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;

public class SoundManager implements GameParameters {

	public static void playSound(String sound, float pitch, float volume) {
		
		float pr = pitch + (((float) Math.random() * RAND_PITCH_TRESHOLD * 2) - RAND_PITCH_TRESHOLD); // alter the pitch by up to RAND_PITCH_TRESHOLD in each direction randomly
		float vr = volume + (((float) Math.random() * RAND_VOL_TRESHOLD * 2) - RAND_VOL_TRESHOLD);	// alter the volume by up to RAND_VOLUME_TRESHOLD in each direction randomly
		
		try {
			SoundStore.get().getWAV(sound).playAsSoundEffect(pr, vr, false);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}

