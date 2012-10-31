package students.ramusivich;

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
