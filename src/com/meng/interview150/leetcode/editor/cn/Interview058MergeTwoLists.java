package com.meng.interview150.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

class Interview058MergeTwoLists {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了51.92% 的Java用户
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode cur = head;
        while (list1 != null && list2 != null){
            if (list1.val <= list2.val){
                cur.next = new ListNode(list1.val);
                list1 = list1.next;
            }else {
                cur.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            cur = cur.next;
        }
        while (list1 != null){
            cur.next = new ListNode(list1.val);
            cur = cur.next;
            list1 = list1.next;
        }
        while (list2 != null){
            cur.next = new ListNode(list2.val);
            cur = cur.next;
            list2 = list2.next;
        }
        return head.next;
    }
}
