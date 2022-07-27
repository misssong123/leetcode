package com.meng.level2.day13;

/**
 * 416. 分割等和子集(中等)
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class CanPartition {
    /**
     * 执行用时：
     * 17 ms
     * , 在所有 Java 提交中击败了
     * 90.07%
     * 的用户
     * 内存消耗：
     * 40 MB
     * , 在所有 Java 提交中击败了
     * 82.42%
     * 的用户
     * 通过测试用例：
     * 117 / 117
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return false;
        }
        int sum = 0;
        int maxNum = 0;
        int index = 0;
        for(int i = 0 ; i < nums.length ; i++){
            int num = nums[i];
           sum += num;
           maxNum = Math.max(maxNum,num);
           if (maxNum == num){
               index = i;
           }
        }
        if (sum % 2 != 0){
            return false;
        }
        int target = sum / 2;
        if (maxNum > target){
            return false;
        }
        if (maxNum == target){
            return true;
        }
        target = target-maxNum;
        nums[index] = Integer.MAX_VALUE;
        boolean[] flags = new boolean[target+1];
        flags[0] = true;
        for(int num : nums){
            for(int i = target ; i >=0 && i>=num ; i--){
                flags[i] = flags[i] || flags[i-num];
                if (flags[target]){
                    return true;
                }
            }
        }
        return flags[target];
    }

    public static void main(String[] args) {
        CanPartition demo = new CanPartition();
        int[] nums = {4,4,4,4,4,4,4,4,8,8,8,8,8,8,8,8,12,12,12,12,12,12,12,12,16,16,16,16,16,16,16,16,20,20,20,20,20,20,20,20,24,24,24,24,24,24,24,24,28,28,28,28,28,28,28,28,32,32,32,32,32,32,32,32,36,36,36,36,36,36,36,36,40,40,40,40,40,40,40,40,44,44,44,44,44,44,44,44,48,48,48,48,48,48,48,48,52,52,52,52,52,52,52,52,56,56,56,56,56,56,56,56,60,60,60,60,60,60,60,60,64,64,64,64,64,64,64,64,68,68,68,68,68,68,68,68,72,72,72,72,72,72,72,72,76,76,76,76,76,76,76,76,80,80,80,80,80,80,80,80,84,84,84,84,84,84,84,84,88,88,88,88,88,88,88,88,92,92,92,92,92,92,92,92,96,96,96,96,96,96,96,96,97,99};
        System.out.println(demo.canPartition(nums));
    }
    /**
     * 执行用时：
     * 25 ms
     * , 在所有 Java 提交中击败了
     * 48.26%
     * 的用户
     * 内存消耗：
     * 42.3 MB
     * , 在所有 Java 提交中击败了
     * 31.70%
     * 的用户
     * 通过测试用例：
     * 117 / 117
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

}
