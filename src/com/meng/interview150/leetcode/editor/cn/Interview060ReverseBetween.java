package com.meng.interview150.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)


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
class Interview060ReverseBetween {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了25.83% 的Java用户
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode  reverseBetweenMy(ListNode head, int left, int right) {
        List<ListNode> list = new ArrayList<>();
        while (head != null){
            list.add(head);
            head = head.next;
        }
        ListNode pre = new ListNode(-1);
        ListNode temp = pre;
        for(int i = 1 ; i < left ; i++){
            temp.next = list.get(i-1);
            temp = temp.next;
        }
        for (int i = right ; i >= left ; i--){
            temp.next = list.get(i-1);
            temp = temp.next;
        }
        for(int i = right ; i < list.size() ; i++) {
            temp.next = list.get(i);
            temp = temp.next;
        }
        temp.next = null;
        return pre.next;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.2 MB,击败了71.44% 的Java用户
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode  reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode p0 = dummy;
        for (int i = 0; i < left - 1; i++) {
            p0 = p0.next;
        }

        ListNode pre = null;
        ListNode cur = p0.next;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode nxt = cur.next;
            cur.next = pre; // 每次循环只修改一个 next，方便大家理解
            pre = cur;
            cur = nxt;
        }

        // 见视频
        p0.next.next = cur;
        p0.next = pre;
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
