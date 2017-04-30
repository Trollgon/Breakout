package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

/**
 * This Textfields will appear after any match, giving some information, like score, time, etc., usually without user interaction.
 * @author Lukas
 *
 */
public class AfterMatchDisplayTextField extends TextField {

	static TrueTypeFont tFont = new TrueTypeFont(new Font("Monospaced", Font.BOLD, 15), true);

	private boolean pressedEnter;
	
	/**
	 * Construcor of an AfterMatchDisplayTextField creates a display with a text.
	 * @param container .
	 * @param x .
	 * @param y .
	 * @param kindOfDisplay
	 * 1: score display
	 * 2: time display
	 * 3: "Name eingeben"
	 * 4: "Highscore verfehlt!"
	 * 5: "Level erfolgreich!"
	 * 6: "Level fehlgeschlagen"
	 * else: empty textfield
	 * @param scoreOrTime a given value, used in case 1 and 2 (above)
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
		case 5:
			this.setText("Level erfolgreich!");
			break;
		case 6:
			this.setText("Level fehlgeschlagen!");
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
	
	@Override
	public void keyPressed (int key, char c) { 
		// only reacts to enter
		if (key == 28) {
			this.pressedEnter = true;
		}
	}

}
