package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.ListNode;

class RotateRight61 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.8 MB,击败了5.00% 的Java用户
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight61(ListNode head, int k) {
        if (head == null || head.next == null || k == 0){
            return head;
        }
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        k = k % len;
        if (k == 0){
            return head;
        }
        //寻找新节点
        ListNode pre = head;
        for (int i = 0; i < len - k - 1; i++) {
            pre = pre.next;
        }
        ListNode newHead = pre.next;
        pre.next = null;
        tail.next = head;
        return newHead;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了5.00% 的Java用户
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightOfficial(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }
}
