package com.meng.top100.leetcode.editor.cn;

class MaximalSquare_221 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了80.58% 的Java用户
     * 	内存消耗:69.5 MB,击败了12.20% 的Java用户
     * @param matrix
     * @return
     */
    public int maximalSquare221(char[][] matrix) {
        int n = matrix.length,m = matrix[0].length;
        int[][] dp = new int[n][m];
        //初始化
        int max = 0;
        for(int i = 0 ; i < n ; i++){
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            max = Math.max(max,dp[i][0]);
        }
        for(int j = 0 ; j < m ; j++){
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            max = Math.max(max,dp[0][j]);
        }
        for(int i = 1 ; i < n ; i++){
            for(int j = 1 ; j < m ; j++){
                if (matrix[i][j] == '1'){
                    int min = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                    dp[i][j] = min + 1;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max * max;
    }
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
}
