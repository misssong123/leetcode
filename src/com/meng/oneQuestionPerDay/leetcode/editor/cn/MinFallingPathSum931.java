package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class MinFallingPathSum931 {
    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 75.88%
     * 的用户
     * 内存消耗：
     * 43.4 MB
     * , 在所有 Java 提交中击败了
     * 9.98%
     * 的用户
     * 通过测试用例：
     * 50 / 50
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int num = matrix.length;
        for(int i = num - 2 ; i >=0 ; i--){
            for(int j = 0 ; j < num ; j++){
                int left = (j - 1 >= 0)?matrix[i+1][j-1]:Integer.MAX_VALUE;
                int right = (j + 1 < num) ? matrix[i+1][j+1]:Integer.MAX_VALUE;
                matrix[i][j] += Math.min(left,Math.min(right,matrix[i+1][j]));
            }
        }
        int res = matrix[0][0];
        for(int n : matrix[0]){
            res = Math.min(res,n);
        }
        return res;
    }

    /**
     *执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 17.89%
     * 的用户
     * 内存消耗：
     * 43.2 MB
     * , 在所有 Java 提交中击败了
     * 27.34%
     * 的用户
     * 通过测试用例：
     * 50 / 50
     * @param matrix
     * @return
     */
    public int minFallingPathSum1(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        System.arraycopy(matrix[0], 0, dp[0], 0, n);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int mn = dp[i - 1][j];
                if (j > 0) {
                    mn = Math.min(mn, dp[i - 1][j - 1]);
                }
                if (j < n - 1) {
                    mn = Math.min(mn, dp[i - 1][j + 1]);
                }
                dp[i][j] = mn + matrix[i][j];
            }
        }
        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }

}

