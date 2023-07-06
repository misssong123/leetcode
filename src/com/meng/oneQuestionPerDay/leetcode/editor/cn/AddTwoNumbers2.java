package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.ListNode;

class AddTwoNumbers2 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 53.64%
     * 的用户
     * 通过测试用例：
     * 1568 / 1568
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1),temp = head;
        int num = 0;
        while (l1 != null || l2 != null){
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int sum = num+ num1 + num2;
            ListNode now = new ListNode(sum%10);
            temp.next = now;
            temp = temp.next;
            num = sum / 10;
            if (l1!=null){
                l1 = l1.next;
            }
            if (l2!=null){
                l2 = l2.next;
            }
        }
        if (num!=0){
            ListNode now = new ListNode(num);
            temp.next = now;
        }
        return head.next;
    }
}
