package com.meng.algorithmfundamentals.twenty;

import java.util.Arrays;
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
public class IsHappy {
    public boolean isHappy(int n) {
        return false;
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
     *方法一：用哈希集合检测循环
     * 我们可以先举几个例子。我们从 77 开始。则下一个数字是 4949（因为 7^2=497
     * 2
     *  =49），然后下一个数字是 9797（因为 4^2+9^2=974
     * 2
     *  +9
     * 2
     *  =97）。我们可以不断重复该的过程，直到我们得到 11。因为我们得到了 11，我们知道 77 是一个快乐数，函数应该返回 true。
     *
     *
     *
     * 再举一个例子，让我们从 116116 开始。通过反复通过平方和计算下一个数字，我们最终得到 5858，再继续计算之后，我们又回到 5858。由于我们回到了一个已经计算过的数字，可以知道有一个循环，因此不可能达到 11。所以对于 116116，函数应该返回 false。
     *
     *
     *
     * 根据我们的探索，我们猜测会有以下三种可能。
     *
     * 最终会得到 11。
     * 最终会进入循环。
     * 值会越来越大，最后接近无穷大。
     * 第三个情况比较难以检测和处理。我们怎么知道它会继续变大，而不是最终得到 11 呢？我们可以仔细想一想，每一位数的最大数字的下一位数是多少。
     *
     * Digits	Largest	Next
     * 1	9	81
     * 2	99	162
     * 3	999	243
     * 4	9999	324
     * 13	9999999999999	1053
     * 对于 33 位数的数字，它不可能大于 243243。这意味着它要么被困在 243243 以下的循环内，要么跌到 11。44 位或 44 位以上的数字在每一步都会丢失一位，直到降到 33 位为止。所以我们知道，最坏的情况下，算法可能会在 243243 以下的所有数字上循环，然后回到它已经到过的一个循环或者回到 11。但它不会无限期地进行下去，所以我们排除第三种选择。
     *
     * 即使在代码中你不需要处理第三种情况，你仍然需要理解为什么它永远不会发生，这样你就可以证明为什么你不处理它。
     *
     * 算法
     *
     * 算法分为两部分，我们需要设计和编写代码。
     *
     * 给一个数字 nn，它的下一个数字是什么？
     * 按照一系列的数字来判断我们是否进入了一个循环。
     * 第 1 部分我们按照题目的要求做数位分离，求平方和。
     *
     * 第 2 部分可以使用哈希集合完成。每次生成链中的下一个数字时，我们都会检查它是否已经在哈希集合中。
     *
     * 如果它不在哈希集合中，我们应该添加它。
     * 如果它在哈希集合中，这意味着我们处于一个循环中，因此应该返回 false。
     * 我们使用哈希集合而不是向量、列表或数组的原因是因为我们反复检查其中是否存在某数字。检查数字是否在哈希集合中需要 O(1)O(1) 的时间，而对于其他数据结构，则需要 O(n)O(n) 的时间。选择正确的数据结构是解决这些问题的关键部分。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 81.87%
     * 的用户
     * 内存消耗：
     * 38.9 MB
     * , 在所有 Java 提交中击败了
     * 12.83%
     * 的用户
     * 通过测试用例：
     * 402 / 402
     */
    public boolean isHappy1(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }


    public int getNext2(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    /**
     * 方法二：快慢指针法
     * 通过反复调用 getNext(n) 得到的链是一个隐式的链表。隐式意味着我们没有实际的链表节点和指针，但数据仍然形成链表结构。起始数字是链表的头 “节点”，链中的所有其他数字都是节点。next 指针是通过调用 getNext(n) 函数获得。
     *
     * 意识到我们实际有个链表，那么这个问题就可以转换为检测一个链表是否有环。因此我们在这里可以使用弗洛伊德循环查找算法。这个算法是两个奔跑选手，一个跑的快，一个跑得慢。在龟兔赛跑的寓言中，跑的慢的称为 “乌龟”，跑得快的称为 “兔子”。
     *
     * 不管乌龟和兔子在循环中从哪里开始，它们最终都会相遇。这是因为兔子每走一步就向乌龟靠近一个节点（在它们的移动方向上）。
     *
     *
     * 1 / 8
     *
     * 算法
     *
     * 我们不是只跟踪链表中的一个值，而是跟踪两个值，称为快跑者和慢跑者。在算法的每一步中，慢速在链表中前进 1 个节点，快跑者前进 2 个节点（对 getNext(n) 函数的嵌套调用）。
     *
     * 如果 n 是一个快乐数，即没有循环，那么快跑者最终会比慢跑者先到达数字 1。
     *
     * 如果 n 不是一个快乐的数字，那么最终快跑者和慢跑者将在同一个数字上相遇。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        int slowRunner = n;
        int fastRunner = getNext2(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext2(slowRunner);
            fastRunner = getNext2(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    private static Set<Integer> cycleMembers =
            new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));

    public int getNext3(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    /**
     * 方法三：数学
     * 前两种方法是你在面试中应该想到的。第三种方法不是你在面试中会写的，而是针对对数学好奇的人，因为它很有趣。
     *
     * 下一个值可能比自己大的最大数字是什么？根据我们之前的分析，我们知道它必须低于 243。因此，我们知道任何循环都必须包含小于 243 的数字，用这么小的数字，编写一个能找到所有周期的强力程序并不困难。
     *
     * 如果这样做，您会发现只有一个循环：4 \rightarrow 16 \rightarrow 37 \rightarrow 58 \rightarrow 89 \rightarrow 145 \rightarrow 42 \rightarrow 20 \rightarrow 44→16→37→58→89→145→42→20→4。所有其他数字都在进入这个循环的链上，或者在进入 11 的链上。
     *
     * 因此，我们可以硬编码一个包含这些数字的散列集，如果我们达到其中一个数字，那么我们就知道在循环中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public boolean isHappy3(int n) {
        while (n != 1 && !cycleMembers.contains(n)) {
            n = getNext3(n);
        }
        return n == 1;
    }


}
