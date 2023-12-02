package com.meng.leetcode75;

/**
 *206 反转链表
 */
public class ReverseList206 {
    /**
     * 时间
     * 详情
     * 0ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 40.66MB
     * 击败 5.08%使用 Java 的用户
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = new ListNode(0,head),next=head.next;
        head.next = null;
        while (next != null){
            head = next;
            next = next.next;
            head.next = newHead.next;
            newHead.next = head;
        }
        return newHead.next;
    }

    /**
     * 时间
     * 详情
     * 0ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 40.68MB
     * 击败 5.08%使用 Java 的用户
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}
