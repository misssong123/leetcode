package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class CountSubIslands1905 {
    /**
     * 解答成功:
     * 	执行耗时:46 ms,击败了5.12% 的Java用户
     * 	内存消耗:72.5 MB,击败了80.80% 的Java用户
     * @param grid1
     * @param grid2
     * @return
     */
    public int countSubIslands1905(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int res = 0;
        for(int i = 0 ; i < m ; i ++){
            for(int j = 0 ; j < n ; j++){
                //未遍历当前节点
                if (grid2[i][j] == 1 && !visited[i][j]){
                    int count = grid1[i][j];
                    //寻找当前节点的岛屿
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[]{i,j});
                    visited[i][j] = true;
                    while (!list.isEmpty()){
                        List<int[]> temp = new ArrayList<>();
                        for(int[] cur : list){
                            //遍历当前节点可以到达的方向
                            for (int[] direction : directions){
                                int newX = cur[0] + direction[0], newY = cur[1] + direction[1];
                                if (newX >= 0 && newX < m && newY >= 0 && newY < n
                                        && grid2[newX][newY] == 1 && !visited[newX][newY]){
                                    if (grid1[newX][newY] == 0){
                                        count = 0;
                                    }
                                    visited[newX][newY] = true;
                                    temp.add(new int[]{newX,newY});
                                }
                            }
                        }
                        list.clear();
                        list.addAll(temp);
                    }
                    res += count;
                }
            }
        }
        return res;
    }
    int rc;
    int cc;

    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了96.32% 的Java用户
     * 	内存消耗:72 MB,击败了83.20% 的Java用户
     * @param grid1
     * @param grid2
     * @return
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        rc = grid1.length;
        cc = grid1[0].length;
        for (int i = 0; i < rc; i++) {
            for (int j = 0; j < cc; j++) {
                if (grid2[i][j] == 1) {
                    grid2[i][j] += grid1[i][j];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < rc; i++) {
            for (int j = 0; j < cc; j++) {
                // DFS BFS任选一个
                if (grid2[i][j] == 2 && fill(grid2, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean fill(int[][] grid, int i, int j) {
        if (i < 0 || i >= rc || j < 0 || j >= cc) {
            return true;
        }
        if (grid[i][j] != 2) {
            return grid[i][j] == 0;
        }
        grid[i][j] = 0;
        boolean down = fill(grid, i - 1, j);
        boolean up = fill(grid, i + 1, j);
        boolean right = fill(grid, i, j - 1);
        boolean left = fill(grid, i, j + 1);
        return down & up & right & left;
    }

}
