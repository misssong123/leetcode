package com.meng.oneday.leetcode.editor.cn;

class MaximumTripletValue2873 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了63.95% 的Java用户
     * 	内存消耗:41.1 MB,击败了89.53% 的Java用户
     * @param nums
     * @return
     */
    public long maximumTripletValue2873(int[] nums) {
        int len = nums.length;
        int[] suf = new int[len];
        for(int i = len - 2 ; i >= 0 ; i--){
            suf[i] = Math.max(suf[i + 1], nums[i + 1]);
        }
        int pre = nums[0];
        long res = 0;
        for(int i = 1 ; i < len - 1 ; i++){
            if (nums[i] < pre){
                res = Math.max(res, (long) (pre - nums[i]) * suf[i]);
            }
            pre = Math.max(pre, nums[i]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.3 MB,击败了45.35% 的Java用户
     * @param nums
     * @return
     */
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long res = 0, imax = 0, dmax = 0;
        for (int k = 0; k < n; k++) {
            res = Math.max(res, dmax * nums[k]);
            dmax = Math.max(dmax, imax - nums[k]);
            imax = Math.max(imax, nums[k]);
        }
        return res;
    }
}
