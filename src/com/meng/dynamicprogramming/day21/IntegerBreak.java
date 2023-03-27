package com.meng.dynamicprogramming.day21;

/**
 * 343. 整数拆分
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 *
 * 返回 你可以获得的最大乘积 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: n = 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 *
 * 提示:
 *
 * 2 <= n <= 58
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        return 0;
    }

    /**
     * 方法二：优化的动态规划
     * 方法一中定义的状态转移方程如下：
     *
     * \textit{dp}[i]=\mathop{\max}\limits_{1 \le j < i}\{\max(j \times (i-j), j \times \textit{dp}[i-j])\}
     * dp[i]=
     * 1≤j<i
     * max
     * ​
     *  {max(j×(i−j),j×dp[i−j])}
     *
     * 使用上述状态转移方程，计算 \textit{dp}[i]dp[i] 时，jj 的值遍历了从 11 到 i-1i−1 的所有值，因此总时间复杂度是 O(n^2)O(n
     * 2
     *  )。是否可以降低时间复杂度？
     *
     * 上述状态转移方程包含两项，当 jj 固定时，\textit{dp}[i]dp[i] 的值由 j \times (i-j)j×(i−j) 和 j \times \textit{dp}[i-j]j×dp[i−j] 中的较大值决定，因此需要对两项分别考虑。
     *
     * 首先考虑 j \times \textit{dp}[i-j]j×dp[i−j] 这一项。
     *
     * 注意到 \textit{dp}dp 的定义，\textit{dp}[i]dp[i] 表示将正整数 ii 拆分成至少两个正整数的和之后，这些正整数的最大乘积，因此对于任意 1 \le j < i1≤j<i，有 \textit{dp}[i] \ge j \times \textit{dp}[i-j]dp[i]≥j×dp[i−j]。
     *
     * 当 jj 是奇数时，有 j=\frac{j-1}{2}+\frac{j+1}{2}j=
     * 2
     * j−1
     * ​
     *  +
     * 2
     * j+1
     * ​
     *  ，因此 \textit{dp}[i] \geq \frac{j-1}{2} \times \textit{dp}[i - \frac{j-1}{2}] \ge \frac{j-1}{2} \times \frac{j+1}{2} \times \textit{dp}[i-j]dp[i]≥
     * 2
     * j−1
     * ​
     *  ×dp[i−
     * 2
     * j−1
     * ​
     *  ]≥
     * 2
     * j−1
     * ​
     *  ×
     * 2
     * j+1
     * ​
     *  ×dp[i−j]。
     *
     * 当 jj 是偶数时，有 j=\frac{j}{2}+\frac{j}{2}j=
     * 2
     * j
     * ​
     *  +
     * 2
     * j
     * ​
     *  ，因此 \textit{dp}[i] \ge \frac{j}{2} \times \textit{dp}[i - \frac{j}{2}] \ge \frac{j}{2} \times \frac{j}{2} \times \textit{dp}[i-j]dp[i]≥
     * 2
     * j
     * ​
     *  ×dp[i−
     * 2
     * j
     * ​
     *  ]≥
     * 2
     * j
     * ​
     *  ×
     * 2
     * j
     * ​
     *  ×dp[i−j]。
     *
     * 如果 j \ge 4j≥4 且 jj 是奇数，则 \frac{j-1}{2} \times \frac{j+1}{2}>j
     * 2
     * j−1
     * ​
     *  ×
     * 2
     * j+1
     * ​
     *  >j 恒成立。如果 j \ge 4j≥4 且 jj 是偶数，则 \frac{j}{2} \times \frac{j}{2} \ge j
     * 2
     * j
     * ​
     *  ×
     * 2
     * j
     * ​
     *  ≥j 恒成立，当且仅当 j=4j=4 时等号成立。
     *
     * 由此可知，如果 j \ge 4j≥4，则 \textit{dp}[j] \ge jdp[j]≥j，当且仅当 j=4j=4 时等号成立，即当 j \ge 4j≥4 时一定能将 jj 拆成至少两个正整数的和，这些正整数的乘积大于或等于 jj。
     *
     * 同时也可以得到，如果 j \ge 4j≥4，则 \textit{dp}[i] \ge j \times \textit{dp}[i-j]dp[i]≥j×dp[i−j]，只有当 j=4j=4 时等号可能成立。又由于
     *
     * \textit{dp}[i] \ge 2 \times \textit{dp}[i-2] \ge 2 \times (2 \times \textit{dp}[i-4]) = 4 \times \textit{dp}[i-4]
     * dp[i]≥2×dp[i−2]≥2×(2×dp[i−4])=4×dp[i−4]
     *
     * 因此取 j=2j=2 计算得到的 \textit{dp}[i]dp[i] 一定不会小于取 j=4j=4 计算得到的 \textit{dp}[i]dp[i]。根据上述分析，j \ge 4j≥4 的情况都是不需要考虑的。
     *
     * 那么 j=1j=1 是否需要考虑？答案是不需要。如果取 j=1j=1，则有 \textit{dp}[i] \ge 1 \times \textit{dp}[i-1]=\textit{dp}[i-1]dp[i]≥1×dp[i−1]=dp[i−1]。当 i \ge 3i≥3 时，\textit{dp}[i-1]dp[i−1] 是将正整数 i-1i−1 拆分成至少两个正整数的和之后，这些正整数的最大乘积，在拆分成的正整数中，任选一个数字加 11，则拆分成的正整数的和变成 ii，且乘积一定大于 \textit{dp}[i-1]dp[i−1]，因此必有 \textit{dp}[i]>\textit{dp}[i-1]dp[i]>dp[i−1]，即当 j=1j=1 时不可能得到最大的 \textit{dp}[i]dp[i] 的值。
     *
     * 根据上述分析可知，计算 \textit{dp}[i]dp[i] 的值只需要考虑 j=2j=2 和 j=3j=3 的情况，不需要遍历从 11 到 i-1i−1 的所有值。
     *
     * 其次考虑 j \times (i-j)j×(i−j) 这一项。
     *
     * 根据上述推导可知，如果 j \ge 4j≥4，则 \textit{dp}[j] \ge jdp[j]≥j，当且仅当 j=4j=4 时等号成立。因此在 i-j \ge 4i−j≥4 的情况下，有 \textit{dp}[i-j] \ge i-jdp[i−j]≥i−j，\textit{dp}[i] \ge j \times \textit{dp}[i-j] \ge j \times (i-j)dp[i]≥j×dp[i−j]≥j×(i−j)，此时计算 \textit{dp}[i]dp[i] 的值不需要考虑 j \times (i-j)j×(i−j) 的值。
     *
     * 如果 i-j<4i−j<4，在计算 \textit{dp}[i]dp[i] 的值的时候就需要考虑 j \times (i-j)j×(i−j) 的值。在考虑 j \times \textit{dp}[i-j]j×dp[i−j] 时，根据上述分析，只需要考虑 j=2j=2 和 j=3j=3 的情况。在考虑 j \times (i-j)j×(i−j) 时，需要考虑 jj 的哪些值？
     *
     * 如果 j=1j=1，则 1 \times (i-1)=i-11×(i−1)=i−1，当 i=2i=2 或 i=3i=3 时有 \textit{dp}[i]=i-1dp[i]=i−1，当 i \ge 4i≥4 时有 \textit{dp}[i] \ge i>i-1dp[i]≥i>i−1，显然当 i \ge 4i≥4 时取 j=1j=1 不可能得到最大乘积，因此 j=1j=1 时是不需要考虑的。
     *
     * 如果 j \ge 4j≥4，\textit{dp}[i]dp[i] 是否可能等于 j \times (i-j)j×(i−j)？当 ii 固定时，要使得 j \times (i-j)j×(i−j) 的值最大，jj 的值应该取 j=i/2j=i/2，这里的 // 表示整数除法。当 j \ge 4j≥4 时，若要满足 j=i/2j=i/2，则 i \ge 8i≥8，此时 i-j \ge 4i−j≥4，利用上述结论，\textit{dp}[i-j] \ge i-jdp[i−j]≥i−j，因此 j \times \textit{dp}[i-j] \ge j \times (i-j)j×dp[i−j]≥j×(i−j)。由此可见，当 j \ge 4j≥4 时，计算 \textit{dp}[i]dp[i] 只需要考虑 j \times \textit{dp}[i-j]j×dp[i−j]，不需要考虑 j \times (i-j)j×(i−j)。
     *
     * 又由于在使用 j \times \textit{dp}[i-j]j×dp[i−j] 计算 \textit{dp}[i]dp[i] 时，j=2j=2 和 j=3j=3 的情况一定优于 j \ge 4j≥4 的情况，因此无论是考虑 j \times \textit{dp}[i-j]j×dp[i−j] 还是考虑 j \times (i-j)j×(i−j)，都只需要考虑 j=2j=2 和 j=3j=3 的情况。
     *
     * 由此可以对方法一的动态规划进行优化。
     *
     * 边界情况是 n=2n=2，此时唯一的拆分方案是 2=1+12=1+1，最大乘积是 1 \times 1=11×1=1。
     *
     * 当 i \ge 3i≥3 时，状态转移方程如下：
     *
     * \textit{dp}[i]=\max(2 \times (i-2), 2 \times \textit{dp}[i-2], 3 \times (i-3), 3 \times \textit{dp}[i-3])
     * dp[i]=max(2×(i−2),2×dp[i−2],3×(i−3),3×dp[i−3])
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/integer-break/solution/zheng-shu-chai-fen-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 33.60%
     * 的用户
     * 通过测试用例：
     * 50 / 50
     */
    public int integerBreak1(int n) {
        if (n < 4) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
        }
        return dp[n];
    }

}
