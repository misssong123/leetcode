package com.meng.level2.day03;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class IsPalindrome234 {
    /**
     * 执行用时：
     * 41 ms
     * , 在所有 Java 提交中击败了
     * 6.87%
     * 的用户
     * 内存消耗：
     * 57.3 MB
     * , 在所有 Java 提交中击败了
     * 58.58%
     * 的用户
     * 通过测试用例：
     * 86 / 86
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head.next == null){
            return true;
        }
        //计算长度
        int len = 0 ;
        ListNode temp = head;
        while (temp != null){
            len++;
            temp = temp.next;
        }
        //获取中间项
        int mid = (len + 1) % 2;
        temp = head;
        for(int i = 0 ; i < mid ; i++){
            temp = temp.next;
        }
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        while (temp != null){
            sb1.append(temp.val);
            sb2.append(head.val);
            head = head.next;
            temp = temp.next;
        }
        return sb1.reverse().toString().equals(sb2.toString());
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(1);
        node2.next = node3;
        IsPalindrome234 demo = new IsPalindrome234();
        System.out.println(demo.isPalindrome(head));
    }

    /**
     * 一共为两个步骤：
     *
     * 复制链表值到数组列表中。
     * 使用双指针法判断是否为回文。
     * 第一步，我们需要遍历链表将值复制到数组列表中。我们用 currentNode 指向当前节点。每次迭代向数组添加 currentNode.val，并更新 currentNode = currentNode.next，当 currentNode = null 时停止循环。
     *
     * 执行第二步的最佳方法取决于你使用的语言。在 Python 中，很容易构造一个列表的反向副本，也很容易比较两个列表。而在其他语言中，就没有那么简单。因此最好使用双指针法来检查是否为回文。我们在起点放置一个指针，在结尾放置一个指针，每一次迭代判断两个指针指向的元素是否相同，若不同，返回 false；相同则将两个指针向内移动，并继续判断，直到两个指针相遇。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 47.40%
     * 的用户
     * 内存消耗：
     * 53.8 MB
     * , 在所有 Java 提交中击败了
     * 70.61%
     * 的用户
     * 通过测试用例：
     * 86 / 86
     */
    public boolean isPalindrome1(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    /**
     * 方法三：快慢指针
     * 思路
     *
     * 避免使用 O(n)O(n) 额外空间的方法就是改变输入。
     *
     * 我们可以将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。比较完成后我们应该将链表恢复原样。虽然不需要恢复也能通过测试用例，但是使用该函数的人通常不希望链表结构被更改。
     *
     * 该方法虽然可以将空间复杂度降到 O(1)O(1)，但是在并发环境下，该方法也有缺点。在并发环境下，函数运行时需要锁定其他线程或进程对链表的访问，因为在函数执行过程中链表会被修改。
     *
     * 算法
     *
     * 整个流程可以分为以下五个步骤：
     *
     * 找到前半部分链表的尾节点。
     * 反转后半部分链表。
     * 判断是否回文。
     * 恢复链表。
     * 返回结果。
     * 执行步骤一，我们可以计算链表节点的数量，然后遍历链表找到前半部分的尾节点。
     *
     * 我们也可以使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。
     *
     * 若链表有奇数个节点，则中间的节点应该看作是前半部分。
     *
     * 步骤二可以使用「206. 反转链表」问题中的解决方法来反转链表的后半部分。
     *
     * 步骤三比较两个部分的值，当后半部分到达末尾则比较完成，可以忽略计数情况中的中间节点。
     *
     * 步骤四与步骤二使用的函数相同，再反转一次恢复链表本身。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 61.27%
     * 的用户
     * 内存消耗：
     * 52 MB
     * , 在所有 Java 提交中击败了
     * 92.52%
     * 的用户
     * 通过测试用例：
     * 86 / 86
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 方法二：递归
     * 思路
     *
     * 为了想出使用空间复杂度为 O(1)O(1) 的算法，你可能想过使用递归来解决，但是这仍然需要 O(n)O(n) 的空间复杂度。
     *
     * 递归为我们提供了一种优雅的方式来方向遍历节点。
     *
     *
     * function print_values_in_reverse(ListNode head)
     *     if head is NOT null
     *         print_values_in_reverse(head.next)
     *         print head.val
     * 如果使用递归反向迭代节点，同时使用递归函数外的变量向前迭代，就可以判断链表是否为回文。
     *
     * 算法
     * currentNode 指针是先到尾节点，由于递归的特性再从后往前进行比较。frontPointer 是递归函数外的指针。若 currentNode.val != frontPointer.val 则返回 false。反之，frontPointer 向前移动并返回 true。
     *
     * 算法的正确性在于递归处理节点的顺序是相反的（回顾上面打印的算法），而我们在函数外又记录了一个变量，因此从本质上，我们同时在正向和逆向迭代匹配。
     *
     * 下面的动画展示了算法的工作原理。我们定义递归函数名字为 recursively_check，每个节点都被赋予了标识符（如 $1）以便更好地解释它们。计算机在递归的过程中将使用堆栈的空间，这就是为什么递归并不是 O(1)O(1) 的空间复杂度。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     * 执行用时：
     * 13 ms
     * , 在所有 Java 提交中击败了
     * 19.82%
     * 的用户
     * 内存消耗：
     * 59.7 MB
     * , 在所有 Java 提交中击败了
     * 7.62%
     * 的用户
     * 通过测试用例：
     * 86 / 86
     */
    public boolean isPalindrome3(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }
}
