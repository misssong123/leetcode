package com.meng.top100.leetcode.editor.cn;

class UniquePathsWithObstacles63 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.7 MB,击败了15.23% 的Java用户
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles63(int[][] obstacleGrid) {
        int m = obstacleGrid.length,n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //初始化
        for (int i = 0 ; i < m ; i++){
            if(obstacleGrid[i][0] == 1){
                dp[i][0] = -1;
            }else{
                dp[i][0] = i > 0 && dp[i-1][0] == -1 ? -1 : 1;
            }
        }
        for (int i = 0 ; i < n ; i++){
            if(obstacleGrid[0][i] == 1){
                dp[0][i] = -1;
            }else{
                dp[0][i] = i > 0 && dp[0][i-1] == -1 ? -1 : 1;
            }
        }
        //状态转移
        for(int i = 1 ; i < m ; i++){
            for(int j = 1 ; j < n ; j++){
                if(obstacleGrid[i][j] == 1 || (dp[i-1][j] == -1 && dp[i][j-1] == -1)){
                    dp[i][j] = -1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],0) + Math.max(dp[i][j-1],0);
                }
            }
        }
        return dp[m-1][n-1] == -1 ? 0 : dp[m-1][n-1];
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.7 MB,击败了12.19% 的Java用户
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] f = obstacleGrid[0];
        f[0] ^= 1; // 0 变成 1，1 变成 0
        for (int j = 1; j < n; j++) {
            f[j] = f[j] == 1 ? 0 : f[j - 1];
        }
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                f[0] = 0;
            }
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    f[j] += f[j - 1];
                } else {
                    f[j] = 0;
                }
            }
        }
        return f[n - 1];
    }

}
