package de.tudarmstadt.informatik.fop.breakout.managers;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;

public class SoundManager implements GameParameters {

	public static void playSound(String sound, float pitch, float volume) {
		
		float pr = pitch + (((float) Math.random() * RAND_PITCH_TRESHOLD * 2) - RAND_PITCH_TRESHOLD); //alter the pitch by up to RAND_PITCH_TRESHOLD in each direction randomly
		
		try {
			new Sound(sound).play(pr, GAME_VOLUME);

		} catch (SlickException e) {

			e.printStackTrace();
		}
	}
}
