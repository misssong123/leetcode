package com.meng.oneday.leetcode.editor.cn;

class SumAndMultiply3754 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了96.30% 的Java用户
     * 	内存消耗:42.1 MB,击败了37.04% 的Java用户
     * @param n
     * @return
     */
    public long sumAndMultiply3754(int n) {
        int res = 0;
        int sum = 0;
        int step = 1;
        while (n > 0) {
            int temp = n % 10;
            if (temp != 0) {
                res += temp * step;
                step *= 10;
                sum += temp;
            }
            n /= 10;
        }
        return (long) res * sum;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了96.30% 的Java用户
     * 	内存消耗:42.1 MB,击败了33.33% 的Java用户
     * @param n
     * @return
     */
    public long sumAndMultiply(int n) {
        int x = 0;
        int sum = 0;
        for (int pow10 = 1; n > 0; n /= 10) {
            int d = n % 10;
            if (d > 0) {
                x += d * pow10;
                sum += d;
                pow10 *= 10;
            }
        }
        return (long) x * sum;
    }

}
