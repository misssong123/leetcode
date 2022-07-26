package com.meng.level2.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题(中等)
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 *
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 *
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 *
 * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 示例 2：
 *
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 *
 *
 * 提示：
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */
public class PacificAtlantic {
    int[] X = {-1,1,0,0},Y={0,0,-1,1};
    int[][] cache ;
    boolean[][] flags ;

    /**
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 24.33%
     * 的用户
     * 内存消耗：
     * 42.4 MB
     * , 在所有 Java 提交中击败了
     * 76.36%
     * 的用户
     * 通过测试用例：
     * 113 / 113
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length,lines = heights[0].length;
        cache = new int[rows][lines];
        flags = new boolean[rows][lines];
        List<int[]> temp = new ArrayList<>();
        for(int i = 0 ; i < rows; i++){
            flags[i][0] = true;
            cache[i][0] +=1;
            temp.add(new int[]{i,0});
        }
        for(int i = 1 ; i < lines; i++){
            flags[0][i] = true;
            cache[0][i] +=1;
            temp.add(new int[]{0,i});
        }
        dfs(heights,rows,lines,temp,1);
        for(int i = 0 ; i < rows ; i++){
            Arrays.fill(flags[i],false);
        }
        for(int i = 0 ; i < rows; i++){
            flags[i][lines-1] = true;
            cache[i][lines-1] +=2;
            temp.add(new int[]{i,lines-1});
        }
        for(int i = 0 ; i < lines-1; i++){
            flags[rows-1][i] = true;
            cache[rows-1][i] +=2;
            temp.add(new int[]{rows-1,i});
        }
        dfs(heights,rows,lines,temp,2);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < lines ; j++){
                if (cache[i][j]==3){
                    res.add(Arrays.asList(i,j));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, int rows, int lines, List<int[]> temp,int num) {
        while(temp.size() > 0){
            int size = temp.size();
            for(int i = 0 ; i < size ; i++){
                int[] remove = temp.remove(0);
                for(int step = 0 ; step < 4 ; step++){
                    int newX = remove[0] + X[step];
                    int newY = remove[1] + Y[step];
                    int dis = heights[remove[0]][remove[1]];
                    if (newX < 0 || newY < 0 || newX >= rows || newY >=lines || flags[newX][newY] || heights[newX][newY] < dis){
                        continue;
                    }
                    cache[newX][newY] += num;
                    flags[newX][newY] = true;
                    temp.add(new int[]{newX,newY});
                }
            }
        }
    }
    public static void main(String[] args) {
        PacificAtlantic demo = new PacificAtlantic();
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(demo.pacificAtlantic(heights));
        System.out.println(demo.pacificAtlantic1(heights));
    }

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] heights;
    int m, n;

    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 90.23%
     * 的用户
     * 内存消耗：
     * 42.8 MB
     * , 在所有 Java 提交中击败了
     * 39.72%
     * 的用户
     * 通过测试用例：
     * 113 / 113
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic1(int[][] heights) {
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
