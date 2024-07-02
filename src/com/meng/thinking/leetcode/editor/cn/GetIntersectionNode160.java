//leetcode submit region begin(Prohibit modification and deletion)

package com.meng.thinking.leetcode.editor.cn;
import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.ListNode;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class GetIntersectionNode160 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.85% 的Java用户
     * 	内存消耗:47.4 MB,击败了61.89% 的Java用户
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeMy(ListNode headA, ListNode headB) {
        ListNode first = headA;
        ListNode second = headB;
        int len1 = 1, len2 = 1;
        while (first.next != null) {
            len1++;
            first = first.next;
        }
        while (second.next != null) {
            len2++;
            second = second.next;
        }
        if (first != second) {
            return null;
        }
        first = headA;
        second = headB;
        if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++) {
                first = first.next;
            }
        } else {
            for (int i = 0; i < len2 - len1; i++) {
                second = second.next;
            }
        }
        while (first != second) {
            first = first.next;
            second = second.next;
        }
        return first;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.85% 的Java用户
     * 	内存消耗:47.7 MB,击败了5.24% 的Java用户
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
