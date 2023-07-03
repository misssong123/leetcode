package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
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
class AddTwoNumbers445 {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 72.21%
     * 的用户
     * 内存消耗：
     * 42.2 MB
     * , 在所有 Java 提交中击败了
     * 41.16%
     * 的用户
     * 通过测试用例：
     * 1563 / 1563
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        while (l1 != null){
            list1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            list2.push(l2.val);
            l2 = l2.next;
        }
        ListNode head = new ListNode();
        int pre = 0;
        while (!list1.isEmpty() || !list2.isEmpty()){
            int num1 = list1.isEmpty()?0:list1.pop();
            int num2 = list2.isEmpty()?0:list2.pop();
            int val = (num1 + num2 + pre) % 10;
            pre = (num1 + num2 + pre) / 10;
            ListNode now = new ListNode(val);
            now.next = head.next;
            head.next = now;
        }
        if (pre != 0){
            ListNode now = new ListNode(pre);
            now.next = head.next;
            head.next = now;
        }
        return head.next;
    }

    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 72.21%
     * 的用户
     * 内存消耗：
     * 42.5 MB
     * , 在所有 Java 提交中击败了
     * 27.17%
     * 的用户
     * 通过测试用例：
     * 1563 / 1563
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<Integer>();
        Deque<Integer> stack2 = new ArrayDeque<Integer>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int cur = a + b + carry;
            carry = cur / 10;
            cur %= 10;
            ListNode curnode = new ListNode(cur);
            curnode.next = ans;
            ans = curnode;
        }
        return ans;
    }

}

