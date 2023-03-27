package com.meng.enterprise.day03;

import java.util.Arrays;

/**
 * 322. 零钱兑换(中等)
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange {
    /**
     * 执行用时：
     * 12 ms
     * , 在所有 Java 提交中击败了
     * 70.21%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 49.04%
     * 的用户
     * 通过测试用例：
     * 189 / 189
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        Arrays.sort(coins);
        if (coins[0] > amount){
            return -1;
        }
        int max = Integer.MAX_VALUE;
        int[] nums = new int[amount+1];
        Arrays.fill(nums,max);
        nums[0] = 0;
        for(int coin : coins){
            for (int i = coin ; i <= amount ;i++){
                if (i==coin){
                    nums[i] =1;
                }else if (nums[i-coin] != max){
                    nums[i] = Math.min(nums[i],nums[i-coin]+1);
                }
            }
        }
        return nums[amount]==max?-1:nums[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        CoinChange demo = new CoinChange();
        System.out.println(demo.coinChange(coins,11));
    }

    /**
     * 执行用时：
     * 12 ms
     * , 在所有 Java 提交中击败了
     * 70.21%
     * 的用户
     * 内存消耗：
     * 40.6 MB
     * , 在所有 Java 提交中击败了
     * 98.23%
     * 的用户
     * 通过测试用例：
     * 189 / 189
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
