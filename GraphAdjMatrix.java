package p08;

import java.util.Iterator;
import java.util.Stack;

public class GraphAdjMatrix implements Graph {
	public int edges[][];
	
	
	public GraphAdjMatrix(int vertices) {
		edges = new int[vertices][vertices];
		
	}
	
	public void addEdge(int v1, int v2) {
		edges[v1][v2] = 1;
	}
	
	public int outdegree(int vertex) {
		int degree = 0;
		for (int i=0; i < edges.length; i++) {
			if (edges[vertex][i] != 0) {
				degree++;
			}
		}
		return degree;
	}
	
	public int indegree(int vertex) {
		int degree = 0;
		for (int i=0; i < edges.length; i++) {
			if (edges[i][vertex] != 0) {
				degree++;
			}
		}
		return degree;
	}
	
	public void topologicalSort() {
		int[] incident = new int[edges.length];
		for (int i=0; i < edges.length; i++) {
			incident[i] = indegree(i);
		}
		Queue sequence = new Queue();
		boolean cycle_detected = false;
		
		while (findZero(incident) != -1 && !(cycle_detected)) {
			int vertex = findZero(incident);
			
			if (vertex != -1) {
				incident[vertex] = -1;
				sequence.enqueue(vertex);
				cycle_detected = true;
				
				for (int i=0; i < edges.length; i++) {
					if (edges[vertex][i] == 1) {
						cycle_detected = false;
						incident[i]--;
					}
				}
				System.out.println("vertex: " + vertex);
			}
		}
	}
	
	public int findZero(int[] incident) {
		for (int i=0; i < incident.length; i++) {
			if (incident[i] == 0) {
				return i;
			}
		}
		return -1;
	}
	
	public int[] neighbors(int vertex) {
		int[] neighbor = new int[outdegree(vertex)];
		int index = 0;
		for (int i=0; i < edges.length; i++) {
			if (edges[vertex][i] == 1) {
				neighbor[index] = i;
				index++;
			}
		}
		return neighbor;
	}
	
	public void topologicalSort(int v, boolean[] visited) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(new Integer(v));
		while(!stack.empty()) {
			int vertex = stack.pop();
			for (int i = 0; i < visited.length; i++) {
				if (!visited[i]) {
					stack.push(new Integer(i));
					visited[i] = true;
				}
			}
		}
		
	}
	
	
	public int[] getNeighbors(int vertex) {
		return neighbors(vertex);
	}
}
