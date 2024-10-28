package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Interview138CoinChange {
    /**
     * 已目标钱为基准
     * 超时
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeMy(int[] coins, int amount) {
        int NUM = amount + 1;
        Set<Integer> cache = Arrays.stream(coins).boxed().collect(Collectors.toSet());
        int[] dp = new int[NUM];
        Arrays.fill(dp,NUM);
        dp[0] = 0;
        for(int i  = 1; i <= amount; i++) {
            for(int j = 0 ; j < i ; j++){
                if(dp[j] != NUM && cache.contains(i - j)) {
                    dp[i] = Math.min(dp[i],dp[j]+1);
                }
            }
        }
        return dp[amount] == NUM ? -1 : dp[amount];
    }

    /**
     * 已硬币为基准
     * 解答成功:
     * 	执行耗时:16 ms,击败了30.60% 的Java用户
     * 	内存消耗:43.3 MB,击败了36.90% 的Java用户
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeMyPro(int[] coins, int amount) {
        int NUM = amount + 1;
        int[] dp = new int[NUM];
        Arrays.fill(dp,NUM);
        dp[0] = 0;
        for(int num : coins){
            for(int i = num ; i <= amount ; i++){
                dp[i] = Math.min(dp[i],dp[i-num] + 1);
            }
        }
        return dp[amount] == NUM ? -1 : dp[amount];
    }

    /**
     * 解答成功:
     * 	执行耗时:37 ms,击败了8.85% 的Java用户
     * 	内存消耗:43.4 MB,击败了28.88% 的Java用户
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

}
