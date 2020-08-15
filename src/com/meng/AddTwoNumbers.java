package com.meng;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(4);
        l2.next = l4;
        //l4.next = l3;
        l5.next = l6;
        l6.next = l7;
        AddTwoNumbers demo = new AddTwoNumbers();
        ListNode node = demo.addTwoNumbers(l2, l5);
        while (null != node){
            System.out.print(node.val+" -> ");
            node = node.next;
        }

    }

    /**
     * 1.遍历给定的两个链表，直到两个链表的next都为空
     * 2.若其中一个链表为空，则val设置为0
     * 3.若返回结果node为空，则新建ListNode赋予node（注意使用pre同时指向node，避免返回结果出错）
     * 4.否则新建ListNode赋予pre的next
     * 5.同时是l1指向l1的next，l2指向l2的next
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = null;
        ListNode pre = null;
        int add = 0;
        int scale = 0;
        int a = 0;
        int b = 0;
        while(null != l1 || null != l2){
            if (null == l1)
                a = 0;
            else
             a = l1.val;
            if (null == l2)
                b = 0;
            else
             b= l2.val;
            add=(a+b+scale)%10;
            scale=(a+b+scale)/10;
            if (null == node){
                node = new ListNode(add);
                pre = node;
            }
            else {
                pre.next = new ListNode(add);
                pre = pre.next;
            }
            if (null != l1)
                l1 = null==l1.next?null:l1.next;
            if(null != l2)
                l2 = null==l2.next?null:l2.next;
        }
        if (scale != 0)
            pre.next = new ListNode(scale);

        return node;
    }

    /**
     * 官方答案
     * @param l1
     * @param l2
     * @return
     */
    public ListNode OfficeResult(ListNode l1, ListNode l2){
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}