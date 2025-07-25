package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MaxSum3487 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了95.83% 的Java用户
     * 	内存消耗:41.3 MB,击败了96.87% 的Java用户
     * @param nums
     * @return
     */
    public int maxSum3487(int[] nums) {
        Arrays.sort(nums);
        int len  = nums.length;
        if (nums[len-1] <= 0){
            return nums[len-1];
        }
        int res = 0;
        for(int i = len - 1; i >=0 ;i--){
            if (nums[i] <=0){
                break;
            }
            if (i == len - 1 || nums[i] != nums[i+1]){
                res += nums[i];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了22.92% 的Java用户
     * 	内存消耗:41.6 MB,击败了56.25% 的Java用户
     * @param nums
     * @return
     */
    public int maxSum(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int s = 0;
        int mx = Integer.MIN_VALUE;
        for (int x : nums) { // 一次遍历
            if (x < 0) {
                mx = Math.max(mx, x); // 计算最大负数
            } else if (set.add(x)) { // x 不在 set 中
                s += x; // 相同元素只留一个，累加元素和
            }
        }
        return set.isEmpty() ? mx : s;
    }

}
