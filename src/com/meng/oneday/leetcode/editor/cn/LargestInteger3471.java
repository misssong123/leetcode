package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class LargestInteger3471 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了82.61% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int largestInteger3471(int[] nums, int k) {
       int[] cnts = new int[51];
       for (int num : nums) {
           cnts[num]++;
       }
       if (k == 1){
           for (int i = 50; i >= 0; i--) {
               if (cnts[i] == 1){
                   return i;
               }
           }
           return -1;
       }
       if(k >= nums.length){
           for (int i = 50; i >= 0; i--) {
               if (cnts[i] > 0){
                   return i;
               }
           }
           return -1;
       }
       int len = nums.length;
       if (cnts[nums[0]] == 1 || cnts[nums[len-1]] == 1){
           if (cnts[nums[0]] != 1){
               return nums[len-1];
           }
           if (cnts[nums[len-1]] != 1){
               return nums[0];
           }
           return Math.max(nums[0],nums[len-1]);
       }
       return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了47.83% 的Java用户
     * 	内存消耗:44.1 MB,击败了47.83% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if (k == n) {
            return Arrays.stream(nums).max().getAsInt();
        }
        if (k == 1) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int x : nums) {
                cnt.merge(x, 1, Integer::sum); // cnt[x]++
            }
            int ans = -1;
            for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
                if (e.getValue() == 1) {
                    ans = Math.max(ans, e.getKey());
                }
            }
            return ans;
        }
        // nums[0] 不能出现在其他地方，nums[n-1] 同理
        return Math.max(f(nums, 1, n, nums[0]), f(nums, 0, n - 1, nums[n - 1]));
    }

    private int f(int[] nums, int begin, int end, int x) {
        for (int i = begin; i < end; i++) {
            if (nums[i] == x) {
                return -1;
            }
        }
        return x;
    }
}
