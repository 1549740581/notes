package backtrace;

import java.util.ArrayDeque;

/**
 * 200 number of islands
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * - 假设网格的四个边均被水包围。
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 *
 * @author sherman
 */
public class Q14NumberOfIslands {
    private boolean[][] visited;
    private int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * dfs
     */
    public int numIslands(char[][] grid) {
        /**
         * 注意判断grid.length == 0情况
         */
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    ++res;
                    infect(grid, i, j);
                    /**
                     * // bfs
                     * numIslands(grid, i, j);
                     */
                }
            }
        }
        return res;
    }

    private void infect(char[][] grid, int x, int y) {
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int newX = x + dir[d][0];
            int newY = y + dir[d][1];
            if (isValidCord(newX, newY) && !visited[newX][newY] && grid[newX][newY] == '1') {
                infect(grid, newX, newY);
            }
        }
    }

    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * bfs
     */
    public void bfs(char[][] grid, int x, int y) {
        ArrayDeque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(x, y));
        while (!deque.isEmpty()) {
            Point point = deque.poll();
            for (int d = 0; d < 4; d++) {
                int newX = point.x + dir[d][0];
                if (newX < 0 || newX >= visited.length) {
                    continue;
                }
                int newY = point.y + dir[d][1];
                if (newY < 0 || newY >= visited[0].length) {
                    continue;
                }
                if (!visited[newX][newY] && grid[newX][newY] == '1') {
                    visited[newX][newY] = true;
                    deque.add(new Point(newX, newY));
                }
            }
        }
    }

    private boolean isValidCord(int x, int y) {
        return x >= 0 && x < visited.length && y >= 0 && y < visited[0].length;
    }
}
