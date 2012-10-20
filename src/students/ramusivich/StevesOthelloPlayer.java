package students.ramusivich;

import edu.drexel.cs.ai.othello.*;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class StevesOthelloPlayer extends OthelloPlayer {
	
	private MiniMax tree;
	
	public StevesOthelloPlayer(String name)
	{
		super(name);
		tree = null;
	}
	
	private Square StevesDecisionMaker(GameState currentState)
	{
		MiniMaxNode node = new MiniMaxNode(currentState, currentState.getScore(currentState.getCurrentPlayer()));
		
		if(tree == null)
			tree = new MiniMax(node);
		
		AbstractSet<GameState> successors = currentState.getSuccessors();
		List<MiniMaxNode> children = new ArrayList<MiniMaxNode>();
		
		for(GameState state : successors)
		{
			log("adding: " + state.toString());
			children.add(new MiniMaxNode(state, state.getScore(state.getCurrentPlayer())));
		}
		
		tree.SetChildren(node, children);
		
		GameState result = (GameState)tree.AlphaBetaSearch().GetObject();
		
		log("result: " + result.toString());
			
		return result.getPreviousMove();
	}

	public Square getMove(GameState currentState, Date deadline) {
		
		
		Square square = StevesDecisionMaker(currentState);
		
		/* register this as our current best move; if there is a deadline and we don't reach it,
		 * then registering the move will make sure that that is the move we take.
		 * If we reach the deadline and we neither registered a move nor returned from this
		 * function, then a move will be chosen for us at random. */
		this.registerCurrentBestMove(square);
		
		if(deadline != null)
			log("I have " + this.getMillisUntilDeadline() + "ms remaining until the deadline.");
		
		
		/* registerCurrentBestMove(...) can be called multiple times to reset the current best
		 * move before returning from this function. */
		
		/* return the move that we have chosen */
		log("Example -- player is moving to " + square + "...");
		return square;
	}

}