package com.meng.oneday.leetcode.editor.cn;

class NumberOfPaths2435 {
    private static final int MOD = 1000000007;
    /**
     * 解答成功:
     * 	执行耗时:37 ms,击败了97.04% 的Java用户
     * 	内存消耗:88.6 MB,击败了21.18% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public int numberOfPaths2435(int[][] grid, int k) {
        int m = grid.length ,n = grid[0].length;
        int[][][] dp = new int[m][n][k];
        //初始化
        int pre = 0;
        for(int i = 0 ; i < m ; i++){
            pre = pre + grid[i][0];
            dp[i][0][(pre%k)] = 1;
        }
        pre = 0;
        for(int i = 0 ; i < n ; i++){
            pre = pre + grid[0][i];
            dp[0][i][(pre%k)] = 1;
        }
        //状态转移
        for(int i = 1 ; i < m ; i++){
            for (int j = 1 ; j < n ; j++){
                int num = grid[i][j];
                //可达路径
                for (int l = 0 ; l < k ; l++){
                    int val = dp[i-1][j][l] + dp[i][j-1][l];
                    if (val != 0){
                        int index = (l + num)%k;
                        dp[i][j][index] = (dp[i][j][index] + val)%MOD;
                    }
                }
            }
        }
        return dp[m-1][n-1][0];
    }

    /**
     * 解答成功:
     * 	执行耗时:37 ms,击败了97.04% 的Java用户
     * 	内存消耗:81.9 MB,击败了27.59% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public int numberOfPaths(int[][] grid, int k) {
        int MOD = 1_000_000_007;
        int m = grid.length;
        int n = grid[0].length;
        int[][] f = new int[n + 1][k];
        f[1][0] = 1;
        for (int[] row : grid) {
            for (int j = 0; j < n; j++) {
                int[] newF = new int[k]; // 为避免提前把 f[j+1][s] 覆盖，先保存到 newF[s] 中
                for (int s = 0; s < k; s++) {
                    int newS = (s + row[j]) % k;
                    newF[s] = (f[j + 1][newS] + f[j][newS]) % MOD;
                }
                f[j + 1] = newF; // 循环结束后再赋给 f[j+1]
            }
        }
        return f[n][0];
    }
}
