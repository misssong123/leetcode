package com.meng.oneday.leetcode.editor.cn;

class LenOfVDiagonal3459 {
    int[][] dirs = {{-1,1},{1,1},{1,-1},{-1,-1}};
    int[][][][] dp;

    /**
     * 解答成功:
     * 	执行耗时:408 ms,击败了51.89% 的Java用户
     * 	内存消耗:101.6 MB,击败了50.03% 的Java用户
     * @param grid
     * @return
     */
    public int lenOfVDiagonal3459(int[][] grid) {
        int m = grid.length ,n = grid[0].length;
        int res = 0;
        dp = new int[m][n][2][4];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if (grid[i][j] == 1){
                    for(int k = 0 ; k < 4 ; k++){
                        res = Math.max(res,dfs(grid,i,j,k,2,1)+1);
                    }
                }
            }
        }
        return res;
    }
    private int dfs(int[][] grid,int x, int y, int k, int target, int canReturn) {
        int m = grid.length ,n = grid[0].length;
        x = x + dirs[k][0];
        y = y + dirs[k][1];
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target){
            return 0;
        }
        //已计算
        if(dp[x][y][canReturn][k] != 0){
            return dp[x][y][canReturn][k];
        }
        //不改变
        int res = dfs(grid,x,y,k,2-target,canReturn) + 1;;
        if (canReturn == 1) {
            res = Math.max(dfs(grid,x,y,(k + 1) % 4,2-target,0) + 1,res);
        }
        return dp[x][y][canReturn][k] = res;
    }

    /**
     * 解答成功:
     * 	执行耗时:191 ms,击败了75.61% 的Java用户
     * 	内存消耗:70.7 MB,击败了87.81% 的Java用户
     * @param grid
     * @return
     */
    private static final int[][] DIRS = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    public int lenOfVDiagonalOther(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 数组维度太多影响效率，这里把 k 和 canTurn 压缩成一个 int
        int[][][] memo = new int[m][n][1 << 3];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) { // 枚举起始方向
                        ans = Math.max(ans, dfs(i, j, k, 1, 2, grid, memo) + 1);
                    }
                }
            }
        }
        return ans;
    }

    // 上一步在 (i,j)，移动方向为 DIRS[k]，是否可以右转，当前位置目标值
    private int dfs(int i, int j, int k, int canTurn, int target, int[][] grid, int[][][] memo) {
        i += DIRS[k][0];
        j += DIRS[k][1];
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != target) {
            return 0;
        }
        int mask = k << 1 | canTurn;
        if (memo[i][j][mask] > 0) { // 之前计算过
            return memo[i][j][mask];
        }
        int res = dfs(i, j, k, canTurn, 2 - target, grid, memo) + 1; // 直行
        if (canTurn == 1) {
            res = Math.max(res, dfs(i, j, (k + 1) % 4, 0, 2 - target, grid, memo) + 1); // 右转
        }
        return memo[i][j][mask] = res; // 记忆化
    }

    /**
     * 解答成功:
     * 	执行耗时:76 ms,击败了91.07% 的Java用户
     * 	内存消耗:79.4 MB,击败了69.00% 的Java用户
     * @param grid
     * @return
     */
    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] memo = new int[m][n][4];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                int[] maxs = {m - i, j + 1, i + 1, n - j}; // 理论最大值（走到底）
                for (int k = 0; k < 4; k++) { // 枚举起始方向
                    // 优化一：如果理论最大值没有超过 ans，那么不递归
                    if (maxs[k] > ans) {
                        ans = Math.max(ans, dfs(i, j, k, true, 2, grid, memo) + 1);
                    }
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j, int k, boolean canTurn, int target, int[][] grid, int[][][] memo) {
        i += DIRS[k][0];
        j += DIRS[k][1];
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != target) {
            return 0;
        }
        // 只在 canTurn=false 时读取和写入 memo
        if (!canTurn && memo[i][j][k] > 0) {
            return memo[i][j][k];
        }
        int res = dfs(i, j, k, canTurn, 2 - target, grid, memo) + 1;
        if (!canTurn) {
            return memo[i][j][k] = res;
        }
        int[] maxs = {grid.length - i, j + 1, i + 1, grid[i].length - j}; // 理论最大值（走到底）
        k = (k + 1) % 4;
        // 优化二：如果理论最大值没有超过 res，那么不递归
        if (Math.min(maxs[k], maxs[(k + 3) % 4]) > res) {
            res = Math.max(res, dfs(i, j, k, false, 2 - target, grid, memo) + 1);
        }
        return res;
    }

}
