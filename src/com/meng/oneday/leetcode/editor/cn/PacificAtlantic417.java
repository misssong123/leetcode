package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PacificAtlantic417 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了53.21% 的Java用户
     * 	内存消耗:45.2 MB,击败了12.79% 的Java用户
     */
    private static final int[][] DIR = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int nLen,mLen;
    public List<List<Integer>> pacificAtlantic417(int[][] heights) {
        nLen = heights.length;
        mLen = heights[0].length;
        //寻找太平洋可以到达的格子
        boolean[][] pacificVis = new boolean[nLen][mLen];
        for(int i = 0 ; i < nLen ; i++){
            dfs(heights, i, 0, pacificVis); // 左边界
        }
        for(int j = 0 ; j < mLen ; j++){
            dfs(heights, 0, j, pacificVis); // 上边界
        }
        //寻找大西洋可以到达的格子
        boolean[][] atlanticVis = new boolean[nLen][mLen];
        for(int i = 0 ; i < nLen ; i++){
            dfs(heights, i, mLen-1, atlanticVis); // 右边界
        }
        for(int j = 0 ; j < mLen ; j++){
            dfs(heights, nLen-1, j, atlanticVis); // 下边界
        }
        //寻找两个海洋都可以到达的格子
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0 ; i < nLen ; i++){
            for(int j = 0 ; j < mLen ; j++){
                if(pacificVis[i][j] && atlanticVis[i][j]){
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, int i, int j, boolean[][] visited) {
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        for (int[] dir : DIR) {
            int x = i + dir[0], y = j + dir[1];
            if (0 <= x && x < nLen && 0 <= y && y < mLen && heights[x][y] >= heights[i][j]) {
                dfs(heights, x, y, visited);
            }
        }
    }
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了30.83% 的Java用户
     * 	内存消耗:45.2 MB,击败了18.08% 的Java用户
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlanticOther(int[][] heights) {
        int m = heights.length, n = heights[0].length;

        // 从太平洋边界出发
        boolean[][] pacificVis = new boolean[m][n];
        for (int j = 0; j < n; j++) {
            dfs(0, j, pacificVis, heights); // 上边界
        }
        for (int i = 1; i < m; i++) {
            dfs(i, 0, pacificVis, heights); // 左边界
        }

        // 从大西洋边界出发
        boolean[][] atlanticVis = new boolean[m][n];
        for (int j = 0; j < n; j++) {
            dfs(m - 1, j, atlanticVis, heights); // 下边界
        }
        for (int i = 0; i < m - 1; i++) {
            dfs(i, n - 1, atlanticVis, heights); // 右边界
        }

        // 交集即为答案
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificVis[i][j] && atlanticVis[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j, boolean[][] vis, int[][] heights) {
        if (vis[i][j]) { // 避免重复访问，避免反复横跳无限递归
            return;
        }
        vis[i][j] = true; // 标记 (i,j) 已访问
        for (int[] d : DIRS) { // 枚举相邻格子
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < heights.length && 0 <= y && y < heights[x].length && heights[x][y] >= heights[i][j]) { // 往高处走
                dfs(x, y, vis, heights);
            }
        }
    }
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] heights;
    int m, n;

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了53.21% 的Java用户
     * 	内存消耗:45 MB,击败了44.89% 的Java用户
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacific);
        }
        for (int j = 1; j < n; j++) {
            dfs(0, j, pacific);
        }
        for (int i = 0; i < m; i++) {
            dfs(i, n - 1, atlantic);
        }
        for (int j = 0; j < n - 1; j++) {
            dfs(m - 1, j, atlantic);
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cell = new ArrayList<Integer>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }
        return result;
    }

    public void dfs(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newCol = col + dir[1];
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, ocean);
            }
        }
    }
}
