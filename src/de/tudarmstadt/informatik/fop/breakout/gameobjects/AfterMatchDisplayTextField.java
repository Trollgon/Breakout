package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

/**
 * An inheritor of TextField that will appear after each match in EndlessGameState, usually not accepting input.
 * 
 * @author Lukas Lehmann
 */
public class AfterMatchDisplayTextField extends TextField {

	static TrueTypeFont tFont = new TrueTypeFont(new Font("Monospaced", Font.BOLD, 15), true);

	private boolean pressedEnter;
	
	/**
	 * The constructor of the TextField, uses switch-case to determine the content of the field
	 * 
	 * @param container period.
	 * @param x x-pos
	 * @param y y-pos
	 * @param kindOfDisplay shows score display if 1, time display if 2, "Name eingeben:" if 3, empty field else
	 * @param scoreOrTime handed value of score/time (whichever needed)
	 */
	public AfterMatchDisplayTextField(GUIContext container, int x, int y, int kindOfDisplay, int scoreOrTime) {
		super(container, tFont, x, y, 200, 25);
		this.setBackgroundColor(Color.black);
		this.setBorderColor(Color.black);
		this.setAcceptingInput(false);
		this.setFocus(false);
		
		switch (kindOfDisplay) {
		case 1:
			this.setText("Score: " + scoreOrTime);
			break;
		case 2:
			this.setText("Time:  " + scoreOrTime);
			break;
		case 3:
			this.setText("Name eingeben:");
			break;
		case 4:
			this.setText("Highscore verfehlt!");
			break;
		default:
			break;	
		}
	}
	
	/**
	 * true if enter pressed.
	 * @return
	 */
	public boolean getPressedEnter() {
		return this.pressedEnter;
	}
	
	/**
	 * only reacts if enter is pressed, sets pressedEnter true
	 */
	@Override
	public void keyPressed (int key, char c) { 
		if (key == 28) {
			this.pressedEnter = true;
		}
	}

}
