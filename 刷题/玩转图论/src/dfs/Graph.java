package dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeSet;
import java.util.Scanner;

/**
 * Use AdjTreeSet class in package presentation for a graph
 *
 * @author sherman
 */
public class Graph {
	/**
	 * Number of vertexes
	 */
	private int V;
	/**
	 * Number of edges
	 */
	private int E;
	/**
	 * Adjacent list
	 */
	private TreeSet<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Graph(String filename) {
		File file = new File(filename);

        // try with resource supported by JDK 1.7
        // not need release resource explicitly in finally block
		try (Scanner scanner = new Scanner(file)) {
            // skips commit line which starts with "#"
			while (scanner.hasNext("#.*")) {
				scanner.nextLine();
			}
			V = scanner.nextInt();
			if (V < 0) {
				throw new IllegalArgumentException("V must be non-negative");
			}
			adj = new TreeSet[V];
			for (int i = 0; i < V; ++i) {
				adj[i] = new TreeSet<Integer>();
			}
			E = scanner.nextInt();
			if (E < 0) {
				throw new IllegalArgumentException("E must be non-negative");
			}
			for (int i = 0; i < E; ++i) {
				int x = scanner.nextInt();
				validVertex(x);
				int y = scanner.nextInt();
				validVertex(y);

				if (x == y) {
					throw new IllegalArgumentException("Self loop is not supported");
				}
                // due to this operation, time complexity becomes to O(E*log(V))
				if (adj[x].contains(y)) {
					throw new IllegalArgumentException("Parallel edge is not supported");
				}
				adj[x].add(y);
				adj[y].add(x);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Make sure the given vertex v is valid, or it throws an
	 * IllegalArgumentException.
	 *
	 * @param v given vertex
	 */
	public void validVertex(int v) {
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("Vertex " + v + " is invalid");
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("V = %d, E = %d\n", V, E)).append("\nAdjacent list:\n");
		for (int v = 0; v < V; ++v) {
			sb.append(String.format("%d : ", v));
			for (int w : adj[v]) {
				sb.append(w + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public int getV() {
		return V;
	}

	public int getE() {
		return E;
	}

	/**
	 * Find if has edge between vertex i and j
	 *
	 * @param i vertex i
	 * @param j vertex j
	 * @return if has return true, otherwise false
	 */
	public boolean hasEdge(int i, int j) {
		validVertex(i);
		validVertex(j);
		return adj[i].contains(j);
	}

	/**
	 * Find all neighbors by given vertex v
	 *
	 * @param v given vertex v
	 * @return Iterable of all neighbors by given vertex v
	 */
	public Iterable<Integer> getNeighbors(int v) {
		validVertex(v);
		return adj[v];
	}

	/**
	 * Get the degree of given vertex v
	 *
	 * @param v given vertex x
	 * @return degree of given vertex v
	 */
	public int degree(int v) {
		validVertex(v);
		return adj[v].size();
	}
}
