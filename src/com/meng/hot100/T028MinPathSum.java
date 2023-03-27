package com.meng.hot100;

import java.util.Arrays;

/**
 * 64. 最小路径和(中等)
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 *
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
public class T028MinPathSum {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 94.53%
     * 的用户
     * 内存消耗：
     * 44.1 MB
     * , 在所有 Java 提交中击败了
     * 45.53%
     * 的用户
     * 通过测试用例：
     * 61 / 61
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[][] nums = new int[m][n];
        for(int i = 0 ; i < m ; i++){
            nums[i][0] = grid[i][0] + (i>0?nums[i-1][0]:0);
        }
        for(int i = 1 ; i < n ; i++){
            nums[0][i] = grid[0][i] + nums[0][i-1];
        }
        for(int i = 1 ; i < m ; i++){
            for(int j = 1 ; j < n ; j++){
                nums[i][j] = Math.min(nums[i-1][j],nums[i][j-1])+grid[i][j];
            }
        }
        return nums[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        T028MinPathSum demo = new T028MinPathSum();
        System.out.println(demo.minPathSum(grid));
    }

    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 94.53%
     * 的用户
     * 内存消耗：
     * 44.6 MB
     * , 在所有 Java 提交中击败了
     * 5.11%
     * 的用户
     * 通过测试用例：
     * 61 / 61
     * @param grid
     * @return
     */
    public int minPathSum1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }

}
