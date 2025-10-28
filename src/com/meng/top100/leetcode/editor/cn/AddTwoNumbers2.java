package com.meng.top100.leetcode.editor.cn;

import com.meng.algorithm.ListNode;

class AddTwoNumbers2 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了30.43% 的Java用户
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode cur = head;
        int pre = 0;
        while (l1 != null || l2 != null){
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            cur.next = new ListNode((num1 + num2 + pre) % 10);
            pre = (num1 + num2 + pre) / 10;
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (pre != 0) {
            cur.next = new ListNode(pre);
        }
        return head.next;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了27.50% 的Java用户
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(); // 哨兵节点
        ListNode cur = dummy;
        int carry = 0; // 进位
        while (l1 != null || l2 != null || carry != 0) { // 有一个不是空节点，或者还有进位，就继续迭代
            if (l1 != null) {
                carry += l1.val; // 节点值和进位加在一起
                l1 = l1.next; // 下一个节点
            }
            if (l2 != null) {
                carry += l2.val; // 节点值和进位加在一起
                l2 = l2.next; // 下一个节点
            }
            cur = cur.next = new ListNode(carry % 10); // 每个节点保存一个数位
            carry /= 10; // 新的进位
        }
        return dummy.next; // 哨兵节点的下一个节点就是头节点
    }
}
