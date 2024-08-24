package com.meng.interview150.leetcode.editor.cn;

class Interview057AddTwoNumbers {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.3 MB,击败了73.56% 的Java用户
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode temp = head;
        int num = 0;
        while (l1!=null||l2!=null){
            int first = l1==null?0:l1.val;
            int second = l2==null?0:l2.val;
            int sum = first+second+num;
            temp.next = new ListNode(sum%10);
            temp = temp.next;
            num = sum/10;
            if(l1!= null){
                l1 = l1.next;
            }
            if(l2!= null){
                l2 = l2.next;
            }
        }
        if (num != 0){
            temp.next = new ListNode(num);
        }
        return head.next;
    }
}
