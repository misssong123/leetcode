package com.meng.oneday.leetcode.editor.cn;

class CountIslands3619 {
    /**
     * 解答成功:
     * 	执行耗时:20 ms,击败了84.55% 的Java用户
     * 	内存消耗:112.5 MB,击败了36.59% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public int countIslands3619(int[][] grid, int k) {
        int m = grid.length , n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0){
                    long sum  = dfs3619(grid,i,j);
                    if (sum % k == 0){
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private long dfs3619(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0){
            return 0;
        }
        long sum = grid[i][j];
        grid[i][j] = 0;
        sum += dfs3619(grid,i-1,j);
        sum += dfs3619(grid,i+1,j);
        sum += dfs3619(grid,i,j-1);
        sum += dfs3619(grid,i,j+1);
        return sum;
    }

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * 解答成功:
     * 	执行耗时:20 ms,击败了84.55% 的Java用户
     * 	内存消耗:111 MB,击败了65.85% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && dfs(i, j, grid) % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private long dfs(int i, int j, int[][] grid) {
        long res = grid[i][j];
        grid[i][j] = 0; // 标记为访问过
        for (int[] d : DIRS) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < grid.length && 0 <= y && y < grid[x].length && grid[x][y] > 0) {
                res += dfs(x, y, grid);
            }
        }
        return res;
    }

}
