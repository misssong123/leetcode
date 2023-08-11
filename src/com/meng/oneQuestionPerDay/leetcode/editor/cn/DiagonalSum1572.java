package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class DiagonalSum1572 {
    /**
     * 暴力法
     * @param mat
     * @return
     * 时间
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 41.89mb
     * 击败 21.67%使用 Java 的用户
     */
    public int diagonalSum(int[][] mat) {
        int n = mat.length,res = 0;
        for(int i = 0 ; i < n ; i++){
            int y = n - i - 1;
            res += mat[i][i];
            if (i != y){
                res +=  mat[i][y];
            }
        }
        return res;
    }
    /**
     *时间
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 41.32mb
     * 击败 60.31%使用 Java 的用户
     */
    public int diagonalSum1(int[][] mat) {
        int n = mat.length, sum = 0, mid = n / 2;
        for (int i = 0; i < n; ++i) {
            sum += mat[i][i] + mat[i][n - 1 - i];
        }
        return sum - mat[mid][mid] * (n & 1);
    }


}

