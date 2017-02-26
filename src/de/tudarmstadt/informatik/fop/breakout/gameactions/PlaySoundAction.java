package de.tudarmstadt.informatik.fop.breakout.gameactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.managers.SoundManager;
import eea.engine.action.Action;
import eea.engine.component.Component;

public class PlaySoundAction implements Action, GameParameters {

	private String sound;
	
	public PlaySoundAction(String sound) {
	this.sound = sound;
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		SoundManager.playSound(sound, 1f);
	}

}
