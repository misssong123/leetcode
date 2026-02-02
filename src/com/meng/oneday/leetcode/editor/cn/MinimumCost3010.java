package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinimumCost3010 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.51% 的Java用户
     * 	内存消耗:44.9 MB,击败了51.96% 的Java用户
     * @param nums
     * @return
     */
    public int minimumCost3010(int[] nums) {
        int first = Math.min(nums[1],nums[2]) , second = Math.max(nums[1],nums[2]);
        for(int i = 3 ; i < nums.length ; i++){
            if (nums[i] < first){
                second = first;
                first = nums[i];
            }else if (nums[i] < second){
                second = nums[i];
            }
        }
        return nums[0] + first + second;
    }
    public int minimumCost(int[] nums) {
        Arrays.sort(nums, 1, nums.length);
        return nums[0] + nums[1] + nums[2];
    }


}
