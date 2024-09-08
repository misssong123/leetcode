package com.meng.interview150.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class Interview091NumIslands {
    int[][] edges = {{0,1},{0,-1},{1,0},{-1,0}};
    int  n , m;

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了78.17% 的Java用户
     * 	内存消耗:48.4 MB,击败了37.25% 的Java用户
     * @param grid
     * @return
     */
    public int numIslandsMy(char[][] grid) {
        int res = 0;
        n = grid.length;
        m = grid[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == '1'){
                    res++;
                    dfs(grid,i,j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        for(int[] edge : edges){
            int newX = i + edge[0];
            int newY = j + edge[1];
            if (newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == '1'){
                dfs(grid,newX,newY);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了9.64% 的Java用户
     * 	内存消耗:47.5 MB,击败了98.54% 的Java用户
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }
}
