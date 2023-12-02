package com.meng.leetcode75;

public class DeleteMiddle2095 {
    /**
     * 时间
     * 详情
     * 3ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 62.49MB
     * 击败 5.06%使用 Java 的用户
     * @param head
     * @return
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null){
            return null;
        }
        ListNode newHead = head,slow = head,fast = head.next;
        while (fast != null){
            fast = fast.next;
            if (fast != null){
                fast = fast.next;
            }
            if (fast != null){
                slow = slow.next;
            }
        }
        slow.next = slow.next.next;
        return newHead;
    }
}
