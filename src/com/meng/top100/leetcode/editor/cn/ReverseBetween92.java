package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.ListNode;

class ReverseBetween92 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了47.61% 的Java用户
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween92(ListNode head, int left, int right) {
        //无需反转
        if (left == right){
            return head;
        }
        //记录前节点/后节点/当前节点
        ListNode pre = null;
        ListNode cur = head;
        ListNode first = null;
        ListNode node = new ListNode(-1);
        int num = 1;
        while (num <= right){
            if (num >= left){
                if (num == left){
                    first = cur;
                }
                ListNode temp =  cur.next;
                cur.next = node.next;
                node.next = cur;
                cur = temp;
            }else{
                pre = cur;
                cur = cur.next;
            }
            num++;
        }
        //链接后节点
        first.next = cur;
        if (pre != null){
            pre.next = node.next;
            return head;
        }
        return node.next;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.6 MB,击败了12.13% 的Java用户
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
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
