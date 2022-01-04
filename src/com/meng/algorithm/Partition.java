package com.meng.algorithm;

/**
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 *
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class Partition {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 37.9 MB
     * , 在所有 Java 提交中击败了
     * 24.98%
     * 的用户
     * 通过测试用例：
     * 168 / 168
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode(-1);
        ListNode more = new ListNode(-1);
        ListNode lHead = less;
        ListNode mHead = more;
        while (head != null){
            if (head.val < x){
                less.next = head;
                less = less.next;
            }else {
                more.next = head;
                more = more.next;
            }
            head = head.next;
        }
        if (lHead.next == null){
            return mHead.next;
        }else {
            more.next = null;
            less.next = mHead.next;
            return lHead.next;
        }
    }

    /**
     * 方法一：模拟
     * 直观来说我们只需维护两个链表 \textit{small}small 和 \textit{large}large 即可，\textit{small}small 链表按顺序存储所有小于 xx 的节点，\textit{large}large 链表按顺序存储所有大于等于 xx 的节点。遍历完原链表后，我们只要将 \textit{small}small 链表尾节点指向 \textit{large}large 链表的头节点即能完成对链表的分隔。
     *
     * 为了实现上述思路，我们设 \textit{smallHead}smallHead 和 \textit{largeHead}largeHead 分别为两个链表的哑节点，即它们的 \textit{next}next 指针指向链表的头节点，这样做的目的是为了更方便地处理头节点为空的边界条件。同时设 \textit{small}small 和 \textit{large}large 节点指向当前链表的末尾节点。开始时 \textit{smallHead}=\textit{small},\textit{largeHead}=\textit{large}smallHead=small,largeHead=large。随后，从前往后遍历链表，判断当前链表的节点值是否小于 xx，如果小于就将 \textit{small}small 的 \textit{next}next 指针指向该节点，否则将 \textit{large}large 的 \textit{next}next 指针指向该节点。
     *
     * 遍历结束后，我们将 \textit{large}large 的 \textit{next}next 指针置空，这是因为当前节点复用的是原链表的节点，而其 \textit{next}next 指针可能指向一个小于 xx 的节点，我们需要切断这个引用。同时将 \textit{small}small 的 \textit{next}next 指针指向 \textit{largeHead}largeHead 的 \textit{next}next 指针指向的节点，即真正意义上的 \textit{large}large 链表的头节点。最后返回 \textit{smallHead}smallHead 的 \textit{next}next 指针即为我们要求的答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/partition-list/solution/fen-ge-lian-biao-by-leetcode-solution-7ade/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @param x
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 37.6 MB
     * , 在所有 Java 提交中击败了
     * 77.77%
     * 的用户
     * 通过测试用例：
     * 168 / 168
     */
    public ListNode partition1(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(2);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        int x = 3;
        Partition demo = new Partition();
        System.out.println(demo.partition(head,x));
    }
}
