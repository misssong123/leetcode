package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class LongestSubarray2419 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了62.83% 的Java用户
     * 	内存消耗:60 MB,击败了36.28% 的Java用户
     * @param nums
     * @return
     */
    public int longestSubarray2419(int[] nums) {
        int max = 0 ,n = 0,res = 0;
        for(int i = 0 ;i < nums.length; i++){
            if (nums[i] > max){
                max = nums[i];
                n = 1;
                res = 1;
            }else if (nums[i] == max){
                if (nums[i] == nums[i-1]){
                    n++;
                }else{
                    res = Math.max(res,n);
                    n = 1;
                }
            }
        }
        return Math.max(n,res);
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了11.50% 的Java用户
     * 	内存消耗:59.9 MB,击败了41.59% 的Java用户
     * @param nums
     * @return
     */
    public int longestSubarrayOther(int[] nums) {
        int mx = Arrays.stream(nums).max().getAsInt();
        int ans = 0;
        int cnt = 0;
        for (int x : nums) {
            if (x == mx) {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 0; // 连续 mx 断开了，重新统计
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了62.83% 的Java用户
     * 	内存消耗:60.4 MB,击败了6.19% 的Java用户
     * @param nums
     * @return
     */
    public int longestSubarray(int[] nums) {
        int ans = 0;
        int mx = 0;
        int cnt = 0;
        for (int x : nums) {
            if (x > mx) {
                // 发现新的 mx，重新统计所有内容
                mx = x;
                cnt = 1;
                ans = 1;
            } else if (x == mx) {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 0; // 连续 mx 断开了，重新统计
            }
        }
        return ans;
    }

}
