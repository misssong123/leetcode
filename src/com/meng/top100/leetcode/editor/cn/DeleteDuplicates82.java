package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.ListNode;

class DeleteDuplicates82 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.1 MB,击败了5.02% 的Java用户
     * @param head
     * @return
     */
    public ListNode deleteDuplicates82(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode  temp = dummy;
        ListNode l = head ,r = head.next;
        while (r != null){
            if(l.val != r.val){
                if (l.next == r){
                    temp.next = l;
                    temp = temp.next;
                }
                l = r;
            }
            r = r.next;
        }
        if (l.next == null){
            temp.next = l;
            temp = temp.next;
        }
        temp.next = null;
        return dummy.next;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.3 MB,击败了5.02% 的Java用户
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            int val = cur.next.val;
            if (cur.next.next.val == val) { // 后两个节点值相同
                // 值等于 val 的节点全部删除
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
