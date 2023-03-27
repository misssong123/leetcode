package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class Largest1BorderedSquare1139 {
    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length,m = grid[0].length;
        int res = 0;
        int[][] lines = new int[n][m];
        int[][] rows = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            for (int j = 0 ; ;){
                
            }
        }
        return res;
    }

    /**
     * 动态规划
     * @param grid
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 64.86%
     * 的用户
     * 内存消耗：
     * 42.6 MB
     * , 在所有 Java 提交中击败了
     * 24.70%
     * 的用户
     * 通过测试用例：
     * 84 / 84
     */
    public int largest1BorderedSquare1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] left = new int[m + 1][n + 1];
        int[][] up = new int[m + 1][n + 1];
        int maxBorder = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i - 1][j - 1] == 1) {
                    left[i][j] = left[i][j - 1] + 1;
                    up[i][j] = up[i - 1][j] + 1;
                    int border = Math.min(left[i][j], up[i][j]);
                    while (left[i - border + 1][j] < border || up[i][j - border + 1] < border) {
                        border--;
                    }
                    maxBorder = Math.max(maxBorder, border);
                }
            }
        }
        return maxBorder * maxBorder;
    }
}
