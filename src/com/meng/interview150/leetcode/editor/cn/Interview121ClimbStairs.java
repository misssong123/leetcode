package com.meng.interview150.leetcode.editor.cn;

class Interview121ClimbStairs {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了18.43% 的Java用户
     * @param n
     * @return
     */
    public int climbStairsMy(int n) {
        if (n <= 2) {
            return n;
        }
        int n1 = 1 ,n2 =2;
        while (n >= 3){
            int temp = n1 + n2;
            n1 = n2;
            n2 = temp;
            n--;
        }
        return n2;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.4 MB,击败了37.27% 的Java用户
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
