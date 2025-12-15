package com.meng.oneday.leetcode.editor.cn;

class GetDescentPeriods210 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:82.7 MB,击败了5.59% 的Java用户
     * @param prices
     * @return
     */
    public long getDescentPeriods210(int[] prices) {
        int pre = 1;
        long res = 1;
        for(int i = 1 ; i < prices.length ; i++){
            if(prices[i] == prices[i-1] -1){
                pre++;
            }else{
                pre = 1;
            }
            res += pre;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:82.7 MB,击败了5.59% 的Java用户
     * @param prices
     * @return
     */
    public long getDescentPeriods(int[] prices) {
        long ans = 0;
        int dec = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i > 0 && prices[i] == prices[i - 1] - 1) {
                dec++; // 连续递减
            } else {
                dec = 1; // 连续递减中断，重新统计
            }
            ans += dec; // dec 是右端点为 i 的连续递减子数组个数
        }
        return ans;
    }

}
