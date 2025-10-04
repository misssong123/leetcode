package com.meng.oneday.leetcode.editor.cn;

class NumWaterBottles1518 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.6 MB,击败了16.75% 的Java用户
     * @param numBottles
     * @param numExchange
     * @return
     */
    public int numWaterBottles1518(int numBottles, int numExchange) {
        int res = numBottles;
        while(numBottles >= numExchange) {
            int temp = numBottles / numExchange;
            res += temp;
            numBottles = numBottles % numExchange + temp;
        }
        return res;
    }
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = 0;
        while (numBottles >= numExchange) {
            ans += numExchange; // 吨吨吨~
            numBottles -= numExchange - 1;
        }
        return ans + numBottles;
    }

}
