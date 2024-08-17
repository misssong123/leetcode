package com.meng.interview150.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview037SetZeroes {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了17.16% 的Java用户
     * 	内存消耗:44.6 MB,击败了59.13% 的Java用户
     * @param matrix
     */
    public void setZeroesMy(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (col.contains(j)|| row.contains(i)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.7 MB,击败了20.91% 的Java用户
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }
}
