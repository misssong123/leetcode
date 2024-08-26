package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

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
class Interview067ReverseKGroup {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了9.78% 的Java用户
     * 	内存消耗:43.1 MB,击败了81.55% 的Java用户
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroupMy(ListNode head, int k) {
        if (k == 1 || head == null || head.next == null) {
            return head;
        }
        List<ListNode> cache = new ArrayList<>();
        //记录
        while (head != null) {
            cache.add(head);
            head = head.next;
        }
        ListNode newHead = new ListNode();
        ListNode temp = newHead;
        for (int i = k; i <= cache.size(); i += k) {
            for (int j = i-1; j >= i-k; j--) {
                temp.next = cache.get(j);
                temp = temp.next;
            }
            temp.next = null;
        }
        for(int i = cache.size() /k * k  ; i < cache.size() ; i++){
            temp.next = cache.get(i);
            temp = temp.next;
        }
        return newHead.next;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.2 MB,击败了58.47% 的Java用户
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
