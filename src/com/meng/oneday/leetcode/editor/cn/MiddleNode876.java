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
class MiddleNode876 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了45.96% 的Java用户
     * @param head
     * @return
     */
    public ListNode middleNode876(ListNode head) {
        ListNode slow = head , fast = head.next;
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
            if (fast != null){
                fast = fast.next;
            }
        }
        return slow;
    }
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
