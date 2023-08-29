package com.meng.route.leetcode.editor.cn;

class UniquePaths62 {
    /**
     * 时间
     * 详情
     * 0ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 37.29MB
     * 击败 45.81%使用 Java 的用户
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0 ; i < m ; i++){
            dp[i][0] = 1;
        }
        for(int i = 0 ; i < n ; i++){
            dp[0][i] = 1;
        }
        for(int i = 1 ; i < m ; i ++){
            for (int j = 1 ; j < n ; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        UniquePaths62 demo = new UniquePaths62();
        System.out.println(demo.uniquePaths(3, 2));
    }
}

