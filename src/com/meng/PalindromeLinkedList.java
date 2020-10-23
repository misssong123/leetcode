package com.meng;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 234. 回文链表
 *请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(0);
        ListNode n3 = new ListNode(0);
        head.next = n2;
        n2.next = n3;
        PalindromeLinkedList demo = new PalindromeLinkedList();
        System.out.println(demo.isPalindrome1(head));
    }
    /**
     * 使用集合解决问题
     * @param head
     * @return
     * 执行用时：4 ms, 在所有 Java 提交中击败了28.12% 的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了44.21% 的用户
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null){
            list.add(temp.val);
            temp = temp.next;
        }
        int start = 0 ,end = list.size()-1;
        while (start<end){
            if (!list.get(start).equals(list.get(end)))//注意不要使用== 在超过-128~127后比较的相当于地址
                return false;
            start++;
            end--;
        }
        return true;
    }

    /**
     * 使用栈解决问题
     * @param head
     * @return
     * 执行用时：3 ms, 在所有 Java 提交中击败了33.88% 的用户
     * 内存消耗：42.1 MB, 在所有 Java 提交中击败了42.92% 的用户
     */
    public boolean isPalindrome1(ListNode head) {
        if (head == null)
            return true;
        Stack<Integer> stack = new Stack<>();
        ListNode temp = head,slow =  head ,fast =head;
        while (fast.next!= null && fast.next.next!=null){
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next != null)
            stack.add(slow.val);
        slow = slow.next;
        while(slow != null){
            if (!stack.pop().equals(slow.val))
                return false;
            slow = slow.next;
        }
        return true;
    }
    /**
     * 官方解法1
     *    复制链表值到数组列表中。
     *     使用双指针法判断是否为回文。
     *
     * 第一步，我们需要遍历链表将值复制到数组列表中。我们用 currentNode 指向当前节点。每次迭代向数组添加 currentNode.val，并更新 currentNode = currentNode.next，当 currentNode = null 时停止循环。
     *
     * 执行第二步的最佳方法取决于你使用的语言。在 Python 中，很容易构造一个列表的反向副本，也很容易比较两个列表。而在其他语言中，就没有那么简单。因此最好使用双指针法来检查是否为回文。我们在起点放置一个指针，在结尾放置一个指针，每一次迭代判断两个指针指向的元素是否相同，若不同，返回 false；相同则将两个指针向内移动，并继续判断，直到两个指针相遇。
     *
     * 在编码的过程中，注意我们比较的是节点值的大小，而不是节点本身。正确的比较方式是：node_1.val == node_2.val，而 node_1 == node_2 是错误的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：3 ms, 在所有 Java 提交中击败了33.88% 的用户
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了40.91% 的用户
     */
    public boolean isPalindrome2(ListNode head) {
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
     * 官方解法2
     * currentNode 指针是先到尾节点，由于递归的特性再从后往前进行比较。frontPointer 是递归函数外的指针。若 currentNode.val != frontPointer.val 则返回 false。反之，frontPointer 向前移动并返回 true。
     *
     * 算法的正确性在于递归处理节点的顺序是相反的（回顾上面打印的算法），而我们在函数外又记录了一个变量，因此从本质上，我们同时在正向和逆向迭代匹配。
     *
     * 下面的动画展示了算法的工作原理。我们定义递归函数名字为 recursively_check，每个节点都被赋予了标识符（如 $1）以便更好地解释它们。计算机在递归的过程中将使用堆栈的空间，这就是为什么递归并不是 O(1)O(1)O(1) 的空间复杂度。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了54.30% 的用户
     * 内存消耗：44.8 MB, 在所有 Java 提交中击败了5.00% 的用户
     */
    private ListNode frontPointer;

    private boolean recursivelyCheck3(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck3(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindrome3(ListNode head) {
        frontPointer = head;
        return recursivelyCheck3(head);
    }
    /**
     * 官方解法3
     * 避免使用 O(n)O(n)O(n) 额外空间的方法就是改变输入。
     *
     * 我们可以将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。比较完成后我们应该将链表恢复原样。虽然不需要恢复也能通过测试用例，但是使用该函数的人通常不希望链表结构被更改。
     *
     * 该方法虽然可以将空间复杂度降到 O(1)O(1)O(1)，但是在并发环境下，该方法也有缺点。在并发环境下，函数运行时需要锁定其他线程或进程对链表的访问，因为在函数执行过程中链表会被修改。
     *
     * 算法
     *
     * 整个流程可以分为以下五个步骤：
     *
     *     找到前半部分链表的尾节点。
     *     反转后半部分链表。
     *     判断是否回文。
     *     恢复链表。
     *     返回结果。
     *
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
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了54.30% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了94.86% 的用户
     * @param head
     * @return
     */
    public boolean isPalindrome4(ListNode head) {
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

}
