package com.meng.oneday.leetcode.editor.cn;

class CanPartitionGrid3546 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了83.33% 的Java用户
     * 	内存消耗:156.8 MB,击败了88.89% 的Java用户
     * @param grid
     * @return
     */
    public boolean canPartitionGrid3546(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[] rows = new long[m];
        long[] cols = new long[n];
        long sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += grid[i][j];
                rows[i] += grid[i][j];
                cols[j] += grid[i][j];
            }
        }
        if (sum % 2 != 0){
            return false;
        }
        long target = sum / 2;
        long temp = 0;
        for(int i = 0 ; i < m - 1 ; i++){
            temp += rows[i];
            if (temp == target){
                return true;
            }
        }
        temp = 0;
        for(int i = 0 ; i < n - 1 ; i++){
            temp += cols[i];
            if (temp == target){
                return true;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了27.78% 的Java用户
     * 	内存消耗:158.2 MB,击败了72.22% 的Java用户
     * @param grid
     * @return
     */
    public boolean canPartitionGrid(int[][] grid) {
        long total = 0;
        for (int[] row : grid) {
            for (int x : row) {
                total += x;
            }
        }

        // 水平分割 or 垂直分割
        return check(grid, total) || check(rotate(grid), total);
    }

    // 能否水平分割
    private boolean check(int[][] a, long total) {
        long s = 0;
        for (int i = 0; i < a.length - 1; i++) { // 最后一行无需遍历
            for (int x : a[i]) {
                s += x;
            }
            if (s * 2 == total) {
                return true;
            }
        }
        return false;
    }

    // 顺时针旋转矩阵 90°
    private int[][] rotate(int[][] a) {
        int m = a.length, n = a[0].length;
        int[][] b = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b[j][m - 1 - i] = a[i][j];
            }
        }
        return b;
    }

}
