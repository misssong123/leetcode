package com.meng.level2.day14;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和(中等)
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 *
 * 返回这三个数的和。
 *
 * 假定每组输入只存在恰好一个解。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 示例 2：
 *
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 */
public class ThreeSumClosest {
    /**
     * 执行用时：
     * 71 ms
     * , 在所有 Java 提交中击败了
     * 39.48%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 46.09%
     * 的用户
     * 通过测试用例：
     * 160 / 160
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target){
        Arrays.sort(nums);
        int len = nums.length;
        int res = Integer.MAX_VALUE;
        for(int i = 0 ; i < len ;i++){
            if (i >0 && nums[i] == nums[i-1]){
                continue;
            }
            int j = i + 1 , k = len - 1;
            while (j < k){
                int sum  = nums[i] + nums[j] + nums[k];
                if (sum == target){
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(res - target)){
                    res = sum;
                }
                if (sum > target){
                    int index = k -1;
                    while (j < index && nums[index] == nums[index+1]){
                        index--;
                    }
                    k = index;
                }else {
                    int index = j + 1;
                    while (j < k && nums[index] == nums[index-1]){
                        index++;
                    }
                    k = index;
                }
            }
        }
        return res;
    }
    /**
     * 执行用时：
     * 109 ms
     * , 在所有 Java 提交中击败了
     * 16.53%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 24.74%
     * 的用户
     * 通过测试用例：
     * 281 / 281
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

}
