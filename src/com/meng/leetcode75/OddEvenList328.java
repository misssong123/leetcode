package com.meng.leetcode75;

/**
 * 328 奇偶链表
 */
public class OddEvenList328 {
    /**
     * 时间
     * 0ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 42.41MB
     * 击败 5.11%使用 Java 的用户
     * @param head
     * @return
     */
    public ListNode oddEvenList1(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode oddNode = new ListNode(),evenNode = new ListNode(),oddHead = oddNode,evenHead = evenNode;
        int index = 1;
        while (head != null){
            if (index % 2 == 0){
                evenNode.next = head;
                evenNode = evenNode.next;
            }else {
                oddNode.next = head;
                oddNode = oddNode.next;
            }
            head = head.next;
            index++;
        }
        oddNode.next = evenHead.next;
        evenNode.next = null;
        return oddHead.next;
    }

    /**
     *时间
     * 详情
     * 0ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 42.51MB
     * 击败 5.11%使用 Java 的用户
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

}
