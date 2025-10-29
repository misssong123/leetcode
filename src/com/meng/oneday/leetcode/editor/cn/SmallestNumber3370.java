package com.meng.oneday.leetcode.editor.cn;

class SmallestNumber3370 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40 MB,击败了44.65% 的Java用户
     * @param n
     * @return
     */
    public int smallestNumber3370(int n) {
        int len = Integer.toBinaryString(n).length();
        return (1 << len) - 1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.8 MB,击败了93.08% 的Java用户
     * @param n
     * @return
     */
    public int smallestNumber(int n) {
        return (Integer.highestOneBit(n) << 1) - 1;
    }

}
