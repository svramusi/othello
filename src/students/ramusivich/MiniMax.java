package students.ramusivich;

import java.util.List;
import java.lang.reflect.*;

import tests.othello.MiniMaxTests;

public class MiniMax {
	
	MiniMaxNode head;
	
	/*
	 * function integer minimax(node, depth)
    		if node is a terminal node or depth <= 0:
        		return the heuristic value of node
    		α = -∞
    		for child in node:                       # evaluation is identical for both players 
        		α = max(α, -minimax(child, depth-1))
    		return α
	 */
	
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
