package de.tudarmstadt.informatik.fop.breakout.test.adapter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.List;

import de.tudarmstadt.informatik.fop.breakout.interfaces.IHighscoreEntry;
import de.tudarmstadt.informatik.fop.breakout.managers.Player;
import de.tudarmstadt.informatik.fop.breakout.managers.HighscoreManager;;

public class AdapterExtended extends Adapter {

  //TODO you probably should declare a highscore object of the "proper type" here...
	
	/**
	 * Use this constructor to set up everything you need.
	 */
	public AdapterExtended() {
		super();
	}
	
	/* *************************************************** 
	 * ********************** Highscore ******************
	 * *************************************************** */	
	
	/**
	 * adds a new highscore entry for the player. Note: only the 10 best entries are kept.
	 * 
	 * @param playerName name of the player
	 * @param numberOfDesBlocks number of destroyed blocks
	 * @param elapsedTime time since starting the map/level
	 * @throws IOException 
	 */
	public void addHighscore(String playerName, int numberOfDesBlocks, long elapsedTime) {
		try {
			HighscoreManager.addPlayerToHighscore(new Player(playerName, numberOfDesBlocks, (int) elapsedTime));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	/**
	 * resets (clears) the highscore list
	 * @throws IOException 
	 */
	public void resetHighscore() {
		try {
			HighscoreManager.clearHighscore();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		

	/**
	 * returns all highscore entries as a list
	 * 
	 * @return the list of all current highscore entries
	 * @throws IOException 
	 */
	public List<? extends IHighscoreEntry> getHighscores() {
		List<Player> playerList;
		try {
			playerList = HighscoreManager.getHisghscoreAsList();
			return playerList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * returns the number of entries in the highscore list
	 * @return returns the number of highscore entries - between 0 and 10
	 * @throws IOException 
	 */
	public int getHighscoreCount() {
		try {
			return HighscoreManager.getHisghscoreAsList().size();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * returns the name of the player at the given position of the highscore table
	 * 
	 * @param position the position in the list, should be inside 
	 * the interval [0, max(9, getHighscoreCount() - 1)]
	 * @return the name of the player at the given position or null if the index is invalid
	 * (negative, greater than 9 and/or greater than or equal to the entry count)
	 * @throws IOException 
	 */
	public String getNameAtHighscorePosition(int position) {
		try {
			if ((position >= 0) && (position < HighscoreManager.getHisghscoreAsList().size()))
				return HighscoreManager.getHisghscoreAsList().get(position).getPlayerName();
			else
				return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * return the time since starting this level for the highscore entry at the given position
	 * 
   * @param position the position in the list, should be inside 
   * the interval [0, max(9, getHighscoreCount() - 1)]
	 * @return the time elapsed for the given highscore entry if this exists; otherwise -1
	 * @throws IOException 
	 */
	public int getTimeElapsedAtHighscorePosition(int position) {
		try {
			if ((position >= 0) && (position < HighscoreManager.getHisghscoreAsList().size()))
				return (int) HighscoreManager.getHisghscoreAsList().get(position).getElapsedTime();
			else
				return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
  /**
   * return the number of blocks destroyed by highscore entry at the given position
   * 
   * @param position the position in the list, should be inside 
   * the interval [0, max(9, getHighscoreCount() - 1)]
   * @return the number of blocks destroyed for the given highscore entry if this exists; otherwise -1
 * @throws IOException 
   */
	public int getNumberOfDesBlocksAtHighscorePosition(int position) {
		try {
			if ((position >= 0) && (position < HighscoreManager.getHisghscoreAsList().size()))
				return HighscoreManager.getHisghscoreAsList().get(position).getScore();
			else
				return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
}
