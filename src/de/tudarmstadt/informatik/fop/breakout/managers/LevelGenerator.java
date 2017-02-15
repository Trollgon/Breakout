package de.tudarmstadt.informatik.fop.breakout.managers;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Block;

public class LevelGenerator implements GameParameters {

	private final static int BLOCKS_PER_ROW = 16;
	private final static int SIDE_SPACE = WINDOW_WIDTH / 2;
	private final static int TOP_SPACE = WINDOW_HEIGHT / 2;

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

			Block block = null;

			FileReader reader = new FileReader(mapFile);
			StreamTokenizer st = new StreamTokenizer(reader);

			int gridX = 0, gridY = 0;
			int xPos, yPos;

			st.whitespaceChars(',', ',');
			st.eolIsSignificant(true);

			// start reading the given .map-file
			while (st.nextToken() != StreamTokenizer.TT_EOF) {

				if (st.ttype == StreamTokenizer.TT_NUMBER) {

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
						block = new Block(BlockType.DEFAULT_TWO, xPos, yPos);
						break;
					}
					gridX++;

				} else if (st.ttype == StreamTokenizer.TT_EOL) {
					gridX = 0;
					gridY++;
				}
				blocks.add(block);
			}
		}
		
		return blocks;
	}

}
