package com.meng.enterprise.day03;

import java.util.Arrays;

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
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 99.97%
     * 的用户
     * 内存消耗：
     * 38.6 MB
     * , 在所有 Java 提交中击败了
     * 98.87%
     * 的用户
     * 通过测试用例：
     * 28 / 28
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        if (amount == 0){
            return 1;
        }
        int[] nums = new int[amount+1];
        for(int coin :coins){
            for(int i = coin ; i<= amount ; i++){
                if (coin == i){
                    nums[i] = nums[i] + 1;
                }else if(i >=coin){
                    nums[i] = nums[i] + nums[i-coin];
                }
            }
        }
        return nums[amount];
    }

    public static void main(String[] args) {
        int[] coins = {7};
        Change demo = new Change();
        System.out.println(demo.change(0,coins));
        System.out.println(demo.change1(0,coins));
    }

    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 99.97%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 95.42%
     * 的用户
     * 通过测试用例：
     * 28 / 28
     * @param amount
     * @param coins
     * @return
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
