package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class SmallestRangeI908 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了21.43% 的Java用户
     * 	内存消耗:44 MB,击败了85.71% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeIMy(int[] nums, int k) {
        if (nums.length == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int res = nums[nums.length - 1] - nums[0];
        return res > 2 * k ? res - 2 * k : 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了80.71% 的Java用户
     * 	内存消耗:44.7 MB,击败了22.86% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeI908(int[] nums, int k) {
        int min = nums[0];
        int max = nums[0];
        for(int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        return Math.max(max - min - 2 * k, 0);
    }
}
