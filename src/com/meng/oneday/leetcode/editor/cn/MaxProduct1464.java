package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaxProduct1464 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了57.89% 的Java用户
     * 	内存消耗:44.3 MB,击败了40.35% 的Java用户
     * @param nums
     * @return
     */
    public int maxProduct1464(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return Math.max((nums[0] - 1) * (nums[1] - 1),(nums[len -1] - 1) * (nums[len -2] - 1));
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.8 MB,击败了75.44% 的Java用户
     * @param nums
     * @return
     */
    public int maxProduct1464_1(int[] nums) {
       int max1 = 0, max2 = 0;
       for (int num : nums){
           if (num > max1){
               max2 = max1;
               max1 = num;
           }else if (num > max2){
               max2 = num;
           }
       }
       return (max1 - 1) * (max2 - 1);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了77.19% 的Java用户
     * 	内存消耗:44 MB,击败了56.14% 的Java用户
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int ans = 0;
        int mx = 0;
        for (int x : nums) {
            ans = Math.max(ans, (mx - 1) * (x - 1));
            mx = Math.max(mx, x);
        }
        return ans;
    }

}
