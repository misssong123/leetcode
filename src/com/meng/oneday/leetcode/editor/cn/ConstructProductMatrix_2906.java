package com.meng.oneday.leetcode.editor.cn;

class ConstructProductMatrix_2906 {
    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了60.00% 的Java用户
     * 	内存消耗:87.3 MB,击败了100.00% 的Java用户
     * @param grid
     * @return
     */
    public int[][] constructProductMatrix2906(int[][] grid) {
        int m = grid.length , n = grid[0].length;
        int[] pre = new int[m*n + 1];
        pre[0] = 1;
        //计算前缀
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                pre[i*n + j + 1] = (pre[i*n + j] * (grid[i][j] % 12345)% 12345);
            }
        }
        int[][] res = new int[m][n];
        int suf = 1;
        for (int i = m - 1 ; i >= 0 ; i--){
            for(int j = n - 1 ; j >= 0 ; j--){
                res[i][j] = (pre[i*n + j] * suf) % 12345;
                suf = (suf * (grid[i][j] % 12345))% 12345;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了100.00% 的Java用户
     * 	内存消耗:87.9 MB,击败了53.33% 的Java用户
     * @param grid
     * @return
     */
    public int[][] constructProductMatrix(int[][] grid) {
        final int MOD = 12345;
        int n = grid.length;
        int m = grid[0].length;
        int[][] p = new int[n][m];

        long suf = 1; // 后缀乘积
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                p[i][j] = (int) suf; // p[i][j] 先初始化成后缀乘积
                suf = suf * grid[i][j] % MOD;
            }
        }

        long pre = 1; // 前缀乘积
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int) (p[i][j] * pre % MOD); // 乘上前缀乘积
                pre = pre * grid[i][j] % MOD;
            }
        }

        return p;
    }

}
