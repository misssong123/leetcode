package com.meng.oneday.leetcode.editor.cn;

class ProfitableSchemes879 {
    public static final int MOD = 1000000007;

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了100.00% 的Java用户
     * 	内存消耗:41 MB,击败了64.55% 的Java用户
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes879(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n+1][minProfit+1];
        dp[0][0] = 1;
        int len = group.length;
        for(int i = 0 ; i < len ; i++){
            int g = group[i];
            int p = profit[i];
            if (g > n){
                continue;
            }
            for(int j = n ; j >= g ; j--){
                for(int k = minProfit ; k >= 0 ; k--){
                    dp[j][k] = (dp[j][k] +dp[j-g][Math.max(0,k-p)])%MOD;
                }
            }
        }
        int res = 0;
        for (int i = 0 ; i <= n ; i++){
            res = (res + dp[i][minProfit])%MOD;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了91.08% 的Java用户
     * 	内存消耗:40.9 MB,击败了72.54% 的Java用户
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        int len = group.length, MOD = (int)1e9 + 7;
        for (int i = 1; i <= len; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = n; j >= members; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j - members][Math.max(0, k - earn)]) % MOD;
                }
            }
        }
        return dp[n][minProfit];
    }

}
