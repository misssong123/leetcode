package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class NumDupDigitsAtMostN1012 {
    //未发现规律
    public int numDupDigitsAtMostN(int n) {
        if (n <= 10){
            return 0;
        }
        return -1;
    }




    /**
     * 方法一：记忆化搜索
     * @param n
     * @return
     * 执行用时：
     * 10 ms
     * , 在所有 Java 提交中击败了
     * 20.71%
     * 的用户
     * 内存消耗：
     * 40.8 MB
     * , 在所有 Java 提交中击败了
     * 19.28%
     * 的用户
     * 通过测试用例：
     * 129 / 129
     */
    int[][] dp;
    public int numDupDigitsAtMostN1(int n) {
        String sn = String.valueOf(n);
        dp = new int[sn.length()][1 << 10];
        for (int i = 0; i < sn.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        return n + 1 - f(0, sn, 0, true);
    }

    public int f(int mask, String sn, int i, boolean same) {
        if (i == sn.length()) {
            return 1;
        }
        if (!same && dp[i][mask] >= 0) {
            return dp[i][mask];
        }
        int res = 0, t = same ? (sn.charAt(i) - '0') : 9;
        for (int k = 0; k <= t; k++) {
            if ((mask & (1 << k)) != 0) {
                continue;
            }
            res += f(mask == 0 && k == 0 ? mask : mask | (1 << k), sn, i + 1, same && k == t);
        }
        if (!same) {
            dp[i][mask] = res;
        }
        return res;
    }

    /**
     *方法二：组合数学
     * @param n
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.1 MB
     * , 在所有 Java 提交中击败了
     * 97.86%
     * 的用户
     * 通过测试用例：
     * 129 / 129
     */
    public int numDupDigitsAtMostN2(int n) {
        String sn = String.valueOf(n);
        return n + 1 - f2(0, sn, 0, true);
    }

    public int f2(int mask, String sn, int i, boolean same) {
        if (i == sn.length()) {
            return 1;
        }
        int t = same ? sn.charAt(i) - '0' : 9, res = 0, c = Integer.bitCount(mask) + 1;
        for (int k = 0; k <= t; k++) {
            if ((mask & (1 << k)) != 0) {
                continue;
            }
            if (same && k == t) {
                res += f2(mask | (1 << t), sn, i + 1, true);
            } else if (mask == 0 && k == 0) {
                res += f2(0, sn, i + 1, false);
            } else {
                res += A(sn.length() - 1 - i, 10 - c);
            }
        }
        return res;
    }

    public int A(int x, int y) {
        int res = 1;
        for (int i = 0; i < x; i++) {
            res *= y--;
        }
        return res;
    }
}

