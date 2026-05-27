package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LuckyNumbers1380 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了76.60% 的Java用户
     * 	内存消耗:46.4 MB,击败了29.79% 的Java用户
     * @param matrix
     * @return
     */
    public List<Integer> luckyNumbers1380(int[][] matrix) {
        int m = matrix.length , n = matrix[0].length ;
        boolean[][] flags = new boolean[m][n];
        //每行最小值
        for (int i = 0; i < m; i++) {
            int index = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][index] > matrix[i][j]){
                    index = j;
                }
            }
            flags[i][index] = true;
        }
        //每列最大值
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index = 0;
            for (int j = 0; j < m; j++) {
                if(matrix[index][i] < matrix[j][i]){
                    index = j;
                }
            }
            if (flags[index][i]){
                res.add(matrix[index][i]);
            }

        }
        return res;
    }
    int N = 55;
    int[] row = new int[N], col = new int[N];

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了36.17% 的Java用户
     * 	内存消耗:46.3 MB,击败了38.30% 的Java用户
     * @param mat
     * @return
     */
    public List<Integer> luckyNumbers (int[][] mat) {
        int n = mat.length, m = mat[0].length;
        for (int i = 0; i < n; i++) {
            row[i] = 100001;
            for (int j = 0; j < m; j++) {
                row[i] = Math.min(row[i], mat[i][j]);
                col[j] = Math.max(col[j], mat[i][j]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int t = mat[i][j];
                if (t == row[i] && t == col[j]) ans.add(t);
            }
        }
        return ans;
    }

}
