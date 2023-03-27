package com.meng.hot100;

import com.meng.hot100.utils.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点(中等)
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
public class T010RemoveNthFromEnd {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.9 MB
     * , 在所有 Java 提交中击败了
     * 24.72%
     * 的用户
     * 通过测试用例：
     * 208 / 208
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next==null){
            return null;
        }
        ListNode end = head,temp = head;
        for(int i = 0 ; i < n ; i++){
            end = end.next;
        }
        if (end == null){
            return head.next;
        }
        while (end.next != null){
            end = end.next;
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return head;
    }

    /**
     * 方法三：双指针
     * 思路与算法
     *
     * 我们也可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
     *
     * 由于我们需要找到倒数第 nn 个节点，因此我们可以使用两个指针 \textit{first}first 和 \textit{second}second 同时对链表进行遍历，并且 \textit{first}first 比 \textit{second}second 超前 nn 个节点。当 \textit{first}first 遍历到链表的末尾时，\textit{second}second 就恰好处于倒数第 nn 个节点。
     *
     * 具体地，初始时 \textit{first}first 和 \textit{second}second 均指向头节点。我们首先使用 \textit{first}first 对链表进行遍历，遍历的次数为 nn。此时，\textit{first}first 和 \textit{second}second 之间间隔了 n-1n−1 个节点，即 \textit{first}first 比 \textit{second}second 超前了 nn 个节点。
     *
     * 在这之后，我们同时使用 \textit{first}first 和 \textit{second}second 对链表进行遍历。当 \textit{first}first 遍历到链表的末尾（即 \textit{first}first 为空指针）时，\textit{second}second 恰好指向倒数第 nn 个节点。
     *
     * 根据方法一和方法二，如果我们能够得到的是倒数第 nn 个节点的前驱节点而不是倒数第 nn 个节点的话，删除操作会更加方便。因此我们可以考虑在初始时将 \textit{second}second 指向哑节点，其余的操作步骤不变。这样一来，当 \textit{first}first 遍历到链表的末尾时，\textit{second}second 的下一个节点就是我们需要删除的节点。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @param n
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.1 MB
     * , 在所有 Java 提交中击败了
     * 5.21%
     * 的用户
     * 通过测试用例：
     * 208 / 208
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

}
