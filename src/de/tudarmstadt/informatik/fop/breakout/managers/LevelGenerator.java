package de.tudarmstadt.informatik.fop.breakout.managers;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameobjects.Block;

public class LevelGenerator implements GameParameters {

	private final static int BLOCKS_PER_ROW = 15;
	private final static int SIDE_SPACE = ((WINDOW_WIDTH - (BLOCKS_PER_ROW * BLOCK_WIDTH)) / 2);

	public static ArrayList<Block> parseLevelFromMap(String mapFile) throws IOException {
		FileReader reader = new FileReader(mapFile);
		ArrayList<Block> blocks = new ArrayList<Block>();
		Block block = null;
		StreamTokenizer st = new StreamTokenizer(reader);

		int gridX = 0, gridY = 0;
		int xPos, yPos;

		st.whitespaceChars(',', ',');
		st.eolIsSignificant(true);

		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			if (st.ttype == StreamTokenizer.TT_NUMBER) {

				xPos = SIDE_SPACE + BLOCK_WIDTH * gridX;
				yPos = BLOCK_HEIGHT / 2 + BLOCK_HEIGHT * gridY;

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
			}
			else if (st.ttype == StreamTokenizer.TT_EOL) {
				gridX = 0;
				gridY++;
			}
			blocks.add(block);
		}
		
		return blocks;
	}

}
