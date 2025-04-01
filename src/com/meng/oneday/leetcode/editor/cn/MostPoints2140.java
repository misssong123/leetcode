package com.meng.oneday.leetcode.editor.cn;

class MostPoints2140 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了34.70% 的Java用户
     * 	内存消耗:105.6 MB,击败了83.63% 的Java用户
     * @param questions
     * @return
     */
    public long mostPoints2140(int[][] questions) {
        int len = questions.length;
        long[] dp = new long[len];
        dp[len-1] = questions[len-1][0];
        for (int i = len - 2 ; i >=0 ; i--){
            dp[i] = questions[i][0];
            if (i + questions[i][1] + 1 < len){
                dp[i] = dp[i + questions[i][1] + 1] + questions[i][0];
            }
            dp[i] = Math.max(dp[i],dp[i + 1]);
        }
        return dp[0];
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了76.02% 的Java用户
     * 	内存消耗:105.9 MB,击败了47.76% 的Java用户
     * @param questions
     * @return
     */
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] f = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int j = Math.min(i + questions[i][1] + 1, n);
            f[i] = Math.max(f[i + 1], f[j] + questions[i][0]);
        }
        return f[0];
    }

}
