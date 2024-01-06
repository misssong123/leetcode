package com.meng.oneQuestionPerDay.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)


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
class InsertGreatestCommonDivisors2807 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.9 MB,击败了16.40% 的Java用户
     * @param head
     * @return
     */
    public ListNode insertGreatestCommonDivisorsMy(ListNode head) {
        ListNode first = head ,second = head.next;
        while (second != null){
            ListNode temp = new ListNode(getNum(first.val,second.val),second);
            first.next = temp;
            first = second;
            second = second.next;
        }
        return head;
    }
    private int getNum(int a,int b){
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44 MB,击败了9.43% 的Java用户
     * @param head
     * @return
     */
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head;
        while (node.next != null) {
            node.next = new ListNode(gcd(node.val, node.next.val), node.next);
            node = node.next.next;
        }
        return head;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
