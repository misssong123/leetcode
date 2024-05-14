package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class CherryPickup741 {
    int num = 0;
    Map<Integer, Set<Integer>> cache = new HashMap<>();
    int ans = 0;
    boolean flag = true;

    /**
     * 超时
     * @param grid
     * @return
     */
    public int cherryPickupMy(int[][] grid) {
        int n = grid.length;
        cache.put(0,new HashSet<>());
        cache.put(1,new HashSet<>());
        for(int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < n ; j++){
                if (grid[i][j] == 1){
                    num++;
                }
            }
        }
        Set<Integer> temp = new HashSet<>();
        if (grid[0][0] == 1){
            temp.add(0);
        }
        if (grid[n-1][n-1] == 1){
            temp.add(n* n-1);
        }
        dfs(0,0,0, grid.length, grid,temp);
        return ans;
    }

    private boolean dfs(int x, int y, int move,int n, int[][] grid, Set<Integer> temp) {
        if (!flag){
            return false;
        }
        //下找终点
        if (move == 0){
            if (x == n - 1 && y == n - 1){
               //上找终点
               return dfs(n-1,n-1,1,n,grid,temp);
            }
            //右找
            if (y+1<n&&grid[x][y+1] != -1&&!cache.get(move).contains(x*n+y+1)){
                boolean addFlag = false;
                if (grid[x][y+1] == 1){
                    addFlag = temp.add(x*n+y+1);
                }
                dfs(x,y+1,move,n,grid,temp);
                if (addFlag&&grid[x][y+1] == 1){
                    temp.remove(x*n+y+1);
                }
            }
            //下找
            if (x+1<n&&grid[x+1][y] != -1&&!cache.get(move).contains((x+1)*n+y)){
                boolean addFlag = false;
                if (grid[x+1][y] == 1){
                    addFlag = temp.add((x+1)*n+y);
                }
                dfs(x+1,y,move,n,grid,temp);
                if (addFlag&&grid[x+1][y] == 1){
                    temp.remove((x+1)*n+y);
                }
            }
            if ((x+1<n&&grid[x+1][y] != -1)||(y+1<n&&grid[x][y+1] != -1)){
                return true;
            }else {
                cache.get(move).add(x*n+y);
                return false;
            }
        }else {//上找起点
            if (x == 0 && y == 0){
                ans = Math.max(ans,temp.size());
                if (ans == num){
                    flag = false;
                }
                return true;
            }
            //左找
            if (y-1>=0&&grid[x][y-1] != -1&&!cache.get(move).contains(x*n+y-1)){
                boolean addFlag = false;
                if (grid[x][y-1] == 1){
                    addFlag = temp.add(x*n+y-1);
                }
                dfs(x,y-1,move,n,grid,temp);
                if (addFlag&&grid[x][y-1] == 1){
                    temp.remove(x*n+y-1);
                }
            }
            //上找
            if (x-1>=0&&grid[x-1][y] != -1&&!cache.get(move).contains((x-1)*n+y)){
                boolean addFlag = false;
                if (grid[x-1][y] == 1){
                    addFlag = temp.add((x-1)*n+y);
                }
                dfs(x-1,y,move,n,grid,temp);
                if (addFlag&&grid[x-1][y] == 1){
                    temp.remove((x-1)*n+y);
                }
            }
            if ((x-1>=0&&grid[x-1][y] != -1)||(y-1>=0&&grid[x][y-1] != -1)){
                return true;
            }else {
                cache.get(move).add(x*n+y);
                return false;
            }
        }
    }

    /**
     * 执行耗时:16 ms,击败了19.16% 的Java用户
     * 	内存消耗:44.3 MB,击败了67.20% 的Java用户
     * @param grid
     * @return
     */
    public int cherryPickup01(int[][] grid) {
        int n = grid.length;
        int[][][] memo = new int[n * 2 - 1][n][n];
        for (int[][] m : memo) {
            for (int[] r : m) {
                Arrays.fill(r, -1); // -1 表示没有计算过
            }
        }
        return Math.max(dfs(n * 2 - 2, n - 1, n - 1, grid, memo), 0);
    }

    private int dfs(int t, int j, int k, int[][] grid, int[][][] memo) {
        // 不能出界，不能访问 -1 格子
        if (j < 0 || k < 0 || t < j || t < k || grid[t - j][j] < 0 || grid[t - k][k] < 0) {
            return Integer.MIN_VALUE;
        }
        if (t == 0) { // 此时 j = k = 0
            return grid[0][0];
        }
        if (memo[t][j][k] != -1) { // 之前计算过
            return memo[t][j][k];
        }
        int res = Math.max(
                Math.max(dfs(t - 1, j, k, grid, memo), dfs(t - 1, j, k - 1, grid, memo)),
                Math.max(dfs(t - 1, j - 1, k, grid, memo), dfs(t - 1, j - 1, k - 1, grid, memo)))
                + grid[t - j][j] + (k != j ? grid[t - k][k] : 0);
        memo[t][j][k] = res; // 记忆化
        return res;
    }

    /**
     * 	执行耗时:14 ms,击败了72.62% 的Java用户
     * 	内存消耗:44.7 MB,击败了14.28% 的Java用户
     * @param grid
     * @return
     */
    public int cherryPickup02(int[][] grid) {
        int n = grid.length;
        int[][][] f = new int[n * 2 - 1][n + 1][n + 1];
        for (int[][] m : f) {
            for (int[] r : m) {
                Arrays.fill(r, Integer.MIN_VALUE);
            }
        }
        f[0][1][1] = grid[0][0];
        for (int t = 1; t < n * 2 - 1; t++) {
            for (int j = Math.max(t - n + 1, 0); j <= Math.min(t, n - 1); j++) {
                if (grid[t - j][j] < 0) continue;
                for (int k = j; k <= Math.min(t, n - 1); k++) {
                    if (grid[t - k][k] < 0) continue;
                    f[t][j + 1][k + 1] = Math.max(Math.max(f[t - 1][j + 1][k + 1], f[t - 1][j + 1][k]), Math.max(f[t - 1][j][k + 1], f[t - 1][j][k])) +
                            grid[t - j][j] + (k != j ? grid[t - k][k] : 0);
                }
            }
        }
        return Math.max(f[n * 2 - 2][n][n], 0);
    }

    /**
     *  执行耗时:8 ms,击败了99.81% 的Java用户
     * 	内存消耗:43.4 MB,击败了90.15% 的Java用户
     * @param grid
     * @return
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][] f = new int[n + 1][n + 1];
        for (int[] r : f) {
            Arrays.fill(r, Integer.MIN_VALUE);
        }
        f[1][1] = grid[0][0];
        for (int t = 1; t < n * 2 - 1; t++) {
            for (int j = Math.min(t, n - 1); j >= Math.max(t - n + 1, 0); j--) {
                for (int k = Math.min(t, n - 1); k >= j; k--) {
                    if (grid[t - j][j] < 0 || grid[t - k][k] < 0) {
                        f[j + 1][k + 1] = Integer.MIN_VALUE;
                    } else {
                        f[j + 1][k + 1] = Math.max(Math.max(f[j + 1][k + 1], f[j + 1][k]), Math.max(f[j][k + 1], f[j][k])) +
                                grid[t - j][j] + (k != j ? grid[t - k][k] : 0);
                    }
                }
            }
        }
        return Math.max(f[n][n], 0);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
