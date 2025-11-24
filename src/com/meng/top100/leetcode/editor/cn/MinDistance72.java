package com.meng.top100.leetcode.editor.cn;

class MinDistance72 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了57.95% 的Java用户
     * 	内存消耗:46.5 MB,击败了5.06% 的Java用户
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance72(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        //初始化
        for(int i = 1 ; i <= m ; i++){
            dp[i][0] = i;
        }
        for (int j = 1; j <= n ; j++){
            dp[0][j] = j;
        }
        //状态转移
        for(int i = 1 ; i <= m ; i++){
            for(int j = 1 ; j <= n ; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了57.95% 的Java用户
     * 	内存消耗:46.4 MB,击败了7.95% 的Java用户
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
