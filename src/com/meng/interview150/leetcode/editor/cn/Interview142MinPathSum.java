package com.meng.interview150.leetcode.editor.cn;

class Interview142MinPathSum {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了90.06% 的Java用户
     * 	内存消耗:46.7 MB,击败了9.95% 的Java用户
     * @param grid
     * @return
     */
    public int minPathSumMy(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if (i == 0 && j ==0){
                    continue;
                }
                if (i == 0){
                    grid[i][j] += grid[i][j-1];
                }else if (j == 0){
                    grid[i][j] += grid[i-1][j];
                }else {
                    grid[i][j] += Math.min(grid[i-1][j],grid[i][j-1]);
                }
            }
        }
        return grid[m-1][n-1];
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了90.06% 的Java用户
     * 	内存消耗:46.5 MB,击败了29.76% 的Java用户
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
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
