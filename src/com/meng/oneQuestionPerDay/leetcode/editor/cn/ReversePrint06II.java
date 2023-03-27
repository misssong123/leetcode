package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.ListNode;

import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ReversePrint06II {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 81.58%
     * 的用户
     * 通过测试用例：
     * 24 / 24
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if (head == null){
            return new int[0];
        }
        int len = 0 ;
        ListNode temp = head;
        while (temp != null){
            len++;
            temp = temp.next;
        }
        int[] res = new int[len];
        while (head != null){
            res[--len] = head.val;
            head = head.next;
        }
        return res;
    }

    /**
     * 栈
     * @param head
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 69.07%
     * 的用户
     * 内存消耗：
     * 42.2 MB
     * , 在所有 Java 提交中击败了
     * 33.09%
     * 的用户
     * 通过测试用例：
     * 24 / 24
     */
    public int[] reversePrint1(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }



    /**
     * 递归
     * @param head
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 86.36%
     * 的用户
     * 通过测试用例：
     * 24 / 24
     */
    int[] res;
    public int[] reversePrint2(ListNode head) {
        backtrack(head,0);
        return res;
    }
    public int backtrack(ListNode node, int length){
        if(node==null){
            res = new int[length];
            return 0;
        }
        int index = backtrack(node.next,length+1);
        res[index] = node.val;
        return index+1;
    }

}
