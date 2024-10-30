package com.meng.interview150.leetcode.editor.cn;

class Interview145MaximalSquare {
    /**
     * 解答成功:
     * 	执行耗时:90 ms,击败了11.60% 的Java用户
     * 	内存消耗:59 MB,击败了14.25% 的Java用户
     * @param matrix
     * @return
     */
    public int maximalSquareMy(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int maxLen  = Math.min(m, n);
        int len = 0;
        int[][] dp = new int[m][n];
        for (int i = 0 ; i < m ; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    len = 1;
                    dp[i][j] = 1;
                }
            }
        }
        if (len == 1){
            boolean flag = false;
            for(int l = 2 ; l <= maxLen; l++){
                for(int i = l - 1 ; i < m ; i++){
                    for(int j = l - 1 ; j < n; j++){
                        int target = l -1;
                        if(dp[i][j] >= target && dp[i-1][j] >= target && dp[i][j-1] >= target && dp[i-1][j-1] >= target){
                            dp[i][j] = l;
                            flag = true;
                            len = l;
                        }
                    }
                }
                if (!flag){
                    break;
                }
            }
        }
        return len * len;
    }
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了79.87% 的Java用户
     * 	内存消耗:59 MB,击败了11.48% 的Java用户
     */
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
        return maxSide * maxSide;
    }
}
