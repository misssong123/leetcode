package com.meng.interview150.leetcode.editor.cn;

class Interview059HasCycle {
    /**
     * 快慢指针
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.4 MB,击败了54.48% 的Java用户
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null){
            return false;
        }
        ListNode slow = head,fast = head.next;
        while ( fast != null&&fast.next!= null){
            if (slow == fast){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
