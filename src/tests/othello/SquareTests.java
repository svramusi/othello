package tests.othello;

import edu.drexel.cs.ai.othello.Square;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SquareTests {
	
	protected Square square;

	@Before
	public void setUp() throws Exception {
		square = new Square(0, 0);
	}

	@Test
	public void testStringConstructor() {
		Square square = new Square("a1");
		assertEquals(1, square.getRow());
		assertEquals(0, square.getCol());
	}

	@Test
	public void testIntConstructor() {
		Square square = new Square(0, 0);
		assertEquals(0, square.getRow());
		assertEquals(0, square.getCol());
	}

	@Test
	public void testToString() {
		assertEquals("a0", square.toString());
	}

	@Test
	public void testEqualObjects() {
		Square square2 = new Square(0, 0);
		assertTrue(square.equals(square2));
	}

	@Test
	public void testUnequalObjects() {
		Square square2 = new Square(1, 0);
		assertFalse(square.equals(square2));

		square2 = new Square(0, 1);
		assertFalse(square.equals(square2));
	}

	@Test
	public void testHashCode() {
		assertEquals(0, square.hashCode());
		assertEquals(1, new Square(0, 1).hashCode());
		assertEquals(10, new Square(1, 1).hashCode());
	}
}
