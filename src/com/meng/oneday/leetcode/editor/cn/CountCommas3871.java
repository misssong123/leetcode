package com.meng.oneday.leetcode.editor.cn;

class CountCommas3871 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了75.00% 的Java用户
     * 	内存消耗:42.1 MB,击败了50.00% 的Java用户
     * @param n
     * @return
     */
    public long countCommas3871(long n) {
        long res = 0;
        for (long i = 1000 ; i <= n ; i *= 1000){
            res += n - i + 1;
        }
        return res;
    }
}
