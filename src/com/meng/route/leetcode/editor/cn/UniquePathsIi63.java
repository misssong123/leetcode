package com.meng.route.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionUniquePathsWithObstacles {
    /**
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了40.74% 的Java用户
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0]==1){
            return 0;
        }
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i = 1 ; i < m ; i++){
            dp[i][0] = (obstacleGrid[i][0]^1) & dp[i-1][0];
        }
        for(int i = 1 ; i < n ; i++){
            dp[0][i] = (obstacleGrid[0][i]^1) & dp[0][i-1];
        }
        for(int i = 1 ; i < m ; i++){
            for(int j = 1 ; j < n ; j++){
                if (obstacleGrid[i][j]==1){
                    continue;
                }
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[m-1][n-1];
    }


}
//leetcode submit region end(Prohibit modification and deletion)
