package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinimumAverage3194 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了89.29% 的Java用户
     * 	内存消耗:47.5 MB,击败了78.57% 的Java用户
     * @param nums
     * @return
     */
    public double minimumAverage3194(int[] nums) {
        Arrays.sort(nums);
        double res = Double.MAX_VALUE;
        for (int i = 0 ; i < nums.length / 2 ; i++){
            res = Math.min(res, (nums[i] + nums[nums.length - i - 1]) / 2.0);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了89.29% 的Java用户
     * 	内存消耗:47.6 MB,击败了57.14% 的Java用户
     * @param nums
     * @return
     */
    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            ans = Math.min(ans, nums[i] + nums[n - 1 - i]);
        }
        return ans / 2.0;
    }
}
