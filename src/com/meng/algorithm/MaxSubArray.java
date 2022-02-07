package com.meng.algorithm;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int len = nums.length,ans = nums[0],cur = nums[0];
        for(int i = 1 ; i < len ; i++){
            if (nums[i] > nums[i] + cur){
                cur = nums[i];
            }else {
                cur += nums[i];
            }
            ans = Math.max(ans,cur);
        }
        return ans;
    }
}
