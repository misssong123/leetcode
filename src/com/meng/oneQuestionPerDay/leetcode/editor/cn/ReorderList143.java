package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.ListNode;

import java.util.ArrayList;
import java.util.List;

class ReorderList143 {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 33.76%
     * 的用户
     * 内存消耗：
     * 44.4 MB
     * , 在所有 Java 提交中击败了
     * 65.62%
     * 的用户
     * 通过测试用例：
     * 12 / 12
     * @param head
     */
    public void reorderList(ListNode head) {
        List<ListNode> cache = new ArrayList<>();
        // 1. 遍历链表，将所有节点存入数组
        while (head != null) {
            cache.add(head);
            head = head.next;
        }
        int size = cache.size();
        //2.重排顺序
        for(int i = 0 ; i < size / 2 ; i++){
            cache.get(i).next = cache.get(size - i - 1);
            cache.get(size - i - 1).next = cache.get(i + 1);
        }
        //3.最后一个节点的next置为null
        if (size % 2 == 1) {
            cache.get(size / 2 ).next = null;
        }
    }

    /**
     *执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.77%
     * 的用户
     * 内存消耗：
     * 44.8 MB
     * , 在所有 Java 提交中击败了
     * 22.55%
     * 的用户
     * 通过测试用例：
     * 12 / 12
     * @param head
     */
    public void reorderList1(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }

}

