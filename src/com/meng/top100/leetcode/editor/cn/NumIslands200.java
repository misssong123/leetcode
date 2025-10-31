package com.meng.top100.leetcode.editor.cn;

class NumIslands200 {
    public static final int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了80.97% 的Java用户
     * 	内存消耗:51.6 MB,击败了5.06% 的Java用户
     * @param grid
     * @return
     */
    public int numIslands200(char[][] grid) {
        int res = 0 ,m = grid.length,n = grid[0].length;
        for(int i = 0 ; i < m ; i++){
            for (int j = 0 ; j < n ; j++){
                if (grid[i][j] == '1'){
                    res++;
                    dfs(grid,i,j,m,n);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '2' || grid[i][j] == '0'){
            return;
        }
        grid[i][j] = '2';
        for (int[] dir : dirs){
            dfs(grid,i + dir[0],j + dir[1],m,n);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了80.97% 的Java用户
     * 	内存消耗:51.4 MB,击败了5.06% 的Java用户
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') { // 找到了一个新的岛
                    dfs(grid, i, j); // 把这个岛插满旗子，这样后面遍历到的 '1' 一定是新的岛
                    ans++;
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int i, int j) {
        // 出界，或者不是 '1'，就不再往下递归
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2'; // 插旗！避免来回横跳无限递归
        dfs(grid, i, j - 1); // 往左走
        dfs(grid, i, j + 1); // 往右走
        dfs(grid, i - 1, j); // 往上走
        dfs(grid, i + 1, j); // 往下走
    }
}
