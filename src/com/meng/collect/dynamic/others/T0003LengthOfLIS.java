package com.meng.collect.dynamic.others;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 难度
 * 中等
 *
 * 3074
 *
 *
 *
 *
 *
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * 进阶：
 *
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class T0003LengthOfLIS {
    /**
     * 执行用时：
     * 67 ms
     * , 在所有 Java 提交中击败了
     * 12.36%
     * 的用户
     * 内存消耗：
     * 41 MB
     * , 在所有 Java 提交中击败了
     * 43.10%
     * 的用户
     * 通过测试用例：
     * 54 / 54
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] cache = new int[len];
        int max = 1;
        Arrays.fill(cache,1);
        for(int i = 1 ; i < len ; i++){
            for(int j = 0 ; j < i; j++){
                if (nums[i] > nums[j]){
                    cache[i] = Math.max(cache[i],cache[j]+1);
                }
            }
            max = Math.max(cache[i],max);
        }
        return max;
    }

    public static void main(String[] args) {
        T0003LengthOfLIS demo = new T0003LengthOfLIS();
        int[] nums = {1,3,6,7,9,4,10,5,6};
        System.out.println(demo.lengthOfLIS(nums));
    }

    /**
     * 执行用时：
     * 59 ms
     * , 在所有 Java 提交中击败了
     * 40.65%
     * 的用户
     * 内存消耗：
     * 40.8 MB
     * , 在所有 Java 提交中击败了
     * 70.99%
     * 的用户
     * 通过测试用例：
     * 54 / 54
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    /**
     *执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 99.23%
     * 的用户
     * 内存消耗：
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 63.30%
     * 的用户
     * 通过测试用例：
     * 54 / 54
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

}
