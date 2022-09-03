package com.meng.enterprise.day02;

import java.util.LinkedList;

/**
 * 25. K 个一组翻转链表(困难)
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 *
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 *
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */
public class ReverseKGroup {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 9.43%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 5.10%
     * 的用户
     * 通过测试用例：
     * 62 / 62
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1){
            return head;
        }
        LinkedList<ListNode> cache = new LinkedList<>();
        int index = 0;
        ListNode newHead = new ListNode(-1);
        ListNode temp = newHead;
        while (head !=null){
            index++;
            cache.push(head);
            if (index==k){
                head = head.next;
                while (index>0){
                    temp.next = cache.remove();
                    temp = temp.next;
                    index--;
                }
            }else {
                head = head.next;
            }
        }
        if (index != 0){
            temp.next = cache.removeLast();
        }else {
            temp.next = null;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        //ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        //l4.next = l5;
        ReverseKGroup demo = new ReverseKGroup();
        demo.print(demo.reverseKGroup(l1,2));
    }
    private void print(ListNode head){
        ListNode temp = head;
        while (temp != null){
            System.out.print(temp.val+"->");
            temp = temp.next;
        }
        System.out.println();
        System.out.println("-----------------");
    }
}
