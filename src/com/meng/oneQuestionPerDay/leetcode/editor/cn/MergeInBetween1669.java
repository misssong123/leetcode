package com.meng.oneQuestionPerDay.leetcode.editor.cn;

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
class MergeInBetween1669 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 44.8 MB
     * , 在所有 Java 提交中击败了
     * 55.86%
     * 的用户
     * 通过测试用例：
     * 61 / 61
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode temp1 = list1,temp2 = list2,pre = null , tail = null;
        int index = 0;
        while (temp1 != null){
            if (index == a-1){
                pre = temp1;
            }
            if (index == b + 1){
                tail = temp1;
                break;
            }
            temp1 = temp1.next;
            index++;
        }
        while (temp2.next != null){
            temp2 = temp2.next;
        }
        if (pre != null){
            pre.next = list2;
        }
        temp2.next = tail;
        return pre == null ? list2 : list1;
    }

    /**
     * 模拟
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 44.9 MB
     * , 在所有 Java 提交中击败了
     * 44.14%
     * 的用户
     * 通过测试用例：
     * 61 / 61
     */
    public ListNode mergeInBetween1(ListNode list1, int a, int b, ListNode list2) {
        ListNode preA = list1;
        for (int i = 0; i < a - 1; i++) {
            preA = preA.next;
        }
        ListNode preB = preA;
        for (int i = 0; i < b - a + 2; i++) {
            preB = preB.next;
        }
        preA.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = preB;
        return list1;
    }

}
