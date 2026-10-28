package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class MinCost3882 {
    /**
     * 解答成功:
     * 	执行耗时:780 ms,击败了22.73% 的Java用户
     * 	内存消耗:278 MB,击败了6.06% 的Java用户
     * @param grid
     * @return
     */
    public int minCost3882(int[][] grid) {
        int m = grid.length , n = grid[0].length;
        Set<Integer>[][] dp = new HashSet[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }
        //初始化
        dp[0][0].add(grid[0][0]);
        int val = grid[0][0];
        for (int i = 1; i < n; i++) {
            val = val ^ grid[0][i];
            dp[0][i].add(val);
        }
        val = grid[0][0];
        for (int i = 1; i < m; i++) {
            val = val ^ grid[i][0];
            dp[i][0].add(val);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //上侧
                for (int num : dp[i-1][j]) {
                    dp[i][j].add(num ^ grid[i][j]);
                }
                //左侧
                for (int num : dp[i][j-1]) {
                    dp[i][j].add(num ^ grid[i][j]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int num : dp[m-1][n-1]) {
            ans = Math.min(ans,num);
        }
        return ans;
    }
    private int ans = Integer.MAX_VALUE;

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了77.27% 的Java用户
     * 	内存消耗:47.4 MB,击败了45.45% 的Java用户
     * @param grid
     * @return
     */
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 异或和不会超过所有元素的 OR
        int orAll = 0;
        for (int[] row : grid) {
            for (int x : row) {
                orAll |= x;
            }
        }

        boolean[][][] vis = new boolean[m][n][orAll + 1];
        dfs(m - 1, n - 1, 0, grid, vis);
        return ans;
    }

    private void dfs(int i, int j, int xor, int[][] grid, boolean[][][] vis) {
        // 最优性剪枝：如果答案已经最小（等于 0），那么不再搜索
        if (ans == 0 || i < 0 || j < 0 || vis[i][j][xor]) {
            return;
        }
        vis[i][j][xor] = true;
        xor ^= grid[i][j];
        if (i == 0 && j == 0) {
            ans = Math.min(ans, xor);
            return;
        }
        dfs(i - 1, j, xor, grid, vis);
        dfs(i, j - 1, xor, grid, vis);
    }
}
