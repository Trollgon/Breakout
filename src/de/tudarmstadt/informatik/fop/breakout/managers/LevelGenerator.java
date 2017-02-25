package de.tudarmstadt.informatik.fop.breakout.managers;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.*;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveDownAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.action.basicactions.Movement;
import eea.engine.action.basicactions.RotateLeftAction;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.LoopEvent;

/**
 * LevelGenerator class for level generation utility
 */
public class LevelGenerator implements GameParameters {

	// private final static int BLOCKS_PER_ROW = 16;
	private final static int SIDE_SPACE = BLOCK_WIDTH / 2;
	private final static int TOP_SPACE = BLOCK_HEIGHT / 2;

	/**
	 * loads a level from a '.map' file and returns an ArrayList<Block> with all
	 * blocks of the level
	 * 
	 * @param mapFile
	 * @return ArrayList<Block> with all blocks of the given level-file
	 * @throws IOException
	 */
	public static ArrayList<AbstractBlock> parseLevelFromMap(String mapFile) throws IOException {

		ArrayList<AbstractBlock> blocks = new ArrayList<AbstractBlock>();

		// just start reading the file if its a .map-file
		if (mapFile.endsWith(".map")) {

			// block that will be configured and added to blocks
			AbstractBlock block = null;

			// FileReader and StreamTokenizer to parse the file
			FileReader reader = new FileReader(mapFile);
			StreamTokenizer st = new StreamTokenizer(reader);

			// grid-positions: x -> 0 to 15 for each row
			// y -> 0 to 9 for each column
			int gridX = 0, gridY = 0;

			// x-/y-positions in the window
			int xPos, yPos;

			// setting ',' to separate the different Tokens
			st.whitespaceChars(',', ',');
			// setting the Tokenizer to endOfLine-significant:
			st.eolIsSignificant(true);

			// start reading the given .map-file
			while (st.nextToken() != StreamTokenizer.TT_EOF) {

				if (st.ttype == StreamTokenizer.TT_NUMBER) {

					// calculating the position of the next block:
					xPos = SIDE_SPACE + BLOCK_WIDTH * gridX;
					yPos = TOP_SPACE + BLOCK_HEIGHT * gridY;

					block = getBlockByID((int) st.nval, xPos, yPos);

					gridX++;

					// end of line reached:
				} else if (st.ttype == StreamTokenizer.TT_EOL) {
					gridX = 0;
					gridY++;
				}

				// adds the configured new block to the ArrayList<Block>
				if (block != null) {
					blocks.add(block);
				}
			}
		}

		return blocks;
	}

	/**
	 * returns a new ArrayList<AbstractBlock> with random blocks in a Row
	 * 
	 * @return
	 */
	public static ArrayList<AbstractBlock> getEndlessGameRow() {
		ArrayList<AbstractBlock> blocks = new ArrayList<AbstractBlock>();
		AbstractBlock block = null;

		int xPos;
		int blockID;

		// for every column
		for (int x = 0; x <= 16; x++) {

			// set next blocks position
			xPos = SIDE_SPACE + BLOCK_WIDTH * x;
			
			// randomly set the next blocks ID from (0 to 4)
			blockID = (int) Math.round(Math.random() * 4);
			
			// generate the random block
			block = getBlockByID(blockID, xPos, -BLOCK_HEIGHT/2);

			// adds the endlessGame specific MoveDownAction
			if (block != null) {
				block.always.addAction(new MoveDownAction(ENDLESS_GAME_SPEED));

				blocks.add(block);
			}
		}

		// returns all generated blocks
		return blocks;
	}

	/**
	 * returns TRUE if top Row has moven down enough to create next random row
	 * 
	 * @return returns if the most top row (area of auxiliary rec) is empty
	 */
	public static boolean topRowMissing() {

		Rectangle rec = new Rectangle(0, -BLOCK_HEIGHT/2, WINDOW_WIDTH, BLOCK_HEIGHT/2);

		// tries to find any Block in the area of rec:
		// if no Block is found, return TRUE, if any is found, return FALSE
		return !StateBasedEntityManager.getInstance()
				.getEntitiesByState(ENDLESS_GAME_STATE)
				.stream()
				.filter(e -> e instanceof AbstractBlock)
				.anyMatch(b -> b.getShape().intersects(rec));
		
	}

	/**
	 * gets the Block-instance by a given ID
	 * 
	 * @param blockID
	 *            of the needed block class
	 * @param xPos
	 *            of the new block
	 * @param yPos
	 *            of the new block
	 * @return a block instance of the block class associated with the given ID
	 */
	public static AbstractBlock getBlockByID(int blockID, int xPos, int yPos) {

		switch (blockID) {
		case 0:
			return null;
		default:
		case 1:
			return new StandardBlock(xPos, yPos);
		case 2:
			return new IronBlock(xPos, yPos);
		case 3:
			return new GoldBlock(xPos, yPos);
		case 4:
			return new IceBlock(xPos, yPos);
		}

	}

}
