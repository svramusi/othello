package tests.othello;

import static org.junit.Assert.*;

import students.ramusivich.StevesOthelloPlayer;
import edu.drexel.cs.ai.othello.Square;

import org.junit.Before;
import org.junit.Test;

public class StevePlayerTests {

	private StevesOthelloPlayer sp;
	
	@Before
	public void setUp() throws Exception {
		sp = new StevesOthelloPlayer("unitTestPlayer");
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
		assertTrue(sp.IsNextToCorner(new Square(0,1)));
		assertTrue(sp.IsNextToCorner(new Square(1,0)));
		assertTrue(sp.IsNextToCorner(new Square(1,1)));

		assertTrue(sp.IsNextToCorner(new Square(0,6)));
		assertTrue(sp.IsNextToCorner(new Square(1,6)));
		assertTrue(sp.IsNextToCorner(new Square(1,7)));
		
		assertTrue(sp.IsNextToCorner(new Square(6,0)));
		assertTrue(sp.IsNextToCorner(new Square(6,1)));
		assertTrue(sp.IsNextToCorner(new Square(7,1)));

		assertTrue(sp.IsNextToCorner(new Square(6,7)));
		assertTrue(sp.IsNextToCorner(new Square(6,6)));
		assertTrue(sp.IsNextToCorner(new Square(7,6)));
	}
	

}
