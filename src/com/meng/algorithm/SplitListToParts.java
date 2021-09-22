package com.meng.algorithm;

import java.util.Arrays;

/**
 * 725. 分隔链表
 * 难度
 * 中等
 *
 * 188
 *
 *
 *
 *
 *
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 *
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 *
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
 *
 * 返回一个由上述 k 部分组成的数组。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3], k = 5
 * 输出：[[1],[2],[3],[],[]]
 * 解释：
 * 第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
 * 最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
 * 示例 2：
 *
 *
 * 输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * 输出：[[1,2,3,4],[5,6,7],[8,9,10]]
 * 解释：
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 1000]
 * 0 <= Node.val <= 1000
 * 1 <= k <= 50
 */
public class SplitListToParts {
    /**
     * \执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.6 MB
     * , 在所有 Java 提交中击败了
     * 50.20%
     * 的用户
     * @param head
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        if (k == 1){
            res[0] = head;
            return res;
        }
        int len = getSize(head);
        if (len == 0){
            return res;
        }
        int size = len / k ;
        int dup = len % k;
        int index = 0;
        for(int i = 0 ; i < dup ; i++){
            head = fill(head,index++,size+1,res);
        }
        for(int i = index ; i < k ; i++){
            head = fill(head,index++,size,res);
        }
        return res;
    }

    private ListNode fill(ListNode head, int index, int size, ListNode[] res) {
        if (head != null){
            ListNode temp = head;
            for(int i = 0 ; i < size - 1 ; i++){
                temp = temp.next;
            }
            ListNode tail = temp.next;
            temp.next = null;
            res[index] = head;
            return tail;
        }
       return null;
    }

    public int getSize(ListNode head){
        if (head == null){
            return 0;
        }
        int len = 0;
        ListNode temp = head ;
        while (temp != null){
            len++;
            temp = temp.next;
        }
        return len;
    }

    public static void main(String[] args) {
        SplitListToParts demo = new SplitListToParts();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        ListNode[] listNodes = demo.splitListToParts(node1, 5);
        System.out.println(Arrays.toString(listNodes));
    }

    /**
     * 方法一：拆分链表
     *
     * 题目要求将给定的链表分隔成
     * k
     * k 个连续的部分。由于分隔成的每个部分的长度和原始链表的长度有关，因此需要首先遍历链表，得到链表的长度
     * n
     * n。
     *
     * 得到链表的长度
     * n
     * n 之后，记
     * quotient
     * =
     * ⌊
     * n
     * k
     * ⌋
     * quotient=⌊
     * k
     * n
     * ​
     *  ⌋，
     * remainder
     * =
     * n
     *
     *
     * k
     * remainder=nmodk，则在分隔成的
     * k
     * k 个部分中，前
     * remainder
     * remainder 个部分的长度各为
     * quotient
     * +
     * 1
     * quotient+1，其余每个部分的长度各为
     * quotient
     * quotient。
     *
     * 分隔链表时，从链表的头结点开始遍历，记当前结点为
     * curr
     * curr，对于每个部分，进行如下操作：
     *
     * 将
     * curr
     * curr 作为当前部分的头结点；
     * 计算当前部分的长度
     * partSize
     * partSize；
     * 将
     * curr
     * curr 向后移动
     * partSize
     * partSize 步，则
     * curr
     * curr 为当前部分的尾结点；
     * 当
     * curr
     * curr 到达当前部分的尾结点时，需要拆分
     * curr
     * curr 和后面一个结点之间的连接关系，在拆分之前需要存储
     * curr
     * curr 的后一个结点
     * next
     * next；
     * 令
     * curr
     * curr 的
     * next
     * next 指针指向
     * null
     * null，完成
     * curr
     * curr 和
     * next
     * next 的拆分；
     * 将
     * next
     * next 赋值给
     * curr
     * curr。
     * 完成上述操作之后，即得到分隔链表后的一个部分。重复上述操作，直到分隔出
     * k
     * k 个部分，或者链表遍历结束，即
     * curr
     * curr 指向
     * null
     * null。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts/solution/fen-ge-lian-biao-by-leetcode-solution-wevt/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @param k
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 11.93%
     * 的用户
     */
    public ListNode[] splitListToParts1(ListNode head, int k) {
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }
        int quotient = n / k, remainder = n % k;

        ListNode[] parts = new ListNode[k];
        ListNode curr = head;
        for (int i = 0; i < k && curr != null; i++) {
            parts[i] = curr;
            int partSize = quotient + (i < remainder ? 1 : 0);
            for (int j = 1; j < partSize; j++) {
                curr = curr.next;
            }
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }
        return parts;
    }
}
