package com.meng.interview150.leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

class Interview110MergeKLists {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了27.61% 的Java用户
     * 	内存消耗:43.4 MB,击败了52.19% 的Java用户
     * @param lists
     * @return
     */
    public ListNode mergeKListsMy(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        int len = lists.length;
        PriorityQueue<Node> queue = new PriorityQueue<>(len, Comparator.comparingInt(o -> o.node.val));
        for(int i = 0 ; i < len; i++){
            if (lists[i]!= null){
                queue.add(new Node(i, lists[i]));
                lists[i] = lists[i].next;
            }
        }
        ListNode head = new ListNode();
        ListNode cur = head;
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            cur.next = poll.node;
            cur = cur.next;
            if (lists[poll.index] != null){
                queue.add(new Node(poll.index, lists[poll.index]));
                lists[poll.index] = lists[poll.index].next;
            }
        }
        return head.next;
    }
    class Node{
        public int index;
        public ListNode node;
        public Node(int index, ListNode node){
            this.node = node;
            this.index = index;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了69.57% 的Java用户
     * 	内存消耗:43.5 MB,击败了18.34% 的Java用户
     */
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

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    public ListNode mergeKLists1(ListNode[] lists) {
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

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了77.02% 的Java用户
     * 	内存消耗:43.2 MB,击败了87.65% 的Java用户
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
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
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
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
}
