package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MinimumOperations2357 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 57.44%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 65.13%
     * 的用户
     * 通过测试用例：
     * 95 / 95
     * @param nums
     * @return
     */
    public int minimumOperations(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        int n = 0;
        for(int num : nums){
            if (num > n){
                n = num;
                count++;
            }
        }
        return count;
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39 MB
     * , 在所有 Java 提交中击败了
     * 81.80%
     * 的用户
     * 通过测试用例：
     * 95 / 95
     * @param nums
     * @return
     */
    public int minimumOperations1(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        return set.size();
    }

}

