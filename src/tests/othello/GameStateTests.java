package tests.othello;

import edu.drexel.cs.ai.othello.GameState;

import edu.drexel.cs.ai.othello.Square;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class GameStateTests extends GameState {


	protected GameState gameState;

	@Before
	public void setUp() throws Exception {
		gameState = new GameState(GameState.Player.PLAYER1);
	}
	
	@Test
	public void testPlayer1StartsTheGame() {
		assertEquals(GameState.Player.PLAYER1, gameState.getCurrentPlayer());
	}
	
	@Test
	public void testGetOpponent() {
		//THIS SEEMS STUPID
		assertEquals(GameState.Player.PLAYER2, gameState.getOpponent(GameState.Player.PLAYER1));
	}
	
	@Test
	public void testGetSquare() {
		assertEquals(GameState.Player.EMPTY, gameState.getSquare(0, 0));

		assertEquals(GameState.Player.PLAYER2, gameState.getSquare(3, 3));
		assertEquals(GameState.Player.PLAYER2, gameState.getSquare(4, 4));

		assertEquals(GameState.Player.PLAYER1, gameState.getSquare(3, 4));
		assertEquals(GameState.Player.PLAYER1, gameState.getSquare(4, 3));
	}

	@Test
	public void testGetPlayer() {
		assertEquals(GameState.Player.EMPTY, gameState.getPlayer(new Square(0, 0)));
		assertEquals(GameState.Player.PLAYER2, gameState.getPlayer(new Square(3, 3)));
		assertEquals(GameState.Player.PLAYER1, gameState.getPlayer( new Square(3, 4)));
	}

	@Test
	public void testGetStatusPlaying() {
		assertEquals(GameState.GameStatus.PLAYING, gameState.getStatus());
	}

	@Test
	public void testGetWinnerPlaying() {
		assertNull(gameState.getWinner());
	}

	@Test
	public void testClone() {
		assertTrue(gameState.equals((GameState)gameState.clone()));
	}

	@Test
	public void testEquals() {
		assertTrue(gameState.equals(gameState));
		assertTrue(gameState.equals(new GameState(GameState.Player.PLAYER1)));
		
		assertFalse(gameState.equals(new GameState(GameState.Player.PLAYER2)));
	}

	@Test
	public void testWouldFlipUpInitialState() {
		Square flippedSquare = gameState.wouldFlip(new Square("5e"), GameState.Player.PLAYER1, GameState.Direction.UP);
		assertTrue(flippedSquare.equals(new Square("3e")));

		flippedSquare = gameState.wouldFlip(new Square("3c"), GameState.Player.PLAYER1, GameState.Direction.RIGHT);
		assertTrue(flippedSquare.equals(new Square("3e")));
	}

	@Test
	public void testIsLegalMoveInitialState() {
		assertTrue(gameState.isLegalMove(new Square("3c"), GameState.Player.PLAYER1));
		assertFalse(gameState.isLegalMove(new Square("4c"), GameState.Player.PLAYER1));
	}
	
	@Test
	public void testGetValidMovesInitialState() {
		List<Square> validMoves = gameState.getValidMoves();
		
		assertEquals(4, validMoves.size());

		assertTrue(validMoves.contains((Square) new Square("5e")));
		assertTrue(validMoves.contains((Square) new Square("4f")));
		assertTrue(validMoves.contains((Square) new Square("3c")));
		assertTrue(validMoves.contains((Square) new Square("2d")));
	}

	@Test
	public void testGetScoreInitialState() {
		assertEquals(2, gameState.getScore(GameState.Player.PLAYER1));
	}
	
	@Test
	public void testGetSuccessorsAndApplyMoveInitialState() {
		List<GameState> successors = gameState.getSuccessors();
		
		assertEquals(4, successors.size());

		assertTrue(successors.contains(gameState.applyMove(new Square("5e"), true)));
		assertTrue(successors.contains(gameState.applyMove(new Square("4f"), true)));
		assertTrue(successors.contains(gameState.applyMove(new Square("3c"), true)));
		assertTrue(successors.contains(gameState.applyMove(new Square("2d"), true)));
	}

	@Test
	public void testGetPreviousState() {
		assertNull(gameState.getPreviousState());
		
		GameState modifiedState = gameState.applyMove(new Square("5e"), true);
		assertTrue(modifiedState.getPreviousState().equals(gameState));
	}

	@Test
	public void testGetPreviousMove() {
		assertNull(gameState.getPreviousMove());
		
		Square move = new Square("5e");
		GameState modifiedState = gameState.applyMove(move, true);
		assertTrue(modifiedState.getPreviousMove().equals(move));
	}

}
