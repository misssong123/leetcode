package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinimizeSum2567 {
    /**
     * 无思路
     * @param nums
     * @return
     */
    public int minimizeSumNoIdea(int[] nums) {
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了80.77% 的Java用户
     * 	内存消耗:58.8 MB,击败了26.92% 的Java用户
     * @param a
     * @return
     */
    public int minimizeSum(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        return Math.min(Math.min(a[n - 3] - a[0]
                        , a[n - 2] - a[1])
                        , a[n - 1] - a[2]);
    }
}
