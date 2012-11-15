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
	
	@Test
	public void testInitialFrontiers() {
		assertEquals(2, sp.getFrontiers(gameState));
	}
	
	@Test
	public void testThreeFrontiers() {
		gameState = gameState.forceMove(new Square(0, 0));
		assertEquals(3, sp.getFrontiers(gameState));
	}
	
	@Test
	public void testIsFrontier() {
		assertTrue(sp.isFrontier(gameState, 3, 3));
		
		for(int i = 0; i < 8; i++) {
			gameState = gameState.forceMove(new Square(0, i));
		}
		assertTrue(sp.isFrontier(gameState, 0, 3));
		
		for(int i = 0; i < 8; i++) {
			gameState = gameState.forceMove(new Square(1, i));
		}
		assertFalse(sp.isFrontier(gameState, 0, 3));
	}
	
	@Test
	public void testIsUpperEmpty() {
		assertTrue(sp.isUpperEmpty(gameState, 3, 3));
		assertFalse(sp.isUpperEmpty(gameState, 5, 3));
		
		assertFalse(sp.isUpperEmpty(gameState, 0, 0));
		assertFalse(sp.isUpperEmpty(gameState, 0, 6));
	}

	@Test
	public void testIsRightEmpty() {
		assertTrue(sp.isRightEmpty(gameState, 3, 4));
		assertFalse(sp.isRightEmpty(gameState, 3, 2));
		
		assertFalse(sp.isRightEmpty(gameState, 0, 7));
		assertFalse(sp.isRightEmpty(gameState, 5, 7));
	}

	@Test
	public void testIsLeftEmpty() {
		assertTrue(sp.isLeftEmpty(gameState, 4, 3));
		assertFalse(sp.isLeftEmpty(gameState, 3, 5));
		
		assertFalse(sp.isLeftEmpty(gameState, 0, 0));
		assertFalse(sp.isLeftEmpty(gameState, 5, 0));
	}

	@Test
	public void testIsLowerEmpty() {
		assertTrue(sp.isLowerEmpty(gameState, 4, 4));
		assertFalse(sp.isLowerEmpty(gameState, 2, 3));
		
		assertFalse(sp.isLowerEmpty(gameState, 7, 0));
		assertFalse(sp.isLowerEmpty(gameState, 7, 5));
	}
}