package com.meng.origin;

import java.util.Arrays;

/**
 * 1423. 可获得的最大点数
 *
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 *
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 *
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 *
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
 * 输出：12
 * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
 *
 * 示例 2：
 *
 * 输入：cardPoints = [2,2,2], k = 2
 * 输出：4
 * 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
 *
 * 示例 3：
 *
 * 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
 * 输出：55
 * 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
 *
 * 示例 4：
 *
 * 输入：cardPoints = [1,1000,1], k = 1
 * 输出：1
 * 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
 *
 * 示例 5：
 *
 * 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
 * 输出：202
 *
 *
 *
 * 提示：
 *
 *     1 <= cardPoints.length <= 10^5
 *     1 <= cardPoints[i] <= 10^4
 *     1 <= k <= cardPoints.length
 */
public class MaxScore {
    /**
     * 1.求出从左侧指定长度的值
     * 2.每次减掉最左侧的值，加上右侧的值
     * 3.比较每次的最大值
     * @param cardPoints
     * @param k
     * @return
    执行用时：2 ms, 在所有 Java 提交中击败了95.77% 的用户
    内存消耗：47.6 MB, 在所有 Java 提交中击败了60.59% 的用户
     */
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        if (k>=len)
            return Arrays.stream(cardPoints).sum();
        int result = 0;
        for(int i = 0 ; i < k ; i++)
            result += cardPoints[i];
        int cur = result;
        for(int i=k-1 ; i >=0 ;i--){
            cur = cur-cardPoints[i]+cardPoints[len-k+i];
            result = Math.max(result,cur);
        }
        return result;
    }
    /**
     * 方法一：滑动窗口
     *
     * 思路
     *
     * 记数组 cardPoints\textit{cardPoints}cardPoints 的长度为 nnn，由于只能从开头和末尾拿 kkk 张卡牌，所以最后剩下的必然是连续的 n−kn-kn−k 张卡牌。
     *
     * 我们可以通过求出剩余卡牌点数之和的最小值，来求出拿走卡牌点数之和的最大值。
     *
     * 算法
     *
     * 由于剩余卡牌是连续的，使用一个固定长度为 n−kn-kn−k 的滑动窗口对数组 cardPoints\textit{cardPoints}cardPoints 进行遍历，求出滑动窗口最小值，然后用所有卡牌的点数之和减去该最小值，即得到了拿走卡牌点数之和的最大值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/solution/ke-huo-de-de-zui-da-dian-shu-by-leetcode-7je9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     *执行用时：6 ms, 在所有 Java 提交中击败了11.51% 的用户
     * 内存消耗：47.6 MB, 在所有 Java 提交中击败了60.25% 的用户
     */
    public int maxScore1(int[] cardPoints, int k) {
        int n = cardPoints.length;
        // 滑动窗口大小为 n-k
        int windowSize = n - k;
        // 选前 n-k 个作为初始值
        int sum = 0;
        for (int i = 0; i < windowSize; ++i) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = windowSize; i < n; ++i) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }
}
