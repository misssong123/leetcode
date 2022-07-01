package com.meng.level2.day01;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」 定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 */
public class IsHappy202 {
    public boolean isHappy(int n) {
        Set<Integer> nums = new HashSet<>();
        nums.add(n);
        while (true){
            n = nextNum(n);
            if (n == 1){
                return true;
            }
            if (!nums.add(n)){
                break;
            }
        }
        return false;
    }

    private int nextNum(int n) {
        int sum = 0;
        while (n>0){
            int m = n % 10;
            sum += m*m;
            n = n /10;
        }
        return sum;
    }

    /**
     *执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 79.29%
     * 的用户
     * 内存消耗：
     * 38.5 MB
     * , 在所有 Java 提交中击败了
     * 70.08%
     * 的用户
     * 通过测试用例：
     * 404 / 404
     */
    public boolean isHappy1(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    /**
     * 我们不是只跟踪链表中的一个值，而是跟踪两个值，称为快跑者和慢跑者。在算法的每一步中，慢速在链表中前进 1 个节点，快跑者前进 2 个节点（对 getNext(n) 函数的嵌套调用）。
     *
     * 如果 n 是一个快乐数，即没有循环，那么快跑者最终会比慢跑者先到达数字 1。
     *
     * 如果 n 不是一个快乐的数字，那么最终快跑者和慢跑者将在同一个数字上相遇。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        int slowRunner = n;
        int fastRunner = getNext1(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext1(slowRunner);
            fastRunner = getNext1(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
    public int getNext1(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

}
