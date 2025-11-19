package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LengthOfLIS300 {
    /**
     * 解答成功:
     * 	执行耗时:40 ms,击败了80.61% 的Java用户
     * 	内存消耗:45.25 MB,击败了7.90%的Java用户
     * @param nums
     * @return
     */
    public int lengthOfLIS300(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res = 0;
        for(int i = 0 ; i < nums.length ; i++){
            for(int j = 0 ; j < i ; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.79% 的Java用户
     * 	内存消耗:44.5 MB,击败了12.51% 的Java用户
     * @param nums
     * @return
     */
    public int lengthOfLISOther(int[] nums) {
        int ng = 0; // g 的长度
        for (int x : nums) {
            int j = lowerBound(nums, ng, x);
            nums[j] = x;
            if (j == ng) { // >=x 的 g[j] 不存在
                ng++;
            }
        }
        return ng;
    }

    // 开区间写法
    private int lowerBound(int[] nums, int right, int target) {
        int left = -1; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // 或者 left+1
    }
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了 86.14% 的Java用户
     * 	内存消耗:45.27 MB,击败了7.37% 的Java用户
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        List<Integer> g = new ArrayList<>();
        for (int x : nums) {
            int j = lowerBound(g, x);
            if (j == g.size()) {
                g.add(x); // >=x 的 g[j] 不存在
            } else {
                g.set(j, x);
            }
        }
        return g.size();
    }

    // 开区间写法
    private int lowerBound(List<Integer> g, int target) {
        int left = -1, right = g.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (g.get(mid) < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // 或者 left+1
    }

}
