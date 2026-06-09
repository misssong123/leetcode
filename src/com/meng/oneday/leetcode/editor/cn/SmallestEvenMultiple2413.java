package com.meng.oneday.leetcode.editor.cn;

class SmallestEvenMultiple2413 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.4 MB,击败了71.81% 的Java用户
     * @param n
     * @return
     */
    public int smallestEvenMultiple2413(int n) {
        if (n % 2 == 0) {
            return n;
        }
        return n * 2;
    }
}
