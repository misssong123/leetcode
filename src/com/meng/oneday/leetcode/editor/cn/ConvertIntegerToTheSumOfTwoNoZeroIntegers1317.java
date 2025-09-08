package com.meng.oneday.leetcode.editor.cn;

class GetNoZeroIntegers1317 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了54.69% 的Java用户
     * 	内存消耗:40.6 MB,击败了42.19% 的Java用户
     * @param n
     * @return
     */
    public int[] getNoZeroIntegers1317(int n) {
        for (int a = 1; ; a++) {
            if (!Integer.toString(a).contains("0") &&
                    !Integer.toString(n - a).contains("0")) {
                return new int[]{a, n - a};
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.4 MB,击败了89.06% 的Java用户
     * @param n
     * @return
     */
    public int[] getNoZeroIntegers(int n) {
        int a = 0;
        int base = 1; // 10^k
        for (int x = n; x > 1; x /= 10) {
            int d = x % 10;
            if (d <= 1) {
                d += 10;
                x -= 10; // 借位
            }
            // a 这一位填 d/2，比如百位数就是 d/2 * 100
            a += d / 2 * base;
            base *= 10;
        }
        return new int[]{a, n - a};
    }


}
