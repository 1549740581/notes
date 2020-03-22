package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjMatrix {
	/**
	 * Number of vertexes
	 */
	private int V;
	/**
	 * Number of edges
	 */
	private int E;
	/**
	 * Adjacent matrix
	 */
	private int[][] matrix;

	public AdjMatrix(String filename) {
		File file = new File(filename);

		// try with resource supported by JDK 1.7
		// not need release resource explicitly in finally block
		try (Scanner scanner = new Scanner(file)) {
			// skip commit line which starts with "#"
			while (scanner.hasNext("#.*")) {
				scanner.nextLine();
			}
			V = scanner.nextInt();
			if (V < 0) {
				throw new IllegalArgumentException("V must be non-negative");
			}
			E = scanner.nextInt();
			if (E < 0) {
				throw new IllegalArgumentException("E must be non-negative");
			}
			matrix = new int[V][V];
			for (int i = 0; i < E; ++i) {
				int x = scanner.nextInt();
				validVertex(x);
				int y = scanner.nextInt();
				validVertex(y);

				if (x == y) {
					throw new IllegalArgumentException("Self loop is not supported");
				}
				if (matrix[x][y] != 0) {
					throw new IllegalArgumentException("Parallel edge is not supported");
				}
				matrix[x][y] = matrix[y][x] = 1;
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
	private void validVertex(int v) {
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("Vertex " + v + " is invalid");
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("V = %d, E = %d\n", V, E)).append("\nAdjacent matrix:\n");
		for (int i = 0; i < V; ++i) {
			for (int j = 0; j < V; ++j) {
				sb.append(matrix[i][j] + " ");
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
		return matrix[i][j] == 1;
	}

	/**
	 * Find all neighbors by given vertex v
	 * 
	 * @param v given vertex v
	 * @return all neighbors of vertex v, stored in an ArrayList
	 */
	public ArrayList<Integer> getNeighbors(int v) {
		ArrayList<Integer> neighbors = new ArrayList<>();
		validVertex(v);
		for (int i = 0; i < V; ++i) {
			if (matrix[v][i] == 1) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}

	/**
	 * Get the degree of given vertex v
	 * 
	 * @param v given vertex x
	 * @return degree of given vertex v
	 */
	public int degree(int v) {
		return getNeighbors(v).size();
	}

	public static void main(String[] args) {
		AdjMatrix matrix = new AdjMatrix("graph.txt");
		System.out.println(matrix);
	}
}
