package com.meng.dynamicprogramming.day20;

/**
 * 518. 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 *
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 *
 * 假设每一种面额的硬币有无限个。
 *
 * 题目数据保证结果符合 32 位带符号整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2：
 *
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 * 示例 3：
 *
 * 输入：amount = 10, coins = [10]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 */
public class Change {
    public int change(int amount, int[] coins) {
        return 0;
    }

    /**
     * 方法一：动态规划
     * 这道题中，给定总金额 \textit{amount}amount 和数组 \textit{coins}coins，要求计算金额之和等于 \textit{amount}amount 的硬币组合数。其中，\textit{coins}coins 的每个元素可以选取多次，且不考虑选取元素的顺序，因此这道题需要计算的是选取硬币的组合数。
     *
     * 可以通过动态规划的方法计算可能的组合数。用 \textit{dp}[x]dp[x] 表示金额之和等于 xx 的硬币组合数，目标是求 \textit{dp}[\textit{amount}]dp[amount]。
     *
     * 动态规划的边界是 \textit{dp}[0]=1dp[0]=1。只有当不选取任何硬币时，金额之和才为 00，因此只有 11 种硬币组合。
     *
     * 对于面额为 \textit{coin}coin 的硬币，当 \textit{coin} \le i \le \textit{amount}coin≤i≤amount 时，如果存在一种硬币组合的金额之和等于 i - \textit{coin}i−coin，则在该硬币组合中增加一个面额为 \textit{coin}coin 的硬币，即可得到一种金额之和等于 ii 的硬币组合。因此需要遍历 \textit{coins}coins，对于其中的每一种面额的硬币，更新数组 \textit{dp}dp 中的每个大于或等于该面额的元素的值。
     *
     * 由此可以得到动态规划的做法：
     *
     * 初始化 \textit{dp}[0]=1dp[0]=1；
     *
     * 遍历 \textit{coins}coins，对于其中的每个元素 \textit{coin}coin，进行如下操作：
     *
     * 遍历 ii 从 \textit{coin}coin 到 \textit{amount}amount，将 \textit{dp}[i - \textit{coin}]dp[i−coin] 的值加到 \textit{dp}[i]dp[i]。
     * 最终得到 \textit{dp}[\textit{amount}]dp[amount] 的值即为答案。
     *
     * 上述做法不会重复计算不同的排列。因为外层循环是遍历数组 \textit{coins}coins 的值，内层循环是遍历不同的金额之和，在计算 \textit{dp}[i]dp[i] 的值时，可以确保金额之和等于 ii 的硬币面额的顺序，由于顺序确定，因此不会重复计算不同的排列。
     *
     * 例如，\textit{coins}=[1,2]coins=[1,2]，对于 \textit{dp}[3]dp[3] 的计算，一定是先遍历硬币面额 11 后遍历硬币面额 22，只会出现以下 22 种组合：
     *
     * \begin{aligned} 3 &= 1+1+1 \\ 3 &= 1+2 \end{aligned}
     * 3
     * 3
     * ​
     *
     * =1+1+1
     * =1+2
     * ​
     *
     *
     * 硬币面额 22 不可能出现在硬币面额 11 之前，即不会重复计算 3=2+13=2+1 的情况。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/coin-change-2/solution/ling-qian-dui-huan-ii-by-leetcode-soluti-f7uh/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param amount
     * @param coins
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 99.94%
     * 的用户
     * 内存消耗：
     * 39 MB
     * , 在所有 Java 提交中击败了
     * 50.13%
     * 的用户
     * 通过测试用例：
     * 28 / 28
     */
    public int change1(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }


}
