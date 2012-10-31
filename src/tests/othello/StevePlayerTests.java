package tests.othello;

import static org.junit.Assert.*;

import students.ramusivich.StevesOthelloPlayer;
import edu.drexel.cs.ai.othello.GameState;
import edu.drexel.cs.ai.othello.Square;

import org.junit.Before;
import org.junit.Test;

public class StevePlayerTests {

	private StevesOthelloPlayer sp;
	private GameState gameState;
	
	@Before
	public void setUp() throws Exception {
		sp = new StevesOthelloPlayer("unitTestPlayer");
		gameState = new GameState();
	}
	
	@Test
	public void testIsCorner() {
		assertTrue(sp.IsCorner(new Square(0,0)));
		assertTrue(sp.IsCorner(new Square(0,7)));
		assertTrue(sp.IsCorner(new Square(7,0)));
		assertTrue(sp.IsCorner(new Square(7,7)));

		assertFalse(sp.IsCorner(new Square(4,4)));
		assertFalse(sp.IsCorner(new Square(0,1)));
		assertFalse(sp.IsCorner(new Square(7,1)));
	}
	
	@Test
	public void testIsNextToCorner() {
		assertTrue(sp.isNextToCorner(new Square(0,1)));
		assertTrue(sp.isNextToCorner(new Square(1,0)));
		assertTrue(sp.isNextToCorner(new Square(1,1)));

		assertTrue(sp.isNextToCorner(new Square(0,6)));
		assertTrue(sp.isNextToCorner(new Square(1,6)));
		assertTrue(sp.isNextToCorner(new Square(1,7)));
		
		assertTrue(sp.isNextToCorner(new Square(6,0)));
		assertTrue(sp.isNextToCorner(new Square(6,1)));
		assertTrue(sp.isNextToCorner(new Square(7,1)));

		assertTrue(sp.isNextToCorner(new Square(6,7)));
		assertTrue(sp.isNextToCorner(new Square(6,6)));
		assertTrue(sp.isNextToCorner(new Square(7,6)));
	}

	@Test
	public void testIsNextToCornerEmpty__AllEmpty() {
		assertTrue(sp.isCornerEmpty(gameState, new Square(0,1)));
		assertTrue(sp.isCornerEmpty(gameState, new Square(1,0)));
		assertTrue(sp.isCornerEmpty(gameState, new Square(1,1)));

		assertTrue(sp.isCornerEmpty(gameState,new Square(0,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(1,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(1,7)));
		
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,0)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,1)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(7,1)));
		
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,7)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(7,6)));
	}

	@Test
	public void testIsNextToCornerEmpty__TopLeftTaken() {
		gameState = gameState.forceMove(new Square(0,0));
		
		assertFalse(sp.isCornerEmpty(gameState, new Square(0,1)));
		assertFalse(sp.isCornerEmpty(gameState, new Square(1,0)));
		assertFalse(sp.isCornerEmpty(gameState, new Square(1,1)));

		assertTrue(sp.isCornerEmpty(gameState,new Square(0,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(1,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(1,7)));
		
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,0)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,1)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(7,1)));
		
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,7)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(7,6)));
	}

	@Test
	public void testIsNextToCornerEmpty__TopRightTaken() {
		gameState = gameState.forceMove(new Square(0,7));
		
		assertTrue(sp.isCornerEmpty(gameState, new Square(0,1)));
		assertTrue(sp.isCornerEmpty(gameState, new Square(1,0)));
		assertTrue(sp.isCornerEmpty(gameState, new Square(1,1)));

		assertFalse(sp.isCornerEmpty(gameState,new Square(0,6)));
		assertFalse(sp.isCornerEmpty(gameState,new Square(1,6)));
		assertFalse(sp.isCornerEmpty(gameState,new Square(1,7)));
		
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,0)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,1)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(7,1)));
		
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,7)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(7,6)));
	}

	@Test
	public void testIsNextToCornerEmpty__BottomLeftTaken() {
		gameState = gameState.forceMove(new Square(7,0));
		
		assertTrue(sp.isCornerEmpty(gameState, new Square(0,1)));
		assertTrue(sp.isCornerEmpty(gameState, new Square(1,0)));
		assertTrue(sp.isCornerEmpty(gameState, new Square(1,1)));

		assertTrue(sp.isCornerEmpty(gameState,new Square(0,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(1,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(1,7)));
		
		assertFalse(sp.isCornerEmpty(gameState,new Square(6,0)));
		assertFalse(sp.isCornerEmpty(gameState,new Square(6,1)));
		assertFalse(sp.isCornerEmpty(gameState,new Square(7,1)));
		
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,7)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(7,6)));
	}

	@Test
	public void testIsNextToCornerEmpty__BottomRightTaken() {
		gameState = gameState.forceMove(new Square(7,7));
		
		assertTrue(sp.isCornerEmpty(gameState, new Square(0,1)));
		assertTrue(sp.isCornerEmpty(gameState, new Square(1,0)));
		assertTrue(sp.isCornerEmpty(gameState, new Square(1,1)));

		assertTrue(sp.isCornerEmpty(gameState,new Square(0,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(1,6)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(1,7)));
		
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,0)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(6,1)));
		assertTrue(sp.isCornerEmpty(gameState,new Square(7,1)));
		
		assertFalse(sp.isCornerEmpty(gameState,new Square(6,7)));
		assertFalse(sp.isCornerEmpty(gameState,new Square(6,6)));
		assertFalse(sp.isCornerEmpty(gameState,new Square(7,6)));
	}

	@Test
	public void testNextToCornerPenalty__TopLeft() {
		assertTrue(sp.isNextToCornerPenalty(gameState, new Square(0, 1)));
		assertTrue(sp.isNextToCornerPenalty(gameState, new Square(1,0)));
		assertTrue(sp.isNextToCornerPenalty(gameState, new Square(1,1)));
		
		gameState = gameState.forceMove(new Square(0,0));
		assertFalse(sp.isNextToCornerPenalty(gameState, new Square(0, 1)));
		assertFalse(sp.isNextToCornerPenalty(gameState, new Square(1,0)));
		assertFalse(sp.isNextToCornerPenalty(gameState, new Square(1,1)));
	}

	@Test
	public void testNextToCornerPenalty__TopRight() {
		assertTrue(sp.isNextToCornerPenalty(gameState,new Square(0,6)));
		assertTrue(sp.isNextToCornerPenalty(gameState,new Square(1,6)));
		assertTrue(sp.isNextToCornerPenalty(gameState,new Square(1,7)));
		
		gameState = gameState.forceMove(new Square(0,7));
		assertFalse(sp.isNextToCornerPenalty(gameState,new Square(0,6)));
		assertFalse(sp.isNextToCornerPenalty(gameState,new Square(1,6)));
		assertFalse(sp.isNextToCornerPenalty(gameState,new Square(1,7)));
	}

	@Test
	public void testNextToCornerPenalty__BottomLeft() {
		assertTrue(sp.isNextToCornerPenalty(gameState,new Square(6,0)));
		assertTrue(sp.isNextToCornerPenalty(gameState,new Square(6,1)));
		assertTrue(sp.isNextToCornerPenalty(gameState,new Square(7,1)));
		
		gameState = gameState.forceMove(new Square(7, 0));
		assertFalse(sp.isNextToCornerPenalty(gameState,new Square(6,0)));
		assertFalse(sp.isNextToCornerPenalty(gameState,new Square(6,1)));
		assertFalse(sp.isNextToCornerPenalty(gameState,new Square(7,1)));
	}

	@Test
	public void testNextToCornerPenalty__BottomRight() {
		assertTrue(sp.isNextToCornerPenalty(gameState,new Square(6,7)));
		assertTrue(sp.isNextToCornerPenalty(gameState,new Square(6,6)));
		assertTrue(sp.isNextToCornerPenalty(gameState,new Square(7,6)));
		
		gameState = gameState.forceMove(new Square(7, 7));
		assertFalse(sp.isNextToCornerPenalty(gameState,new Square(6,7)));
		assertFalse(sp.isNextToCornerPenalty(gameState,new Square(6,6)));
		assertFalse(sp.isNextToCornerPenalty(gameState,new Square(7,6)));
	}
}