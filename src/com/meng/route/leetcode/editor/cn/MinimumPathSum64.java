package com.meng.route.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionMinPathSum64 {
    /**
     * 执行耗时:2 ms,击败了93.44% 的Java用户
     * 	内存消耗:42.7 MB,击败了62.33% 的Java用户
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length , n = grid[0].length;
        int dp[][] = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1 ; i < m ; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int i = 1 ; i < n ; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for(int i = 1 ; i < m ; i++){
            for(int j = 1 ; j < n ; j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
