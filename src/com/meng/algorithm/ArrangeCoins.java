package com.meng.algorithm;

/**
 * 441. 排列硬币
 *
 * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
 *
 * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 5
 * 输出：2
 * 解释：因为第三行不完整，所以返回 2 。
 *
 * 示例 2：
 *
 * 输入：n = 8
 * 输出：3
 * 解释：因为第四行不完整，所以返回 3 。
 *
 *
 *
 * 提示：
 *
 *     1 <= n <= 231 - 1
 */
public class ArrangeCoins {
    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了35.69% 的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了25.62% 的用户
     * 通过测试用例：1335 / 1335
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        int res = 0 , num = 1;
        while(n - num >=0){
            n -= num;
            res++;
            num++;
        }
        return res;
    }
    /**
     * 方法一：二分查找
     *
     * 思路和算法
     *
     * 根据等差数列求和公式可知，前 kkk 个完整阶梯行所需的硬币数量为
     *
     * total=k×(k+1)2\textit{total} = \frac{k \times (k+1)}{2} total=2k×(k+1)​
     *
     * 因此，可以通过二分查找计算 nnn 枚硬币可形成的完整阶梯行的总行数。
     *
     * 因为 1≤n≤231−11 \le n \le 2^{31} -11≤n≤231−1，所以 nnn 枚硬币至少可以组成 111 个完整阶梯行，至多可以组成 nnn 个完整阶梯行（在 n=1n = 1n=1 时得到）。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/arranging-coins/solution/pai-lie-ying-bi-by-leetcode-solution-w52c/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了18.06% 的用户
     * 通过测试用例：1335 / 1335
     */
    public int arrangeCoins1(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if ((long) mid * (mid + 1) <= (long) 2 * n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 方法二：数学
     *
     * 思路和算法
     *
     * 考虑直接通过求解方程来计算 nnn 枚硬币可形成的完整阶梯行的总行数。不妨设可以形成的行数为 xxx，则有
     *
     * (x+1)×x2=n\frac{(x+1) \times x}{2} = n 2(x+1)×x​=n
     *
     * 整理得一元二次方程
     *
     * x2+x−2n=0x^2 + x - 2n = 0 x2+x−2n=0
     *
     * 因为 n≥1n \ge 1n≥1 ，所以判别式
     *
     * Δ=b2−4ac=8n+1>0\Delta = b^2 - 4ac = 8n + 1 > 0 Δ=b2−4ac=8n+1>0
     *
     * 解得
     *
     * x1=−1−8n+12,x2=−1+8n+12x_1 = \frac{-1 - \sqrt{8n+1}}{2}, \hspace{1em} x_2 = \frac{-1 + \sqrt{8n+1}}{2} x1​=2−1−8n+1
     * ​​,x2​=2−1+8n+1
     *
     * ​​
     *
     * 因为 x1<0x_1 < 0x1​<0，故舍去。此时 x2x_2x2​ 即为硬币可以排列成的行数，可以完整排列的行数即 ⌊x2⌋\lfloor x_2 \rfloor⌊x2​⌋，其中符号 ⌊x⌋\lfloor x \rfloor⌊x⌋ 表示 xxx 的向下取整。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/arranging-coins/solution/pai-lie-ying-bi-by-leetcode-solution-w52c/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了51.18% 的用户
     * 通过测试用例：1335 / 1335
     */
    public int arrangeCoins2(int n) {
        return (int) ((Math.sqrt((long) 8 * n + 1) - 1) / 2);
    }

}
