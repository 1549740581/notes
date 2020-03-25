package dfs.app;

import dfs.Graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ~
 * Count the number of Connected Component in a graph and
 * Record the belonging of each node
 */
public class CountConnectedComponentWithRecord {
	private Graph graph;
	private int ccCount;
	/**
	 * visited[i] == -1: node i has not been visited
	 * visited[i] == n: node is has been visited and belongs to Connected Component n - 1
	 */
	private int[] visited;

	public CountConnectedComponentWithRecord(Graph graph) {
		this.graph = graph;
		visited = new int[graph.getV()];
		Arrays.fill(visited, -1);
		for (int i = 0; i < graph.getV(); i++) {
			if (visited[i] == -1) {
				dfs(i, ccCount++);
			}
		}
	}

	private void dfs(int v, int ccid) {
		visited[v] = ccid;
		for (Integer item : graph.getNeighbors(v)) {
			if (visited[item] == -1) {
				dfs(item, ccid);
			}
		}
	}

	public int getCcCount() {
		System.out.println(Arrays.toString(visited));
		return ccCount;
	}

	/**
	 * Judge if node v and node w belong to same Connected Component
	 */
	public boolean isConnect(int v, int w) {
		graph.validVertex(v);
		graph.validVertex(w);
		return visited[v] == visited[w];
	}

	/**
	 * Get all Connected Components
	 */
	public ArrayList<Integer>[] components() {
		ArrayList<Integer>[] res = new ArrayList[ccCount];
		for (int i = 0; i < ccCount; ++i) {
			res[i] = new ArrayList<>();
		}
		for (int v = 0; v < graph.getV(); v++) {
			res[visited[v]].add(v);
		}
		return res;
	}

	public static void main(String[] args) {
		// get all Connected Components
		CountConnectedComponentWithRecord cccr = new CountConnectedComponentWithRecord(new Graph("dfs.txt"));
		System.out.println(cccr.getCcCount());
		// judge if node 5 connects node 1
		System.out.println(cccr.isConnect(5, 1));
		// get each Connected Component
		ArrayList<Integer>[] components = cccr.components();
		for (int ccid = 0; ccid < components.length; ++ccid) {
			System.out.println(ccid + ": " + components[ccid]);
		}
	}
}
