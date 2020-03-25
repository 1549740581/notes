package dfs;

import java.util.ArrayList;

/**
 * dfs in graph
 * time complexity O(E+V)
 *
 * @author sherman
 */
public class GraphDFS {
	private Graph graph;
	private int V;
	private boolean[] visited;
	private ArrayList<Integer> preOrder = new ArrayList<>();
	private ArrayList<Integer> postOrder = new ArrayList<>();

	public GraphDFS(Graph graph) {
		this.graph = graph;
		this.V = graph.getV();
		visited = new boolean[V];

		// if the graph has more than one connection components
		// you need iterate all vertex in order to not left any connection components
		for (int i = 0; i < V; ++i) {
			if (!visited[i]) {
				dfs(i);
			}
		}
	}

	public Iterable<Integer> preOrderDFS() {
		return preOrder;
	}

	public Iterable<Integer> postOrderDFS() {
		return postOrder;
	}

	// merge post-order and pre-order dfs into one method
	private void dfs(int i) {
		preOrder.add(i);
		visited[i] = true;
		for (int w : graph.getNeighbors(i)) {
			if (!visited[w]) {
				dfs(w);
			}
		}
		postOrder.add(i);
	}

	public static void main(String[] args) {
		Graph graph = new Graph("dfs.txt");
		System.out.println(new GraphDFS(graph).preOrderDFS());
		System.out.println(new GraphDFS(graph).postOrderDFS());
	}

}
