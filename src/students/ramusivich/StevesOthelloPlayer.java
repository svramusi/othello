package students.ramusivich;

import edu.drexel.cs.ai.othello.*;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import java.io.*;

public class StevesOthelloPlayer extends OthelloPlayer {
	
	private MiniMax tree;

	private static final double CORNER_SCORE = 100;
	private static final double NEXT_TO_CORNER_SCORE = -500;
	
	public StevesOthelloPlayer(String name)
	{
		super(name);
		tree = null;
	}
	
	//http://radagast.se/othello/howto.html
	private double heuristic(GameState state)
	{
		double score = 0;
		score += state.getScore(state.getCurrentPlayer());
		
		Square previousMove = state.getPreviousMove();
		
		//Favor corner squares
		if(IsCorner(previousMove))
			score += CORNER_SCORE;
		
		//Avoid giving your opponent the corner square
		if(IsNextToCorner(previousMove))
			score += NEXT_TO_CORNER_SCORE;
		
		return score;
	}

	public Square getMove(GameState currentState, Date deadline) {
		
		if(tree == null)
			tree = new MiniMax(new MiniMaxNode(currentState, currentState.getScore(currentState.getCurrentPlayer())));
		else
		{
			try{
				//BufferedWriter out = new BufferedWriter(new FileWriter("output.txt", true));
				
				//out.write("\n---------------------------------------------------------------");
				//out.write("\ncount before setting a new head: " + tree.Count());
				
				try{
					tree.SetNewHead(new MiniMaxNode(currentState, 0));
				}
				catch(Exception e)
				{
					tree = new MiniMax(new MiniMaxNode(currentState, currentState.getScore(currentState.getCurrentPlayer())));
					//out.write("\n\nFailed to update head to: " + currentState.toString());
				}

				//out.write("\ncount after setting a new head: " + tree.Count());
				
				//out.close();
			} catch (Exception e)
			{
				System.out.println("CAUGHT ERROR: " + e.getMessage());
			}
		}

		Square nextMove = null;
		MiniMaxNode node = null;
		
		//NEED TO FIND A BETTER WAY TO DO THIS!
		while(this.getMillisUntilDeadline() > 500 && tree.GetEmptyParents().size() > 0)
		{
			node = tree.GetEmptyParents().get(0);
			
			AbstractSet<GameState> successors = ((GameState)node.GetObject()).getSuccessors();
			List<MiniMaxNode> children = new ArrayList<MiniMaxNode>();
			
			for(GameState state : successors)
			{
				children.add(new MiniMaxNode(state, heuristic(state)));
			}
			
			tree.SetChildren(node, children);
			
			nextMove = ((GameState)tree.AlphaBetaSearch().GetObject()).getPreviousMove();
			//log("setting current best move: " + nextMove.toString());
			//log("time left: " + this.getMillisUntilDeadline());
			this.registerCurrentBestMove(nextMove);
		}
		
		/* return the move that we have chosen */
		log("Steve's player is moving to " + nextMove);
		return nextMove;
	}
	
	public boolean IsCorner(Square s)
	{
		int row = s.getRow();
		int col = s.getCol();
		
		if(row == 0)
		{
			if(col == 0)
				return true;
			else if(col == 7)
				return true;
			else
				return false;
		}

		else if(row == 7)
		{
			if(col == 0)
				return true;
			else if(col == 7)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public boolean IsNextToCorner(Square s)
	{
		int row = s.getRow();
		int col = s.getCol();

		if(row == 0)
		{
			if(col == 1 || col == 6)
				return true;
			else
				return false;
		}
		else if(row == 1)
		{
			if(col == 0 || col == 1 || col == 6 || col ==7)
				return true;
			else
				return false;
		}
		else if(row == 6)
		{
			if(col == 0 || col == 1 || col == 6 || col == 7)
				return true;
			else
				return false;
		}
		else if(row == 7)
		{
			if(col == 1 || col == 6)
				return true;
			else
				return false;
		}
		else
		{
			return false;
		}
	}
}
