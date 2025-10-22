package com.meng.top100.leetcode.editor.cn;

class MaxProfit122 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.2 MB,击败了5.11% 的Java用户
     * @param prices
     * @return
     */
    public int maxProfit122(int[] prices) {
        int res = 0;
        for(int i = 1 ; i < prices.length ; i++){
            res += Math.max(0,prices[i] - prices[i - 1]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.8 MB,击败了82.29% 的Java用户
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int f0 = 0;
        int f1 = Integer.MIN_VALUE;
        for (int p : prices) {
            int newF0 = Math.max(f0, f1 + p);
            f1 = Math.max(f1, f0 - p);
            f0 = newF0;
        }
        return f0;
    }

}
