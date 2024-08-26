package com.meng.interview150.leetcode.editor.cn;

class Interview063DeleteDuplicates {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了87.26% 的Java用户
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        ListNode cur = head.next,pre = head;
        while (cur != null) {
            //cur的值与pre的值不同时
            if (pre.val != cur.val ) {
                if (pre.next ==cur){
                    temp.next = pre;
                    temp = temp.next;
                }
                pre = cur;
            }
            cur = cur.next;
        }
        //最后一个节点
        if (pre.next ==cur){
            temp.next = pre;
            temp = temp.next;
        }
        temp.next = null;
        return dummy.next;
    }
}
