package com.meng.dynamicprogramming.seven07;

/**
 * 122. 买卖股票的最佳时机 II
 * 给定一个数组 prices ，其中 prices[i] 表示股票第 i 天的价格。
 *
 * 在每一天，你可能会决定购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以购买它，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 */
public class MaxProfitII {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 84.74%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 20.38%
     * 的用户
     * 通过测试用例：
     * 200 / 200
     * @param prices
     * @return
     * 贪心
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for(int i = 1 ; i < prices.length ; i++){
            max+= Math.max(0,prices[i] - prices[i-1]);
        }
        return max;
    }

    /**
     *执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 84.74%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 23.17%
     * 的用户
     * 通过测试用例：
     * 200 / 200
     * @param prices
     * @return
     * 动态规划
     */
    public int maxProfitDy(int[] prices) {
        int y = -prices[0],n=0;
        for(int i = 1 ; i < prices.length ; i++){
            int temp = y;
           y = Math.max(y,n-prices[i]);
           n = Math.max(temp + prices[i] , n);
        }
        return Math.max(y,n);
    }
}
