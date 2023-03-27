package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class RestoreMatrix1605 {
    /**
     * 不存在思路
     * @param rowSum
     * @param colSum
     * @return
     */
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int rows = rowSum.length;
        int cols = colSum.length;
        int[][] res = new int[rows][cols];

        return res;
    }

    /**
     *执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 70.99%
     * 的用户
     * 内存消耗：
     * 49.3 MB
     * , 在所有 Java 提交中击败了
     * 52.67%
     * 的用户
     * 通过测试用例：
     * 84 / 84
     * @param rowSum
     * @param colSum
     * @return
     */
    public int[][] restoreMatrix1(int[] rowSum, int[] colSum) {
        int n = rowSum.length, m = colSum.length;
        int[][] matrix = new int[n][m];
        int i = 0, j = 0;
        while (i < n && j < m) {
            int v = Math.min(rowSum[i], colSum[j]);
            matrix[i][j] = v;
            rowSum[i] -= v;
            colSum[j] -= v;
            if (rowSum[i] == 0) {
                ++i;
            }
            if (colSum[j] == 0) {
                ++j;
            }
        }
        return matrix;
    }

}

