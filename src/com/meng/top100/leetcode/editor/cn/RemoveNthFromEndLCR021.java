package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.ListNode;

class RemoveNthFromEndLCR021 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.7 MB,击败了5.20% 的Java用户
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndLCR021(ListNode head, int n) {
        ListNode next = head;
        for(int i = 0 ; i < n ; i++){
            next = next.next;
        }
        if (next == null){
            return head.next;
        }
        ListNode cur = head;
        while (next.next != null){
            next = next.next;
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return head;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了5.20% 的Java用户
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 由于可能会删除链表头部，用哨兵节点简化代码
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode right = dummy;
        while (n-- > 0) {
            right = right.next; // 右指针先向右走 n 步
        }
        while (right.next != null) {
            left = left.next;
            right = right.next; // 左右指针一起走
        }
        left.next = left.next.next; // 左指针的下一个节点就是倒数第 n 个节点
        return dummy.next;
    }

}
