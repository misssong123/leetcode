package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinimumCost3218 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了79.40% 的Java用户
     * @param m
     * @param n
     * @param horizontalCut
     * @param verticalCut
     * @return
     */
    public int minimumCostMy(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        int x = horizontalCut.length-1 ,xLen = horizontalCut.length,
                y = verticalCut.length-1,yLen= verticalCut.length;
        int cost = 0;
        while (x >=0 && y >=0){
            if(horizontalCut[x]  >= verticalCut[y]){
                cost += horizontalCut[x--] * (yLen - y);
            }else{
                cost += verticalCut[y--] * (xLen -x);
            }
        }
        while (x >=0){
            cost += horizontalCut[x--] * n;
        }
        while (y >=0){
            cost += verticalCut[y--] * m;
        }
        return cost;
    }
    int[][][][] memo;
    int[] horizontalCut;
    int[] verticalCut;

    /**
     * 解答成功:
     * 	执行耗时:81 ms,击败了14.29% 的Java用户
     * 	内存消耗:44.2 MB,击败了28.64% 的Java用户
     * @param m
     * @param n
     * @param horizontalCut
     * @param verticalCut
     * @return
     */
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        this.memo = new int[m][n][m][n];
        this.horizontalCut = horizontalCut;
        this.verticalCut = verticalCut;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    Arrays.fill(memo[i][j][k], -1);
                }
            }
        }
        return dp(0, 0, m - 1, n - 1);
    }

    public int dp(int row1, int col1, int row2, int col2) {
        if (row1 == row2 && col1 == col2) {
            return 0;
        }
        if (memo[row1][col1][row2][col2] < 0) {
            int res = Integer.MAX_VALUE;
            for (int i = row1; i < row2; i++) {
                res = Math.min(res, dp(row1, col1, i, col2) + dp(i + 1, col1, row2, col2) + horizontalCut[i]);
            }
            for (int i = col1; i < col2; i++) {
                res = Math.min(res, dp(row1, col1, row2, i) + dp(row1, i + 1, row2, col2) + verticalCut[i]);
            }
            memo[row1][col1][row2][col2] = res;
        }
        return memo[row1][col1][row2][col2];
    }

}
