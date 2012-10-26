package students.ramusivich;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.reflect.*;

public class MiniMax {
	
	MiniMaxNode head;
	List<MiniMaxNode> emptyParents;
	
	public MiniMax(MiniMaxNode head)
	{
		this.head = head;
		emptyParents = new ArrayList<MiniMaxNode>();
		emptyParents.add(head);
	}
	
	public MiniMaxNode GetHead()
	{
		return head;
	}
	
	public void SetNewHead(MiniMaxNode newHead) throws Exception
	{
		MiniMaxNode node = Find(newHead);
		if(node == null)
		{
			throw new Exception("No such node: " + newHead.toString());
		}
		else
		{
			this.head = node;
			this.head.parent = null;
			
			//Rather than figuring out which empty parents are no long valid,
			//Just clear the list and start over.  The cost is minimal.
			emptyParents.clear();
			emptyParents.addAll(FindEmptyParents());
		}
	}
	
	public List<MiniMaxNode> GetEmptyParents()
	{
		return emptyParents;
	}
	
	public void SetChildren(MiniMaxNode node, List<MiniMaxNode> children)
	{
		emptyParents.remove(node);
		emptyParents.addAll(emptyParents.size(), children);
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
	
	private MiniMaxNode Find(MiniMaxNode nodeToFind, Queue<MiniMaxNode> queue)
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
	
	public List<MiniMaxNode> FindEmptyParents()
	{
		List<MiniMaxNode> emptyParents = new ArrayList<MiniMaxNode>();
		
		Queue<MiniMaxNode> queue = new LinkedList<MiniMaxNode>();
		queue.add(head);
		
		while(!queue.isEmpty())
		{
			MiniMaxNode node = queue.poll();
			
			List<MiniMaxNode> children = node.GetChildren();
			
			if(children.size() == 0)
				emptyParents.add(node);
			else
			{
				for(MiniMaxNode child : children)
					queue.add(child);
			}
			
		}
		
		return emptyParents;
	}

	
	public int Count()
	{
		Queue<MiniMaxNode> queue = new LinkedList<MiniMaxNode>();
		queue.add(head);
		
		int count = 0;
		
		while(!queue.isEmpty())
		{
			count++;
			for(MiniMaxNode child : queue.poll().GetChildren())
				queue.add(child);
		}
		
		return count;
	}
	
	public MiniMaxNode AlphaBetaSearch()
	{
		return MaxValue(head, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}
	
	public MiniMaxNode MaxValue(MiniMaxNode node, double alpha, double beta)
	{
		SetVisited(node);

		if(TerminalTest(node))
			return node;

		MiniMaxNode bestNode = new MiniMaxNode("", Double.NEGATIVE_INFINITY);
		for(MiniMaxNode child : node.GetChildren())
		{
			MiniMaxNode minNode = MinValue(child, alpha, beta);
			
			if(minNode.GetValue() > bestNode.GetValue())
				bestNode = minNode;
			
			if(bestNode.GetValue() >= beta)
				return bestNode;
			
			alpha = Math.max(alpha, bestNode.GetValue());
		}
		
		MiniMaxNode returnNode = bestNode;
		
		//Since MiniMax always starts with max, only need to do this in MaxValue
		if(returnNode.GetParent() != head)
			returnNode = returnNode.GetParent();
		
		return returnNode;		
	}

	public MiniMaxNode MinValue(MiniMaxNode node, double alpha, double beta)
	{
		SetVisited(node);

		if(TerminalTest(node))
			return node;
		
		MiniMaxNode bestNode = new MiniMaxNode("", Double.POSITIVE_INFINITY);
		for(MiniMaxNode child : node.GetChildren())
		{
			MiniMaxNode maxNode = MaxValue(child, alpha, beta);
			
			if(maxNode.GetValue() < bestNode.GetValue())
				bestNode = maxNode;
			
			if(bestNode.GetValue() <= alpha)
				return bestNode;
			
			beta = Math.min(beta, bestNode.GetValue());
		}
		
		//Return parent because we need to know the next move to make, not what the ultimate best state is
		return bestNode.GetParent();
	}

	private void SetVisited(MiniMaxNode node)
	{
		//Use reflection to determine if there is a SetVisited method
		//This is necessary for the unit testing to test the alpha beta tree
		try{
			Method method = node.getClass().getMethod("SetVisited", (Class<MiniMaxTestNode>[])null);
			method.invoke(node, (Object[])null);
		} catch (NoSuchMethodException e) {
			//System.out.println("Caught no such method exception on " + node.toString());
		} catch (IllegalAccessException e) {
			//System.out.println("Caught illegal access exception on " + node.toString());
		} catch  (InvocationTargetException e) {
			//System.out.println("Caught invocation target exception on " + node.toString());
		}
	}
	
	public boolean TerminalTest(MiniMaxNode node)
	{
		if(node.GetChildren().size() == 0)
			return true;
		else
			return false;
	}
}

