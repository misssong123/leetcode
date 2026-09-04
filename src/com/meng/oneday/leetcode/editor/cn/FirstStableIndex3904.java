package com.meng.oneday.leetcode.editor.cn;

class FirstStableIndex3904 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了32.00% 的Java用户
     * 	内存消耗:130.4 MB,击败了24.00% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int firstStableIndex3904(int[] nums, int k) {
        int n = nums.length;
        int[] left = new int[n];
        for (int i = 0 ; i < n ; i++){
            if (i == 0){
                left[i] = nums[i];
            }else{
                left[i] = Math.max(left[i-1] , nums[i]);
            }
        }
        int res = n;
        int min = nums[n-1];
        for (int i = n-1 ; i >= 0 ; i--){
            min = Math.min(min,nums[i]);
            if (left[i] - min <= k){
                res = i;
            }
        }
        return res == n ? -1 : res;
    }

    /**
     * > 2026/09/04 10:00:52
     * 解答成功:
     * 	执行耗时:6 ms,击败了76.00% 的Java用户
     * 	内存消耗:130.5 MB,击败了12.00% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] sufMin = new int[n]; // 后缀最小值
        sufMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sufMin[i] = Math.min(sufMin[i + 1], nums[i]);
        }

        int preMax = 0; // 前缀最大值
        for (int i = 0; i < n; i++) {
            preMax = Math.max(preMax, nums[i]);
            if (preMax - sufMin[i] <= k) {
                return i;
            }
        }
        return -1;
    }
}
