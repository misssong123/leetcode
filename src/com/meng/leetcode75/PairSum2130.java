package com.meng.leetcode75;

import java.util.ArrayList;
import java.util.List;

/**
 * 2130. 链表最大孪生和
 */
public class PairSum2130 {
    /**
     * 时间
     * 详情
     * 11ms
     * 击败 30.22%使用 Java 的用户
     * 内存
     * 详情
     * 58.55MB
     * 击败 84.52%使用 Java 的用户
     * @param head
     * @return
     */
    public int pairSum(ListNode head) {
        List<Integer> cache = new ArrayList<>();
        while (head != null){
            cache.add(head.val);
            head = head.next;
        }
        int len = cache.size();
        int res = 0;
        for(int i = 0 ;i < len/2 ; i++){
            res = Math.max(cache.get(i)+cache.get(len-i-1),res);
        }
        return res;
    }
}
