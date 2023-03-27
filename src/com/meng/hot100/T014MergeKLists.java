package com.meng.hot100;

import com.meng.hot100.utils.ListNode;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表(困难)
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */
public class T014MergeKLists {
    /**
     * 执行用时：
     * 101 ms
     * , 在所有 Java 提交中击败了
     * 17.04%
     * 的用户
     * 内存消耗：
     * 43.1 MB
     * , 在所有 Java 提交中击败了
     * 82.53%
     * 的用户
     * 通过测试用例：
     * 133 / 133
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        if (lists.length==1){
            return lists[0];
        }
        ListNode ans = null;
        for(int i = 1 ; i < lists.length ; i++){
            if (i==1){
                ans = mergeTwoLists(lists[0],lists[1]);
            }else {
                ans = mergeTwoLists(ans,lists[i]);
            }
        }
        return ans;
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null ){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        ListNode head = new ListNode(),temp = head;
        while (list1 != null && list2 != null){
            int num1 = list1 == null ? 200 : list1.val;
            int num2 = list2 == null ? 200 : list2.val;
            if (num1 > num2){
                temp.next = list2;
                list2 = list2.next;
            }else {
                temp.next = list1;
                list1 = list1.next;
            }
            temp = temp.next;
        }
        temp.next = list1 == null ? list2 : list1;
        return head.next;
    }

    /**
     *方法一：顺序合并
     * 思路
     *
     * 我们可以想到一种最朴素的方法：用一个变量 \textit{ans}ans 来维护以及合并的链表，第 ii 次循环把第 ii 个链表和 \textit{ans}ans 合并，答案保存到 \textit{ans}ans 中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param lists
     * @return
     * 执行用时：
     * 99 ms
     * , 在所有 Java 提交中击败了
     * 21.90%
     * 的用户
     * 内存消耗：
     * 43.9 MB
     * , 在所有 Java 提交中击败了
     * 5.05%
     * 的用户
     * 通过测试用例：
     * 133 / 133
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; ++i) {
            ans = mergeTwoLists1(ans, lists[i]);
        }
        return ans;
    }

    public ListNode mergeTwoLists1(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    /**
     * 方法二：分治合并
     * 思路
     *
     * 考虑优化方法一，用分治的方法进行合并。
     *
     * 将 kk 个链表配对并将同一对中的链表合并；
     * 第一轮合并以后， k 个链表被合并成了 \{k}{2}
     * 2
     * k
     * ​
     *   个链表，平均长度为 \frac{2n}{k}
     * k
     * 2n
     * ​
     *  ，然后是 \frac{k}{4}
     * 4
     * k
     * ​
     *   个链表， \frac{k}{8}
     * 8
     * k
     * ​
     *   个链表等等；
     * 重复这一过程，直到我们得到了最终的有序链表。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param lists
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 43.7 MB
     * , 在所有 Java 提交中击败了
     * 12.63%
     * 的用户
     * 通过测试用例：
     * 133 / 133
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists2(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists2(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    /**
     *方法三：使用优先队列合并
     * 思路
     *
     * 这个方法和前两种方法的思路有所不同，我们需要维护当前每个链表没有被合并的元素的最前面一个，kk 个链表就最多有 kk 个满足这样条件的元素，每次在这些元素里面选取 \textit{val}val 属性最小的元素合并到答案中。在选取最小元素的时候，我们可以用优先队列来优化这个过程。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param lists
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 70.67%
     * 的用户
     * 内存消耗：
     * 43.1 MB
     * , 在所有 Java 提交中击败了
     * 87.23%
     * 的用户
     * 通过测试用例：
     * 133 / 133
     */
    PriorityQueue<Status> queue = new PriorityQueue<Status>();
    public ListNode mergeKLists3(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }
}
