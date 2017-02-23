package de.tudarmstadt.informatik.fop.breakout.managers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.LinkedList;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;

/**
 * This class is used to "manage" the "highscore.hsc"-file.
 * 
 * @author Lukas Lehmann
 */
public class HighscoreManager implements GameParameters {
	
	/**
	 * Checks if a given score is high enough to be added to the highscore.
	 * 
	 * @param score the given score
	 * @return true if score high enough, false if not
	 * @throws IOException
	 */
	public static boolean checkIfScoreHighEnough (int score) throws IOException {
		FileReader highscore = new FileReader(HIGHSCORE_FILE);
		StreamTokenizer st = new StreamTokenizer(highscore);
		st.whitespaceChars(',', ',');
		st.eolIsSignificant(true);
		
		int amountOfEntries = 0;
		boolean nextIntIsAScore = true;
		
		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			if (st.ttype == StreamTokenizer.TT_NUMBER && nextIntIsAScore) {
				if (score >= ((int) st.nval)) {
					highscore.close();
					return true;
				}
				else {
					nextIntIsAScore = false;
					amountOfEntries++;
				}
			}
			else {
				nextIntIsAScore = true;
			}
		}
		
		if (amountOfEntries < 10) {
			highscore.close();
			return true;
		}
		
		highscore.close();
		return false;
	}
	
	/**
	 * Adds a player with his name, his score and the time he needed to the highscore.hsc-file.
	 * 
	 * @param player a given player
	 * @throws IOException
	 */
	public static void addPlayerToHighscore (Player player) throws IOException {
		FileReader highscore = new FileReader(HIGHSCORE_FILE);
		StreamTokenizer st = new StreamTokenizer(highscore);
		st.whitespaceChars(',', ',');
		st.eolIsSignificant(false);
		
		LinkedList<Player> playerList = new LinkedList<Player>();
		
		int i = 1;
		String firstToken = null;
		int secondToken = 0;
		int thirdToken = 0;
		
		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			if (i == 1)
				firstToken = st.sval;
			if (i == 2)
				secondToken = (int) st.nval;
			if (i == 3)
				thirdToken = (int) st.nval;
			i++;
			if (i > 3) {
				playerList.add(new Player(firstToken, secondToken, thirdToken));
				i = 1;
			}
		}
		
		highscore.close();
		
		playerList.add(player);
		
		playerList.sort(new PlayerComparator());
		if (playerList.size() > 10)
			playerList.remove(10);
		
		FileWriter updatedHighscore = new FileWriter(HIGHSCORE_FILE);
		
		for (int j = 0; j <= playerList.size() - 2; j++) {
			updatedHighscore.write(playerList.get(j).toString());
			updatedHighscore.write("\n");
		}
		updatedHighscore.write(playerList.get(playerList.size() - 1).toString());
		
		updatedHighscore.close();
	}
	
	/**
	 * clears the highscore.hsc-file
	 * 
	 * @throws IOException
	 */
	public static void clearHighscore() throws IOException {
		FileWriter emptyHighscore = new FileWriter(HIGHSCORE_FILE);
		emptyHighscore.close();
	}

}
