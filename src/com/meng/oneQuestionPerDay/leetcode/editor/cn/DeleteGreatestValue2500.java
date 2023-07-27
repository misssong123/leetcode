package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class DeleteGreatestValue2500 {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 77.63%
     * 的用户
     * 内存消耗：
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 37.63%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     * @param grid
     * @return
     */
    public int deleteGreatestValue(int[][] grid) {
        for(int [] arr: grid){
            Arrays.sort(arr);
        }
        int m = grid.length , n = grid[0].length;
        int res = 0;
        for(int i = 0 ; i < n ; i++){
            int temp = Integer.MIN_VALUE;
            for(int j = 0 ; j < m ; j++){
                temp = Math.max(temp , grid[j][i]);
            }
            res += temp;
        }
        return res;
    }
}

