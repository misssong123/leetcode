package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class CanMakeArithmeticProgression1502 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了38.07% 的Java用户
     * 	内存消耗:43.6 MB,击败了26.70% 的Java用户
     * @param arr
     * @return
     */
    public boolean canMakeArithmeticProgression1502(int[] arr) {
        int len = arr.length;
        if (len == 2){
            return true;
        }
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < len; i++) {
            if (arr[i] - arr[i-1] != diff){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了96.94% 的Java用户
     * 	内存消耗:43.2 MB,击败了82.93% 的Java用户
     * @param arr
     * @return
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        int mn = Integer.MAX_VALUE;
        int mx = Integer.MIN_VALUE;
        for (int x : arr) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
        }
        if (mn == mx) { // 特殊情况：公差为 0 的等差数列
            return true;
        }

        int n = arr.length;
        if ((mx - mn) % (n - 1) > 0) { // 公差必须是整数
            return false;
        }
        int d = (mx - mn) / (n - 1); // 公差

        boolean[] has = new boolean[n];
        for (int x : arr) {
            int k = (x - mn) / d;
            if ((x - mn) % d > 0 || has[k]) { // k 不是整数或者之前遇到过
                return false;
            }
            has[k] = true;
        }
        return true;
    }
}
