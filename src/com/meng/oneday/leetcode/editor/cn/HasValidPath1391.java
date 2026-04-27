package com.meng.oneday.leetcode.editor.cn;

class HasValidPath1391 {
    int [][] lDirs = {{1,4,6},{},{1,4,6},{},{1,4,6},{}};
    int [][] rDirs = {{1,3,5},{},{},{1,3,5},{},{1,3,5}};
    int [][] tDirs = {{},{2,3,4},{},{},{2,3,4},{2,3,4}};
    int [][] dDirs = {{},{2,5,6},{2,5,6},{2,5,6},{},{}};
    boolean flag;
    int m,n;

    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了58.06% 的Java用户
     * 	内存消耗:97.6 MB,击败了43.55% 的Java用户
     * @param grid
     * @return
     */
    public boolean hasValidPath1391(int[][] grid) {
        flag = false;
        m = grid.length ;
        n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        dfs(grid,0,0,visited);
        return flag;
    }

    private void dfs(int[][] grid, int x, int y, boolean[][] visited) {
        if (flag){
            return;
        }
        //找到数据
        if (x == m - 1 && y == n - 1){
            flag = true;
            return;
        }
        //左侧
        if(y - 1 >= 0 && !visited[x][y-1] && canMove(lDirs[grid[x][y]-1],grid[x][y-1])){
            visited[x][y-1] = true;
            dfs(grid,x,y-1,visited);
            visited[x][y-1] = false;
        }
        //右侧
        if(y + 1 < n && !visited[x][y+1] && canMove(rDirs[grid[x][y]-1],grid[x][y+1])){
            visited[x][y+1] = true;
            dfs(grid,x,y+1,visited);
            visited[x][y+1] = false;
        }
        //上侧
        if(x - 1 >= 0 && !visited[x-1][y] && canMove(tDirs[grid[x][y]-1],grid[x-1][y])){
            visited[x-1][y] = true;
            dfs(grid,x-1,y,visited);
            visited[x-1][y] = false;
        }
        //下侧
        if(x + 1 < m && !visited[x+1][y] && canMove(dDirs[grid[x][y]-1],grid[x+1][y])){
            visited[x+1][y] = true;
            dfs(grid,x+1,y,visited);
            visited[x+1][y] = false;
        }
    }

    private boolean canMove(int[] dir, int index) {
        if (dir == null){
            return false;
        }
        for(int d : dir){
            if (d == index){
                return true;
            }
        }
        return false;
    }
    private static final int[][][] DIRS = {
            {},
            {{0, -1}, {0, 1}},  // 站在街道 1，可以往左或者往右
            {{-1, 0}, {1, 0}},  // 站在街道 2，可以往上或者往下
            {{0, -1}, {1, 0}},  // 站在街道 3，可以往左或者往下
            {{0, 1}, {1, 0}},   // 站在街道 4，可以往右或者往下
            {{0, -1}, {-1, 0}}, // 站在街道 5，可以往左或者往上
            {{0, 1}, {-1, 0}},  // 站在街道 6，可以往右或者往上
    };

    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了58.06% 的Java用户
     * 	内存消耗:99 MB,击败了24.19% 的Java用户
     * @param grid
     * @return
     */
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        return dfs(0, 0, grid, vis);
    }

    private boolean dfs(int x, int y, int[][] grid, boolean[][] vis) {
        int m = grid.length;
        int n = grid[x].length;
        if (x == m - 1 && y == n - 1) {
            return true;
        }
        vis[x][y] = true; // 标记 (x, y) 访问过，从而避免重复访问
        for (int[] d : DIRS[grid[x][y]]) { // 枚举下一步往哪走
            int i = x + d[0];
            int j = y + d[1];
            if (0 <= i && i < m && 0 <= j && j < n && !vis[i][j] &&
                    contains(grid[i][j], -d[0], -d[1]) && dfs(i, j, grid, vis)) {
                return true;
            }
        }
        return false;
    }

    // 判断街道 street 是否包含移动方向 (dx, dy)
    private boolean contains(int street, int dx, int dy) {
        int[][] ds = DIRS[street];
        return ds[0][0] == dx && ds[0][1] == dy ||
                ds[1][0] == dx && ds[1][1] == dy;
    }

}
