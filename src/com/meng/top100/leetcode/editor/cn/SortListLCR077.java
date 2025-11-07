package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.ListNode;

class SortListLCR077 {
    /**
     * 1.记录链表长度
     * 2.找到中间节点，拆分链表
     * 3.长度小于等于2，排序后返回
     * 4.否则继续进行步骤2
     * 5.合并两个有序链表
     * 解答成功:
     * 	执行耗时:5 ms,击败了99.80% 的Java用户
     * 	内存消耗:53.6 MB,击败了5.04% 的Java用户
     * @param head
     * @return
     */
    public ListNode sortListLCR077(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode temp = head;
        int len = 0;
        while (temp != null){
            len++;
            temp = temp.next;
        }
        return dfsLCR077(head,len);
    }

    private ListNode dfsLCR077(ListNode head, int len) {
        if (len == 1){
            return head;
        }
        int mid = len / 2;
        ListNode temp = head;
        while (mid > 1){
            mid--;
            temp = temp.next;
        }
        ListNode rNode = temp.next;
        temp.next = null;
        //左子树递归
        ListNode l = dfsLCR077(head,len / 2);
        //右子树递归
        ListNode r = dfsLCR077(rNode,len - len / 2);
        //合并两个有序链表
        return merge(l,r);
    }

    private ListNode merge(ListNode l, ListNode r) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        while (l != null && r != null){
            if (l.val < r.val){
                temp.next = l;
                l = l.next;
            }else {
                temp.next = r;
                r = r.next;
            }
            temp = temp.next;
        }
        temp.next = l == null ? r : l;
        return dummy.next;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了88.31% 的Java用户
     * 	内存消耗:53.8 MB,击败了5.04% 的Java用户
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 如果链表为空或者只有一个节点，无需排序
        if (head == null || head.next == null) {
            return head;
        }
        // 找到中间节点 head2，并断开 head2 与其前一个节点的连接
        // 比如 head=[4,2,1,3]，那么 middleNode 调用结束后 head=[4,2] head2=[1,3]
        ListNode head2 = middleNode(head);
        // 分治
        head = sortList(head);
        head2 = sortList(head2);
        // 合并
        return mergeTwoLists(head, head2);
    }

    // 876. 链表的中间结点（快慢指针）
    private ListNode middleNode(ListNode head) {
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow; // 记录 slow 的前一个节点
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null; // 断开 slow 的前一个节点和 slow 的连接
        return slow;
    }

    // 21. 合并两个有序链表（双指针）
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(); // 用哨兵节点简化代码逻辑
        ListNode cur = dummy; // cur 指向新链表的末尾
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1; // 把 list1 加到新链表中
                list1 = list1.next;
            } else { // 注：相等的情况加哪个节点都是可以的
                cur.next = list2; // 把 list2 加到新链表中
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2; // 拼接剩余链表
        return dummy.next;
    }


}
