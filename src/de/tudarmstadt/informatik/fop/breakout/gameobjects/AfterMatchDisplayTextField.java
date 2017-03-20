package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

public class AfterMatchDisplayTextField extends TextField {

	static TrueTypeFont tFont = new TrueTypeFont(new Font("Monospaced", Font.BOLD, 15), true);

	private boolean pressedEnter;
	
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
	
	public boolean getPressedEnter() {
		return this.pressedEnter;
	}
	
	@Override
	public void keyPressed (int key, char c) { 
		if (key == 28) {
			this.pressedEnter = true;
		}
	}

}
