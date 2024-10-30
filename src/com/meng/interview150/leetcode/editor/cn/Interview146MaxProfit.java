package com.meng.interview150.leetcode.editor.cn;

class Interview146MaxProfit {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了66.92% 的Java用户
     * 	内存消耗:59.7 MB,击败了34.50% 的Java用户
     * @param prices
     * @return
     */
    public int maxProfitMy(int[] prices) {
        int len = prices.length;
        int[] preMax = new int[len];
        int[] postMax = new int[len];
        int min = prices[0];
        for(int i = 1 ; i < len ; i++){
            min = Math.min(min, prices[i]);
            preMax[i] = Math.max(preMax[i-1], prices[i] - min);
        }
        int max = prices[len-1];
        for(int i = len-2 ; i >=0 ; i--){
            max = Math.max(max, prices[i]);
            postMax[i] = Math.max(postMax[i+1], max - prices[i]);
        }
        int maxProfit = 0;
        for(int i = 0 ; i < len ; i++){
            maxProfit = Math.max(maxProfit, preMax[i] + postMax[i]);
        }
        return maxProfit;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:59.7 MB,击败了39.55% 的Java用户
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
