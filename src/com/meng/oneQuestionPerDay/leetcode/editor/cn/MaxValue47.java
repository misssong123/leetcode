package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class MaxValue47 {
    public int maxValue(int[][] grid) {
        return 0;
    }

    /**
     * 动态规划
     * @param grid
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 3.89%
     * 的用户
     * 内存消耗：
     * 43.8 MB
     * , 在所有 Java 提交中击败了
     * 88.34%
     * 的用户
     * 通过测试用例：
     * 61 / 61
     */
    public int maxValue1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[2][n];
        for (int i = 0; i < m; ++i) {
            int pos = i % 2;
            for (int j = 0; j < n; ++j) {
                f[pos][j] = 0;
                if (i > 0) {
                    f[pos][j] = Math.max(f[pos][j], f[1 - pos][j]);
                }
                if (j > 0) {
                    f[pos][j] = Math.max(f[pos][j], f[pos][j - 1]);
                }
                f[pos][j] += grid[i][j];
            }
        }
        return f[(m - 1) % 2][n - 1];
    }
}

