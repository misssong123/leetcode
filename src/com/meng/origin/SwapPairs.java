package com.meng.origin;

import com.meng.origin.ListNode;

public class SwapPairs {
    /**
     * 思路
     * 1.判断当前节点及当前节点的next节点是否为空
     * 2.不为空，，新返回的头结点为最初节点的next节点，
     * 创建pre前一个节点，记录当前节点的上一个节点
     *          创建临时节点temp存放当前节点的下一个节点
     *          当前节点的next指向temp的next
     *          temp的next指向当前节点
     *          若前一个节点不为空，则前一个节点的next指向temp
     *          更新pre节点
     *          更新当前节点的值
     * 3.为空 返回
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        //若当前节点或者当前节点的下一个节点为空，返回head
        if(head == null || head.next==null)
            return head;
        //pre存在当前节点的前一个节点，root为新的头结点
        ListNode pre = null , root = head.next;
        while(head!=null && head.next!=null){
            //存放当前节点的下一个节点
            ListNode temp = head.next;
            //当前节点的next指向当前节点的next的next
            head.next = temp.next;
            //原当前节点的next指向当前节点
            temp.next = head;
            //若前一个节点不为空的话，则前一个节点的next指向原当前节点的next
            if (pre != null)
                pre.next = temp;
            //更新前一个节点
            pre = head;
            //更新当前节点
            head = head.next;
        }
        return root;
    };

    /**
     * 官方解法一
     * 方法一：递归
     * 思路与算法
     * 可以通过递归的方式实现两两交换链表中的节点。
     * 递归的终止条件是链表中没有节点，或者链表中只有一个节点，此时无法进行交换。
     * 如果链表中至少有两个节点，则在两两交换链表中的节点之后，原始链表的头节点变成新的链表的第二个节点，原始链表的第二个节点变成新的链表的头节点。链表中的其余节点的两两交换可以递归地实现。在对链表中的其余节点递归地两两交换之后，更新节点之间的指针关系，即可完成整个链表的两两交换。
     * 用 head 表示原始链表的头节点，新的链表的第二个节点，用 newHead 表示新的链表的头节点，原始链表的第二个节点，则原始链表中的其余节点的头节点是 newHead.next。令 head.next = swapPairs(newHead.next)，表示将其余节点进行两两交换，交换后的新的头节点为 head 的下一个节点。然后令 newHead.next = head，即完成了所有节点的交换。最后返回新的链表的头节点 newHead。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-91/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    /**
     * 方法二：迭代
     *
     * 思路与算法
     *
     * 也可以通过迭代的方式实现两两交换链表中的节点。
     *
     * 创建哑结点 dummyHead，令 dummyHead.next = head。令 temp 表示当前到达的节点，初始时 temp = dummyHead。每次需要交换 temp 后面的两个节点。
     *
     * 如果 temp 的后面没有节点或者只有一个节点，则没有更多的节点需要交换，因此结束交换。否则，获得 temp 后面的两个节点 node1 和 node2，通过更新节点的指针关系实现两两交换节点。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-91/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }
}
