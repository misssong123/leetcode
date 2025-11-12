package com.meng.hot100.leetcode.editor.cn;

class MinPathSumLCR099 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了11.22% 的Java用户
     * 	内存消耗:47.1 MB,击败了5.28% 的Java用户
     * @param grid
     * @return
     */
    public int minPathSumLCR099(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                int min = Integer.MAX_VALUE;
                if(i > 0){
                    min = Math.min(min,grid[i-1][j]);
                }
                if (j > 0){
                    min = Math.min(min,grid[i][j-1]);
                }
                if (min != Integer.MAX_VALUE){
                    grid[i][j] += min;
                }
            }
        }
        return grid[m-1][n-1];
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了94.06% 的Java用户
     * 	内存消耗:47.1 MB,击败了5.28% 的Java用户
     * @param grid
     * @return
     */
    public int minPathSumOfficial(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] f = grid[0]; // 这里没有拷贝，f 和 grid[0] 都持有同一段内存
        for (int j = 1; j < n; j++) {
            f[j] += f[j - 1];
        }
        for (int i = 1; i < m; i++) {
            f[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                f[j] = Math.min(f[j - 1], f[j]) + grid[i][j];
            }
        }
        return f[n - 1];
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了38.41% 的Java用户
     * 	内存消耗:47.2 MB,击败了5.30% 的Java用户
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //初始化第一行
        for(int i = 1 ; i < n ; i++){
            grid[0][i] += grid[0][i-1];
        }
        for(int i = 1 ; i < m ; i++){
            grid[0][0] += grid[i][0];
            for(int j = 1 ; j < n ; j++){
                grid[0][j] = Math.min(grid[0][j-1],grid[0][j]) + grid[i][j];
            }
        }
        return grid[0][n-1];
    }

}
