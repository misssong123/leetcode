package com.meng.top100.leetcode.editor.cn;

import java.util.Arrays;

class SetZeroes73 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.8 MB,击败了36.64% 的Java用户
     * @param matrix
     */
    public void setZeroes73(int[][] matrix) {
        int m = matrix.length ,n = matrix[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] == 0){
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }
        for (int i = m -1 ; i >= 0 ; i--){
            for(int j = n - 1 ; j >= 0 ; j--){
                if(rows[i] == 1 || cols[j] == 1){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.7 MB,击败了82.79% 的Java用户
     * @param matrix
     */
    public void setZeroes73Prove(int[][] matrix) {
        int m = matrix.length ,n = matrix[0].length;
        int firstRow = 0 ,firstCol = 0;
        for (int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] == 0){
                   matrix[i][0] = 0;
                   matrix[0][j] = 0;
                   if (i == 0){
                       firstRow = 1;
                   }
                   if (j == 0){
                       firstCol = 1;
                   }
                }
            }
        }
        for (int i = m -1 ; i >= 0 ; i--){
            for(int j = n - 1 ; j >= 0 ; j--){
                if (i == 0 || j == 0){
                    if ((i == 0 && firstRow == 1)|| (j == 0 && firstCol == 1)){
                        matrix[i][j] = 0;
                    }
                } else if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
    }
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowHasZero = false;
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            // 倒着遍历，避免提前把 matrix[i][0] 改成 0，误认为这一行要全部变成 0
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowHasZero) {
            Arrays.fill(matrix[0], 0);
        }
    }
}
