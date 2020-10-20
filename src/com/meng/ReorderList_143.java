package com.meng;

import java.util.ArrayList;
import java.util.List;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 *
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReorderList_143 {
    /**
     * 将元素放到集合中，通过集合的定点存储完成题目要求
     * @param head
     */
    public void reorderList(ListNode head) {
        if(head != null){
            List<ListNode> list = new ArrayList<>();
            ListNode temp = head;
            while (temp != null){
                list.add(temp);
                temp = temp.next;
            }
            int len = list.size();
            temp = new ListNode(0);
            for(int i = 0 ; i <=(len-1)/2;i++){
                //放入当前元素
                temp.next = list.get(i);
                temp = temp.next;
                if (i != (len-1-i)){
                    list.get(len-1-i).next = temp.next;
                    temp.next = list.get(len - 1 - i);
                    temp = temp.next;
                }
            }
            temp.next = null;
        }
    }
    /**
     * 官方解法1
     * 方法一：线性表
     *
     * 因为链表不支持下标访问，所以我们无法随机访问链表中任意位置的元素。
     *
     * 因此比较容易想到的一个方法是，我们利用线性表存储该链表，然后利用线性表可以下标访问的特点，直接按顺序访问指定元素，重建该链表即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reorder-list/solution/zhong-pai-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public void reorderList1(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
    /**
     * 官方解法2
     * 方法二：寻找链表中点 + 链表逆序 + 合并链表
     *
     * 注意到目标链表即为将原链表的左半端和反转后的右半端合并后的结果。
     *
     * 这样我们的任务即可划分为三步：
     *
     *     找到原链表的中点（参考「876. 链表的中间结点」）。
     *         我们可以使用快慢指针来 O(N)O(N)O(N) 地找到链表的中间节点。
     *
     *     将原链表的右半端反转（参考「206. 反转链表」）。
     *         我们可以使用迭代法实现链表的反转。
     *
     *     将原链表的两端合并。
     *         因为两链表长度相差不超过 111，因此直接合并即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reorder-list/solution/zhong-pai-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        //寻找中间节点
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    /**
     * 通过快慢指针寻找中间节点
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 反序中间节点以后的链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 合并前端链表和反序后的链表
     * @param l1
     * @param l2
     */
    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }
}
