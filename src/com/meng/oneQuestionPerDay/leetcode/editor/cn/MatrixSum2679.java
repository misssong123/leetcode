package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class MatrixSum2679 {
    /**
     * 执行用时：
     * 14 ms
     * , 在所有 Java 提交中击败了
     * 77.89%
     * 的用户
     * 内存消耗：
     * 55.6 MB
     * , 在所有 Java 提交中击败了
     * 7.93%
     * 的用户
     * 通过测试用例：
     * 1057 / 1057
     * @param nums
     * @return
     */
    public int matrixSum(int[][] nums) {
        //排序每一行
        for (int [] num : nums){
            Arrays.sort(num);
        }
        int res = 0;
        //选取每一列的最大值
        for(int j = 0 ; j < nums[0].length ; j++){
            int temp = Integer.MIN_VALUE;
            for(int i = 0 ; i < nums.length ; i++){
                temp = Math.max(temp,nums[i][j]);
            }
            res += temp;
        }
        return res;
    }
}

