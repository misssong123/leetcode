package com.meng.interview150.leetcode.editor.cn;
class Interview008MaxProfit {
    /**
     * 贪心做法
     * 只要比前一天的结果大，就卖出
     * 解答成功:
     * 	执行耗时:1 ms,击败了75.60% 的Java用户
     * 	内存消耗:44.5 MB,击败了92.09% 的Java用户
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for(int i = 1 ; i < prices.length; i++){
            res+=Math.max(0,prices[i]-prices[i-1]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
