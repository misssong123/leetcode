package com.meng.interview150.leetcode.editor.cn;

class Interview064RotateRight {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了46.20% 的Java用户
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightMy(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        //计算链表长度
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        k = k % len;
        if(k == 0) {
            return head;
        }
        ListNode slow = head ,fast = head;
        //找到倒数第k个节点
        while (k-- > 0){
            fast = fast.next;
        }
        while(fast.next != null) {
            slow =slow.next;
            fast = fast.next;
        }
        //构建新的head
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了50.23% 的Java用户
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }
}
