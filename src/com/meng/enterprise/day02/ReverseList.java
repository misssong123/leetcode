package com.meng.enterprise.day02;

/**
 * 剑指 Offer 24. 反转链表(简单)
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 *
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 *
 *
 * 注意：本题与主站 206 题相同：https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class ReverseList {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 60.51%
     * 的用户
     * 通过测试用例：
     * 27 / 27
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode newHead = new ListNode(-1);
        while (head != null){
            ListNode temp = head.next;
            head.next = newHead.next;
            newHead.next = head;
            head = temp;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ReverseList demo = new ReverseList();
        demo.reverseList(l1);
    }

    /**
     * 方法一：迭代
     * 假设链表为 1 \rightarrow 2 \rightarrow 3 \rightarrow \varnothing1→2→3→∅，我们想要把它改成 \varnothing \leftarrow 1 \leftarrow 2 \leftarrow 3∅←1←2←3。
     *
     * 在遍历链表时，将当前节点的 \textit{next}next 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。在更改引用之前，还需要存储后一个节点。最后返回新的头引用。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/solution/fan-zhuan-lian-biao-by-leetcode-solution-jvs5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 61.00%
     * 的用户
     * 通过测试用例：
     * 27 / 27
     */
    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 方法二：递归
     * 递归版本稍微复杂一些，其关键在于反向工作。假设链表的其余部分已经被反转，现在应该如何反转它前面的部分？
     *
     * 假设链表为：
     *
     * n_1\rightarrow \ldots \rightarrow n_{k-1} \rightarrow n_k \rightarrow n_{k+1} \rightarrow \ldots \rightarrow n_m \rightarrow \varnothing
     * n
     * 1
     * ​
     *  →…→n
     * k−1
     * ​
     *  →n
     * k
     * ​
     *  →n
     * k+1
     * ​
     *  →…→n
     * m
     * ​
     *  →∅
     *
     * 若从节点 n_{k+1}n
     * k+1
     * ​
     *   到 n_mn
     * m
     * ​
     *   已经被反转，而我们正处于 n_kn
     * k
     * ​
     *  。
     *
     * n_1\rightarrow \ldots \rightarrow n_{k-1} \rightarrow n_k \rightarrow n_{k+1} \leftarrow \ldots \leftarrow n_m
     * n
     * 1
     * ​
     *  →…→n
     * k−1
     * ​
     *  →n
     * k
     * ​
     *  →n
     * k+1
     * ​
     *  ←…←n
     * m
     * ​
     *
     *
     * 我们希望 n_{k+1}n
     * k+1
     * ​
     *   的下一个节点指向 n_kn
     * k
     * ​
     *  。
     *
     * 所以，n_k.\textit{next}.\textit{next} = n_kn
     * k
     * ​
     *  .next.next=n
     * k
     * ​
     *  。
     *
     * 需要注意的是 n_1n
     * 1
     * ​
     *   的下一个节点必须指向 \varnothing∅。如果忽略了这一点，链表中可能会产生环。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/solution/fan-zhuan-lian-biao-by-leetcode-solution-jvs5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.8 MB
     * , 在所有 Java 提交中击败了
     * 70.46%
     * 的用户
     * 通过测试用例：
     * 27 / 27
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


}
