package de.tudarmstadt.informatik.fop.breakout.managers;

import java.util.Comparator;

/**
 * Compares two players by score (and time).
 * @author Lukas
 *
 */
public class PlayerComparator implements Comparator<Player> {
	
	@Override
	public int compare(Player p1, Player p2) {
		return p1.compareTo(p2);
	}

}
