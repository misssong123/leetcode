package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class PartitionArray2294 {
    /**
     * 1.排序
     * 2.贪心
     * 解答成功:
     * 	执行耗时:32 ms,击败了94.55% 的Java用户
     * 	内存消耗:55.6 MB,击败了83.64% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int partitionArray2294(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 1,pre = nums[0];
        for(int i = 1 ; i < nums.length ; i++){
            if(nums[i] - pre > k){
                pre = nums[i];
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:32 ms,击败了94.55% 的Java用户
     * 	内存消耗:55.7 MB,击败了76.36% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        int mn = Integer.MIN_VALUE / 2; // 防止减法溢出
        for (int x : nums) {
            if (x - mn > k) { // 必须分割
                ans++;
                mn = x; // mn 是下一段的最小值
            }
        }
        return ans;
    }


}
