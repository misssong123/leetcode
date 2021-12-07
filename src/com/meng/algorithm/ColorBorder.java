package com.meng.algorithm;

import java.util.*;

/**
 * 1034. 边界着色
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 *
 * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
 *
 * 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
 *
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * 输出：[[3,3],[3,2]]
 * 示例 2：
 *
 * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
 * 输出：[[1,3,3],[2,3,3]]
 * 示例 3：
 *
 * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
 * 输出：[[2,2,2],[2,1,2],[2,2,2]]
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j], color <= 1000
 * 0 <= row < m
 * 0 <= col < n
 *
 */
public class ColorBorder {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 23.76%
     * 的用户
     * 内存消耗：
     * 39.5 MB
     * , 在所有 Java 提交中击败了
     * 23.27%
     * 的用户
     * 通过测试用例：
     * 154 / 154
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     */
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length,n = grid[0].length;
        int[][] diff = {{0,1},{0,-1},{1,0},{-1,0}};
        List<int[] > res = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        int origin = grid[row][col];
        boolean[][] flags = new boolean[m][n];
        queue.add(new int[]{row,col});
        flags[row][col] = true;
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            boolean isBorder = false;
            for(int i = 0 ; i < 4 ; i++){
                int x = poll[0] + diff[i][0];
                int y = poll[1] + diff[i][1];
                if (!(x >=0 && x <m && y >=0 && y < n&& grid[x][y] == origin)){
                    isBorder = true;
                }else if (!flags[x][y]){
                    flags[x][y] =true;
                    queue.add(new int[]{x,y});
                }
            }
            if (isBorder){
                res.add(poll);
            }
        }
        for(int[] nums : res){
            grid[nums[0]][nums[1]] = color;
        }
        return grid;
    }

    /**
     * 方法一：深度优先搜索
     * 思路及解法
     *
     * 我们用递归来实现深度优先搜索遍历连通分量，用一个大小和 \textit{grid}grid 相同的矩阵 \textit{visited}visited 来记录当前节点是否被访问过，并把边界点存入数组\textit{borders}borders 中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/coloring-a-border/solution/bian-kuang-zhao-se-by-leetcode-solution-0h5l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 82.67%
     * 的用户
     * 内存消耗：
     * 39.4 MB
     * , 在所有 Java 提交中击败了
     * 42.58%
     * 的用户
     * 通过测试用例：
     * 154 / 154
     */
    public int[][] colorBorder1(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> borders = new ArrayList<>();
        int originalColor = grid[row][col];
        visited[row][col] = true;
        dfs(grid, row, col, visited, borders, originalColor);
        for (int i = 0; i < borders.size(); i++) {
            int x = borders.get(i)[0], y = borders.get(i)[1];
            grid[x][y] = color;
        }
        return grid;
    }

    private void dfs(int[][] grid, int x, int y, boolean[][] visited, List<int[]> borders, int originalColor) {
        int m = grid.length, n = grid[0].length;
        boolean isBorder = false;
        int[][] direc = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < 4; i++) {
            int nx = direc[i][0] + x, ny = direc[i][1] + y;
            if (!(nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == originalColor)) {
                isBorder = true;
            } else if (!visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(grid, nx, ny, visited, borders, originalColor);
            }
        }
        if (isBorder) {
            borders.add(new int[]{x, y});
        }
    }

    /**
     * 方法二：广度优先搜索
     * 思路及解法
     *
     * 我们用一个队列来实现广度优先搜索遍历连通分量，并用一个大小和 \textit{grid}grid 相同的矩阵 \textit{visited}visited 来记录当前节点是否被访问过，并把边界点存入数组\textit{borders}borders 中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/coloring-a-border/solution/bian-kuang-zhao-se-by-leetcode-solution-0h5l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 82.67%
     * 的用户
     * 内存消耗：
     * 39.2 MB
     * , 在所有 Java 提交中击败了
     * 76.24%
     * 的用户
     * 通过测试用例：
     * 154 / 154
     */
    public int[][] colorBorder2(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> borders = new ArrayList<>();
        int originalColor = grid[row][col];
        int[][] direc = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row, col});
        visited[row][col] = true;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int x = node[0], y = node[1];

            boolean isBorder = false;
            for (int i = 0; i < 4; i++) {
                int nx = direc[i][0] + x, ny = direc[i][1] + y;
                if (!(nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == originalColor)) {
                    isBorder = true;
                } else if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
            if (isBorder) {
                borders.add(new int[]{x, y});
            }
        }
        for (int i = 0; i < borders.size(); i++) {
            int x = borders.get(i)[0], y = borders.get(i)[1];
            grid[x][y] = color;
        }
        return grid;
    }


}
