package com.meng.interview150.leetcode.editor.cn;

class Interview141UniquePathsWithObstacles {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.6 MB,击败了95.91% 的Java用户
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstaclesMy(int[][] obstacleGrid) {
        int m = obstacleGrid.length , n = obstacleGrid[0].length;
        if (obstacleGrid[m-1][n-1] == 1|| obstacleGrid[0][0] == 1){
            return 0;
        }
        obstacleGrid[0][0] = 1;
        //初始化第一列
        for(int i = 1 ; i < m ; i++){
            if(obstacleGrid[i][0] == 1|| obstacleGrid[i-1][0] == -1){
                obstacleGrid[i][0] = -1;
            }else {
                obstacleGrid[i][0] = 1;
            }
        }
        //初始化第一行
        for(int i = 1 ; i < n ; i++){
            if(obstacleGrid[0][i] == 1|| obstacleGrid[0][i-1] == -1){
                obstacleGrid[0][i] = -1;
            }else {
                obstacleGrid[0][i] = 1;
            }
        }
        for(int i = 1 ; i < m ; i++){
            for(int j = 1; j < n ; j ++){
                if (obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = -1;
                }else {
                    obstacleGrid[i][j] = Math.max(obstacleGrid[i-1][j],0) + Math.max(obstacleGrid[i][j-1],0);
                }
            }
        }
        return  obstacleGrid[m-1][n-1];
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了98.18% 的Java用户
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[m - 1];
    }
}
