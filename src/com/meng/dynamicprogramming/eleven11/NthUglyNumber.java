package com.meng.dynamicprogramming.eleven11;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 264. 丑数 II
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 *
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 *
 * 提示：
 *
 * 1 <= n <= 1690
 */
public class NthUglyNumber {
    /**
     * 方法一：最小堆
     * 要得到从小到大的第 nn 个丑数，可以使用最小堆实现。
     *
     * 初始时堆为空。首先将最小的丑数 11 加入堆。
     *
     * 每次取出堆顶元素 xx，则 xx 是堆中最小的丑数，由于 2x, 3x, 5x2x,3x,5x 也是丑数，因此将 2x, 3x, 5x2x,3x,5x 加入堆。
     *
     * 上述做法会导致堆中出现重复元素的情况。为了避免重复元素，可以使用哈希集合去重，避免相同元素多次加入堆。
     *
     * 在排除重复元素的情况下，第 nn 次从最小堆中取出的元素即为第 nn 个丑数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode-solution-uoqd/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 49 ms
     * , 在所有 Java 提交中击败了
     * 28.52%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 10.75%
     * 的用户
     * 通过测试用例：
     * 596 / 596
     */
    public int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    /**
     * 方法二：动态规划
     * 方法一使用最小堆，会预先存储较多的丑数，导致空间复杂度较高，维护最小堆的过程也导致时间复杂度较高。可以使用动态规划的方法进行优化。
     *
     * 定义数组 \textit{dp}dp，其中 \textit{dp}[i]dp[i] 表示第 ii 个丑数，第 nn 个丑数即为 \textit{dp}[n]dp[n]。
     *
     * 由于最小的丑数是 11，因此 \textit{dp}[1]=1dp[1]=1。
     *
     * 如何得到其余的丑数呢？定义三个指针 p_2,p_3,p_5p
     * 2
     * ​
     *  ,p
     * 3
     * ​
     *  ,p
     * 5
     * ​
     *  ，表示下一个丑数是当前指针指向的丑数乘以对应的质因数。初始时，三个指针的值都是 11。
     *
     * 当 2 \le i \le n2≤i≤n 时，令 \textit{dp}[i]=\min(\textit{dp}[p_2] \times 2, \textit{dp}[p_3] \times 3, \textit{dp}[p_5] \times 5)dp[i]=min(dp[p
     * 2
     * ​
     *  ]×2,dp[p
     * 3
     * ​
     *  ]×3,dp[p
     * 5
     * ​
     *  ]×5)，然后分别比较 \textit{dp}[i]dp[i] 和 \textit{dp}[p_2] \times 2,\textit{dp}[p_3] \times 3,\textit{dp}[p_5] \times 5dp[p
     * 2
     * ​
     *  ]×2,dp[p
     * 3
     * ​
     *  ]×3,dp[p
     * 5
     * ​
     *  ]×5 是否相等，如果相等则将对应的指针加 11。
     *
     * 正确性证明
     *
     * 对于 i>1i>1，在计算 \textit{dp}[i]dp[i] 时，指针 p_x(x \in \{2,3,5\})p
     * x
     * ​
     *  (x∈{2,3,5}) 的含义是使得 \textit{dp}[j] \times x>\textit{dp}[i-1]dp[j]×x>dp[i−1] 的最小的下标 jj，即当 j \ge p_xj≥p
     * x
     * ​
     *   时 \textit{dp}[j] \times x>\textit{dp}[i-1]dp[j]×x>dp[i−1]，当 j<p_xj<p
     * x
     * ​
     *   时 \textit{dp}[j] \times x \le \textit{dp}[i-1]dp[j]×x≤dp[i−1]。
     *
     * 因此，对于 i>1i>1，在计算 \textit{dp}[i]dp[i] 时，\textit{dp}[p_2] \times 2,\textit{dp}[p_3] \times 3,\textit{dp}[p_5] \times 5dp[p
     * 2
     * ​
     *  ]×2,dp[p
     * 3
     * ​
     *  ]×3,dp[p
     * 5
     * ​
     *  ]×5 都大于 \textit{dp}[i-1]dp[i−1]，\textit{dp}[p_2-1] \times 2,\textit{dp}[p_3-1] \times 3,\textit{dp}[p_5-1] \times 5dp[p
     * 2
     * ​
     *  −1]×2,dp[p
     * 3
     * ​
     *  −1]×3,dp[p
     * 5
     * ​
     *  −1]×5 都小于或等于 \textit{dp}[i-1]dp[i−1]。令 \textit{dp}[i]=\min(\textit{dp}[p_2] \times 2, \textit{dp}[p_3] \times 3, \textit{dp}[p_5] \times 5)dp[i]=min(dp[p
     * 2
     * ​
     *  ]×2,dp[p
     * 3
     * ​
     *  ]×3,dp[p
     * 5
     * ​
     *  ]×5)，则 \textit{dp}[i]>\textit{dp}[i-1]dp[i]>dp[i−1] 且 \textit{dp}[i]dp[i] 是大于 \textit{dp}[i-1]dp[i−1] 的最小的丑数。
     *
     * 在计算 \textit{dp}[i]dp[i] 之后，会更新三个指针 p_2,p_3,p_5p
     * 2
     * ​
     *  ,p
     * 3
     * ​
     *  ,p
     * 5
     * ​
     *  ，更新之后的指针将用于计算 \textit{dp}[i+1]dp[i+1]，同样满足 \textit{dp}[i+1]>\textit{dp}[i]dp[i+1]>dp[i] 且 \textit{dp}[i+1]dp[i+1] 是大于 \textit{dp}[i]dp[i] 的最小的丑数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode-solution-uoqd/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int nthUglyNumber1(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
