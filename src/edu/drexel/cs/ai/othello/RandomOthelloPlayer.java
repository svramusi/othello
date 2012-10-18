package edu.drexel.cs.ai.othello;

import java.util.Date;
import java.util.List;

/**
 * An othello-playing agent that plays at random.
 *
 * @author <a href="http://www.sultanik.com" target="_blank">Evan A. Sultanik</a>
 */
public class RandomOthelloPlayer extends OthelloPlayer {
	/**
	 * Creates a new othello-playing agent that plays at random.
	 */
	public RandomOthelloPlayer(String name) {
		super(name);
	}

	/**
	 * Returns a random, valid move from <code>currentState</code>.
	 */
	public Square getMove(GameState currentState, Date deadline) {
		List<Square> moves = currentState.getValidMoves();
		
		int next = currentState.getRandom().nextInt(moves.size());
		
		log("Randomly moving to " + moves.get(next).toString() + "...");
		return moves.get(next);
	}
}
