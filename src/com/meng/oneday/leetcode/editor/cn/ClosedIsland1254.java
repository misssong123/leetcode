package com.meng.oneday.leetcode.editor.cn;

class ClosedIsland1254 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.6 MB,击败了69.01% 的Java用户
     * @param grid
     * @return
     */
    public int closedIsland1254(int[][] grid) {
        //统计所有与四边连接的0
        int m = grid.length,n= grid[0].length;
        //上侧
        for (int i = 0; i < n; i++) {
            if(grid[0][i] == 0){
                dfs1254(grid,0,i);
            }
        }
        //下册
        for (int i = 0; i < n; i++) {
            if(grid[m-1][i] == 0){
                dfs1254(grid,m-1,i);
            }
        }
        //左侧
        for (int i = 0; i < m; i++) {
            if(grid[i][0] == 0){
                dfs1254(grid,i,0);
            }
        }
        //右侧
        for (int i = 0; i < m; i++) {
            if(grid[i][n-1] == 0){
                dfs1254(grid,i,n-1);
            }
        }
        //统计岛屿数量
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 0){
                    res++;
                    dfs1254(grid,i,j);
                }
            }
        }
        return res;
    }

    private void dfs1254(int[][] grid, int x, int y) {
        if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length){
            return;
        }
        if(grid[x][y] == 1){
            return;
        }
        grid[x][y] = 1;
        dfs1254(grid,x+1,y);
        dfs1254(grid,x-1,y);
        dfs1254(grid,x,y+1);
        dfs1254(grid,x,y-1);
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了79.34% 的Java用户
     * 	内存消耗:45.5 MB,击败了92.15% 的Java用户
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m < 3 || n < 3) return 0;
        for (int i = 0; i < m; i++) {
            // 如果是第一行和最后一行，访问所有格子
            // 如果不是，只访问第一列和最后一列的格子
            int step = i == 0 || i == m - 1 ? 1 : n - 1;
            for (int j = 0; j < n; j += step)
                dfs(grid, i, j);
        }

        int ans = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) { // 从没有访问过的 0 出发
                    ans++; // 一定是封闭岛屿
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] != 0)
            return;
        grid[x][y] = 1; // 标记 (x,y) 被访问，避免重复访问
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }

}
