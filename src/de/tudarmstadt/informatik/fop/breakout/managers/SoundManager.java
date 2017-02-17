package de.tudarmstadt.informatik.fop.breakout.managers;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;

public class SoundManager implements GameParameters {

	public static void playSound(String sound, float pitch, float volume) {

		try {
			new Sound(sound).play(pitch, GAME_VOLUME);

		} catch (SlickException e) {

			e.printStackTrace();
		}
	}
}
