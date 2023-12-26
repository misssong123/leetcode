package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class MaxStudents1349 {
    /**
     * 无思路
     * @param seats
     * @return
     */
    public int maxStudentsMy(char[][] seats) {
        return 4;
    }
    //依次移除最多影响的数据
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了11.54% 的Java用户
     * 	内存消耗:43.2 MB,击败了7.69% 的Java用户
     * @param seats
     * @return
     */
    public int maxStudents1(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        int mx = 0;
        for (int i = 0; i < 1 << n; i++) {
            mx = Math.max(mx, dp(seats, m - 1, i));
        }
        return mx;
    }

    public int dp(char[][] seats, int row, int status) {
        int n = seats[0].length;
        int key = (row << n) + status;
        if (!memo.containsKey(key)) {
            if (!isSingleRowCompliant(seats, status, n, row)) {
                memo.put(key, Integer.MIN_VALUE);
                return Integer.MIN_VALUE;
            }
            int students = Integer.bitCount(status);
            if (row == 0) {
                memo.put(key, students);
                return students;
            }
            int mx = 0;
            for (int upperRowStatus = 0; upperRowStatus < 1 << n; upperRowStatus++) {
                if (isCrossRowsCompliant(status, upperRowStatus, n)) {
                    mx = Math.max(mx, dp(seats, row - 1, upperRowStatus));
                }
            }
            memo.put(key, students + mx);
        }
        return memo.get(key);
    }

    public boolean isSingleRowCompliant(char[][] seats, int status, int n, int row) {
        for (int j = 0; j < n; j++) {
            if (((status >> j) & 1) == 1) {
                if (seats[row][j] == '#') {
                    return false;
                }
                if (j > 0 && ((status >> (j - 1)) & 1) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCrossRowsCompliant(int status, int upperRowStatus, int n) {
        for (int j = 0; j < n; j++) {
            if (((status >> j) & 1) == 1) {
                if (j > 0 && ((upperRowStatus >> (j - 1)) & 1) == 1) {
                    return false;
                }
                if (j < n - 1 && ((upperRowStatus >> (j + 1)) & 1) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *解答成功:
     * 	执行耗时:3 ms,击败了51.28% 的Java用户
     * 	内存消耗:40 MB,击败了11.54% 的Java用户
     * @param seats
     * @return
     */
    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        int[] a = new int[m]; // a[i] 是第 i 排可用椅子的下标集合
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == '.') {
                    a[i] |= 1 << j;
                }
            }
        }

        int[][] f = new int[m][1 << n];
        for (int j = 1; j < (1 << n); j++) {
            int lb = j & -j;
            f[0][j] = f[0][j & ~(lb * 3)] + 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = a[i]; j > 0; j = (j - 1) & a[i]) { // 枚举 a[i] 的子集 j
                f[i][j] = f[i - 1][a[i - 1]]; // 第 i 排空着
                for (int s = j; s > 0; s = (s - 1) & j) { // 枚举 j 的子集 s
                    if ((s & (s >> 1)) == 0) { // s 没有连续的 1
                        int t = a[i - 1] & ~(s << 1 | s >> 1); // 去掉不能坐人的位置
                        f[i][j] = Math.max(f[i][j], f[i - 1][t] + f[0][s]);
                    }
                }
            }
            f[i][0] = f[i - 1][a[i - 1]];
        }
        return f[m - 1][a[m - 1]];
    }
}

