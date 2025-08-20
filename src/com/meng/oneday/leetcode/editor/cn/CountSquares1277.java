package com.meng.oneday.leetcode.editor.cn;

class CountSquares1277 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了68.40% 的Java用户
     * 	内存消耗:55.5 MB,击败了5.20% 的Java用户
     * @param matrix
     * @return
     */
    public int countSquares1277(int[][] matrix) {
        int res = 0;
        int m = matrix.length , n = matrix[0].length;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] == 0){
                    continue;
                }
                res++;
                if (i > 0 && j > 0){
                    int min = Math.min(Math.min(matrix[i-1][j],matrix[i][j-1]), matrix[i-1][j-1]);
                    res += min;
                    matrix[i][j] = min + 1;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了97.20% 的Java用户
     * 	内存消耗:55.3 MB,击败了8.40% 的Java用户
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] row = matrix[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] > 0 && i > 0 && j > 0) {
                    row[j] += Math.min(Math.min(matrix[i - 1][j], matrix[i - 1][j - 1]), row[j - 1]);
                }
                ans += row[j];
            }
        }
        return ans;
    }
}
