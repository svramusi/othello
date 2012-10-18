package students.ramusivich;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.reflect.*;

import tests.othello.MiniMaxTests;

public class MiniMax {
	
	MiniMaxNode head;
	
	public MiniMax(MiniMaxNode head)
	{
		this.head = head;
	}
	
	public MiniMaxNode GetHead()
	{
		return head;
	}
	
	public void SetChildren(MiniMaxNode node, List<MiniMaxNode> children)
	{
		node.SetChildren(children);
	}
	
	public List<MiniMaxNode> GetChildren(MiniMaxNode node)
	{
		return node.GetChildren();
	}
	
	public MiniMaxNode Find(MiniMaxNode nodeToFind)
	{
		Queue<MiniMaxNode> queue = new LinkedList<MiniMaxNode>();
		queue.add(head);
		
		return Find(nodeToFind, queue);
	}
	
	public MiniMaxNode Find(MiniMaxNode nodeToFind, Queue<MiniMaxNode> queue)
	{
		MiniMaxNode head = queue.poll();
		
		if(head == null)
			return null;
		else if (head.equals(nodeToFind))
			return head;
		else
		{
			List<MiniMaxNode> children = head.GetChildren();
			for(MiniMaxNode child : children)
				queue.add(child);
			
			return Find(nodeToFind, queue);
		}
	}
	
	public MiniMaxNode MakeBestDecision(MiniMaxNode node)
	{
		//Use reflection to determine if there is a SetVisited method
		//This is necessary for the unit testing to test the alpha beta tree
		try{
			Method method = node.getClass().getMethod("SetVisited", (Class<MiniMaxTests.MiniMaxTestNode>[])null);
			method.invoke(node, (Object[])null);
		} catch (NoSuchMethodException e) {
			//System.out.println("Caught no such method exception on " + node.toString());
		} catch (IllegalAccessException e) {
			//System.out.println("Caught illegal access exception on " + node.toString());
		} catch  (InvocationTargetException e) {
			//System.out.println("Caught invocation target exception on " + node.toString());
		}
		
		if(node.GetChildren().size() == 0)
			return node;
		
		MiniMaxNode bestNode = new MiniMaxNode("", Double.NEGATIVE_INFINITY);
		double alpha = Double.NEGATIVE_INFINITY;
		
		for(MiniMaxNode child : node.GetChildren())
		{
			alpha = Math.max(alpha, -(MakeBestDecision(child).GetValue()));
			
			if(alpha != bestNode.GetValue())
				bestNode = child;
		}
		
		return bestNode;
	}

}
