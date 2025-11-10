package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.ListNode;

class Partition86 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.3 MB,击败了5.02% 的Java用户
     * @param head
     * @param x
     * @return
     */
    public ListNode partition86(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode minHead = new ListNode(0);
        ListNode maxHead = new ListNode(0);
        ListNode min = minHead;
        ListNode max = maxHead;
        while (head != null) {
            if (head.val < x) {
                min.next = head;
                min = min.next;
            }else{
                max.next = head;
                max = max.next;
            }
            head = head.next;
        }
        min.next = maxHead.next;
        max.next = null;
        return minHead.next;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.1 MB,击败了5.02% 的Java用户
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
