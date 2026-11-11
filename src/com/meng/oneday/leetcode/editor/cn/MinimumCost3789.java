package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinimumCost3789 {
    /**
     * 内存受限
     * @param cost1
     * @param cost2
     * @param costBoth
     * @param need1
     * @param need2
     * @return
     */
    public long minimumCostMemoryLimit(int cost1, int cost2, int costBoth, int need1, int need2) {
        if (need1 == 0 && need2 == 0){
            return 0;
        }
        if (need1 == 0){
            return (long)need2 * Math.min(cost2,costBoth);
        }
        if (need2 == 0){
            return (long)need1 * Math.min(cost1,costBoth);
        }
        if(costBoth <= cost1 && costBoth <= cost2){
            return (long) costBoth * Math.max(need1,need2);
        }
        int max = Math.max(need1, need2);
        long[][] dp = new long[max + 1][max + 1];
        //初始化
        //首行
        for(int i = 1 ; i <= max ; i++){
            dp[0][i] = dp[0][i - 1] + cost1;
        }
        //首列
        for(int i = 1 ; i <= max ; i++){
            dp[i][0] = dp[i - 1][0] + cost2;
        }
        for(int i = 1 ; i <= max ; i++){
            for(int j = 1 ; j <= max ; j++){
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + cost2, dp[i][j - 1] + cost1),dp[i - 1][j - 1] + costBoth);
            }
        }
        long res = dp[need2][need1];
        for (int i = need2 ; i <= max ; i++){
            for (int j = need1 ; j <= max ; j++){
                res = Math.min(res, dp[i][j]);
            }
        }
        return res;
    }

    /**
     * 超时
     * @param cost1
     * @param cost2
     * @param costBoth
     * @param need1
     * @param need2
     * @return
     */
    public long minimumCostTimeOut(int cost1, int cost2, int costBoth, int need1, int need2) {
        if (need1 == 0 && need2 == 0){
            return 0;
        }
        if (need1 == 0){
            return (long)need2 * Math.min(cost2,costBoth);
        }
        if (need2 == 0){
            return (long)need1 * Math.min(cost1,costBoth);
        }
        if(costBoth <= cost1 && costBoth <= cost2){
            return (long) costBoth * Math.max(need1,need2);
        }
        int max = Math.max(need1, need2);
        long[] rows = new long[max + 1];
        //首行
        for(int i = 1 ; i <= max ; i++){
            rows[i] = rows[i - 1] + cost1;
        }
        //临时存储
        long[] temp = new long[max + 1];
        long res = Long.MAX_VALUE;
        for(int i = 1 ; i <= max ; i++){
            temp[0] = (long)Math.min(cost2,costBoth) * i;
            for(int j = 1 ; j <= max ; j++){
                temp[j]= Math.min(Math.min(temp[j-1]+ cost1,rows[j] + cost2),rows[j-1]+costBoth);
                if (i >= need2 && j >= need1){
                    res = Math.min(temp[j],res);
                }
            }
            rows = Arrays.copyOf(temp,temp.length);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.9 MB,击败了88.24% 的Java用户
     * @param cost1
     * @param cost2
     * @param costBoth
     * @param need1
     * @param need2
     * @return
     */
    public long minimumCost3789(int cost1, int cost2, int costBoth, int need1, int need2) {
        long res1 = (long) cost1 * need1 + (long) cost2 * need2; // 各买各的
        if (need1 > need2) {
            int tmp = need1;
            need1 = need2;
            need2 = tmp;
            cost2 = cost1;
        }
        long res2 = (long) costBoth * need2; // 我包了
        long res3 = (long) costBoth * need1 + (long) cost2 * (need2 - need1); // 混合策略
        return Math.min(res1, Math.min(res2, res3));
    }

}
