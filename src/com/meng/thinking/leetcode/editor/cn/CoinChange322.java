package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;

class CoinChange322 {
    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了97.73% 的Java用户
     * 	内存消耗:43.2 MB,击败了64.82% 的Java用户
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int coin : coins){
            if (coin > amount){
                continue;
            }
            if (coin == amount){
                return 1;
            }
            dp[coin] = 1;
            for(int i = coin + 1; i <= amount; i++){
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}