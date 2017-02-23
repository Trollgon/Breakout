package de.tudarmstadt.informatik.fop.breakout.managers;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Block;

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
	public static ArrayList<Block> parseLevelFromMap(String mapFile) throws IOException {

		ArrayList<Block> blocks = new ArrayList<Block>();

		// just start reading the file if its a .map-file
		if (mapFile.endsWith(".map")) {

			// block that will be configured and added to blocks
			Block block = null;

			// FileReader and StreamTokenizer to parse the file
			FileReader reader = new FileReader(mapFile);
			StreamTokenizer st = new StreamTokenizer(reader);

			// grid-positions: 	x -> 0 to 15 for each row
			// 					y -> 0 to 9 for each column
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

					switch ((int) st.nval) {
					case 0:
						break;

					default:

					case 1:
						block = new Block(BlockType.DEFAULT, xPos, yPos);
						break;

					case 2:
						block = new Block(BlockType.IRON, xPos, yPos);
						break;
						
					case 3:
						block = new Block(BlockType.GOLD, xPos, yPos);
						break;
						
					case 4:
						block = new Block(BlockType.TNT, xPos, yPos);
						break;
					}
					
					gridX++;

				// end of line reached:
				} else if (st.ttype == StreamTokenizer.TT_EOL) {
					gridX = 0;
					gridY++;
				}
				// adds the configured new block to the ArrayList<Block>
				blocks.add(block);
			}
		}
		
		return blocks;
	}

}
