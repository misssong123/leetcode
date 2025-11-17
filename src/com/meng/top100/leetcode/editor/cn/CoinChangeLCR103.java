package com.meng.top100.leetcode.editor.cn;

import java.util.Arrays;

class CoinChangeLCR103 {
    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了88.35% 的Java用户
     * 	内存消耗:45.5 MB,击败了5.68% 的Java用户
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeLCR103(int[] coins, int amount) {
        int MAX = amount + 1;
        int[] dp = new int[MAX];
        Arrays.fill(dp,MAX);
        dp[0] = 0;
        for(int coin : coins){
            if (coin == amount){
                return 1;
            }
            for(int i = coin; i<= amount ; i++){
                if (dp[i-coin] != MAX){
                    dp[i] = Math.min(dp[i],dp[i-coin] + 1);
                }
            }
        }
        return dp[amount] >= MAX ? -1 : dp[amount];
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了99.72% 的Java用户
     * 	内存消耗:45.4 MB,击败了8.24% 的Java用户
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int x : coins) {
            for (int c = x; c <= amount; c++) {
                f[c] = Math.min(f[c], f[c - x] + 1);
            }
        }
        int ans = f[amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }
}
