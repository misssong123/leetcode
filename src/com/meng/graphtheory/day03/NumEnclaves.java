package com.meng.graphtheory.day03;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1020. 飞地的数量(中等)
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 *
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 *
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 示例 2：
 *
 *
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 的值为 0 或 1
 */
public class NumEnclaves {
    public int numEnclaves(int[][] grid) {
        return -1;
    }

    /**
     * 方法一：深度优先搜索
     * 根据飞地的定义，如果从一个陆地单元格出发无法移动到网格边界，则这个陆地单元格是飞地。因此可以将所有陆地单元格分成两类：第一类陆地单元格和网格边界相连，这些陆地单元格不是飞地；第二类陆地单元格不和网格边界相连，这些陆地单元格是飞地。
     *
     * 我们可以从网格边界上的每个陆地单元格开始深度优先搜索，遍历完边界之后，所有和网格边界相连的陆地单元格就都被访问过了。然后遍历整个网格，如果网格中的一个陆地单元格没有被访问过，则该陆地单元格不和网格的边界相连，是飞地。
     *
     * 代码实现时，由于网格边界上的单元格一定不是飞地，因此遍历网格统计飞地的数量时只需要遍历不在网格边界上的单元格。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/number-of-enclaves/solution/fei-di-de-shu-liang-by-leetcode-solution-nzs3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 60.87%
     * 的用户
     * 内存消耗：
     * 49.1 MB
     * , 在所有 Java 提交中击败了
     * 55.00%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     */
    public static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;
    private boolean[][] visited;

    public int numEnclaves1(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        for (int j = 1; j < n - 1; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }

    public void dfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0 || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        for (int[] dir : dirs) {
            dfs(grid, row + dir[0], col + dir[1]);
        }
    }

    /**
     * 方法二：广度优先搜索
     * 也可以通过广度优先搜索判断每个陆地单元格是否和网格边界相连。
     *
     * 首先从网格边界上的每个陆地单元格开始广度优先搜索，访问所有和网格边界相连的陆地单元格，然后遍历整个网格，统计飞地的数量。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/number-of-enclaves/solution/fei-di-de-shu-liang-by-leetcode-solution-nzs3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid
     * @return
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 26.88%
     * 的用户
     * 内存消耗：
     * 49 MB
     * , 在所有 Java 提交中击败了
     * 66.56%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     */
    public int numEnclaves2(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<int[]>();
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                visited[i][0] = true;
                queue.offer(new int[]{i, 0});
            }
            if (grid[i][n - 1] == 1) {
                visited[i][n - 1] = true;
                queue.offer(new int[]{i, n - 1});
            }
        }
        for (int j = 1; j < n - 1; j++) {
            if (grid[0][j] == 1) {
                visited[0][j] = true;
                queue.offer(new int[]{0, j});
            }
            if (grid[m - 1][j] == 1) {
                visited[m - 1][j] = true;
                queue.offer(new int[]{m - 1, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int currRow = cell[0], currCol = cell[1];
            for (int[] dir : dirs) {
                int nextRow = currRow + dir[0], nextCol = currCol + dir[1];
                if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && grid[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }

    /**
     * 方法三：并查集
     * 除了深度优先搜索和广度优先搜索的方法以外，也可以使用并查集判断每个陆地单元格是否和网格边界相连。
     *
     * 并查集的核心思想是计算网格中的每个陆地单元格所在的连通分量。对于网格边界上的每个陆地单元格，其所在的连通分量中的所有陆地单元格都不是飞地。如果一个陆地单元格所在的连通分量不同于任何一个网格边界上的陆地单元格所在的连通分量，则该陆地单元格是飞地。
     *
     * 并查集的做法是，遍历整个网格，对于网格中的每个陆地单元格，将其与所有相邻的陆地单元格做合并操作。由于需要判断每个陆地单元格所在的连通分量是否和网格边界相连，因此并查集还需要记录每个单元格是否和网格边界相连的信息，在合并操作时更新该信息。
     *
     * 在遍历网格完成并查集的合并操作之后，再次遍历整个网格，通过并查集中的信息判断每个陆地单元格是否和网格边界相连，统计飞地的数量。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/number-of-enclaves/solution/fei-di-de-shu-liang-by-leetcode-solution-nzs3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid
     * @return
     * 执行用时：
     * 18 ms
     * , 在所有 Java 提交中击败了
     * 5.18%
     * 的用户
     * 内存消耗：
     * 48.6 MB
     * , 在所有 Java 提交中击败了
     * 88.39%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     */
    public int numEnclaves3(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int index = i * n + j;
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        uf.union(index, index + 1);
                    }
                    if (i + 1 < m && grid[i + 1][j] == 1) {
                        uf.union(index, index + n);
                    }
                }
            }
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !uf.isOnEdge(i * n + j)) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }
}

class UnionFind {
    private int[] parent;
    private boolean[] onEdge;
    private int[] rank;

    public UnionFind(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m * n];
        onEdge = new boolean[m * n];
        rank = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int index = i * n + j;
                    parent[index] = index;
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        onEdge[index] = true;
                    }
                }
            }
        }
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
                onEdge[rootx] |= onEdge[rooty];
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
                onEdge[rooty] |= onEdge[rootx];
            } else {
                parent[rooty] = rootx;
                onEdge[rootx] |= onEdge[rooty];
                rank[rootx]++;
            }
        }
    }

    public boolean isOnEdge(int i) {
        return onEdge[find(i)];
    }


}
