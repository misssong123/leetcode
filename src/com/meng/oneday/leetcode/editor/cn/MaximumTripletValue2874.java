package com.meng.oneday.leetcode.editor.cn;

class MaximumTripletValue2874 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了83.61% 的Java用户
     * 	内存消耗:56.2 MB,击败了41.38% 的Java用户
     * @param nums
     * @return
     */
    public long maximumTripletValue(int[] nums) {
        long res = 0;
        int max = Math.max(nums[0],nums[1]);
        int diff = nums[0] - nums[1];
        for(int i = 2 ; i < nums.length ; i++){
            res = Math.max(res,(long) diff * nums[i]);
            max = Math.max(max,nums[i]);
            diff = Math.max(diff,max - nums[i]);
        }
        return res;
    }
}
