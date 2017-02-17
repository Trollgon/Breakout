package de.tudarmstadt.informatik.fop.breakout.gameactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.managers.SoundManager;
import eea.engine.action.Action;
import eea.engine.component.Component;

public class PlaySoundAction implements Action, GameParameters {

	private String sound;
	private float pitch;
	
	public PlaySoundAction(String sound, float pitch) {
	this.sound = sound;
	this.pitch = pitch;
	
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		SoundManager.playSound(sound, pitch, GAME_VOLUME);
	}

}
