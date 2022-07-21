package com.meng.dynamicprogramming.day21;

/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *
 * 题目数据保证答案符合 32 位整数范围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 *
 * 输入：nums = [9], target = 3
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 *
 *
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 */
public class CombinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        return 0;
    }

    /**
     * 方法一：动态规划
     * 这道题中，给定数组 \textit{nums}nums 和目标值 \textit{target}target，要求计算从 \textit{nums}nums 中选取若干个元素，使得它们的和等于 \textit{target}target 的方案数。其中，\textit{nums}nums 的每个元素可以选取多次，且需要考虑选取元素的顺序。由于需要考虑选取元素的顺序，因此这道题需要计算的是选取元素的排列数。
     *
     * 可以通过动态规划的方法计算可能的方案数。用 \textit{dp}[x]dp[x] 表示选取的元素之和等于 xx 的方案数，目标是求 \textit{dp}[\textit{target}]dp[target]。
     *
     * 动态规划的边界是 \textit{dp}[0]=1dp[0]=1。只有当不选取任何元素时，元素之和才为 00，因此只有 11 种方案。
     *
     * 当 1 \le i \le \textit{target}1≤i≤target 时，如果存在一种排列，其中的元素之和等于 ii，则该排列的最后一个元素一定是数组 \textit{nums}nums 中的一个元素。假设该排列的最后一个元素是 \textit{num}num，则一定有 \textit{num} \le inum≤i，对于元素之和等于 i - \textit{num}i−num 的每一种排列，在最后添加 \textit{num}num 之后即可得到一个元素之和等于 ii 的排列，因此在计算 \textit{dp}[i]dp[i] 时，应该计算所有的 \textit{dp}[i-\textit{num}]dp[i−num] 之和。
     *
     * 由此可以得到动态规划的做法：
     *
     * 初始化 \textit{dp}[0]=1dp[0]=1；
     *
     * 遍历 ii 从 11 到 \textit{target}target，对于每个 ii，进行如下操作：
     *
     * 遍历数组 \textit{nums}nums 中的每个元素 \textit{num}num，当 \textit{num} \le inum≤i 时，将 \textit{dp}[i - \textit{num}]dp[i−num] 的值加到 \textit{dp}[i]dp[i]。
     * 最终得到 \textit{dp}[\textit{target}]dp[target] 的值即为答案。
     *
     * 上述做法是否考虑到选取元素的顺序？答案是肯定的。因为外层循环是遍历从 11 到 \textit{target}target 的值，内层循环是遍历数组 \textit{nums}nums 的值，在计算 \textit{dp}[i]dp[i] 的值时，\textit{nums}nums 中的每个小于等于 ii 的元素都可能作为元素之和等于 ii 的排列的最后一个元素。例如，11 和 33 都在数组 \textit{nums}nums 中，计算 \textit{dp}[4]dp[4] 的时候，排列的最后一个元素可以是 11 也可以是 33，因此 \textit{dp}[1]dp[1] 和 \textit{dp}[3]dp[3] 都会被考虑到，即不同的顺序都会被考虑到。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/combination-sum-iv/solution/zu-he-zong-he-iv-by-leetcode-solution-q8zv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 97.89%
     * 的用户
     * 内存消耗：
     * 38.6 MB
     * , 在所有 Java 提交中击败了
     * 70.40%
     * 的用户
     * 通过测试用例：
     * 15 / 15
     */
    public int combinationSum41(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}
