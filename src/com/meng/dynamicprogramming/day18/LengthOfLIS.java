package com.meng.dynamicprogramming.day18;

/**
 * 300. 最长递增子序列
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
public class LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        return 0;
    }

    /**
     * 方法一：动态规划
     * 思路与算法
     *
     * 定义 \textit{dp}[i]dp[i] 为考虑前 ii 个元素，以第 ii 个数字结尾的最长上升子序列的长度，注意 \textit{nums}[i]nums[i] 必须被选取。
     *
     * 我们从小到大计算 \textit{dp}dp 数组的值，在计算 \textit{dp}[i]dp[i] 之前，我们已经计算出 \textit{dp}[0 \ldots i-1]dp[0…i−1] 的值，则状态转移方程为：
     *
     * \textit{dp}[i] = \max(\textit{dp}[j]) + 1, \text{其中} \, 0 \leq j < i \, \text{且} \, \textit{num}[j]<\textit{num}[i]
     * dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     *
     * 即考虑往 \textit{dp}[0 \ldots i-1]dp[0…i−1] 中最长的上升子序列后面再加一个 \textit{nums}[i]nums[i]。由于 \textit{dp}[j]dp[j] 代表 \textit{nums}[0 \ldots j]nums[0…j] 中以 \textit{nums}[j]nums[j] 结尾的最长上升子序列，所以如果能从 \textit{dp}[j]dp[j] 这个状态转移过来，那么 \textit{nums}[i]nums[i] 必然要大于 \textit{nums}[j]nums[j]，才能将 \textit{nums}[i]nums[i] 放在 \textit{nums}[j]nums[j] 后面以形成更长的上升子序列。
     *
     * 最后，整个数组的最长上升子序列即所有 \textit{dp}[i]dp[i] 中的最大值。
     *
     * \text{LIS}_{\textit{length}}= \max(\textit{dp}[i]), \text{其中} \, 0\leq i < n
     * LIS
     * length
     * ​
     *  =max(dp[i]),其中0≤i<n
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 55 ms
     * , 在所有 Java 提交中击败了
     * 71.04%
     * 的用户
     * 内存消耗：
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 42.76%
     * 的用户
     * 通过测试用例：
     * 54 / 54
     */
    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
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


}
