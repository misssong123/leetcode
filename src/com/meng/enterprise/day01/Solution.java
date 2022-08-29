package com.meng.enterprise.day01;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 382. 链表随机节点(中等)
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 *
 * 实现 Solution 类：
 *
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 *
 *
 * 示例：
 *
 *
 * 输入
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * 输出
 * [null, 1, 3, 2, 2, 3]
 *
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // 返回 1
 * solution.getRandom(); // 返回 3
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 3
 * // getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。
 *
 *
 * 提示：
 *
 * 链表中的节点数在范围 [1, 104] 内
 * -104 <= Node.val <= 104
 * 至多调用 getRandom 方法 104 次
 *
 *
 * 进阶：
 *
 * 如果链表非常大且长度未知，该怎么处理？
 * 你能否在不使用额外空间的情况下解决此问题？
 */

/**
 * 执行用时：
 * 11 ms
 * , 在所有 Java 提交中击败了
 * 55.74%
 * 的用户
 * 内存消耗：
 * 43.3 MB
 * , 在所有 Java 提交中击败了
 * 59.75%
 * 的用户
 * 通过测试用例：
 * 8 / 8
 */
public class Solution {
    Random random = null;
    List<Integer> list = null;
    public Solution(ListNode head) {
        list = new ArrayList<>();
        random = new Random();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * 方法二：水塘抽样
 * 方法一需要花费 O(n)O(n) 的空间存储链表中的所有元素，那么能否做到 O(1)O(1) 的空间复杂度呢？
 *
 * 我们可以设计如下算法：
 *
 * 从链表头开始，遍历整个链表，对遍历到的第 ii 个节点，随机选择区间 [0,i)[0,i) 内的一个整数，如果其等于 00，则将答案置为该节点值，否则答案不变。
 *
 * 该算法会保证每个节点的值成为最后被返回的值的概率均为 \dfrac{1}{n}
 * n
 * 1
 * ​
 *  ，证明如下：
 *
 * \begin{aligned} &P(第\ i\ 个节点的值成为最后被返回的值)\\ =&P(第\ i\ 次随机选择的值= 0) \times P(第\ i+1\ 次随机选择的值\ne 0) \times \cdots \times P(第\ n\ 次随机选择的值\ne 0)\\ =&\dfrac{1}{i} \times (1-\dfrac{1}{i+1}) \times \cdots \times (1-\dfrac{1}{n})\\ =&\dfrac{1}{i} \times \dfrac{i}{i+1} \times \cdots \times \dfrac{n-1}{n}\\ =&\dfrac{1}{n} \end{aligned}
 * =
 * =
 * =
 * =
 * ​
 *
 * P(第 i 个节点的值成为最后被返回的值)
 * P(第 i 次随机选择的值=0)×P(第 i+1 次随机选择的值
 * 
 * ​
 *  =0)×⋯×P(第 n 次随机选择的值
 * 
 * ​
 *  =0)
 * i
 * 1
 * ​
 *  ×(1−
 * i+1
 * 1
 * ​
 *  )×⋯×(1−
 * n
 * 1
 * ​
 *  )
 * i
 * 1
 * ​
 *  ×
 * i+1
 * i
 * ​
 *  ×⋯×
 * n
 * n−1
 * ​
 *
 * n
 * 1
 * ​
 *
 * ​
 *
 *
 * Python3C++JavaC#GolangCJavaScript
 *
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode.cn/problems/linked-list-random-node/solution/lian-biao-sui-ji-jie-dian-by-leetcode-so-x6it/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *执行用时：
 * 13 ms
 * , 在所有 Java 提交中击败了
 * 10.16%
 * 的用户
 * 内存消耗：
 * 43.7 MB
 * , 在所有 Java 提交中击败了
 * 18.07%
 * 的用户
 * 通过测试用例：
 * 8 / 8
 */
class Solution1 {
    ListNode head;
    Random random;

    public Solution1(ListNode head) {
        this.head = head;
        random = new Random();
    }

    public int getRandom() {
        int i = 1, ans = 0;
        for (ListNode node = head; node != null; node = node.next) {
            if (random.nextInt(i) == 0) { // 1/i 的概率选中（替换为答案）
                ans = node.val;
            }
            ++i;
        }
        return ans;
    }
}

