package com.meng.oneday.leetcode.editor.cn;

class MaxBottlesDrunk3100 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.51% 的Java用户
     * 	内存消耗:39.9 MB,击败了84.24% 的Java用户
     * @param numBottles
     * @param numExchange
     * @return
     */
    public int maxBottlesDrunk3100(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            ans += 1;
            numBottles = numBottles - numExchange + 1;
            numExchange++;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.51% 的Java用户
     * 	内存消耗:40.1 MB,击败了45.63% 的Java用户
     * @param numBottles
     * @param numExchange
     * @return
     */
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int ans = 0;
        while (numBottles >= numExchange) {
            ans += numExchange; // 吨吨吨~
            numBottles -= numExchange - 1;
            numExchange++;
        }
        return ans + numBottles;
    }

}
