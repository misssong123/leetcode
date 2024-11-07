package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;

class Interview150MaxProfit {
    /**
     * 执行用时分布
     * 34
     * ms
     * 击败
     * 5.67%
     * 复杂度分析
     * 消耗内存分布
     * 47.17
     * MB
     * 击败
     * 5.05%
     * @param k
     * @param prices
     * @return
     */
    int min = - 10000;
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        int[][][] dp = new int[len][k + 1][2];
        for(int[][] nums : dp){
            for (int[] num : nums){
                Arrays.fill(num,min);
            }
        }
        dfs(prices,dp,len-1,k,0);
        return dp[len-1][k][0];
    }

    private int dfs(int[] prices, int[][][] dp, int i, int k, int hold) {
        if (k < 0) {
            return min;
        }
        if (i < 0) {
            return hold == 1 ? min : 0;
        }
        if (dp[i][k][hold] != min){
            return dp[i][k][hold];
        }
        //持有股票 = 前一个不持有股票 - 今天的价格 或者 前一个持有股票
        if(hold == 1){
            return dp[i][k][hold] = Math.max(dfs(prices,dp,i-1,k-1,0) - prices[i],dfs(prices,dp,i-1,k,1));
        }
        //不持有股票 = 前一个持有股票 + 今天的价格 或者 前一个不持有股票
        return dp[i][k][hold] = Math.max(dfs(prices,dp,i-1,k,0),dfs(prices,dp,i-1,k,1) + prices[i]);
    }
}
