package dfs.app;

import dfs.Graph;

/**
 * Count the number of Connected Component in a graph
 */
public class CountConnectedComponent {
	private Graph graph;
	/**
	 * number of Connected Component
	 */
	private int ccCount;
	private boolean[] visited;

	public CountConnectedComponent(Graph graph) {
		this.graph = graph;
		visited = new boolean[graph.getV()];
		for (int i = 0; i < graph.getV(); i++) {
			if (!visited[i]) {
				dfs(i);
				++ccCount;
			}
		}
	}

	private void dfs(int v) {
		visited[v] = true;
		for (Integer w : graph.getNeighbors(v)) {
			if (!visited[w]) {
				dfs(w);
			}
		}
	}

	public int getCcCount() {
		return ccCount;
	}

	public static void main(String[] args) {
		CountConnectedComponent ccc = new CountConnectedComponent(new Graph("dfs.txt"));
		System.out.println(ccc.getCcCount());
	}
}
