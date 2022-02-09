
public class Edge {
	private int starting;
	private int ending;
	private int flow;
	private int capacity;
	private Edge resEdge;
	
	
	
	public Edge(int start,int end, int capacity) {
		starting = start;
		ending = end;
		this.capacity = capacity;
	}
	public void load(int amount) {
		flow += amount;
		resEdge.flow-= amount;
	}
	public int getStarting() {
		return starting;
	}
	public void setStarting(int starting) {
		this.starting = starting;
	}
	public int getEnding() {
		return ending;
	}
	public void setEnding(int ending) {
		this.ending = ending;
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Edge getResEdge() {
		return resEdge;
	}
	public void setResEdge(Edge resEdge) {
		this.resEdge = resEdge;
	}
	
	
}
