package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinimumDifference1984 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了18.39% 的Java用户
     * 	内存消耗:45.8 MB,击败了79.27% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int minimumDifference1984(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for(int i = 0 ; i <= nums.length - k ; i ++){
            ans = Math.min(ans,nums[i+k-1] - nums[i]);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了34.78% 的Java用户
     * 	内存消耗:46.2 MB,击败了19.74% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int minimumDifference(int[] nums, int k) {

        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE;

        for (int i = k - 1; i < nums.length; i++) {

            ans = Math.min(ans, nums[i] - nums[i - k + 1]);

        }

        return ans;

    }

}

