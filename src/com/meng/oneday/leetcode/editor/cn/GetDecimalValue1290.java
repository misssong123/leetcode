package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.ListNode;

class GetDecimalValue1290 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了14.46% 的Java用户
     * @param head
     * @return
     */
    public int getDecimalValue1290(ListNode head) {
        int res = 0;
        while(head != null){
            res = res * 2 + head.val;
            head = head.next;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.2 MB,击败了97.79% 的Java用户
     * @param head
     * @return
     */
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        while (head != null) {
            ans = ans * 2 + head.val;
            head = head.next;
        }
        return ans;
    }

}
