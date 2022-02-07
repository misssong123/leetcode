package com.meng.origin;

/**
 * 从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 *
 *
 * 插入排序算法：
 *
 *     插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 *     每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 *     重复直到所有输入数据插入完为止。
 *
 *
 *
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 示例 2：
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insertion-sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InsertionSortList {
    /**
     * 1.若当前元素小于等于下一个元素，则指向下一个元素，继续执行
     * 2.若当前元素大于下一个元素，则从头找到第一个大于该元素的下标cur，同时记录cur的前一个坐标pre
     *      若cur为第一个元素，
     *              ListNode t = head.next;
     *              head.next =t.next;
     *               t.next =first;
     *               first =t;
     *       若cur不为第一个元素
     *              ListNode t = head.next;
     *              head.next =t.next;
     *              t.next = pre.next;
     *              pre.next =t;
     * 最后放回first即可
     * @param head
     * @return
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.81% 的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了96.51% 的用户
     */
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode first = head;
        while (head.next != null){
            if (head.val<= head.next.val){
                head = head.next;
                continue;
            }
            ListNode temp = first, pre = first;
            while (temp.val<=head.next.val){
                pre =temp;
                temp =temp.next;
            }
            ListNode t = head.next;
            head.next =t.next;
            if (temp == first){
                t.next =first;
                first =t;
            }else{
                t.next = pre.next;
                pre.next =t;
            }
        }
        return first;
    }
    /**
     * 官方解法
     * 方法一：从前往后找插入点
     *
     * 插入排序的基本思想是，维护一个有序序列，初始时有序序列只有一个元素，每次将一个新的元素插入到有序序列中，将有序序列的长度增加 111，直到全部元素都加入到有序序列中。
     *
     * 如果是数组的插入排序，则数组的前面部分是有序序列，每次找到有序序列后面的第一个元素（待插入元素）的插入位置，将有序序列中的插入位置后面的元素都往后移动一位，然后将待插入元素置于插入位置。
     *
     * 对于链表而言，插入元素时只要更新相邻节点的指针即可，不需要像数组一样将插入位置后面的元素往后移动，因此插入操作的时间复杂度是 O(1)O(1)O(1)，但是找到插入位置需要遍历链表中的节点，时间复杂度是 O(n)O(n)O(n)，因此链表插入排序的总时间复杂度仍然是 O(n2)O(n^2)O(n2)，其中 nnn 是链表的长度。
     *
     * 对于单向链表而言，只有指向后一个节点的指针，因此需要从链表的头节点开始往后遍历链表中的节点，寻找插入位置。
     *
     * 对链表进行插入排序的具体过程如下。
     *
     *     首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
     *
     *     创建哑节点 dummyHead，令 dummyHead.next = head。引入哑节点是为了便于在 head 节点之前插入节点。
     *
     *     维护 lastSorted 为链表的已排序部分的最后一个节点，初始时 lastSorted = head。
     *
     *     维护 curr 为待插入的元素，初始时 curr = head.next。
     *
     *     比较 lastSorted 和 curr 的节点值。
     *
     *         若 lastSorted.val <= curr.val，说明 curr 应该位于 lastSorted 之后，将 lastSorted 后移一位，curr 变成新的 lastSorted。
     *
     *         否则，从链表的头节点开始往后遍历链表中的节点，寻找插入 curr 的位置。令 prev 为插入 curr 的位置的前一个节点，进行如下操作，完成对 curr 的插入：
     *
     * lastSorted.next = curr.next
     * curr.next = prev.next
     * prev.next = curr
     *
     *
     * 令 curr = lastSorted.next，此时 curr 为下一个待插入的元素。
     *
     * 重复第 5 步和第 6 步，直到 curr 变成空，排序结束。
     *
     * 返回 dummyHead.next，为排序后的链表的头节点。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/insertion-sort-list/solution/dui-lian-biao-jin-xing-cha-ru-pai-xu-by-leetcode-s/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.81% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了89.05% 的用户
     */
    public ListNode insertionSortList1(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }
}
