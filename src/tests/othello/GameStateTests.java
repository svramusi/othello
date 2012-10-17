package tests.othello;

import edu.drexel.cs.ai.othello.GameState;
import edu.drexel.cs.ai.othello.Square;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
	public void testEquals() {
		assertTrue(gameState.equals(gameState));
		assertTrue(gameState.equals(new GameState(GameState.Player.PLAYER1)));
		
		assertFalse(gameState.equals(new GameState(GameState.Player.PLAYER2)));
	}

}
