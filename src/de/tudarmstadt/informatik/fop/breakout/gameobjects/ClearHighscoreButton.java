package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.managers.HighscoreManager;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

public class ClearHighscoreButton extends Entity implements GameParameters {

	ANDEvent mainEvent;
	
	public ClearHighscoreButton(int xPos, int yPos) {
		super(CLEAR_HIGHSCORE_BUTTON_ID);
		this.setPosition(new Vector2f(xPos, yPos));
		
		try {
			this.addComponent(new ImageRenderComponent(new Image(BUTTON_IMAGE)));
		} catch (SlickException e) {
			e.printStackTrace();
		}

		mainEvent = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
		mainEvent.addAction(new Action() {
			
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				try {
					HighscoreManager.clearHighscore();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		this.addComponent(mainEvent);
		
	}

}
