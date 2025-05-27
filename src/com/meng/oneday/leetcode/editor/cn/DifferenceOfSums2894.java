package com.meng.oneday.leetcode.editor.cn;

class DifferenceOfSums2894 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了6.15% 的Java用户
     * @param n
     * @param m
     * @return
     */
    public int differenceOfSums2894(int n, int m) {
        int res = 0;
        for(int i = 1 ; i <= n ; i ++){
            if (i % m == 0){
                res -= i;
            }else {
                res += i;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40 MB,击败了53.85% 的Java用户
     * @param n
     * @param m
     * @return
     */
    public int differenceOfSums(int n, int m) {
        return n * (n + 1) / 2 - n / m * (n / m + 1) * m;
    }
}
