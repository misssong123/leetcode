package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class SolutionMinCountLCP06 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.7 MB,击败了57.11% 的Java用户
     * @param coins
     * @return
     */
    public int minCount1(int[] coins) {
        int res = 0;
        for(int coin : coins){
            res += (coin + 1 ) /2;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.7 MB,击败了52.95% 的Java用户
     * @param coins
     * @return
     */
    public int minCount(int[] coins) {
        int sum = 0;
        for (int i : coins) {
            sum += (i + 1) / 2;
        }
        return sum;
    }
}

