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

	private static double longestMiniMaxRun;
	private static double averageGetSuccessors;
	private static double totalGetSuccessors;
	private static double iterationsGetSuccessors;
	
	public StevesOthelloPlayer(String name)
	{
		super(name);
		tree = null;
		longestMiniMaxRun = -1;
		averageGetSuccessors = 0;
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
		
		Square nextMove = findNextBest();
		log("next best move: " + nextMove);
		
		while(isEnoughTimeToGoAgain())
		{
			getNextRowOfSuccessors();
			nextMove = findNextBest();
		}
		
		/* return the move that we have chosen */
		log("Steve's agent is moving to " + nextMove + "...");
		log("time wasted: " + this.getMillisUntilDeadline());
		return nextMove;
	}

	private Square findNextBest() {
		long startTime = new Date().getTime();
		Square nextMove = ((GameState)tree.AlphaBetaSearch().GetObject()).getPreviousMove();
		long endTime = new Date().getTime();
		long difference = endTime - startTime;
		
		log("longest mini max: " + longestMiniMaxRun);
		
		if(difference > longestMiniMaxRun)
			longestMiniMaxRun = difference;
		
		this.registerCurrentBestMove(nextMove);
		return nextMove;
	}
	
	private boolean isEnoughTimeToGoAgain()
	{
		//If there is less time than the time required to get the successors
		//and twice the time to perform minimax, then return

		log("average successors: " + averageGetSuccessors);
		log("longest minimax: " + longestMiniMaxRun);
		
		if(this.getMillisUntilDeadline() > averageGetSuccessors + (2 * longestMiniMaxRun))
			return true;
		else
			return false;
	}

	private void getNextRowOfSuccessors() {
		List<MiniMaxNode> emptyParents = tree.GetEmptyParents();
		
		MiniMaxNode node = null;
		int initialEmptyParentsSize = emptyParents.size();
		
		log("finding children for # of empty parents: " + initialEmptyParentsSize);
		
		for(int i = 0; i < initialEmptyParentsSize; i++)
		{
			if(this.getMillisUntilDeadline() < averageGetSuccessors)
				return;
			
			long startTime = new Date().getTime();
			
			node = emptyParents.get(i);
			
			AbstractSet<GameState> successors = ((GameState)node.GetObject()).getSuccessors();
			List<MiniMaxNode> children = new ArrayList<MiniMaxNode>();
			
			for(GameState state : successors)
			{
				children.add(new MiniMaxNode(state, heuristic(state)));
			}
			
			tree.SetChildren(node, children);

			long endTime = new Date().getTime();
			
			//Only count it if its for a non-trivial amount of time
			//Yes, anything greater than 100ms is a non-trivial amount of time
			//Think about how many ms you wasted reading this...
			if(endTime - startTime > 100)
			{
				totalGetSuccessors += endTime - startTime;
				iterationsGetSuccessors++;
				log("time it took to get successors: " + Long.toString(endTime - startTime));
				log("total: " + totalGetSuccessors);
				log("iteratsion: " + iterationsGetSuccessors);
				log("count: " + tree.Count());
				averageGetSuccessors = totalGetSuccessors/iterationsGetSuccessors;
			}
		}
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