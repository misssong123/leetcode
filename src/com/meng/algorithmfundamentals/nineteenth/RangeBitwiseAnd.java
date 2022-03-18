package com.meng.algorithmfundamentals.nineteenth;

/**
 * 201. 数字范围按位与
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：left = 5, right = 7
 * 输出：4
 * 示例 2：
 *
 * 输入：left = 0, right = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：left = 1, right = 2147483647
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= left <= right <= 231 - 1
 */
public class RangeBitwiseAnd {
    /**
     * 超出时间限制
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd(int left, int right) {
        int res = left;
        for(int i = left + 1 ; i <= right ; i++){
            res &= i;
        }
        return res;
    }

    /**
     * 方法一：位移
     * 思路
     *
     * 鉴于上述问题的陈述，我们的目的是求出两个给定数字的二进制字符串的公共前缀，这里给出的第一个方法是采用位移操作。
     *
     * 我们的想法是将两个数字不断向右移动，直到数字相等，即数字被缩减为它们的公共前缀。然后，通过将公共前缀向左移动，将零添加到公共前缀的右边以获得最终结果。
     *
     *
     *
     * 算法
     *
     * 如上述所说，算法由两个步骤组成：
     *
     * 我们通过右移，将两个数字压缩为它们的公共前缀。在迭代过程中，我们计算执行的右移操作数。
     * 将得到的公共前缀左移相同的操作数得到结果。
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/solution/shu-zi-fan-wei-an-wei-yu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param m
     * @param n
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.7 MB
     * , 在所有 Java 提交中击败了
     * 21.18%
     * 的用户
     * 通过测试用例：
     * 8268 / 8268
     */
    public int rangeBitwiseAnd1(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

    /**
     * 方法二：Brian Kernighan 算法
     * 思路与算法
     *
     * 还有一个位移相关的算法叫做「Brian Kernighan 算法」，它用于清除二进制串中最右边的 11。
     *
     * Brian Kernighan 算法的关键在于我们每次对 \textit{number}number 和 \textit{number}-1number−1 之间进行按位与运算后，\textit{number}number 中最右边的 11 会被抹去变成 00。
     *
     *
     *
     * 基于上述技巧，我们可以用它来计算两个二进制字符串的公共前缀。
     *
     * 其思想是，对于给定的范围 [m,n][m,n]（m<nm<n），我们可以对数字 nn 迭代地应用上述技巧，清除最右边的 11，直到它小于或等于 mm，此时非公共前缀部分的 11 均被消去。因此最后我们返回 nn 即可。
     *
     *
     *
     * 在上图所示的示例（m=9, n=12m=9,n=12）中，公共前缀是 0000100001。在对数字 nn 应用 Brian Kernighan 算法后，后面三位都将变为零，最后我们返回 nn 即可。
     *
     * Python3JavaC++JavaScriptCGolang
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/solution/shu-zi-fan-wei-an-wei-yu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param m
     * @param n
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.5 MB
     * , 在所有 Java 提交中击败了
     * 39.08%
     * 的用户
     * 通过测试用例：
     * 8268 / 8268
     */
    public int rangeBitwiseAnd2(int m, int n) {
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;
    }


    public static void main(String[] args) {
        System.out.println(Integer.bitCount(5));
        System.out.println();
        RangeBitwiseAnd demo = new RangeBitwiseAnd();
        System.out.println(demo.rangeBitwiseAnd(8,24));
    }
}
