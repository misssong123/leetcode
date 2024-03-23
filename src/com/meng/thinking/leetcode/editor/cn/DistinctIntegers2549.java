package com.meng.thinking.leetcode.editor.cn;

class DistinctIntegers2549 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了51.89% 的Java用户
     * @param n
     * @return
     */
    public int distinctIntegers(int n) {
        return n == 1 ?1 : n-1;
    }
}
