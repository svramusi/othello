package students.ramusivich;

import java.util.List;
import java.util.ArrayList;

public class MiniMaxNode {
	
	protected Object object;
	protected double value;
	protected MiniMaxNode parent;
	protected List<MiniMaxNode> children;
	
	public MiniMaxNode (Object object, double value)
	{
		this.object = object;
		this.value = value;
		this.parent = null;
		this.children = new ArrayList<MiniMaxNode>();
	}
	
	public Object GetObject()
	{
		return this.object;
	}
	
	public double GetValue()
	{
		return this.value;
	}
	
	public MiniMaxNode GetParent()
	{
		return this.parent;
	}
	
	public void SetChildren(List<MiniMaxNode> children)
	{
		for(MiniMaxNode child : children)
		{
			SetChild(child);
		}
	}
	
	public void SetChild(MiniMaxNode child)
	{
		child.SetParent(this);
		this.children.add(child);
	}
	
	private void SetParent(MiniMaxNode parent)
	{
		this.parent = parent;
	}
	
	public List<MiniMaxNode> GetChildren()
	{
		return this.children;
	}
	
	public boolean equals(Object o)
	{
		if(!(o instanceof MiniMaxNode))
			return false;
		
		MiniMaxNode node = (MiniMaxNode)o;
		
		return this.GetObject().equals(node.GetObject()) && this.GetValue() == node.GetValue();
	}
	
	public String toString()
	{
		return this.object.toString() + " - " + Double.toString(this.value);
	}
}
