package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;

class Interview030minSubArrayLen {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.81% 的Java用户
     * 	内存消耗:56.8 MB,击败了5.51% 的Java用户
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLenMy(int target, int[] nums) {
        int slow = 0,res = Integer.MAX_VALUE,sum = 0;
        for(int i = 0 ; i < nums.length; i++){
            sum += nums[i];
            if (sum >= target){
                //找到不满足的位置
                while (sum >= target){
                    res = Math.min(res,i-slow+1);
                    sum -= nums[slow++];
                }
            }
        }
        return res== Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了5.24% 的Java用户
     * 	内存消耗:56.7 MB,击败了46.24% 的Java用户
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
