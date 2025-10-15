package com.meng.oneday.leetcode.editor.cn;

class ProjectionArea883 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了25.58% 的Java用户
     * 	内存消耗:43.3 MB,击败了68.60% 的Java用户
     * @param grid
     * @return
     */
    public int projectionArea883(int[][] grid) {
        int n = grid.length;
        int ans = 0;
        int[] row = new int[n];
        int[] col = new int[n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == 0){
                    continue;
                }
                //俯视
                ans++;
                row[i] = Math.max(row[i],grid[i][j]);
                col[j] = Math.max(col[j],grid[i][j]);
            }
        }
        for(int i = 0 ; i < n ; i++){
            ans += row[i] + col[i];
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了94.19% 的Java用户
     * 	内存消耗:43.3 MB,击败了62.79% 的Java用户
     * @param grid
     * @return
     */
    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int xyArea = 0, yzArea = 0, zxArea = 0;
        for (int i = 0; i < n; i++) {
            int yzHeight = 0, zxHeight = 0;
            for (int j = 0; j < n; j++) {
                xyArea += grid[i][j] > 0 ? 1 : 0;
                yzHeight = Math.max(yzHeight, grid[j][i]);
                zxHeight = Math.max(zxHeight, grid[i][j]);
            }
            yzArea += yzHeight;
            zxArea += zxHeight;
        }
        return xyArea + yzArea + zxArea;
    }

}
