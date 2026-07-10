package com.meng.oneday.leetcode.editor.cn;
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
class AddTwoNumbers445 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了24.02% 的Java用户
     * 	内存消耗:45.9 MB,击败了21.30% 的Java用户
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers445(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode pre = new ListNode();
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int sum = carry;
            sum += s1.isEmpty() ? 0 : s1.pop();
            sum += s2.isEmpty() ? 0 : s2.pop();
            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;
            node.next = pre.next;
            pre.next = node;
        }
        if (carry > 0) {
            ListNode node = new ListNode(carry);
            node.next = pre.next;
            pre.next = node;
        }
        return pre.next;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46 MB,击败了8.99% 的Java用户
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2); // l1 和 l2 反转后，就变成【2. 两数相加】了
        ListNode l3 = addTwo(l1, l2, 0);
        return reverseList(l3);
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head; // 把下一个节点指向自己
        head.next = null; // 断开指向下一个节点的连接，保证最终链表的末尾节点的 next 是空节点
        return newHead;
    }

    // l1 和 l2 为当前遍历的节点，carry 为进位
    private ListNode addTwo(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) { // 递归边界：l1 和 l2 都是空节点
            return carry != 0 ? new ListNode(carry) : null; // 如果进位了，就额外创建一个节点
        }
        if (l1 == null) { // 如果 l1 是空的，那么此时 l2 一定不是空节点
            l1 = l2;
            l2 = null; // 交换 l1 与 l2，保证 l1 非空，从而简化代码
        }
        carry += l1.val + (l2 != null ? l2.val : 0); // 节点值和进位加在一起
        l1.val = carry % 10; // 每个节点保存一个数位
        l1.next = addTwo(l1.next, (l2 != null ? l2.next : null), carry / 10); // 进位
        return l1;
    }

}
