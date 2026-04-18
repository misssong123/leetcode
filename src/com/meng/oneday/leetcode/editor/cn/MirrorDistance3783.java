package com.meng.oneday.leetcode.editor.cn;

class MirrorDistance3783 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.86% 的Java用户
     * 	内存消耗:42 MB,击败了53.49% 的Java用户
     * @param n
     * @return
     */
    public int mirrorDistance3783(int n) {
        return Math.abs(n - reverse(n));
    }
    private int reverse(int n){
        int res = 0;
        while (n > 0 ){
            res = res * 10 + n % 10;
            n /= 10;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.86% 的Java用户
     * 	内存消耗:41.8 MB,击败了86.05% 的Java用户
     * @param n
     * @return
     */
    public int mirrorDistance(int n) {
        int rev = 0;
        for (int x = n; x > 0; x /= 10) {
            rev = rev * 10 + x % 10;
        }
        return Math.abs(n - rev);
    }

}
