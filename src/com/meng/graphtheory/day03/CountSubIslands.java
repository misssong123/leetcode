package com.meng.graphtheory.day03;

import java.util.ArrayList;
import java.util.List;

/**
 * 1905. 统计子岛屿(中等)
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
 *
 * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 子岛屿 。
 *
 * 请你返回 grid2 中 子岛屿 的 数目 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * 输出：3
 * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 * grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
 * 示例 2：
 *
 *
 * 输入：grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * 输出：2
 * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 * grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
 *
 *
 * 提示：
 *
 * m == grid1.length == grid2.length
 * n == grid1[i].length == grid2[i].length
 * 1 <= m, n <= 500
 * grid1[i][j] 和 grid2[i][j] 都要么是 0 要么是 1 。
 */
public class CountSubIslands {
    /**
     * 执行用时：
     * 37 ms
     * , 在所有 Java 提交中击败了
     * 6.21%
     * 的用户
     * 内存消耗：
     * 77.9 MB
     * , 在所有 Java 提交中击败了
     * 8.84%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     * @param grid1
     * @param grid2
     * @return
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int sum = 0;
        int lines = grid2.length;
        int rows = grid2[0].length;
        int[] X = {-1,0,1,0}, Y = {0,1,0,-1};
        List<int[]> temp = new ArrayList<>();
        boolean flag = true;
        for(int i = 0 ; i < lines ; i++){
            for(int j = 0 ; j < rows ; j++){
                if (grid2[i][j]==1){
                    flag = true;
                    temp.add(new int[]{i,j});
                    while (temp.size() > 0){
                        int size = temp.size();
                        for(int k = 0 ; k < size ; k++){
                            int[] point = temp.remove(0);
                            if (grid2[point[0]][point[1]] == 0){
                                continue;
                            }
                            grid2[point[0]][point[1]] = 0;
                            if (grid1[point[0]][point[1]]==0){
                                flag = false;
                            }
                            for(int m = 0 ; m < 4 ; m++){
                                int newX = point[0] + X[m];
                                int newY = point[1] + Y[m];
                                if(newX>=0 && newX < lines && newY>=0 && newY < rows && grid2[newX][newY]==1){
                                    temp.add(new int[]{newX,newY});
                                }
                            }
                        }
                    }
                    if (flag){
                        sum++;
                    }
                }
            }
        }

        return sum;
    }

    /**
     * 方法一：广度优先搜索
     * 思路与算法
     *
     * 我们可以使用广度优先搜索（也可以使用深度优先搜索）找出所有的岛屿，具体可以参考「200. 岛屿数量」的官方题解。
     *
     * 在 \textit{grid}_2grid
     * 2
     * ​
     *   中搜索某一个岛屿的过程中，我们需要判断岛屿包含的每一个格子是否都在 \textit{grid}_1grid
     * 1
     * ​
     *   中出现了。如果全部出现，那么将答案增加 1。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/count-sub-islands/solution/tong-ji-zi-dao-yu-by-leetcode-solution-x32x/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid1
     * @param grid2
     * @return
     * 执行用时：
     * 19 ms
     * , 在所有 Java 提交中击败了
     * 95.04%
     * 的用户
     * 内存消耗：
     * 73.5 MB
     * , 在所有 Java 提交中击败了
     * 20.95%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     */
    public int countSubIslands1(int[][] grid1, int[][] grid2) {
        int m = grid2.length;
        int n = grid2[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    dfs(grid1, grid2, i, j);
                    //是子岛屿才计数
                    if (flag) count++;
                    //要重置标志位
                    flag = true;
                }
            }
        }
        return count;
    }

    boolean flag = true;

    void dfs(int[][] grid1, int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) return;
        if (grid[i][j] == 0) return;

        //当前遍历的grid2【陆地】在grid1中如果是【水】
        //标记为false，说明不是子岛屿，所以不计数
        if (grid1[i][j] == 0) flag = false;

        grid[i][j] = 0;
        dfs(grid1, grid, i - 1, j);
        dfs(grid1, grid, i + 1, j);
        dfs(grid1, grid, i, j - 1);
        dfs(grid1, grid, i, j + 1);
    }
}
