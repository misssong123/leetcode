package com.meng.origin;

/**
 * 714. 买卖股票的最佳时机含手续费
 *
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1:
 *
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * 注意:
 *
 *     0 < prices.length <= 50000.
 *     0 < prices[i] < 50000.
 *     0 <= fee < 50000.
 */
public class MaxProfitWithFee {
    /**
     * 动态规划，记录每次购买和出售该股票的情况
     * @param prices
     * @param fee
     * @return
     * 执行用时：22 ms, 在所有 Java 提交中击败了35.83% 的用户
     * 内存消耗：47.8 MB, 在所有 Java 提交中击败了49.93% 的用户
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int [][] res = new int[len][2];
        res[0][0] = 0;
        res[0][1] = - prices[0];
        for(int i = 1 ; i < len ; i++){
            res[i][0] = Math.max(res[i-1][0],res[i-1][1]+prices[i]-fee);
            res[i][1] = Math.max(res[i-1][1],res[i-1][0]-prices[i]);
        }
        return res[len-1][0];
    }
    /**方法一：动态规划

     思路与算法

     考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。

     定义状态 dp[i][0]\textit{dp}[i][0]dp[i][0] 表示第 iii 天交易完后手里没有股票的最大利润，dp[i][1]\textit{dp}[i][1]dp[i][1] 表示第 iii 天交易完后手里持有一支股票的最大利润（iii 从 000 开始）。

     考虑 dp[i][0]\textit{dp}[i][0]dp[i][0] 的转移方程，如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，即 dp[i−1][0]\textit{dp}[i-1][0]dp[i−1][0]，或者前一天结束的时候手里持有一支股票，即 dp[i−1][1]\textit{dp}[i-1][1]dp[i−1][1]，这时候我们要将其卖出，并获得 prices[i]\textit{prices}[i]prices[i] 的收益，但需要支付 fee\textit{fee}fee 的手续费。因此为了收益最大化，我们列出如下的转移方程：

     dp[i][0]=max⁡{dp[i−1][0],dp[i−1][1]+prices[i]−fee}\textit{dp}[i][0]=\max\{\textit{dp}[i-1][0],\textit{dp}[i-1][1]+\textit{prices}[i]-\textit{fee}\} dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]−fee}

     再来按照同样的方式考虑 dp[i][1]\textit{dp}[i][1]dp[i][1] 按状态转移，那么可能的转移状态为前一天已经持有一支股票，即 dp[i−1][1]\textit{dp}[i-1][1]dp[i−1][1]，或者前一天结束时还没有股票，即 dp[i−1][0]\textit{dp}[i-1][0]dp[i−1][0]，这时候我们要将其买入，并减少 prices[i]\textit{prices}[i]prices[i] 的收益。可以列出如下的转移方程：

     dp[i][1]=max⁡{dp[i−1][1],dp[i−1][0]−prices[i]}\textit{dp}[i][1]=\max\{\textit{dp}[i-1][1],\textit{dp}[i-1][0]-\textit{prices}[i]\} dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}

     对于初始状态，根据状态定义我们可以知道第 000 天交易结束的时候有 dp[0][0]=0\textit{dp}[0][0]=0dp[0][0]=0 以及 dp[0][1]=−prices[0]\textit{dp}[0][1]=-\textit{prices}[0]dp[0][1]=−prices[0]。

     因此，我们只要从前往后依次计算状态即可。由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，因此这时候 dp[n−1][0]\textit{dp}[n-1][0]dp[n−1][0] 的收益必然是大于 dp[n−1][1]\textit{dp}[n-1][1]dp[n−1][1] 的，最后的答案即为 dp[n−1][0]\textit{dp}[n-1][0]dp[n−1][0]。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-han-sh-rzlz/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：21 ms, 在所有 Java 提交中击败了40.27% 的用户
     * 内存消耗：47.8 MB, 在所有 Java 提交中击败了51.68% 的用户
     * 执行用时：4 ms, 在所有 Java 提交中击败了99.77% 的用户
     * 内存消耗：47.5 MB, 在所有 Java 提交中击败了85.93% 的用户
     */
    public int maxProfit1(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
    /**
     * 注意到字状态转移方程中，dp[i][0]\textit{dp}[i][0]dp[i][0] 和 dp[i][1]\textit{dp}[i][1]dp[i][1] 只会从 dp[i−1][0]\textit{dp}[i-1][0]dp[i−1][0] 和 dp[i−1][1]\textit{dp}[i-1][1]dp[i−1][1] 转移而来，因此我们不必使用数组存储所有的状态，而是使用两个变量 sell\textit{sell}sell 以及 buy\textit{buy}buy 分别表示 dp[..][0]\textit{dp}[..][0]dp[..][0] 和 dp[..][1]\textit{dp}[..][1]dp[..][1] 直接进行状态转移即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-han-sh-rzlz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 优化
     */
    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int sell = 0, buy = -prices[0];
        for (int i = 1; i < n; ++i) {
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }
}
