package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


class Interview109SortList {
    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了24.61% 的Java用户
     * 	内存消耗:53.8 MB,击败了92.52% 的Java用户
     * @param head
     * @return
     */
    public ListNode sortListMy(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> temp = new ArrayList<>();
        while (head != null) {
            temp.add(head);
            head = head.next;
        }
        temp.sort(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < temp.size() - 1; i++) {
            temp.get(i).next = temp.get(i + 1);
        }
        temp.get(temp.size() - 1).next = null;
        return temp.get(0);
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了61.45% 的Java用户
     * 	内存消耗:55.7 MB,击败了50.63% 的Java用户
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return dfs(head,null);
    }

    private ListNode dfs(ListNode head, ListNode tail) {
        if (head == null){
            return head;
        }
        if (head.next == tail){
            head.next = null;
            return head;
        }
        //快慢指针寻找中间节点
        ListNode fast = head,slow = head;
        while (fast != tail){
            slow = slow.next;
            fast = fast.next;
            if (fast != tail){
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        return merge(dfs(head,mid),dfs(mid,tail));
    }

    //
    public ListNode merge(ListNode l1,ListNode l2){
        ListNode head = new ListNode();
        ListNode cur = head,temp1 = l1,temp2 = l2;
        while (temp1 != null && temp2 != null){
            if (temp1.val < temp2.val){
                cur.next = temp1;
                temp1 = temp1.next;
            }else {
                cur.next = temp2;
                temp2 = temp2.next;
            }
            cur = cur.next;
        }
        if (temp1 != null){
            cur.next = temp1;
        }
        if (temp2 != null) {
            cur.next = temp2;
        }
        return head.next;
    }
}
