package com.meng.oneday.leetcode.editor.cn;

class DivisorGame1025 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了74.12% 的Java用户
     * @param n
     * @return
     */
    public boolean divisorGame1025(int n) {
        return n % 2 == 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了22.74% 的Java用户
     * 	内存消耗:39.7 MB,击败了22.35% 的Java用户
     * @param n
     * @return
     */
    public boolean divisorGame(int n) {
        boolean[] f = new boolean[n + 5];

        f[1] = false;
        f[2] = true;
        for (int i = 3; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                if ((i % j) == 0 && !f[i - j]) {
                    f[i] = true;
                    break;
                }
            }
        }

        return f[n];
    }

}
