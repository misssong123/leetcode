package com.meng.interview150.leetcode.editor.cn;

class Interview062RemoveNthFromEnd {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了44.84% 的Java用户
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = null,slow = head,fast = head;
        while(n-- >0){
            fast = fast.next;
        }
        while (fast!=null){
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        if (pre == null){
            return head.next;
        }
        pre.next = slow.next;
        return head;
    }
}
