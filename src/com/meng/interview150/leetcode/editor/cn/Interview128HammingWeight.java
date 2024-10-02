package com.meng.interview150.leetcode.editor.cn;

class Interview128HammingWeight {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.8 MB,击败了51.17% 的Java用户
     * @param n
     * @return
     */
    public int hammingWeightMy(int n) {
        return Integer.bitCount(n);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.6 MB,击败了77.67% 的Java用户
     * @param n
     * @return
     */
    public int hammingWeight1(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.8 MB,击败了29.75% 的Java用户
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }
}
