package de.tudarmstadt.informatik.fop.breakout.managers;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.geom.Rectangle;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.AbstractBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.DiamondBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.DropperBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.EarthBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.GlassBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.GoldBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.IceBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.IronBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.MagmaBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.ObsidianBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.SnowBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.StandardBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.StoneBlock;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.blocks.WoodBlock;
import eea.engine.action.basicactions.MoveDownAction;
import eea.engine.entity.StateBasedEntityManager;

/**
 * LevelGenerator class for level generation utility
 */
public class LevelGenerator implements GameParameters {

	// private final static int BLOCKS_PER_ROW = 16;
	private final static int SIDE_SPACE = BLOCK_WIDTH / 2;
	private final static int TOP_SPACE = BLOCK_HEIGHT / 2;

	private static BlockType[] blockTypes = BlockType.values();

	/** 
	 * loads a level from a '.map' file and returns an ArrayList<Block> with all
	 * blocks of the level
	 * @author Jonas Henry Grebe
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

					block = getBlockByID(blockTypes[(int) st.nval], xPos, yPos);

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
	 * @author Jonas Henry Grebe
	 * @return ArrayList<AbstractBlock> of random blocks in the row directly
	 *         'over the window'
	 */
	public static ArrayList<AbstractBlock> getEndlessGameRow() {
		ArrayList<AbstractBlock> blocks = new ArrayList<AbstractBlock>();
		AbstractBlock block = null;

		int xPos;
		BlockType type;
		Random random = new Random();

		// for every column
		for (int x = 0; x <= 16; x++) {

			// set next blocks position
			xPos = SIDE_SPACE + BLOCK_WIDTH * x;

			// get random blockType
			type = blockTypes[random.nextInt(blockTypes.length)];

			// generate the random block
			block = getBlockByID(type, xPos, -BLOCK_HEIGHT / 2);

			// adds the endlessGame specific MoveDownAction
			// and adds the to the list if not null
			if (block != null) {
				block.always.addAction(new MoveDownAction(ENDLESS_GAME_SPEED));

				blocks.add(block);
			}
		}

		// returns all generated blocks
		return blocks;
	}

	/**
	 * returns TRUE if top Row has moven down enough to create the next random
	 * row
	 * 
	 * @author Jonas Henry Grebe
	 * @return returns if the most top row (area of auxiliary rec) is empty
	 */
	public static boolean topRowMissing() {

		Rectangle rec = new Rectangle(0, -BLOCK_HEIGHT / 2, WINDOW_WIDTH, BLOCK_HEIGHT / 2);

		// tries to find any Block in the area of rec:
		// if no Block is found, return TRUE, if any is found, return FALSE
		return !StateBasedEntityManager.getInstance().getEntitiesByState(ENDLESS_GAME_STATE).stream()
				.filter(e -> e instanceof AbstractBlock).anyMatch(b -> b.getShape().intersects(rec));

	}

	/**
	 * gets the Block-instance by a given BlockType
	 * 
	 * @param blockType
	 *            of the needed block class
	 * @param xPos
	 *            of the new block
	 * @param yPos
	 *            of the new block
	 * @return a block instance of the block class associated with the given ID
	 */
	public static AbstractBlock getBlockByID(BlockType block, int xPos, int yPos) {

		switch (block) {
		case DROPPER:
			return new DropperBlock(xPos, yPos);
		case GOLD:
			return new GoldBlock(xPos, yPos);
		case ICE:
			return new IceBlock(xPos, yPos);
		case IRON:
			return new IronBlock(xPos, yPos);
		case STANDARD:
			return new StandardBlock(xPos, yPos);
		case DIAMOND:
			return new DiamondBlock(xPos, yPos);
		case SNOW:
			return new SnowBlock(xPos, yPos);
		case EARTH:
			return new EarthBlock(xPos, yPos);
		case FIRE:
			return null;
		case GLASS:
			return new GlassBlock(xPos, yPos);
		case MAGMA:
			return new MagmaBlock(xPos, yPos);
		case METEOR:
			return null;
		case OBSIDIAN:
			return new ObsidianBlock(xPos, yPos);
		case STONE:
			return new StoneBlock(xPos, yPos);
		case JUNGLE:
			return null;
		case WOOD:
			return new WoodBlock(xPos, yPos);
		default:
		case NULL:
			return null;
		}
	}
	
	
	
	
	// ALTERNATIVE METHOD (TEST ONLY)
	public static AbstractBlock getBlockByID(BlockType block, int xPos, int yPos, int dontcare) {

		switch (block) {
		case DROPPER:
			return new DropperBlock(xPos, yPos);
		case GOLD:
			return new GoldBlock(xPos, yPos, dontcare);
		case ICE:
			return new IceBlock(xPos, yPos);
		case IRON:
			return new IronBlock(xPos, yPos, dontcare);
		case STANDARD:
			return new StandardBlock(xPos, yPos, dontcare);
		case DIAMOND:
			return new DiamondBlock(xPos, yPos);
		case SNOW:
			return new SnowBlock(xPos, yPos);
		case EARTH:
			return new EarthBlock(xPos, yPos);
		case FIRE:
			return null;
		case GLASS:
			return new GlassBlock(xPos, yPos);
		case MAGMA:
			return new MagmaBlock(xPos, yPos);
		case METEOR:
		case OBSIDIAN:
			return new ObsidianBlock(xPos, yPos);
		case STONE:
			return new StoneBlock(xPos, yPos, dontcare);
		case JUNGLE:
			// return new JungleBlock(xPos, yPos);
		case WOOD:
			return new WoodBlock(xPos, yPos);
		default:
		case NULL:
			return null;
		}
	}
	
	// ALTERNATIVE METHOD (TEST ONLY) //
	public static ArrayList<AbstractBlock> parseLevelFromMap(String mapFile, int dontcare) throws IOException {

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

					block = getBlockByID(blockTypes[(int) st.nval], xPos, yPos, dontcare);

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
}
