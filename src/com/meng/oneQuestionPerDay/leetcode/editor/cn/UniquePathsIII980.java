package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class UniquePathsIII980 {
    int res = 0,num = 0;
    int[] xIndex = {0,0,1,-1};
    int[] yIndex = {1,-1,0,0};
    boolean[][] visited;
    int startX = 0 , startY = 0,endX = 0,endY = 0;
    int row = 0 , line = 0;

    /**
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 37.78mb
     * 击败 75.39%使用 Java 的用户
     * @param grid
     * @return
     */
    public int uniquePathsIII(int[][] grid) {
        row = grid.length;
        line = grid[0].length;
        visited = new boolean[row][line];
        //找到起点和终点,以及需要走的格子数,以及障碍点
        for(int i = 0 ; i < row ; i++){
            for(int j = 0 ; j < line ; j++){
                if (grid[i][j] == 1) {//起点
                    startX = i;
                    startY = j;
                    visited[i][j] = true;
                }else if (grid[i][j] == 2){//终点
                    endX = i;
                    endY = j;
                }else if (grid[i][j] == 0){//需要走的格子数
                    num++;
                }else {//障碍点
                    visited[i][j] = true;
                }
            }
        }
        dfs(startX,startY,0);
        return res;
    }

    private void dfs(int x, int y, int n) {
        if (x == endX && y == endY ){//到达终点
            if (n == num+1){//走完所有格子,结果有效
                res++;
            }
            return;
        }
        for(int i  = 0 ; i < 4 ; i++){
            int nextX = x + xIndex[i];
            int nextY = y + yIndex[i];
            if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < line && !visited[nextX][nextY]){
                visited[nextX][nextY] = true;
                dfs(nextX,nextY,n+1);
                visited[nextX][nextY] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        UniquePathsIII980 demo = new UniquePathsIII980();
        System.out.println(demo.uniquePathsIII(grid));
    }

    /**
     * 时间
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 37.84mb
     * 击败 63.55%使用 Java 的用户
     * @param grid
     * @return
     */
    public int uniquePathsIII1(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int si = 0, sj = 0, n = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) {
                    n++;
                } else if (grid[i][j] == 1) {
                    n++;
                    si = i;
                    sj = j;
                }
            }
        }
        return dfs(grid, si, sj, n);
    }

    public int dfs(int[][] grid, int i, int j, int n) {
        if (grid[i][j] == 2) {
            return n == 0 ? 1 : 0;
        }
        int r = grid.length, c = grid[0].length;
        int t = grid[i][j];
        grid[i][j] = -1;
        int res = 0;
        for (int[] dir : dirs) {
            int ni = i + dir[0], nj = j + dir[1];
            if (ni >= 0 && ni < r && nj >= 0 && nj < c && (grid[ni][nj] == 0 || grid[ni][nj] == 2)) {
                res += dfs(grid, ni, nj, n - 1);
            }
        }
        grid[i][j] = t;
        return res;
    }

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    /**
     * 时间
     * 详情
     * 5ms
     * 击败 12.46%使用 Java 的用户
     * 内存
     * 详情
     * 39.26mb
     * 击败 17.13%使用 Java 的用户
     * @param grid
     * @return
     */
    public int uniquePathsIII2(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int si = 0, sj = 0, st = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0 || grid[i][j] == 2) {
                    st |= 1 << (i * c + j);
                } else if (grid[i][j] == 1) {
                    si = i;
                    sj = j;
                }
            }
        }
        return dp(grid, si, sj, st);
    }

    public int dp(int[][] grid, int i, int j, int st) {
        if (grid[i][j] == 2) {
            return st == 0 ? 1 : 0;
        }
        int r = grid.length, c = grid[0].length;
        int key = ((i * c + j) << (r * c)) + st;
        if (!memo.containsKey(key)) {
            int res = 0;
            for (int[] dir : dirs) {
                int ni = i + dir[0], nj = j + dir[1];
                if (ni >= 0 && ni < r && nj >= 0 && nj < c && (st & (1 << (ni * c + nj))) > 0) {
                    res += dp(grid, ni, nj, st ^ (1 << (ni * c + nj)));
                }
            }
            memo.put(key, res);
        }
        return memo.get(key);
    }

}

