package com.meng.level2.day10;

import java.util.*;

/**
 * 994. 腐烂的橘子
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 *
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 *
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 示例 3：
 *
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 */
public class OrangesRotting {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.6 MB
     * , 在所有 Java 提交中击败了
     * 94.25%
     * 的用户
     * 通过测试用例：
     * 306 / 306
     */
    public int orangesRotting(int[][] grid) {
        int[] x = {0,0,-1,1};
        int[] y = {-1,1,0,0};
        int rows = grid.length;
        int lines = grid[0].length;
        List<int[]> cache = new ArrayList<>();
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < lines ; j++){
                if (grid[i][j] == 2){
                    cache.add(new int[]{i,j});
                }
            }
        }
        int time = 0;
        while (cache.size() > 0){
            int size = cache.size();
            boolean flag = false;
            for(int i = 0 ; i < size ; i++){
                int[] nums = cache.remove(0);
                for(int n = 0 ; n < 4 ; n++){
                    int newX = nums[0] + x[n];
                    int newY = nums[1] + y[n];
                    if (newX < 0 || newX >=rows || newY < 0 || newY >= lines){
                        continue;
                    }
                    if (grid[newX][newY] == 1){
                        grid[newX][newY] = 2;
                        cache.add(new int[]{newX,newY});
                        flag = true;
                    }
                }
            }
            if (flag){
                time++;
            }
        }
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < lines ; j++){
                if (grid[i][j] == 1){
                    return -1;
                }
            }
        }
        return time;
    }

    private void printOrigin(int[][] grip){
        for(int[] nums : grip){
            System.out.println(Arrays.toString(nums));
        }
        System.out.println("---------------------------------------------");
    }

    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        OrangesRotting demo = new OrangesRotting();
        demo.orangesRotting(grid);
    }
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 38.06%
     * 的用户
     * 内存消耗：
     * 40.8 MB
     * , 在所有 Java 提交中击败了
     * 76.62%
     * 的用户
     * 通过测试用例：
     * 306 / 306
     * @param grid
     * @return
     */
    public int orangesRotting1(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        Queue<Integer> queue = new ArrayDeque<Integer>();
        Map<Integer, Integer> depth = new HashMap<Integer, Integer>();
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] == 2) {
                    int code = r * C + c;
                    queue.add(code);
                    depth.put(code, 0);
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int code = queue.remove();
            int r = code / C, c = code % C;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int ncode = nr * C + nc;
                    queue.add(ncode);
                    depth.put(ncode, depth.get(code) + 1);
                    ans = depth.get(ncode);
                }
            }
        }
        for (int[] row: grid) {
            for (int v: row) {
                if (v == 1) {
                    return -1;
                }
            }
        }
        return ans;
    }

}
