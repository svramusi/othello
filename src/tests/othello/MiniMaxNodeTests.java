package tests.othello;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import students.ramusivich.MiniMaxNode;


public class MiniMaxNodeTests {
	
	MiniMaxNode node;

	@Before
	public void setUp() throws Exception {
		node = new MiniMaxNode("object name", 1);
	}
	
	@Test
	public void testGetObject() {
		assertEquals("object name", (String)node.GetObject());
	}

	@Test
	public void testGetValue() {
		assertEquals(1, (int)node.GetValue());
	}

	@Test
	public void testEquals() {
		assertTrue(node.equals(node));
		assertTrue(node.equals(new MiniMaxNode("object name", 1)));
		assertTrue(node.equals(new MiniMaxNode("object name", 2)));
		assertFalse(node.equals(new MiniMaxNode("object name1", 1)));
	}

	@Test
	public void testGetParentNoParent() {
		assertNull(node.GetParent());
	}

	@Test
	public void testGetParentWithParent() {
		MiniMaxNode child = new MiniMaxNode("child", 2);
		node.SetChild(child);
		assertEquals(node, child.GetParent());
	}

	@Test
	public void testGetChildrenNoChildren() {
		assertEquals(0, node.GetChildren().size());
	}

	@Test
	public void testGetChildren() {
		MiniMaxNode childNode1 = new MiniMaxNode("child1", 1);
		MiniMaxNode childNode2 = new MiniMaxNode("child2", 2);
		node.SetChild(childNode1);
		node.SetChild(childNode2);
		
		assertEquals(2, node.GetChildren().size());
		
		List<MiniMaxNode> children = node.GetChildren();
		assertTrue(children.contains(childNode1));
		assertTrue(children.contains(childNode2));
		
		//Make sure child node has correct parent
		assertEquals(node, childNode1.GetParent());
		assertEquals(node, childNode2.GetParent());
	}


	@Test
	public void testSetChildren() {
		List<MiniMaxNode> children = new ArrayList<MiniMaxNode>();
		MiniMaxNode childNode1 = new MiniMaxNode("child1", 1);
		MiniMaxNode childNode2 = new MiniMaxNode("child2", 2);

		children.add(childNode1);
		children.add(childNode2);

		node.SetChildren(children);
		
		assertEquals(2, node.GetChildren().size());
		
		children = node.GetChildren();
		assertTrue(children.contains(childNode1));
		assertTrue(children.contains(childNode2));
		
		//Make sure child node has correct parent
		assertEquals(node, childNode1.GetParent());
		assertEquals(node, childNode2.GetParent());
	}
}
