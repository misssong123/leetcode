package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class ModifiedMatrix3033 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了96.84% 的Java用户
     * 	内存消耗:44.5 MB,击败了52.96% 的Java用户
     * @param matrix
     * @return
     */
    public int[][] modifiedMatrixMy(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        int[][]res = new int[m][n];
        int[] max = new int[n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                res[i][j] = matrix[i][j];
                max[j] = Math.max(max[j],matrix[i][j]);
            }
        }
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (res[i][j]==-1){
                    res[i][j] = max[j];
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了96.84% 的Java用户
     * 	内存消耗:44.6 MB,击败了26.75% 的Java用户
     * @param matrix
     * @return
     */
    public int[][] modifiedMatrix(int[][] matrix) {
        for (int j = 0; j < matrix[0].length; j++) {
            int mx = 0;
            for (int[] row : matrix) {
                mx = Math.max(mx, row[j]);
            }
            for (int[] row : matrix) {
                if (row[j] == -1) {
                    row[j] = mx;
                }
            }
        }
        return matrix;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
