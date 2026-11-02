package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinMoves2462 {
    /**
     * 思路错误
     * @param nums
     * @return
     */
    public int minMoves2462(int[] nums) {
        int n = nums.length;
        long sum = 0L;
        for (int num : nums) {
            sum += num;
        }
        int res = 0;
        if (sum % n == 0){
            int avg = (int)sum / n;
            for (int num : nums) {
                res += Math.abs(num - avg);
            }
        }else {
            int avgPre = (int)sum / n;
            int avgNext = avgPre + 1;
            int res1 = 0;
            int res2 = 0;
            for (int num : nums) {
                res1 += Math.abs(num - avgPre);
                res2 += Math.abs(num - avgNext);
            }
            res = Math.min(res1,res2);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了91.80% 的Java用户
     * 	内存消耗:46.2 MB,击败了81.97% 的Java用户
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int avg = nums[nums.length / 2];
        int res = 0;
        for (int num : nums) {
            res += Math.abs(num - avg);
        }
        return res;
    }
}
