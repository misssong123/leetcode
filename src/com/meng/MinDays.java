package com.meng;

/**
 * 1482. 制作 m 束花所需的最少天数
 *
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 *
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 *
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 *
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
 * 输出：3
 * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
 * 现在需要制作 3 束花，每束只需要 1 朵。
 * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
 * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
 * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
 *
 * 示例 2：
 *
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
 * 输出：-1
 * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
 *
 * 示例 3：
 *
 * 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * 输出：12
 * 解释：要制作 2 束花，每束需要 3 朵。
 * 花园在 7 天后和 12 天后的情况如下：
 * 7 天后：[x, x, x, x, _, x, x]
 * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
 * 12 天后：[x, x, x, x, x, x, x]
 * 显然，我们可以用不同的方式制作两束花。
 *
 * 示例 4：
 *
 * 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
 * 输出：1000000000
 * 解释：需要等 1000000000 天才能采到花来制作花束
 *
 * 示例 5：
 *
 * 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
 * 输出：9
 *
 *
 *
 * 提示：
 *
 *     bloomDay.length == n
 *     1 <= n <= 10^5
 *     1 <= bloomDay[i] <= 10^9
 *     1 <= m <= 10^6
 *     1 <= k <= n
 */
public class MinDays {
    /**
     * 方法一：二分查找
     *
     * 每束花需要 kkk 朵花，需要制作 mmm 束花，因此一共需要 k×mk \times mk×m 朵花。如果花园中的花朵数量少于 k×mk \times mk×m，即数组 bloomDay\textit{bloomDay}bloomDay 的长度小于 k×mk \times mk×m，则无法制作出指定数量的花束，返回 −1-1−1。如果数组 bloomDay\textit{bloomDay}bloomDay 的长度大于或等于 k×mk \times mk×m，则一定可以制作出指定数量的花束。
     *
     * 为了计算制作出指定数量的花束的最少天数，首先需要实现一个辅助函数用于判断在给定的天数内能否制作出指定数量的花束，辅助函数的参数除了 bloomDay\textit{bloomDay}bloomDay、mmm 和 kkk 之外，还有一个参数 days\textit{days}days 表示指定天数。例如，当 bloomDay=[1,10,3,10,2]\textit{bloomDay}=[1,10,3,10,2]bloomDay=[1,10,3,10,2]、m=3m=3m=3、k=1k=1k=1 时，如果 days=3\textit{days}=3days=3 则辅助函数返回 true\text{true}true，如果 days=2\textit{days}=2days=2 则辅助函数返回 false\text{false}false。
     *
     * 对于辅助函数的实现，可以遍历数组 bloomDay\textit{bloomDay}bloomDay，计算其中的长度为 kkk 且最大元素不超过 days\textit{days}days 的不重合的连续子数组的数量，如果符合要求的不重合的连续子数组的数量大于或等于 mmm 则返回 true\text{true}true，否则返回 false\text{false}false。
     *
     * 当 days\textit{days}days 很小的时候，辅助函数总是返回 false\text{false}false，因为天数太少不能收齐 mmm 个花束；当 days\textit{days}days 很大的时候，辅助函数总是返回 true\text{true}true，如果给定序列可以制作出 mmm 个花束。在 days\textit{days}days 慢慢变大的过程中，辅助函数的返回值会从 false\text{false}false 变成 true\text{true}true，所以我们可以认为这个辅助函数是关于 days\textit{days}days 递增的，于是可以通过二分查找得到最少天数。在确保可以制作出指定数量的花束的情况下，所需的最少天数一定不会小于数组 bloomDay\textit{bloomDay}bloomDay 中的最小值，一定不会大于数组 bloomDay\textit{bloomDay}bloomDay 中的最大值，因此二分查找的初始值是 low\textit{low}low 等于数组 bloomDay\textit{bloomDay}bloomDay 中的最小值，high\textit{high}high 等于数组 bloomDay\textit{bloomDay}bloomDay 中的最大值。
     *
     * 当 low\textit{low}low 和 high\textit{high}high 的值相等时，二分查找结束，此时 low\textit{low}low 的值即为最少天数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/solution/zhi-zuo-m-shu-hua-suo-xu-de-zui-shao-tia-mxci/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * @param bloomDay
     * @param m
     * @param k
     * @return
     * 执行用时：19 ms, 在所有 Java 提交中击败了86.76% 的用户
     * 内存消耗：47.1 MB, 在所有 Java 提交中击败了75.00% 的用户
     */
    public int minDays(int[] bloomDay, int m, int k) {
        //如果总长度小于要求的个数，返回-1
        if (m > bloomDay.length / k) {
            return -1;
        }
        int low = Integer.MAX_VALUE, high = 0;
        int length = bloomDay.length;
        //获取最长天数和最短天数
        for (int i = 0; i < length; i++) {
            low = Math.min(low, bloomDay[i]);
            high = Math.max(high, bloomDay[i]);
        }
        //如果天数相同直接返回
        if(low == high){
            return low;
        }
        while (low < high) {
            int days = (high - low) / 2 + low;
            if (canMake(bloomDay, days, m, k)) {
                high = days;
            } else {
                low = days + 1;
            }
        }
        return low;
    }

    public boolean canMake(int[] bloomDay, int days, int m, int k) {
        //能够组成花的束数
        int bouquets = 0;
        //记录连续的花的朵数
        int flowers = 0;
        int length = bloomDay.length;
        for(int i = 0 ; i < length && bouquets < m ; i++){
            if (bloomDay[i] <= days){
                flowers++;
                if (flowers == k ){
                    bouquets++;
                    flowers = 0;
                }
            }else {
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}
