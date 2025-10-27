package com.meng.top100.leetcode.editor.cn;

class Rotate48 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.4 MB,击败了41.67% 的Java用户
     * @param matrix
     */
    public void rotate48(int[][] matrix) {
        int n = matrix.length;
        //层数
        for(int i = 0 ; i < n / 2 ; i++){
            for(int j = i ; j < n - i -1 ; j++){
                int x = i , y = j,temp = matrix[i][j];
                for(int k = 0 ; k < 4 ; k++){
                    int nx  = y , ny = n - 1 - x;
                    int next = matrix[nx][ny];
                    matrix[nx][ny] = temp;
                    temp = next;
                    x = nx;
                    y = ny;

                }
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.4 MB,击败了57.94% 的Java用户
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            int[] row = matrix[i];
            for (int j = i + 1; j < n; j++) { // 遍历对角线上方元素，做转置
                int tmp = row[j];
                row[j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
            for (int j = 0; j < n / 2; j++) { // 遍历左半元素，做行翻转
                int tmp = row[j];
                row[j] = row[n - 1 - j];
                row[n - 1 - j] = tmp;
            }
        }
    }

}
