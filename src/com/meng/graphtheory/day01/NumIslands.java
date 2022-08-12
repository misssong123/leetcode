package com.meng.graphtheory.day01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 200. 岛屿数量(中等)
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class NumIslands {
    /**
     * 超过
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int[] xIndex = {0,0,1,-1};
        int[] yIndex = {1,-1,0,0};
        int res = 0,lines = grid.length,rows = grid[0].length;
        boolean[][] flags = new boolean[lines][rows];
        List<int[]> cache = new ArrayList<>();
        for(int i = 0 ; i < lines ; i ++){
            for(int j = 0 ; j < rows ; j ++){
                if (flags[i][j] || grid[i][j] == '0'){
                    continue;
                }
                cache.add(new int[]{i,j});
                res++;
                while (cache.size() > 0){
                    int size = cache.size();
                    for(int k = 0 ; k < size ; k++){
                        int[] temp = cache.remove(0);
                        int x = temp[0];
                        int y = temp[1];
                        flags[x][y] = true;
                        for(int m = 0 ; m < 4 ; m++){
                            int newX = x + xIndex[m];
                            int newY = y + yIndex[m];
                            if (newX>=0 && newX < lines && newY >=0 && newY < rows && !flags[newX][newY] && grid[newX][newY]=='1'){
                                cache.add(new int[]{newX,newY});
                            }
                        }
                    }
                }
            }
        }
        return res;
    }


    /**
     * 方法一：深度优先搜索
     * 我们可以将二维网格看成一个无向图，竖直或水平相邻的 1 之间有边相连。
     *
     * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则以其为起始节点开始进行深度优先搜索。在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。
     *
     * 最终岛屿的数量就是我们进行深度优先搜索的次数。
     *
     * 作者：LeetCode
     * 链接：https://leetcode.cn/problems/number-of-islands/solution/dao-yu-shu-liang-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 49.8 MB
     * , 在所有 Java 提交中击败了
     * 42.11%
     * 的用户
     * 通过测试用例：
     * 49 / 49
     */
    public int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }
        return num_islands;
    }
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    /**
     * 方法二：广度优先搜索
     * 同样地，我们也可以使用广度优先搜索代替深度优先搜索。
     *
     * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 11，则将其加入队列，开始进行广度优先搜索。在广度优先搜索的过程中，每个搜索到的 11 都会被重新标记为 00。直到队列为空，搜索结束。
     *
     * 最终岛屿的数量就是我们进行广度优先搜索的次数。
     *
     * 作者：LeetCode
     * 链接：https://leetcode.cn/problems/number-of-islands/solution/dao-yu-shu-liang-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid
     * @return
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 12.52%
     * 的用户
     * 内存消耗：
     * 48.9 MB
     * , 在所有 Java 提交中击败了
     * 93.12%
     * 的用户
     * 通过测试用例：
     * 49 / 49
     */
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

    /**
     * 方法三：并查集
     * 同样地，我们也可以使用并查集代替搜索。
     *
     * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 11，则将其与相邻四个方向上的 11 在并查集中进行合并。
     *
     * 最终岛屿的数量就是并查集中连通分量的数目。
     *
     * 作者：LeetCode
     * 链接：https://leetcode.cn/problems/number-of-islands/solution/dao-yu-shu-liang-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid
     * @return
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 6.77%
     * 的用户
     * 内存消耗：
     * 49.5 MB
     * , 在所有 Java 提交中击败了
     * 76.45%
     * 的用户
     * 通过测试用例：
     * 49 / 49
     */
    public int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }

    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
