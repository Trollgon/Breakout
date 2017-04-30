package de.tudarmstadt.informatik.fop.breakout.managers;

import de.tudarmstadt.informatik.fop.breakout.interfaces.IHighscoreEntry;

public class Player implements Comparable<Player>, IHighscoreEntry {

	private String name;
	private int score;
	private int time;
	
	/**
	 * The Player class represents a player with his name, score and the time he needed.
	 * Primarly needed to add the player entry to the highscore.hsc.
	 * 
	 * @param name The name of the player
	 * @param score The score of the player
	 * @param time The time he needed
	 */
	public Player(String name, int score, int time) {
		this.name = name;
		this.score = score;
		this.time = time;
	}
	
	/**
	 * get the player score
	 * 
	 * @return the score
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * get the time the player needed
	 * 
	 * @return the time
	 */
	public int getTime() {
		return this.time;
	}
	
	@Override
	public int compareTo(Player otherPlayer) {
		if (this.getScore() > otherPlayer.getScore())
			return -1;
		else if ((this.getScore() == otherPlayer.getScore()) && (this.getTime() <= otherPlayer.getTime()))
			return -1;
		else
			return 1;
	}
	
	@Override
	public String toString() {
		return (this.name + "," + this.score + "," + this.time + ",");
	}

	@Override
	public String getPlayerName() {
		return this.name;
	}

	@Override
	public int getNumberOfDestroyedBlocks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getElapsedTime() {
		return this.time;
	}

	@Override
	public double getPoints() {
		return this.score;
	}

}
