package com.meng.oneday.leetcode.editor.cn;

class FindClosest3516 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.1 MB,击败了61.19% 的Java用户
     * @param x
     * @param y
     * @param z
     * @return
     */
    public int findClosest3516(int x, int y, int z) {
        int a = Math.abs(x-z);
        int b = Math.abs(y-z);
        return a  < b ? 1 : a > b ? 2 : 0;
    }
}
