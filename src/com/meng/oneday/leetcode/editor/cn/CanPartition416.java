package com.meng.oneday.leetcode.editor.cn;

import java.math.BigInteger;
import java.util.Arrays;

class CanPartition416 {
    boolean res = false;
    /**
     * 超时
     * @param nums
     * @return
     */
    public boolean canPartitionTimeOut(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0) {
            return false;
        }
        Arrays.sort(nums);
        dfs(nums,0,0,sum /2);
        return res;
    }

    private void dfs(int[] nums, int index, int pre, int target) {
        if(pre == target){
            res = true;
            return;
        }
        if(res||index >= nums.length){
            return;
        }
        //不选
        dfs(nums,index + 1,pre,target);
        //选
        if(pre + nums[index] <= target){
            dfs(nums,index + 1,pre + nums[index],target);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了46.45% 的Java用户
     * 	内存消耗:41.3 MB,击败了80.90% 的Java用户
     * @param nums
     * @return
     */
    public boolean canPartition416(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0) {
            return false;
        }
        Arrays.sort(nums);
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for(int num : nums){
            if (num > target) {
                return false;
            }
            if (num == target) {
                return true;
            }
            for(int j =  target ; j >= num; j--){
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了99.67% 的Java用户
     * 	内存消耗:43.6 MB,击败了41.48% 的Java用户
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 != 0) {
            return false;
        }
        s /= 2; // 注意这里把 s 减半了
        BigInteger f = BigInteger.ONE;
        for (int x : nums) {
            f = f.or(f.shiftLeft(x)); // f |= f << x;
        }
        return f.testBit(s); // 判断 f 中第 s 位是否为 1
    }

}
