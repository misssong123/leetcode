package com.meng.oneday.leetcode.editor.cn;
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
class DeleteMiddle2095 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了94.65% 的Java用户
     * 	内存消耗:198 MB,击败了81.68% 的Java用户
     * @param head
     * @return
     */
    public ListNode deleteMiddle2095(ListNode head) {
        if (head == null || head.next == null){
            return null;
        }
        ListNode slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了94.65% 的Java用户
     * 	内存消耗:198.1 MB,击败了74.23% 的Java用户
     * @param head
     * @return
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) { // 只有一个节点
            return null;
        }

        // 876. 链表的中间结点
        // 本题先让快指针走两步，这样慢指针少走一步，刚好落在中间节点的前一个节点
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = slow.next.next; // 删除 slow 的下一个节点
        return head;
    }

}
