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
			children.add(new MiniMaxNode(state, state.getScore(state.getCurrentPlayer())));
		}
		
		tree.SetChildren(node, children);
		
		GameState result = (GameState)tree.AlphaBetaSearch().GetObject();
		
		log("result: " + result.toString());
			
		return result.getPreviousMove();
	}

	public Square getMove(GameState currentState, Date deadline) {
		//START A NEW TREE EVERY TIME
		tree = new MiniMax(new MiniMaxNode(currentState, currentState.getScore(currentState.getCurrentPlayer())));

		Square nextMove = null;
		MiniMaxNode node = null;
		
		//NEED TO FIND A BETTER WAY TO DO THIS!
		while(this.getMillisUntilDeadline() > 300)
		{
			node = tree.GetEmptyParents().get(0);
			
			log("finding successors of: " + node.toString());
			
			AbstractSet<GameState> successors = ((GameState)node.GetObject()).getSuccessors();
			List<MiniMaxNode> children = new ArrayList<MiniMaxNode>();
			
			for(GameState state : successors)
			{
				children.add(new MiniMaxNode(state, state.getScore(state.getCurrentPlayer())));
			}
			
			tree.SetChildren(node, children);
			
			nextMove = ((GameState)tree.AlphaBetaSearch().GetObject()).getPreviousMove();
			log("setting current best move: " + nextMove.toString());
			log("time left: " + this.getMillisUntilDeadline());
			this.registerCurrentBestMove(nextMove);
		}
		
		/* registerCurrentBestMove(...) can be called multiple times to reset the current best
		 * move before returning from this function. */
		
		/* return the move that we have chosen */
		log("Example -- player is moving to " + nextMove + "...");
		return nextMove;
	}

}