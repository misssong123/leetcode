package com.meng.oneQuestionPerDay.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)


import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.ListNode;

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
class DeleteDuplicates82 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.9 MB,击败了50.88% 的Java用户
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesMy(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        boolean flag = true;
        ListNode pre = null;
        ListNode temp = head;
        while (temp!= null){
            flag = true;
            while (temp.next != null && temp.next.val == temp.val){
                temp = temp.next;
                flag = false;
            }
            if (flag){
                if (pre ==null){
                    head = temp;
                    pre = temp;
                }else {
                    pre.next = temp;
                    pre = pre.next;
                }
            }
            temp = temp.next;
        }
        if (pre !=null){
            pre.next = null;
        }
        return pre == null ? null:head;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了38.58% 的Java用户
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }


}
