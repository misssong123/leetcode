package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class TriangleType3024 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了49.28% 的Java用户
     * 	内存消耗:41.3 MB,击败了29.66% 的Java用户
     * @param nums
     * @return
     */
    public String triangleType3024(int[] nums) {
        Arrays.sort(nums);
        if (nums[0] + nums[1] <= nums[2]) {
            return "none";
        }
        if (nums[0]  == nums[2]) {
            return "equilateral";
        }
        if (nums[0] == nums[1] || nums[1] == nums[2] ) {
            return "isosceles";
        }
        return "scalene";
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了49.28% 的Java用户
     * 	内存消耗:41.2 MB,击败了63.64% 的Java用户
     * @param nums
     * @return
     */
    public String triangleType(int[] nums) {
        Arrays.sort(nums);
        int a = nums[0];
        int b = nums[1];
        int c = nums[2];
        if (a + b <= c) {
            return "none";
        }
        if (a == c) {
            return "equilateral";
        }
        if (a == b || b == c) {
            return "isosceles";
        }
        return "scalene";
    }

}
