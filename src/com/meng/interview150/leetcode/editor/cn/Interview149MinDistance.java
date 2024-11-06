package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;

class Interview149MinDistance {
    /**
     * 执行用时分布
     * 3
     * ms
     * 击败
     * 98.32%
     * 复杂度分析
     * 消耗内存分布
     * 44.04
     * MB
     * 击败
     * 17.12%
     * @param word1
     * @param word2
     * @return
     */
    int MAX = 0;
    public int minDistanceMy(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        MAX = m+n;
        int[][] dp = new int[m+1][n+1];
        for(int[] num : dp){
            Arrays.fill(num,MAX);
        }
        dp[0][0] = 0;
        dfs(word1,word2,dp,m,n);
        return dp[m][n];
    }

    private int dfs(String word1, String word2, int[][] dp, int m, int n) {
        if (m <= 0){
            return n;
        }
        if (n <= 0){
            return m;
        }
        if(dp[m][n] != MAX){
            return dp[m][n];
        }
        if (word1.charAt(m-1) == word2.charAt(n-1)){
            return dp[m][n] = dfs(word1,word2,dp,m-1,n-1);
        }
        dp[m][n] = Math.min(Math.min(dfs(word1,word2,dp,m-1,n),dfs(word1,word2,dp,m,n-1)),dfs(word1,word2,dp,m-1,n-1))+1;
        return dp[m][n];
    }

    /**
     * 执行用时分布
     * 5
     * ms
     * 击败
     * 54.85%
     * 复杂度分析
     * 消耗内存分布
     * 44.05
     * MB
     * 击败
     * 15.45%
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

}
