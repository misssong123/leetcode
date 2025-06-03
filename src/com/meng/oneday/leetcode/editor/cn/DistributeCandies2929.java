package com.meng.oneday.leetcode.editor.cn;

class DistributeCandies2929 {
    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了58.80% 的Java用户
     * 	内存消耗:40 MB,击败了81.24% 的Java用户
     * @param n
     * @param limit
     * @return
     */
    public long distributeCandies2929(int n, int limit) {
        if (n>limit*3){
            return 0;
        }
        int firstMin = Math.max(0,n-2*limit);
        int firstMax = Math.min(n,limit);
        long res = 0;
        for (int i = firstMin; i <= firstMax; i++) {
            int secondMin = Math.max(0,n-i-limit);
            int secondMax = Math.min(n-i,limit);
            res += secondMax-secondMin+1;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了64.87% 的Java用户
     * 	内存消耗:40.2 MB,击败了43.71% 的Java用户
     * @param n
     * @param limit
     * @return
     */
    public long distributeCandies(int n, int limit) {
        return c2(n + 2) - 3 * c2(n - limit + 1) + 3 * c2(n - 2 * limit) - c2(n - 3 * limit - 1);
    }

    private long c2(int n) {
        return n > 1 ? (long) n * (n - 1) / 2 : 0;
    }

}
