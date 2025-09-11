package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class WaysToChange0811 {
    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了89.68% 的Java用户
     * 	内存消耗:42.8 MB,击败了57.94% 的Java用户
     * @param n
     * @return
     */
    public int waysToChange0811(int n) {
        if (n < 5){
            return 1;
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp,1);
        int[] coins = {5,10,25};
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                dp[i] += dp[i-coin];
                dp[i] %= 1000000007;
            }
        }
        return dp[n];
    }
    static final int MOD = 1000000007;

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.4 MB,击败了100.00% 的Java用户
     * @param n
     * @return
     */
    public int waysToChange(int n) {
        int ans = 0;
        for (int i = 0; i * 25 <= n; ++i) {
            int rest = n - i * 25;
            int a = rest / 10;
            int b = rest % 10 / 5;
            ans = (ans + (int) ((long) (a + 1) * (a + b + 1) % MOD)) % MOD;
        }
        return ans;
    }

}
