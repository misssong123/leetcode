package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.ListNode;

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
class IsPalindrome27II {
    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 53.84%
     * 的用户
     * 内存消耗：
     * 53.9 MB
     * , 在所有 Java 提交中击败了
     * 71.26%
     * 的用户
     * 通过测试用例：
     * 85 / 85
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        int size = list.size()-1,first = 0;
        while (first <= size){
            if (list.get(first) != list.get(size)){
                return false;
            }
            first++;
            size--;
        }
        return true;
    }



    /**
     * 执行用时：
     * 16 ms
     * , 在所有 Java 提交中击败了
     * 18.79%
     * 的用户
     * 内存消耗：
     * 59.1 MB
     * , 在所有 Java 提交中击败了
     * 14.01%
     * 的用户
     * 通过测试用例：
     * 85 / 85
     * @param head
     * @return
     */
    private ListNode frontPointer;
    public boolean isPalindrome1(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }
}
