package com.meng.oneQuestionPerDay.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)


import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class RemoveNodes2487 {
    /**
     * 单调栈
     * 解答成功:
     * 执行耗时:234 ms,击败了5.28% 的Java用户
     * 内存消耗:60.6 MB,击败了72.45% 的Java用户
     *
     * @param head
     * @return
     */
    public ListNode removeNodesMyStack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            while (!stack.isEmpty() && stack.peek().val < head.val) {
                stack.pop();
            }
            stack.push(head);
            head = head.next;
        }
        ListNode newHead = new ListNode();
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            pop.next = newHead.next;
            newHead.next = pop;
        }
        return newHead.next;
    }

    /**
     * 官方解法1
     * 递归
     * 解答成功:
     * 执行耗时:21 ms,击败了55.85% 的Java用户
     * 内存消耗:73.6 MB,击败了5.27% 的Java用户
     *
     * @param head
     * @return
     */
    public ListNode removeNodes1(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeNodes1(head.next);
        if (head.next != null && head.val < head.next.val) {
            return head.next;
        } else {
            return head;
        }
    }

    /**
     * 官方解法2
     * 单调栈
     * 解答成功:
     * 执行耗时:16 ms,击败了85.28% 的Java用户
     * 内存消耗:64.3 MB,击败了63.39% 的Java用户
     *
     * @param head
     * @return
     */
    public ListNode removeNodes2(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<ListNode>();
        for (; head != null; head = head.next) {
            stack.push(head);
        }
        for (; !stack.isEmpty(); stack.pop()) {
            if (head == null || stack.peek().val >= head.val) {
                stack.peek().next = head;
                head = stack.peek();
            }
        }
        return head;
    }

    /**
     *  官方解法3
     *    反转链表
     * 解答成功:
     * 	执行耗时:8 ms,击败了91.32% 的Java用户
     * 	内存消耗:67.4 MB,击败了7.54% 的Java用户
     * @param head
     * @return
     */
    public ListNode removeNodes3(ListNode head) {
        head = reverse(head);
        for (ListNode p = head; p.next != null; ) {
            if (p.val > p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return reverse(head);
    }

    public ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        while (head != null) {
            ListNode p = head;
            head = head.next;
            p.next = dummy.next;
            dummy.next = p;
        }
        return dummy.next;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
