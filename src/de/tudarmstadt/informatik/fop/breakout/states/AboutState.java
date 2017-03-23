package de.tudarmstadt.informatik.fop.breakout.states;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import eea.engine.entity.StateBasedEntityManager;

public class AboutState extends BasicGameState implements GameParameters {

	private int stateID;
	private StateBasedEntityManager entityManager;
	private TrueTypeFont tFont;
	private Font font;
	String[] text;

	public AboutState() {
		this.stateID = ABOUT_STATE;
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		if (!Breakout.getDebug()) {
			font = new Font("Monospaced", Font.BOLD, 25);
			tFont = new TrueTypeFont(font, true);

			text = new String[] {

					"FoP-Projekt 2017, TU-Darmstadt", "Dozent: Dr. Guido Rößling", "", "Entwickler:", "",
					"Peter Franke, Jonas Henry Grebe,", "Lukas Lehmann und Matthias Spörkmann", "", "Block-Typen:"

			};
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		if (!Breakout.getDebug()) {

			g.drawImage(new Image(DEFAULT_MENU_IMAGE), 0, 0);

			entityManager.renderEntities(container, game, g);

			int line = 100;
			int lineSpace = 20;

			for (int i = 0; i < text.length; i++) {

				tFont.drawString(100, line, text[i]);
				line += lineSpace;
			}

			g.drawImage(new Image(BLOCK_STANDARD_IMAGE), 100f, 320f);
			tFont.drawString(175f, 320f, "Standard");

			g.drawImage(new Image(BLOCK_STONE_IMAGE), 100f, 360f);
			tFont.drawString(175f, 360f, "Stone");

			g.drawImage(new Image(BLOCK_IRON_IMAGE), 100f, 400f);
			tFont.drawString(175f, 400f, "Iron");

			g.drawImage(new Image(BLOCK_GOLD_IMAGE), 100f, 440f);
			tFont.drawString(175f, 440f, "Gold");

			g.drawImage(new Image(BLOCK_DIAMOND_IMAGE), 100f, 480f);
			tFont.drawString(175f, 480f, "Diamond");

			g.drawImage(new Image(BLOCK_SNOW_IMAGE), 325f, 320f);
			tFont.drawString(400f, 320f, "Snow");

			g.drawImage(new Image(BLOCK_ICE_IMAGE), 325f, 360f);
			tFont.drawString(400f, 360f, "Ice");

			g.drawImage(new Image(BLOCK_DROPPER_IMAGE), 325f, 400f);
			tFont.drawString(400f, 400f, "Dropper");

			g.drawImage(new Image(BLOCK_EARTH_IMAGE), 325f, 440f);
			tFont.drawString(400f, 440f, "Earth");

			g.drawImage(new Image(BLOCK_WOOD_IMAGE), 325f, 480f);
			tFont.drawString(400f, 480f, "Wood");

			g.drawImage(new Image(BLOCK_MAGMA_IMAGE), 550f, 320f);
			tFont.drawString(625f, 320f, "Magma");

			g.drawImage(new Image(BLOCK_OBSIDIAN_IMAGE), 550f, 360f);
			tFont.drawString(625f, 360f, "Obsidian");

			g.drawImage(new Image(BLOCK_GLASS_IMAGE), 550f, 400f);
			tFont.drawString(625f, 400f, "Glass");

			tFont.drawString(100f, 525f, "ESC - zurück zum Hauptmenü");

		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}
