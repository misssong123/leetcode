package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class EqualPairs2352 {
    /**
     * 执行用时：
     * 56 ms
     * , 在所有 Java 提交中击败了
     * 25.78%
     * 的用户
     * 内存消耗：
     * 51.1 MB
     * , 在所有 Java 提交中击败了
     * 17.69%
     * 的用户
     * 通过测试用例：
     * 72 / 72
     * @param grid
     * @return
     */
    public int equalPairs(int[][] grid) {
        Map<String,Integer> cache = new HashMap<>();
        //记录当前数据每一行的值，作为map的key，出现次数作为value
        StringBuffer sb = new StringBuffer();
        int res = 0;
        int n = grid.length;
        for(int i = 0 ; i < n ; i++){
            sb.delete(0,sb.length());
            for(int j = 0 ; j < n ; j++){
                sb.append(grid[i][j]+"&");
            }
           cache.put(sb.toString(),cache.getOrDefault(sb.toString(),0)+1);
        }
        //记录每一列的值，查看出现的次数
        for(int i = 0 ; i < n ; i++){
            sb.delete(0,sb.length());
            for(int j = 0 ; j < n ; j++){
                sb.append(grid[j][i]+"&");
            }
            res += cache.getOrDefault(sb.toString(),0);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] grid = {{3,2,1},{1,7,6},{2,7,7}};
        EqualPairs2352 demo = new EqualPairs2352();
        System.out.println(demo.equalPairs(grid));;
    }

    /**
     * 执行用时：
     * 18 ms
     * , 在所有 Java 提交中击败了
     * 75.78%
     * 的用户
     * 内存消耗：
     * 51 MB
     * , 在所有 Java 提交中击败了
     * 18.16%
     * 的用户
     * 通过测试用例：
     * 72 / 72
     * @param grid
     * @return
     */
    public int equalPairs1(int[][] grid) {
        int n = grid.length;
        Map<List<Integer>, Integer> cnt = new HashMap<List<Integer>, Integer>();
        for (int[] row : grid) {
            List<Integer> arr = new ArrayList<Integer>();
            for (int num : row) {
                arr.add(num);
            }
            cnt.put(arr, cnt.getOrDefault(arr, 0) + 1);
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            List<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                arr.add(grid[i][j]);
            }
            if (cnt.containsKey(arr)) {
                res += cnt.get(arr);
            }
        }
        return res;
    }

    /**
     *执行用时：
     * 40 ms
     * , 在所有 Java 提交中击败了
     * 39.44%
     * 的用户
     * 内存消耗：
     * 47 MB
     * , 在所有 Java 提交中击败了
     * 73.91%
     * 的用户
     * 通过测试用例：
     * 72 / 72
     * @param grid
     * @return
     */
    public int equalPairs2(int[][] grid) {
        int res = 0, n = grid.length;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (equal(row, col, n, grid)) {
                    res++;
                }
            }
        }
        return res;
    }

    public boolean equal(int row, int col, int n, int[][] grid) {
        for (int i = 0; i < n; i++) {
            if (grid[row][i] != grid[i][col]) {
                return false;
            }
        }
        return true;
    }

}

