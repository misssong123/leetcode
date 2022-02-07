package com.meng.origin;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProfit {
    /**
     * 只需要买相邻两天大于的股票即可
     * @param prices
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.54% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了88.68% 的用户
     */
    public int maxProfit(int[] prices) {
        int ans = 0,len=prices.length;
        for(int i = 1 ; i < len ; i++){
            ans += prices[i]>prices[i-1]?prices[i]-prices[i-1] : 0;
        }
        return ans;
    }

    /**
     * 每次在下降的时候卖出
     * @param prices
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.54% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了83.04% 的用户
     */
    public int maxProfitOther(int[] prices) {
        int ans = 0,len=prices.length,first = 0,cur=0;
        for(int i = 1 ; i < len ; i++){
            if (prices[i]<=prices[i-1]){
                ans += (prices[cur] - prices[first]);
                first = i ;
                cur = i;
            }else {
                cur ++;
            }
        }
        ans += Math.max(0,prices[cur]-prices[first]);
        return ans;
    }
    /**
     * 官方解法1
     *方法一：动态规划
     *
     * 考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
     *
     * 定义状态 dp[i][0]\textit{dp}[i][0]dp[i][0] 表示第 iii 天交易完后手里没有股票的最大利润，dp[i][1]\textit{dp}[i][1]dp[i][1] 表示第 iii 天交易完后手里持有一支股票的最大利润（iii 从 000 开始）。
     *
     * 考虑 dp[i][0]\textit{dp}[i][0]dp[i][0] 的转移方程，如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，即 dp[i−1][0]\textit{dp}[i-1][0]dp[i−1][0]，或者前一天结束的时候手里持有一支股票，即 dp[i−1][1]\textit{dp}[i-1][1]dp[i−1][1]，这时候我们要将其卖出，并获得 prices[i]\textit{prices}[i]prices[i] 的收益。因此为了收益最大化，我们列出如下的转移方程：
     *
     * dp[i][0]=max⁡{dp[i−1][0],dp[i−1][1]+prices[i]}\textit{dp}[i][0]=\max\{\textit{dp}[i-1][0],\textit{dp}[i-1][1]+\textit{prices}[i]\} dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]}
     *
     * 再来考虑 dp[i][1]\textit{dp}[i][1]dp[i][1]，按照同样的方式考虑转移状态，那么可能的转移状态为前一天已经持有一支股票，即 dp[i−1][1]\textit{dp}[i-1][1]dp[i−1][1]，或者前一天结束时还没有股票，即 dp[i−1][0]\textit{dp}[i-1][0]dp[i−1][0]，这时候我们要将其买入，并减少 prices[i]\textit{prices}[i]prices[i] 的收益。可以列出如下的转移方程：
     *
     * dp[i][1]=max⁡{dp[i−1][1],dp[i−1][0]−prices[i]}\textit{dp}[i][1]=\max\{\textit{dp}[i-1][1],\textit{dp}[i-1][0]-\textit{prices}[i]\} dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
     *
     * 对于初始状态，根据状态定义我们可以知道第 000 天交易结束的时候 dp[0][0]=0\textit{dp}[0][0]=0dp[0][0]=0，dp[0][1]=−prices[0]\textit{dp}[0][1]=-\textit{prices}[0]dp[0][1]=−prices[0]。
     *
     * 因此，我们只要从前往后依次计算状态即可。由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，因此这时候 dp[n−1][0]\textit{dp}[n-1][0]dp[n−1][0] 的收益必然是大于 dp[n−1][1]\textit{dp}[n-1][1]dp[n−1][1] 的，最后的答案即为 dp[n−1][0]\textit{dp}[n-1][0]dp[n−1][0]。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：4 ms, 在所有 Java 提交中击败了12.95% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了85.01% 的用户
     */
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
/**
 * 官方解法2
 *注意到上面的状态转移方程中，每一天的状态只与前一天的状态有关，而与更早的状态都无关，因此我们不必存储这些无关的状态，只需要将 dp[i−1][0]\textit{dp}[i-1][0]dp[i−1][0] 和 dp[i−1][1]\textit{dp}[i-1][1]dp[i−1][1] 存放在两个变量中，通过它们计算出 dp[i][0]\textit{dp}[i][0]dp[i][0] 和 dp[i][1]\textit{dp}[i][1]dp[i][1] 并存回对应的变量，以便于第 i+1i+1i+1 天的状态转移即可。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：2 ms, 在所有 Java 提交中击败了26.63% 的用户
 * 内存消耗：38.2 MB, 在所有 Java 提交中击败了92.31% 的用户
 */
public int maxProfit2(int[] prices) {
    int n = prices.length;
    int dp0 = 0, dp1 = -prices[0];
    for (int i = 1; i < n; ++i) {
        int newDp0 = Math.max(dp0, dp1 + prices[i]);
        int newDp1 = Math.max(dp1, dp0 - prices[i]);
        dp0 = newDp0;
        dp1 = newDp1;
    }
    return dp0;
}

/**
 * 官方解法3
 *方法二：贪心
 *
 * 由于股票的购买没有限制，因此整个问题等价于寻找 xxx 个不相交的区间 (li,ri](l_i,r_i](li​,ri​] 使得如下的等式最大化
 *
 * ∑i=1xa[ri]−a[li]\sum_{i=1}^{x} a[r_i]-a[l_i] i=1∑x​a[ri​]−a[li​]
 *
 * 其中 lil_ili​ 表示在第 lil_ili​ 天买入，rir_iri​ 表示在第 rir_iri​ 天卖出。
 *
 * 同时我们注意到对于 (li,ri](l_i,r_i](li​,ri​] 这一个区间贡献的价值 a[ri]−a[li]a[r_i]-a[l_i]a[ri​]−a[li​]，其实等价于 (li,li+1],(li+1,li+2],…,(ri−1,ri](l_i,l_i+1],(l_i+1,l_i+2],\ldots,(r_i-1,r_i](li​,li​+1],(li​+1,li​+2],…,(ri​−1,ri​] 这若干个区间长度为 111 的区间的价值和，即
 *
 * a[ri]−a[li]=(a[ri]−a[ri−1])+(a[ri−1]−a[ri−2])+…+(a[li+1]−a[li])a[r_i]-a[l_i]=(a[r_i]-a[r_i-1])+(a[r_i-1]-a[r_i-2])+\ldots+(a[l_i+1]-a[l_i]) a[ri​]−a[li​]=(a[ri​]−a[ri​−1])+(a[ri​−1]−a[ri​−2])+…+(a[li​+1]−a[li​])
 *
 * 因此问题可以简化为找 xxx 个长度为 111 的区间 (li,li+1](l_i,l_i+1](li​,li​+1] 使得 ∑i=1xa[li+1]−a[li]\sum_{i=1}^{x} a[l_i+1]-a[l_i]∑i=1x​a[li​+1]−a[li​] 价值最大化。
 *
 * 贪心的角度考虑我们每次选择贡献大于 000 的区间即能使得答案最大化，因此最后答案为
 *
 * ans=∑i=1n−1max⁡{0,a[i]−a[i−1]}\textit{ans}=\sum_{i=1}^{n-1}\max\{0,a[i]-a[i-1]\} ans=i=1∑n−1​max{0,a[i]−a[i−1]}
 *
 * 其中 nnn 为数组的长度。
 *
 * 需要说明的是，贪心算法只能用于计算最大利润，计算的过程并不是实际的交易过程。
 *
 * 考虑题目中的例子 [1,2,3,4,5][1,2,3,4,5][1,2,3,4,5]，数组的长度 n=5n=5n=5，由于对所有的 1≤i<n1 \le i < n1≤i<n 都有 a[i]>a[i−1]a[i]>a[i-1]a[i]>a[i−1]，因此答案为
 *
 * ans=∑i=1n−1a[i]−a[i−1]=4\textit{ans}=\sum_{i=1}^{n-1}a[i]-a[i-1]=4 ans=i=1∑n−1​a[i]−a[i−1]=4
 *
 * 但是实际的交易过程并不是进行 444 次买入和 444 次卖出，而是在第 111 天买入，第 555 天卖出。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：1 ms, 在所有 Java 提交中击败了99.54% 的用户
 * 内存消耗：38.5 MB, 在所有 Java 提交中击败了78.80% 的用户
 */
public int maxProfi3t(int[] prices) {
    int ans = 0;
    int n = prices.length;
    for (int i = 1; i < n; ++i) {
        ans += Math.max(0, prices[i] - prices[i - 1]);
    }
    return ans;
}
}
