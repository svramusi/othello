package tests.othello;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import students.ramusivich.MiniMaxNode;
import students.ramusivich.MiniMax;


public class MiniMaxTests {
	
	
	/*
	 * MAX	MIN			MAX
	 * 								child3a, 3
	 * 					child2a, 5
	 * 								child3b, 5

	 * 		child1a, 5

	 *								child3c, 9
	 * 					child2b, 9
	 * 								child3d, 2 -- NV
	 * 
	 * 
	 * 								child3e, 7
	 * 					child2c, 7
	 * 								child3f, 1 -- NV
	 * 
	 * head, 5
	 * 
	 * 
	 * 								child3g, 6
	 * 					child2d, 8
	 * 								child3h, 8
	 * 
	 * 		child1b, 4
	 * 
	 * 								child3i, 2
	 * 					child2e, 4
	 * 								child3j, 4
	 * 
	 * 
	 * 								child3k, 9 -- NV
	 * 					child2f, 9
	 * 								child3l, 1 -- NV
	 */
	
	
	MiniMax tree;
	
	public class MiniMaxTestNode extends MiniMaxNode
	{
		private boolean visited;
		
		public MiniMaxTestNode(Object o, double value)
		{
			super(o, value);
			this.visited = false;
		}
		
		public void SetVisited()
		{
			this.visited = true;
		}
		
		public boolean WasVisited()
		{
			return visited;
		}
	}

	@Before
	public void setUp() throws Exception {
		tree = new MiniMax(new MiniMaxTestNode("head", 0));

		MiniMaxNode child1 = new MiniMaxTestNode("child1a", 0);
		MiniMaxNode child2 = new MiniMaxTestNode("child1b", 0);
		
		List<MiniMaxNode> children = new ArrayList<MiniMaxNode>();
		children.add(child1);
		children.add(child2);
		
		tree.SetChildren(tree.GetHead(), children);

		children.clear();
		MiniMaxNode child2a = new MiniMaxTestNode("child2a", 0);
		MiniMaxNode child2b = new MiniMaxTestNode("child2b", 0);
		MiniMaxNode child2c = new MiniMaxTestNode("child2c", 0);
		children.add(child2a);
		children.add(child2b);
		children.add(child2c);
		tree.SetChildren(child1, children);
		

		children.clear();
		MiniMaxNode child2d = new MiniMaxTestNode("child2d", 0);
		MiniMaxNode child2e = new MiniMaxTestNode("child2e", 0);
		MiniMaxNode child2f = new MiniMaxTestNode("child2f", 0);
		children.add(child2d);
		children.add(child2e);
		children.add(child2f);
		tree.SetChildren(child2, children);

		children.clear();
		children.add(new MiniMaxTestNode("child3a", 3));
		children.add(new MiniMaxTestNode("child3b", 5));
		tree.SetChildren(child2a, children);
		

		children.add(new MiniMaxTestNode("child3c", 9));
		children.add(new MiniMaxTestNode("child3d", 2));
		tree.SetChildren(child2b, children);

		children.add(new MiniMaxTestNode("child3e", 7));
		children.add(new MiniMaxTestNode("child3f", 1));
		tree.SetChildren(child2c, children);

		children.add(new MiniMaxTestNode("child3g", 6));
		children.add(new MiniMaxTestNode("child3h", 8));
		tree.SetChildren(child2d, children);

		children.add(new MiniMaxTestNode("child3i", 2));
		children.add(new MiniMaxTestNode("child3j", 4));
		tree.SetChildren(child2e, children);

		children.add(new MiniMaxTestNode("child3k", 9));
		children.add(new MiniMaxTestNode("child3l", 1));
		tree.SetChildren(child2f, children);
	}
	
	@Test
	public void testDefaultTree() {
		assertEquals(tree.GetHead(), new MiniMaxNode("head", 0));
	}
	
	@Test
	public void testSetOneRowOfChildren() {
		assertEquals(2, tree.GetChildren(tree.GetHead()).size());
		assertEquals(tree.GetHead(), tree.GetChildren(tree.GetHead()).get(0).GetParent());
	}

	@Test
	public void testSetTwoRowsOfChildren() {
		List<MiniMaxNode> children = tree.GetChildren(tree.GetHead());
		MiniMaxNode child1 = children.get(0);

		children = tree.GetChildren(child1);
		MiniMaxNode child2 = children.get(0);
		
		assertEquals(3, tree.GetChildren(child1).size());
		assertEquals(tree.GetHead(), child1.GetParent());
		assertEquals(child1, child2.GetParent());
		assertEquals(child2, tree.GetChildren(child1).get(0));
	}
	
	@Test
	public void testMakeBestDecision() {
		MiniMaxNode expectedBestDecision = new MiniMaxNode("child1a", 0);
		
		assertFalse(((MiniMaxTestNode)tree.GetHead()).WasVisited());
		
		MiniMaxNode result = tree.MakeBestDecision(tree.GetHead());
		
		assertEquals(expectedBestDecision, result);
		assertTrue(((MiniMaxTestNode)tree.GetHead()).WasVisited());
		assertTrue(((MiniMaxTestNode)result).WasVisited());
	}
}
