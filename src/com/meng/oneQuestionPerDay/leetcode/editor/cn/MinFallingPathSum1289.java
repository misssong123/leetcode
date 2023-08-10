package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

class MinFallingPathSum1289 {
    /**
     * 时间
     * 详情
     * 60ms
     * 击败 28.07%使用 Java 的用户
     * 内存
     * 详情
     * 45.78mb
     * 击败 98.60%使用 Java 的用户
     * @param grid
     * @return
     */
    public int minFallingPathSum(int[][] grid) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int n = grid.length;
        for(int i = n -2 ; i >=0 ; i--){
            //存储下一行的所有值
            for(int j = 0 ; j < n ; j++){
                queue.add(grid[i+1][j]);
            }
            //计算当前行的最小值
            for(int j = 0 ; j < n ; j++){
                //移除当前列对应的下一行的值
                queue.remove(grid[i+1][j]);
                grid[i][j] += queue.peek();
                //将移除的值加回来
                queue.add(grid[i+1][j]);
            }
            queue.clear();
        }
        //计算第一行的最小值
        return Arrays.stream(grid[0]).min().getAsInt();
    }

    public static void main(String[] args) {
        MinFallingPathSum1289 demo = new MinFallingPathSum1289();
        int[][] grid = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(demo.minFallingPathSum(grid));
        for(int[] num : grid){
            System.out.println(Arrays.toString(num));
        }
    }

    /**
     * 动态规划
     * @param grid
     * @return
     * 详情
     * 84ms
     * 击败 24.21%使用 Java 的用户
     * 内存
     * 详情
     * 48.79mb
     * 击败 22.45%使用 Java 的用户
     */
    public int minFallingPathSum1(int[][] grid) {
        int n = grid.length;
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            d[0][i] = grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k) {
                        continue;
                    }
                    d[i][j] = Math.min(d[i][j], d[i - 1][k] + grid[i][j]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res, d[n - 1][j]);
        }
        return res;
    }

    /**
     * 状态转移
     * @param grid
     * @return
     * 时间
     * 详情
     * 1ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 47.37mb
     * 击败 77.19%使用 Java 的用户
     */
    public int minFallingPathSum2(int[][] grid) {
        int n = grid.length;
        int first_min_sum = 0;
        int second_min_sum = 0;
        int first_min_index = -1;

        for (int i = 0; i < n; i++) {
            int cur_first_min_sum = Integer.MAX_VALUE;
            int cur_second_min_sum = Integer.MAX_VALUE;
            int cur_first_min_index = -1;

            for (int j = 0; j < n; j++) {
                int cur_sum = (j != first_min_index ? first_min_sum : second_min_sum) + grid[i][j];
                if (cur_sum < cur_first_min_sum) {
                    cur_second_min_sum = cur_first_min_sum;
                    cur_first_min_sum = cur_sum;
                    cur_first_min_index = j;
                } else if (cur_sum < cur_second_min_sum) {
                    cur_second_min_sum = cur_sum;
                }
            }
            first_min_sum = cur_first_min_sum;
            second_min_sum = cur_second_min_sum;
            first_min_index = cur_first_min_index;
        }
        return first_min_sum;
    }

}

