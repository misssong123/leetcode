package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview007MaxProfit {
    /**
     * 贪心算法
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:60.2 MB,击败了53.18% 的Java用户
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int res = 0;
        for(int price : prices){
            if (min > price){
                min = price;
                continue;
            }
            res = Math.max(res,price-min);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
