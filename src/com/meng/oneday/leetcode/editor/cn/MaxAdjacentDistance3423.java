package com.meng.oneday.leetcode.editor.cn;

class MaxAdjacentDistance3423 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.76% 的Java用户
     * 	内存消耗:42.6 MB,击败了51.10% 的Java用户
     * @param nums
     * @return
     */
    public int maxAdjacentDistance3423(int[] nums) {
        int len = nums.length;
        int res = Math.abs(nums[0] - nums[len - 1]);
        for(int i = 1 ; i < len ; i++){
            res = Math.max(res,Math.abs(nums[i] - nums[i - 1]));
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.76% 的Java用户
     * 	内存消耗:42.4 MB,击败了95.05% 的Java用户
     * @param nums
     * @return
     */
    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int ans = Math.abs(nums[0] - nums[n - 1]);
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, Math.abs(nums[i] - nums[i - 1]));
        }
        return ans;
    }

}
