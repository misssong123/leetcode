package com.meng.dynamicprogramming.day21;

/**
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 *
 * 1 <= n <= 104
 */
public class NumSquares {
    public int numSquares(int n) {
        return 0;
    }

    /**
     * 方法一：动态规划
     * 思路及算法
     *
     * 我们可以依据题目的要求写出状态表达式：f[i]f[i] 表示最少需要多少个数的平方来表示整数 ii。
     *
     * 这些数必然落在区间 [1,\sqrt{n}][1,
     * n
     * ​
     *  ]。我们可以枚举这些数，假设当前枚举到 jj，那么我们还需要取若干数的平方，构成 i-j^2i−j
     * 2
     *  。此时我们发现该子问题和原问题类似，只是规模变小了。这符合了动态规划的要求，于是我们可以写出状态转移方程。
     *
     * f[i]=1+\min_{j=1}^{\lfloor\sqrt{i}\rfloor}{f[i-j^2]}
     * f[i]=1+
     * j=1
     * min
     * ⌊
     * i
     * ​
     *  ⌋
     * ​
     *  f[i−j
     * 2
     *  ]
     *
     * 其中 f[0]=0f[0]=0 为边界条件，实际上我们无法表示数字 00，只是为了保证状态转移过程中遇到 jj 恰为 \sqrt{i}
     * i
     * ​
     *   的情况合法。
     *
     * 同时因为计算 f[i]f[i] 时所需要用到的状态仅有 f[i-j^2]f[i−j
     * 2
     *  ]，必然小于 ii，因此我们只需要从小到大地枚举 ii 来计算 f[i]f[i] 即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode-solut-t99c/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 21 ms
     * , 在所有 Java 提交中击败了
     * 85.95%
     * 的用户
     * 内存消耗：
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 16.49%
     * 的用户
     * 通过测试用例：
     * 588 / 588
     */
    public int numSquares1(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }


}
