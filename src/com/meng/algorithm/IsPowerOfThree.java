package com.meng.algorithm;

/**
 * 326. 3的幂
 * 难度
 * 简单
 *
 * 198
 *
 *
 *
 *
 *
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 27
 * 输出：true
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：false
 * 示例 3：
 *
 * 输入：n = 9
 * 输出：true
 * 示例 4：
 *
 * 输入：n = 45
 * 输出：false
 *
 *
 * 提示：
 *
 * -231 <= n <= 231 - 1
 */
public class IsPowerOfThree {
    /**
     * 执行用时：
     * 15 ms
     * , 在所有 Java 提交中击败了
     * 93.08%
     * 的用户
     * 内存消耗：
     * 38.1 MB
     * , 在所有 Java 提交中击败了
     * 75.71%
     * 的用户
     * 通过测试用例：
     * 21038 / 21038
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if(n == 0){
            return false;
        }
        while (n % 3 == 0){
            n = n / 3;
        }
        return n == 1;
    }

    /**
     * 方法一：试除法
     *
     * 思路与算法
     *
     * 我们不断地将
     * n
     * n 除以
     * 3
     * 3，直到
     * n
     * =
     * 1
     * n=1。如果此过程中
     * n
     * n 无法被
     * 3
     * 3 整除，就说明
     * n
     * n 不是
     * 3
     * 3 的幂。
     *
     * 本题中的
     * n
     * n 可以为负数或
     * 0
     * 0，可以直接提前判断该情况并返回
     * False
     * False，也可以进行试除，因为负数或
     * 0
     * 0 也无法通过多次除以
     * 3
     * 3 得到
     * 1
     * 1。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode-solution-hnap/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 15 ms
     * , 在所有 Java 提交中击败了
     * 93.08%
     * 的用户
     * 内存消耗：
     * 38 MB
     * , 在所有 Java 提交中击败了
     * 84.58%
     * 的用户
     */
    public boolean isPowerOfThree1(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 方法二：判断是否为最大
     * 3
     * 3 的幂的约数
     *
     * 思路与算法
     *
     * 我们还可以使用一种较为取巧的做法。
     *
     * 在题目给定的
     * 32
     * 32 位有符号整数的范围内，最大的
     * 3
     * 3 的幂为
     * 3
     * 19
     * =
     * 1162261467
     * 3
     * 19
     *  =1162261467。我们只需要判断
     * n
     * n 是否是
     * 3
     * 19
     * 3
     * 19
     *  的约数即可。
     *
     * 与方法一不同的是，这里需要特殊判断
     * n
     * n 是负数或
     * 0
     * 0 的情况。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode-solution-hnap/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 15 ms
     * , 在所有 Java 提交中击败了
     * 93.08%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 50.73%
     * 的用户
     */
    public boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

}
