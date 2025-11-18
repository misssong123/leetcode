package com.meng.top100.leetcode.editor.cn;

class MaxSubArray53 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:75.8 MB,击败了7.47% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubArray53(int[] nums) {
        int pre = 0 , res = Integer.MIN_VALUE;
        for(int num : nums){
            pre = Math.max(0,pre) + num;
            res = Math.max(res,pre);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了35.74% 的Java用户
     * 	内存消耗:75.5 MB,击败了13.01% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int minPreSum = 0;
        int preSum = 0;
        for (int x : nums) {
            preSum += x; // 当前的前缀和
            ans = Math.max(ans, preSum - minPreSum); // 减去前缀和的最小值
            minPreSum = Math.min(minPreSum, preSum); // 维护前缀和的最小值
        }
        return ans;
    }
}
