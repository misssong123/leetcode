package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class SwimInWater778 {
    int n , m ;
    int[][] DIR = {{0,1},{0,-1},{1,0},{-1,0}};
    boolean flag ;
    int res ;
    boolean[][] visited;
    /**
     * 超时
     * @param grid
     * @return
     */
    public int swimInWater778(int[][] grid) {
        flag = false;
        res = Integer.MAX_VALUE;
        n = grid.length;
        m = grid[0].length;
        visited = new boolean[n][m];
        visited[0][0] = true;
        dfs(grid,0,0,grid[0][0]);
        return res;
    }
    private void dfs(int[][] grid, int i, int j,int cur) {
        //找到路径
        if (flag){
            return;
        }
        //到达终点
        if (i == n - 1 && j == m -1){
            res = Math.min(res,Math.max(cur,grid[i][j]));
            if (res == grid[i][j]){
                flag = true;
            }
            return;
        }
        //计算所有路径
        for(int[] dir : DIR){
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y] && grid[x][y] < res){
                visited[x][y] = true;
                dfs(grid,x,y,Math.max(cur,grid[x][y]));
                visited[x][y] = false;
            }
        }
    }
    private static final int[][] DIRS = {{0,1},{0,-1},{1,0},{-1,0}};

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.95% 的Java用户
     * 	内存消耗:43.2 MB,击败了96.18% 的Java用户
     * @param grid
     * @return
     */
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        int[][] VISITED = new int[N][N];
        //获取最大值
        int max = N * N -1;
        int min = Math.max(grid[0][0],grid[N-1][N-1]);
        if (min == max){
            return min;
        }
        int res = max;
        while (min <= max){
            int mid = (min + max) / 2;
            if (search(grid,0,0,mid,VISITED)){
                res = mid;
                max = mid - 1;
            }else {
                min = mid + 1;
            }
        }
        return res;
    }
    private boolean search(int[][] grid, int i, int j,int cur,int[][] VISITED) {
        int n = grid.length;
        //找到路径
        if(i == n-1 && j == n-1){
            return true;
        }
        VISITED[i][j] = cur;
        //计算所有路径
        for(int[] dir : DIRS){
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < n && y >= 0 && y < n && VISITED[x][y] != cur && grid[x][y] <= cur){
                if(search(grid,x,y,cur,VISITED)){
                    return true;
                }
            }
        }
        return false;
    }
    private static final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.95% 的Java用户
     * 	内存消耗:43.3 MB,击败了85.44% 的Java用户
     * @param grid
     * @return
     */
    public int swimInWaterOther(int[][] grid) {
        int n = grid.length;
        int[][] vis = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(vis[i], -1);
        }
        int left = Math.max(grid[0][0], grid[n - 1][n - 1]) - 1;
        int right = n * n - 1;
        while (left + 1 < right) { // 开区间二分
            int mid = left + (right - left) / 2;
            if (dfs(0, 0, mid, grid, vis)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
    // 判断在只访问 grid[i][j] <= mx 的情况下，能否到达终点
    private boolean dfs(int i, int j, int mx, int[][] grid, int[][] vis) {
        int n = grid.length;
        if (i == n - 1 && j == n - 1) { // 到达终点
            return true;
        }
        // 标记访问过，避免重复访问
        // 用 mx 区分不同时候的二分，如果 vis[x][y] != mx，说明不是本轮二分访问过的格子
        vis[i][j] = mx;
        for (int[] dir : dirs) { // 访问相邻的格子
            int x = i + dir[0], y = j + dir[1];
            if (0 <= x && x < n && 0 <= y && y < n && grid[x][y] <= mx && vis[x][y] != mx) {
                if (dfs(x, y, mx, grid, vis)) {
                    return true;
                }
            }
        }
        return false;
    }
}
