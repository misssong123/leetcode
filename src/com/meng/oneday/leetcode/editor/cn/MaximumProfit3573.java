package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaximumProfit3573 {
    long[][][] dp;

    /**
     * 解答成功:
     * 	执行耗时:222 ms,击败了10.33% 的Java用户
     * 	内存消耗:152.2 MB,击败了18.08% 的Java用户
     * @param prices
     * @param k
     * @return
     */
    public long maximumProfit3573(int[] prices, int k) {
        int n = prices.length;
        dp = new long[n][k+1][3];
        for (long[][] d : dp) {
            for (long[] dd : d) {
                Arrays.fill(dd, Long.MIN_VALUE);
            }
        }
        return dfs(prices,n-1,k,0);
    }

    private long dfs(int[] prices, int i, int k, int state) {
        //无法继续操作
        if (k < 0) {
            return Long.MIN_VALUE / 2; // 除 2 防止溢出
        }
        //无法继续购买/售卖
        if (i < 0 ){
            return state > 0 ? Long.MIN_VALUE / 2 : 0;
        }
        //已经计算过
        if(dp[i][k][state] != Long.MIN_VALUE){
            return dp[i][k][state];
        }
        int price = prices[i];
        //未持有=(上次未买或者上次购买+本次卖出或者上次做空-本次买入)
        if (state == 0) {
            return dp[i][k][state] = Math.max(Math.max(dfs(prices, i - 1, k, 0),
                    dfs(prices, i - 1, k, 1) + price),
                    dfs(prices, i - 1, k , 2) - price);
        }
        //持有 = (上次持有或者上次未买-本次买入)
        if (state == 1) {
            return dp[i][k][state] = Math.max(dfs(prices, i - 1, k, 1),
                    dfs(prices, i - 1, k-1, 0) - price);
        }
        //做空 = （上次做空或者上次购买+本次卖出）
        return dp[i][k][state] = Math.max(dfs(prices, i - 1, k, 2),
                    dfs(prices, i - 1, k-1, 0) + price);
    }
    public long maximumProfitError(int[] prices, int k) {
        int n = prices.length;
        long[][][] dp = new long[n+1][k+1][3];
        for (long[][] mat : dp) {
            for (long[] row : mat) {
                Arrays.fill(row, Long.MIN_VALUE / 2);
            }
        }
        //初始化
        for(int i = 0 ; i <= k ; i++){
            dp[0][i][0] = 0;
        }
        for (int i = 0 ; i < n ; i++){
            int price = prices[i];
            for(int j = 1 ; j <= k ; j++){
                dp[i + 1][j][0] = Math.max(dp[i][j][0],Math.max(dp[i][j][1] + price,dp[i][j][2] - price));
                dp[i + 1][j][1] = dp[i][j][1];
                dp[i + 1][j][2] = dp[i][j][2];
                if(j > 0){
                    dp[i + 1][j][1] = Math.max(dp[i][j][1],dp[i][j-1][0] - price);
                    dp[i + 1][j][2] = Math.max(dp[i][j][2],dp[i][j-1][0] + price);
                }
            }
        }
        return dp[n][k][0];
    }
    public long maximumProfitOther(int[] prices, int k) {
        int n = prices.length;
        long[][][] dp = new long[n + 1][k + 1][3];

        // 初始化所有状态为负无穷大
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                Arrays.fill(dp[i][j], Long.MIN_VALUE / 2);
            }
        }

        // 初始化：第0天，完成0笔交易，不持有任何头寸的利润为0
        for (int j = 0; j <= k; j++) {
            dp[0][j][0] = 0;
        }

        for (int i = 0; i < n; i++) {
            int price = prices[i];
            for (int j = 0; j <= k; j++) {
                // 状态0：不持有任何头寸
                // 1. 前一天也不持有，今天不操作
                dp[i + 1][j][0] = Math.max(dp[i + 1][j][0], dp[i][j][0]);
                // 2. 前一天持有普通买入，今天卖出（完成一笔交易）
                if (j > 0) {
                    dp[i + 1][j][0] = Math.max(dp[i + 1][j][0], dp[i][j - 1][1] + price);
                }
                // 3. 前一天持有做空，今天买回（完成一笔交易）
                if (j > 0) {
                    dp[i + 1][j][0] = Math.max(dp[i + 1][j][0], dp[i][j - 1][2] - price);
                }

                // 状态1：持有普通买入
                // 1. 前一天也持有普通买入，今天继续持有
                dp[i + 1][j][1] = Math.max(dp[i + 1][j][1], dp[i][j][1]);
                // 2. 前一天不持有，今天买入（开始新交易，交易笔数不变）
                dp[i + 1][j][1] = Math.max(dp[i + 1][j][1], dp[i][j][0] - price);

                // 状态2：持有做空
                // 1. 前一天也持有做空，今天继续持有
                dp[i + 1][j][2] = Math.max(dp[i + 1][j][2], dp[i][j][2]);
                // 2. 前一天不持有，今天卖出（开始新交易，交易笔数不变）
                dp[i + 1][j][2] = Math.max(dp[i + 1][j][2], dp[i][j][0] + price);
            }
        }

        // 返回所有可能交易笔数中的最大值
        long ans = 0;
        for (int j = 0; j <= k; j++) {
            ans = Math.max(ans, dp[n][j][0]);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:158 ms,击败了18.45% 的Java用户
     * 	内存消耗:155.6 MB,击败了11.81% 的Java用户
     * @param prices
     * @param k
     * @return
     */
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long[][][] dp = new long[n+1][k+1][3];
        //填充dp数组
        for(long[][] d : dp){
            for(long[] arr : d){
                Arrays.fill(arr,Long.MIN_VALUE / 2);
            }
        }
        //初始化无元素无持有的利润为0
        for(int i = 0 ; i <= k ; i++){
            dp[0][i][0] = 0;
        }
        //推到
        for(int i = 0 ; i < n ; i++){
            int p = prices[i];
            for (int j = 0 ; j <= k ; j++){
                //无持有
                dp[i + 1][j][0] = Math.max(dp[i][j][0],dp[i + 1][j][0]);
                if ( j > 0 ){
                    long max = Math.max(dp[i][j-1][1] + p ,dp[i][j-1][2] - p);
                    dp[i + 1][j][0] = Math.max(dp[i + 1][j][0],max);
                }
                //持有
                dp[i + 1][j][1] = Math.max(dp[i][j][1],dp[i][j][0] - p);
                //做空
                dp[i + 1][j][2] = Math.max(dp[i][j][2],dp[i][j][0] + p);
            }
        }
        long res = 0;
        for (int i = 0 ; i <= k ; i++){
            res = Math.max(res,dp[n][i][0]);
        }
        return res;
    }
}
