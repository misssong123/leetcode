package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinPairSum1877 {
    /**
     * 解答成功:
     * 	执行耗时:67 ms,击败了10.00% 的Java用户
     * 	内存消耗:94.8 MB,击败了78.57% 的Java用户
     * @param nums
     * @return
     */
    public int minPairSum1877(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length - 1;
        for(int i = 0 ; i <= n/2 ; i++){
            ans = Math.max(ans,nums[i]+nums[n - i]);
        }
        return ans;
    }
}

