package com.meng.oneday.leetcode.editor.cn;

class MaxProfit3652 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了72.83% 的Java用户
     * 	内存消耗:94.2 MB,击败了43.36% 的Java用户
     * @param prices
     * @param strategy
     * @param k
     * @return
     */
    public long maxProfit3652(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        //计算前缀和
        long[] preSum = new long[n+1];
        for(int i = 0 ; i < n ; i++){
            preSum[i+1] = preSum[i] + (long)prices[i] * strategy[i];
        }

        //初始化
        long pre = 0;
        for(int i = k/2 ; i < k ; i++){
            pre += prices[i];
        }
        long max = Math.max(0,pre - preSum[k]);
        for(int i = k ; i < n ; i++){
            pre += prices[i] - prices[i-k/2];
            max = Math.max(max, pre - (preSum[i+1] - preSum[i-k+1]));
        }
        return max + preSum[n];
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了20.03% 的Java用户
     * 	内存消耗:100.5 MB,击败了25.88% 的Java用户
     * @param prices
     * @param strategy
     * @param k
     * @return
     */
    public long maxProfitOther(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long[] sum = new long[n + 1];
        long[] sumSell = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + prices[i] * strategy[i];
            sumSell[i + 1] = sumSell[i] + prices[i];
        }

        long ans = sum[n]; // 不修改
        for (int i = k; i <= n; i++) {
            long res = sum[i - k] + sum[n] - sum[i] + sumSell[i] - sumSell[i - k / 2];
            ans = Math.max(ans, res);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了72.83% 的Java用户
     * 	内存消耗:105 MB,击败了6.75% 的Java用户
     * @param prices
     * @param strategy
     * @param k
     * @return
     */
    public long maxProfit(int[] prices, int[] strategy, int k) {
        long total = 0, maxSum = 0, sum = 0;
        for (int i = 0; i < prices.length; i++) {
            int p = prices[i], s = strategy[i];
            total += p * s;

            // 1. 入右半，交易策略从 s 变成 1
            sum += p * (1 - s);

            if (i < k - 1) { // 尚未形成第一个窗口
                // 在下一轮循环中，下标为 i-k/2+1 的元素从右半移到左半，交易策略从 1 变成 0
                if (i >= k / 2 - 1) {
                    sum -= prices[i - k / 2 + 1];
                }
                continue;
            }

            // 2. 更新
            maxSum = Math.max(maxSum, sum);

            // 3. 出，为下一个窗口做准备
            // 对于下一个窗口，下标为 i-k/2+1 的元素从右半移到左半，交易策略从 1 变成 0，下标为 i-k+1 的元素从左半离开窗口
            sum -= prices[i - k / 2 + 1] - prices[i - k + 1] * strategy[i - k + 1];
        }

        return total + maxSum;
    }


}
