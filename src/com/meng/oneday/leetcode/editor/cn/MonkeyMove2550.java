package com.meng.oneday.leetcode.editor.cn;

class MonkeyMove2550 {
    private static final int MOD = 1_000_000_007;
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.8 MB,击败了20.64% 的Java用户
     * @param n
     * @return
     */
    public int monkeyMove2550(int n) {
        return (pow(2, n) - 2 + MOD) % MOD;
    }

    private int pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return (int) res;
    }
}
