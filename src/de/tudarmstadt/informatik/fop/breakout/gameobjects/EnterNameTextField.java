package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

/**
 * An inheritor of TextField that will be used in EndlessGameState to enter a name after the match.
 * 
 * @author Lukas Lehmann
 */
public class EnterNameTextField extends TextField {

	private boolean pressedEnter;
	
	static TrueTypeFont tFont = new TrueTypeFont(new Font("Monospaced", Font.BOLD, 15), true);
	
	/**
	 * Creates an instance of EnterNameTextField.
	 * 
	 * @param container period.
	 * @param x x-pos
	 * @param y y-pos
	 */
	public EnterNameTextField(GUIContext container, int x, int y) {
		super(container, tFont, x, y, 200, 25);
		this.setBackgroundColor(Color.black);
		this.setBorderColor(Color.black);
		this.pressedEnter = false;
		this.setAcceptingInput(true);
		this.setMaxLength(20);
		this.setFocus(true);
		this.setCursorPos(0);
	}
	
	/**
	 * true if enter pressed.
	 * @return
	 */
	public boolean getPressedEnter() {
		return this.pressedEnter;
	}
	
	/**
	 * handles actions for different keys pressed, sets pressedEnter true when enter pressed, 
	 * ignores ',' and uses the inherited method for anything else
	 */
	@Override
	public void keyPressed (int key, char c) { 
		// -> ENTER
		if (key == 28) {
			this.pressedEnter = true;
			this.setAcceptingInput(false);
		}
		// -> ','
		else if (key == 51);
		else {
			super.keyPressed(key, c);
		}
	}

}
