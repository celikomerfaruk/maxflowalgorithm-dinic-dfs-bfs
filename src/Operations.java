import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedList;


public class Operations {
	private int[] height;
	private ArrayList<ArrayList<Edge>> graph;
	private int sink;
	private int numOfNodes;
	private int[] deadPath; 
	private int maxflow;
	
	public Operations(int numOfNodes) {
		this.numOfNodes =numOfNodes+2;
		height = new int[this.numOfNodes];
		deadPath = new int[this.numOfNodes];
		graph = new ArrayList<>(this.numOfNodes);
		for (int i = 0; i < this.numOfNodes; i++) {
			graph.add(new ArrayList<>());
		}
		sink = numOfNodes+1;
		maxflow = 0;
		
	}
	
	
	public void addEdge(int start,int end,int capacity ) {
		Edge edg1 = new Edge(start, end, capacity);
		Edge edg2 = new Edge(end, start, 0);
		edg1.setResEdge(edg2);
		edg2.setResEdge(edg1);
		graph.get(start).add(edg1);
		graph.get(end).add(edg2);
	}
	
	private int dfs(int start , int[] deadPath, int flow) {
		if (start == sink) {
			return flow;
		}
		int adjacentEdgesCount = graph.get(start).size();
		while( deadPath[start] <adjacentEdgesCount ) {
			Edge tmpEdge = graph.get(start).get(deadPath[start]);
			int capacity = tmpEdge.getCapacity()- tmpEdge.getFlow();
			if (height[tmpEdge.getEnding()] == height[start] + 1 && capacity >0) {
				int amount = dfs(tmpEdge.getEnding(), deadPath, Math.min(flow,capacity));
				if (amount > 0 ) {
					tmpEdge.load(amount);
					return amount;
				}
			}
			deadPath[start]+=1;
		}
		return 0 ;
	}
	private boolean bfs() {
		for (int i = 0; i < height.length; i++) {
			height[i] = -1;
		}
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		height[0] = 0;
		queue.add(0);
		while (!queue.isEmpty()) {
			int tmpNode = queue.poll();
			
			for (Iterator<Edge> itr= graph.get(tmpNode).iterator(); itr.hasNext();) {
				Edge tmpEdge = itr.next();
				int capacity = tmpEdge.getCapacity() - tmpEdge.getFlow();
				if (height[tmpEdge.getEnding()] == -1 && capacity>0) {
					height[tmpEdge.getEnding()] = height[tmpNode]+1;
					queue.add(tmpEdge.getEnding());
				}
				
			}
		}
		return height[sink] > -1;    
	}
	
	//dinic algorithm
	public int operate() {
		
		while(bfs()) {
			for (int i = 0; i < deadPath.length; i++) {
				deadPath[i] = 0;
			}
			for (int i = dfs(0, deadPath, Integer.MAX_VALUE); i != 0;i = dfs(0, deadPath, Integer.MAX_VALUE)) {
				maxflow += i;
			}
			
		}
		return maxflow;
	}
	
}
