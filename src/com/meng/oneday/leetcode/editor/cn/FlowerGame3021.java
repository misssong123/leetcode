package com.meng.oneday.leetcode.editor.cn;

class FlowerGame3021 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40 MB,击败了46.67% 的Java用户
     * @param n
     * @param m
     * @return
     */
    public long flowerGame3021(int n, int m) {
        return (long) ((n + 1) / 2) * (m / 2) + (long) (n / 2) * ((m + 1) / 2);
    }
}
