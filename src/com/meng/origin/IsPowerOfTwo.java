package com.meng.origin;

/**
 * 231. 2 的幂
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：true
 * 解释：20 = 1
 *
 * 示例 2：
 *
 * 输入：n = 16
 * 输出：true
 * 解释：24 = 16
 *
 * 示例 3：
 *
 * 输入：n = 3
 * 输出：false
 *
 * 示例 4：
 *
 * 输入：n = 4
 * 输出：true
 *
 * 示例 5：
 *
 * 输入：n = 5
 * 输出：false
 *
 *
 *
 * 提示：
 *
 *     -231 <= n <= 231 - 1
 *
 *
 *
 * 进阶：你能够不使用循环/递归解决此问题吗？
 * 通过次数135,640
 * 提交次数272,375
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-two
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsPowerOfTwo {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了90.01% 的用户
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if(n < 1){
            return false;
        }
        if( n == 1){
            return true;
        }
        while(n % 2 == 0){
            n /= 2;
        }
        return n == 1 ;
    }
    /**
     * 方法一：二进制表示
     *
     * 思路与算法
     *
     * 一个数 nnn 是 222 的幂，当且仅当 nnn 是正整数，并且 nnn 的二进制表示中仅包含 111 个 111。
     *
     * 因此我们可以考虑使用位运算，将 nnn 的二进制表示中最低位的那个 111 提取出来，再判断剩余的数值是否为 000 即可。下面介绍两种常见的与「二进制表示中最低位」相关的位运算技巧。
     *
     * 第一个技巧是
     *
     * n & (n - 1)\texttt{n \& (n - 1)} n & (n - 1)
     *
     * 其中 &\texttt{\&}& 表示按位与运算。该位运算技巧可以直接将 nnn 二进制表示的最低位 111 移除，它的原理如下：
     *
     *     假设 nnn 的二进制表示为 (a10⋯0)2(a 10\cdots 0)_2(a10⋯0)2​，其中 aaa 表示若干个高位，111 表示最低位的那个 111，0⋯00\cdots 00⋯0 表示后面的若干个 000，那么 n−1n-1n−1 的二进制表示为：
     *
     *     (a01⋯1)2(a 01\cdots1)_2 (a01⋯1)2​
     *
     *     我们将 (a10⋯0)2(a 10\cdots 0)_2(a10⋯0)2​ 与 (a01⋯1)2(a 01\cdots1)_2(a01⋯1)2​ 进行按位与运算，高位 aaa 不变，在这之后的所有位都会变为 000，这样我们就将最低位的那个 111 移除了。
     *
     * 因此，如果 nnn 是正整数并且 n & (n - 1) = 0\texttt{n \& (n - 1) = 0}n & (n - 1) = 0，那么 nnn 就是 222 的幂。
     *
     * 第二个技巧是
     *
     * n & (-n)\texttt{n \& (-n)} n & (-n)
     *
     * 其中 −n-n−n 是 nnn 的相反数，是一个负数。该位运算技巧可以直接获取 nnn 二进制表示的最低位的 111。
     *
     * 由于负数是按照补码规则在计算机中存储的，−n-n−n 的二进制表示为 nnn 的二进制表示的每一位取反再加上 111，因此它的原理如下：
     *
     *     假设 nnn 的二进制表示为 (a10⋯0)2(a 10\cdots 0)_2(a10⋯0)2​，其中 aaa 表示若干个高位，111 表示最低位的那个 111，0⋯00\cdots 00⋯0 表示后面的若干个 000，那么 −n-n−n 的二进制表示为：
     *
     *     (aˉ01⋯1)2+(1)2=(aˉ10⋯0)2(\bar{a} 01\cdots1)_2 + (1)_2 = (\bar{a} 10\cdots0)_2 (aˉ01⋯1)2​+(1)2​=(aˉ10⋯0)2​
     *
     *     其中 aˉ\bar{a}aˉ 表示将 aaa 每一位取反。我们将 (a10⋯0)2(a 10\cdots 0)_2(a10⋯0)2​ 与 (aˉ10⋯0)2(\bar{a} 10\cdots0)_2(aˉ10⋯0)2​ 进行按位与运算，高位全部变为 000，最低位的 111 以及之后的所有 000 不变，这样我们就获取了 nnn 二进制表示的最低位的 111。
     *
     * 因此，如果 nnn 是正整数并且 n & (-n) = n\texttt{n \& (-n) = n}n & (-n) = n，那么 nnn 就是 222 的幂。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/power-of-two/solution/2de-mi-by-leetcode-solution-rny3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了48.75% 的用户
     */
    public boolean isPowerOfTwo1(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了25.92% 的用户
     * @param n
     * @return
     */
    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & -n) == n;
    }
    /**
     * 方法二：判断是否为最大 222 的幂的约数
     *
     * 思路与算法
     *
     * 除了使用二进制表示判断之外，还有一种较为取巧的做法。
     *
     * 在题目给定的 323232 位有符号整数的范围内，最大的 222 的幂为 230=10737418242^{30} = 1073741824230=1073741824。我们只需要判断 nnn 是否是 2302^{30}230 的约数即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/power-of-two/solution/2de-mi-by-leetcode-solution-rny3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了15.18% 的用户
     */
    static final int BIG = 1 << 30;

    public boolean isPowerOfTwo3(int n) {
        return n > 0 && BIG % n == 0;
    }
}