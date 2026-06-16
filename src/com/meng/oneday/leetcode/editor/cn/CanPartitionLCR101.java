package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class CanPartitionLCR101 {
    /**
     * 思路错误
     * @param nums
     * @return
     */
    public boolean canPartitionError(int[] nums) {
        //计算平均值
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        Set<Integer> set = new HashSet<>();
        int temp = 0;
        for (int num : nums) {
            temp += num;
            if(!set.add(temp % target)){
                return true;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了78.91% 的Java用户
     * 	内存消耗:43.1 MB,击败了59.18% 的Java用户
     * @param nums
     * @return
     */
    public boolean canPartitionLCR101(int[] nums) {
        //计算平均值
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max,num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target){
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target ; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }
}
