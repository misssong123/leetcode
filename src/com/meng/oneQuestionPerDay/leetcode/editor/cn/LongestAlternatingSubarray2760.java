package com.meng.oneQuestionPerDay.leetcode.editor.cn;
class LongestAlternatingSubarray2760 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了98.25% 的Java用户
     * 	内存消耗:42.5 MB,击败了63.75% 的Java用户
     * @param nums
     * @param threshold
     * @return
     */
    public int longestAlternatingSubarrayMy(int[] nums, int threshold) {
        int res = 0 ,index = 0,len = nums.length,n=1,temp =0;
        boolean flag = false;
        while (index < len){
            if (flag){
                if (nums[index] > threshold || nums[index]% 2 != n){
                    flag = false;
                    res = Math.max(res,temp);
                    temp = 0;
                    n=1;
                    if (nums[index] % 2 == 0 && nums[index] <=threshold){
                        flag = true;
                        temp = 1;
                    }
                }else {
                    n ^= 1;
                    temp++;
                }
            }else {
                if (nums[index] % 2 == 0 && nums[index] <=threshold){
                    flag = true;
                    temp = 1;
                }
            }
            index++;
        }
        res = Math.max(res,temp);
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:175 ms,击败了5.24% 的Java用户
     * 	内存消耗:42.5 MB,击败了44.54% 的Java用户
     * @param nums
     * @param threshold
     * @return
     */
    public int longestAlternatingSubarray1(int[] nums, int threshold) {
        int res = 0, n = nums.length;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                if (isSatisfied(nums, l, r, threshold)) {
                    res = Math.max(res, r - l + 1);
                }
            }
        }
        return res;
    }

    public boolean isSatisfied(int[] nums, int l, int r, int threshold) {
        if (nums[l] % 2 != 0) {
            return false;
        }
        for (int i = l; i <= r; i++) {
            if (nums[i] > threshold || (i < r && nums[i] % 2 == nums[i + 1] % 2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了98.25% 的Java用户
     * 	内存消耗:42.6 MB,击败了42.79% 的Java用户
     * @param nums
     * @param threshold
     * @return
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int res = 0, dp = 0;
        for (int l = nums.length - 1; l >= 0; l--) {
            if (nums[l] > threshold) {
                dp = 0;
            } else if (l == nums.length - 1 || nums[l] % 2 != nums[l + 1] % 2) {
                dp++;
            } else {
                dp = 1;
            }
            if (nums[l] % 2 == 0) {
                res = Math.max(res, dp);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
