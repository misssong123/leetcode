package com.meng.oneday.leetcode.editor.cn;

class MaximumDifference2016 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.2 MB,击败了49.47% 的Java用户
     * @param nums
     * @return
     */
    public int maximumDifference2016(int[] nums) {
        int res = -1,pre = nums[0];
        for(int i = 1;i < nums.length;i++){
            if(nums[i] > pre){
                res = Math.max(res,nums[i] - pre);
            }else {
                pre = nums[i];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了90.53% 的Java用户
     * @param nums
     * @return
     */
    public int maximumDifference(int[] nums) {
        int ans = 0;
        int preMin = Integer.MAX_VALUE;
        for (int x : nums) {
            ans = Math.max(ans, x - preMin); // 把 x 当作 nums[j]
            preMin = Math.min(preMin, x);    // 把 x 当作 nums[i]
        }
        return ans > 0 ? ans : -1;
    }


}
