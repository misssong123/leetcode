package com.meng;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 19. 删除链表的倒数第N个节点

 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

 示例：

 给定一个链表: 1->2->3->4->5, 和 n = 2.

 当删除了倒数第二个节点后，链表变为 1->2->3->5.

 说明：

 给定的 n 保证是有效的。

 进阶：

 你能尝试使用一趟扫描实现吗？

 */
public class RemoveNthFromEnd {
    /**
     * 使用栈存储所有节点
     * 找到需要删除的节点
     *  判断是否为头结点
     *      1.头结点，head = head.next
     *      2.非头结点,栈中弹出的元素.next = 待删除元素的next
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp!=null){
            stack.push(temp);
            temp = temp.next;
        }
        while(n>1){
            stack.pop();
            n--;
        }
        //待删除的节点
        temp = stack.pop();
        if (temp == head){//头结点
            head = head.next;
        }else{//不为头结点
            stack.pop().next = temp.next;
        }
        return head;
    }
    /**
     * 官方解法1
     *方法一：计算链表长度
     *
     * 思路与算法
     *
     * 一种容易想到的方法是，我们首先从头节点开始对链表进行一次遍历，得到链表的长度 LLL。随后我们再从头节点开始对链表进行一次遍历，当遍历到第 L−n+1L-n+1L−n+1 个节点时，它就是我们需要删除的节点。
     *
     *     为了与题目中的 nnn 保持一致，节点的编号从 111 开始，头节点为编号 111 的节点。
     *
     * 为了方便删除操作，我们可以从哑节点开始遍历 L−n+1L-n+1L−n+1 个节点。当遍历到第 L−n+1L-n+1L−n+1 个节点时，它的下一个节点就是我们需要删除的节点，这样我们只需要修改一次指针，就能完成删除操作。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }
    //样例类
    class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
/**
 * 官方解法2
 * 栈
 *
 * 思路与算法
 *
 * 我们也可以在遍历链表的同时将所有节点依次入栈。根据栈「先进后出」的原则，我们弹出栈的第 nnn 个节点就是需要删除的节点，并且目前栈顶的节点就是待删除节点的前驱节点。这样一来，删除操作就变得十分方便了。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public ListNode removeNthFromEnd2(ListNode head, int n) {
    ListNode dummy = new ListNode(0, head);
    Deque<ListNode> stack = new LinkedList<ListNode>();
    ListNode cur = dummy;
    while (cur != null) {
        stack.push(cur);
        cur = cur.next;
    }
    for (int i = 0; i < n; ++i) {
        stack.pop();
    }
    ListNode prev = stack.peek();
    prev.next = prev.next.next;
    ListNode ans = dummy.next;
    return ans;
}
/**
 * 官方解法三
 * 方法三：一次遍历
 *
 * 思路与算法
 *
 * 我们也可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
 *
 * 由于我们需要找到倒数第 nnn 个节点，因此我们可以使用两个指针 first\textit{first}first 和 second\textit{second}second 同时对链表进行遍历，并且 first\textit{first}first 比 second\textit{second}second 超前 nnn 个节点。当 first\textit{first}first 遍历到链表的末尾时，second\textit{second}second 就恰好处于倒数第 nnn 个节点。
 *
 * 具体地，初始时 first\textit{first}first 和 second\textit{second}second 均指向头节点。我们首先使用 first\textit{first}first 对链表进行遍历，遍历的次数为 nnn。此时，first\textit{first}first 和 second\textit{second}second 之间间隔了 n−1n-1n−1 个节点，即 first\textit{first}first 比 second\textit{second}second 超前了 nnn 个节点。
 *
 * 在这之后，我们同时使用 first\textit{first}first 和 second\textit{second}second 对链表进行遍历。当 first\textit{first}first 遍历到链表的末尾（即 first\textit{first}first 为空指针）时，second\textit{second}second 恰好指向倒数第 nnn 个节点。
 *
 * 根据方法一和方法二，如果我们能够得到的是倒数第 nnn 个节点的前驱节点而不是倒数第 nnn 个节点的话，删除操作会更加方便。因此我们可以考虑在初始时将 second\textit{second}second 指向哑节点，其余的操作步骤不变。这样一来，当 first\textit{first}first 遍历到链表的末尾时，second\textit{second}second 的下一个节点就是我们需要删除的节点。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public ListNode removeNthFromEnd3(ListNode head, int n) {
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
